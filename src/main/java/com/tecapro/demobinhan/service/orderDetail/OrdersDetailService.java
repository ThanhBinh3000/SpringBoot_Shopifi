package com.tecapro.demobinhan.service.orderDetail;

import com.tecapro.demobinhan.model.Orders;
import com.tecapro.demobinhan.model.OrdersDetail;
import com.tecapro.demobinhan.repository.IOrderDetailRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.Optional;

@Service
public class OrdersDetailService implements IOrdersDetailService {
    @Autowired
    private IOrderDetailRepository orderDetailRepository;

    @Override
    public Iterable<OrdersDetail> findAll() {
        return orderDetailRepository.findAll();
    }

    @Override
    public Optional<OrdersDetail> findById(Long id) {
        return orderDetailRepository.findById(id);
    }

    @Override
    public OrdersDetail save(OrdersDetail ordersDetail) {
        return orderDetailRepository.save(ordersDetail);
    }

    @Override
    public void remove(Long id) {
        orderDetailRepository.deleteById(id);
    }

    @Override
    public Integer sumProductAmount(Long id) {
        return orderDetailRepository.sumProductAmount(id);
    }

    @Override
    public Iterable<OrdersDetail> findAllByOrders(Orders orders) {
        return orderDetailRepository.findAllByOrders(orders);
    }
}
