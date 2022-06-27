package com.tecapro.demobinhan.service.order;

import com.tecapro.demobinhan.model.Orders;
import com.tecapro.demobinhan.model.Product;
import com.tecapro.demobinhan.model.auth.User;
import com.tecapro.demobinhan.model.query.IProductImage;
import com.tecapro.demobinhan.repository.IOrdersRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.Optional;


@Service
public class OrdersService implements IOrdersService {
    @Autowired
    private IOrdersRepository ordersRepository;

    @Override
    public Iterable<Orders> findAll() {
        return ordersRepository.findAll();
    }

    @Override
    public Optional<Orders> findById(Long id) {
        return ordersRepository.findById(id);
    }

    @Override
    public Orders save(Orders orders) {
        return ordersRepository.save(orders);
    }

    @Override
    public void remove(Long id) {
        ordersRepository.deleteById(id);
    }

    @Override
    public Iterable<Orders> findAllByUserAndStatus(User user, Integer status) {
        return ordersRepository.findAllByUserAndStatus(user, status);
    }

    @Override
    public Iterable<Orders> findAllByStatus(Integer status) {
        return ordersRepository.findAllByStatus(status);
    }

    @Override
    public Iterable<Product> findAllProductUserBought(User user) {
        return ordersRepository.findAllProductUserBought(user);
    }

    @Override
    public Long sumTotalPriceInput(Integer month, Integer year) {
        return ordersRepository.sumTotalPriceInput(month, year);
    }

    @Override
    public Iterable<IProductImage> getAllProductByOrder(Long orderId) {
        return ordersRepository.getAllProductByOrder(orderId);
    }

    @Override
    public Iterable<Orders> findAllByUserAndStatusIsNot(User user, Integer status) {
        return ordersRepository.findAllByUserAndStatusIsNot(user, status);
    }
}
