package com.tecapro.demobinhan.service.shoppingCart;


import com.tecapro.demobinhan.model.ShoppingCart;
import com.tecapro.demobinhan.model.auth.User;
import com.tecapro.demobinhan.service.IGeneralService;

import java.util.Optional;

public interface IShoppingCartService extends IGeneralService<ShoppingCart> {
    Optional<ShoppingCart> findByUser(User user);
}
