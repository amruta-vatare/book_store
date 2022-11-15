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
    
}
