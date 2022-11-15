package com.bridgelabz.bookstore.service.order;

import java.time.LocalDateTime;
import java.util.ArrayList;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.jaxb.SpringDataJaxb.OrderDto;
import org.springframework.stereotype.Service;

import com.bridgelabz.bookstore.controller.order.model.BookOrderRequest;
import com.bridgelabz.bookstore.controller.order.model.CreateOrderRequest;
import com.bridgelabz.bookstore.repository.order.IOrderDetailRepository;
import com.bridgelabz.bookstore.repository.order.IOrderSummaryRepository;
import com.bridgelabz.bookstore.repository.order.model.OrderDetail;
import com.bridgelabz.bookstore.repository.order.model.OrderSummary;
import com.bridgelabz.bookstore.service.book.IBookService;
import com.bridgelabz.bookstore.service.order.model.BookOrderDTO;
import com.bridgelabz.bookstore.service.order.model.CreateOrderDTO;
import com.bridgelabz.bookstore.service.order.model.OrderDTO;

@Service
public class OrderService implements IOrderService {
    @Autowired
    IBookService bookService;

    @Autowired
    IOrderSummaryRepository iOrderSummaryRepository;

    @Autowired
    IOrderDetailRepository iOrderDetailRepository;
    @Autowired
    DTOMapper mapper;

    @Override
    public void addOrder(Long userId, CreateOrderRequest createOrderRequest) {
        CreateOrderDTO createOrderDTO = BuildDto(userId, createOrderRequest);
        OrderSummary orderSummary = mapper.toRepository(createOrderDTO);
        iOrderSummaryRepository.save(orderSummary);       

        for (OrderDetail orderDetail : orderSummary.getOrderDetail()) {
            iOrderDetailRepository.save(orderDetail);
        }
    }

    private CreateOrderDTO BuildDto(Long userId, CreateOrderRequest createOrderRequest){
        CreateOrderDTO orderDTO = new CreateOrderDTO();
        List<BookOrderDTO> orderBooks = new ArrayList<BookOrderDTO>();
        
        float totalOrderPrice = 0;
        for (BookOrderRequest bookOrderRequest : createOrderRequest.getBookOrders()) {
            BookOrderDTO bookDto = new BookOrderDTO();
            bookDto.setBookId(bookOrderRequest.getBookId());
            bookDto.setQuantity(bookOrderRequest.getQuantity());            
            float bookPrice = bookService.getBook(bookOrderRequest.getBookId()).getPrice();
            float netBookPrice = bookPrice * bookOrderRequest.getQuantity();
            bookDto.setPrice(netBookPrice);
            orderBooks.add(bookDto);
            totalOrderPrice += netBookPrice;
        }
        orderDTO.setBooksInOrder(orderBooks);
        orderDTO.setTotalPrice(totalOrderPrice);
        orderDTO.setAddress(createOrderRequest.getAddress());
        orderDTO.setOrderDate(LocalDateTime.now());
        orderDTO.setUserId(userId);
        return orderDTO; 
    }

    @Override
    public OrderDTO getOrderById(Long orderId) {
        OrderSummary  orderSummary = iOrderSummaryRepository.findById(orderId).get();
        return ToOrderDTO(orderSummary);
    }

    @Override
    public List<OrderDTO> getAllOrders(Long userId) {
        List<OrderSummary> orderSummaries = iOrderSummaryRepository.findOrdersByUserId(userId);
        List<OrderDTO> orderDtos = new ArrayList<OrderDTO>();
        for (OrderSummary orderSummary : orderSummaries) {
            orderDtos.add(ToOrderDTO(orderSummary));
        }
        return orderDtos;
    }
    
    private OrderDTO ToOrderDTO(OrderSummary orderSummary){
        OrderDTO dto = new OrderDTO();
        dto.setUserId(orderSummary.getUserData().getUser_id());
        dto.setAddress(orderSummary.getAddress());
        List<BookOrderDTO> bookOrderDTOlist = new ArrayList<>();
        for (OrderDetail detail : orderSummary.getOrderDetail()) {
            BookOrderDTO bookOrderDTO = new BookOrderDTO();
            bookOrderDTO.setBookId(detail.getBookData().getBookId());
            bookOrderDTO.setPrice(detail.getPrice());
            bookOrderDTO.setQuantity(detail.getQuantity());
            bookOrderDTOlist.add(bookOrderDTO);
        }
        dto.setBooksInOrder(bookOrderDTOlist);
        dto.setOrderDate(orderSummary.getOrderDate());
        dto.setTotalPrice(orderSummary.getTotalPrice());
        dto.setCancel(false);
        return dto;
    }

    @Override
    public void deleteOrder(Long orderId) {
       iOrderSummaryRepository.deleteById(orderId);
    }
}
