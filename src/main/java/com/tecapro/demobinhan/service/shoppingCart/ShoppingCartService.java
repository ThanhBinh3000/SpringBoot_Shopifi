package com.tecapro.demobinhan.service.shoppingCart;

import com.tecapro.demobinhan.model.ShoppingCart;
import com.tecapro.demobinhan.model.auth.User;
import com.tecapro.demobinhan.repository.IShoppingCartRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.Optional;


@Service
public class ShoppingCartService implements IShoppingCartService {
    @Autowired
    private IShoppingCartRepository shoppingCartRepository;

    @Override
    public Iterable<ShoppingCart> findAll() {
        return shoppingCartRepository.findAll();
    }

    @Override
    public Optional<ShoppingCart> findById(Long id) {
        return shoppingCartRepository.findById(id);
    }

    @Override
    public ShoppingCart save(ShoppingCart shoppingCart) {
        return shoppingCartRepository.save(shoppingCart);
    }

    @Override
    public void remove(Long id) {
        shoppingCartRepository.deleteById(id);
    }

    @Override
    public Optional<ShoppingCart> findByUser(User user) {
        return shoppingCartRepository.findByUser(user);
    }
}
