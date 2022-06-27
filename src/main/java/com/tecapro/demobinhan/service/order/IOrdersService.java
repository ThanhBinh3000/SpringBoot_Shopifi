package com.tecapro.demobinhan.service.order;


import com.tecapro.demobinhan.model.Orders;
import com.tecapro.demobinhan.model.Product;
import com.tecapro.demobinhan.model.auth.User;
import com.tecapro.demobinhan.model.query.IProductImage;
import com.tecapro.demobinhan.service.IGeneralService;

public interface IOrdersService extends IGeneralService<Orders> {
    Iterable<Orders> findAllByUserAndStatus(User user, Integer status);

    Iterable<Orders> findAllByStatus(Integer status);

    Iterable<Product> findAllProductUserBought(User user);

    Long sumTotalPriceInput(Integer month, Integer year);

    Iterable<IProductImage> getAllProductByOrder(Long orderId);

    Iterable<Orders> findAllByUserAndStatusIsNot(User user, Integer status);
}
