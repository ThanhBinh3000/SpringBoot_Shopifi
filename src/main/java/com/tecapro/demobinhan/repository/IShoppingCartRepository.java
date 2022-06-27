package com.tecapro.demobinhan.repository;

import com.tecapro.demobinhan.model.ShoppingCart;
import com.tecapro.demobinhan.model.auth.User;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.Optional;


@Repository
public interface IShoppingCartRepository extends JpaRepository<ShoppingCart, Long> {
    Optional<ShoppingCart> findByUser(User user);
}
