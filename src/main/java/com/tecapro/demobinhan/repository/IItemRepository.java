package com.tecapro.demobinhan.repository;

import com.tecapro.demobinhan.model.Item;
import com.tecapro.demobinhan.model.ShoppingCart;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface IItemRepository extends JpaRepository<Item, Long> {
    Iterable<Item> findAllByShoppingCart(ShoppingCart shoppingCart);
}
