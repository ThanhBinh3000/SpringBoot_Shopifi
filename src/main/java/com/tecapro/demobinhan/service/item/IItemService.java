package com.tecapro.demobinhan.service.item;


import com.tecapro.demobinhan.model.Item;
import com.tecapro.demobinhan.model.ShoppingCart;
import com.tecapro.demobinhan.service.IGeneralService;

public interface IItemService extends IGeneralService<Item> {
    Iterable<Item> findAllByShoppingCart(ShoppingCart shoppingCart);
}
