package com.bridgelabz.bookstore.service.order;

import java.util.ArrayList;
import java.util.List;

import org.springframework.stereotype.Component;

import com.bridgelabz.bookstore.repository.book.model.BookData;
import com.bridgelabz.bookstore.repository.order.model.OrderDetail;
import com.bridgelabz.bookstore.repository.order.model.OrderSummary;
import com.bridgelabz.bookstore.repository.user.model.UserData;
import com.bridgelabz.bookstore.service.order.model.BookOrderDTO;
import com.bridgelabz.bookstore.service.order.model.CreateOrderDTO;

@Component
public class DTOMapper {

    public OrderSummary toRepository(CreateOrderDTO createOrderDTO) {
        OrderSummary orderSummary = new OrderSummary();
        orderSummary.setAddress(createOrderDTO.getAddress());
        orderSummary.setOrderDate(createOrderDTO.getOrderDate());
        orderSummary.setTotalPrice(createOrderDTO.getTotalPrice());

        UserData userData = new UserData();
        userData.setUser_id(createOrderDTO.getUserId());
        orderSummary.setUserData(userData);

        List<OrderDetail> orderDetails = new ArrayList<OrderDetail>();
        for (BookOrderDTO bookOrderDTO : createOrderDTO.getBooksInOrder()) {
            OrderDetail orderDetail = new OrderDetail();
            orderDetail.setOrderSummary(orderSummary);
            orderDetail.setPrice(bookOrderDTO.getPrice());
            orderDetail.setQuantity(bookOrderDTO.getQuantity());

            BookData bookData = new BookData();
            bookData.setBookId(bookOrderDTO.getBookId());
            orderDetail.setBookData(bookData);
            orderDetails.add(orderDetail);
        }
        orderSummary.setOrderDetail(orderDetails);
        return orderSummary;
    }
}
