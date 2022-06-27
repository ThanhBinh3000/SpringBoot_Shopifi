package com.tecapro.demobinhan.service.orderDetail;


import com.tecapro.demobinhan.model.Orders;
import com.tecapro.demobinhan.model.OrdersDetail;
import com.tecapro.demobinhan.service.IGeneralService;

public interface IOrdersDetailService extends IGeneralService<OrdersDetail> {
    Integer sumProductAmount(Long id);

    Iterable<OrdersDetail> findAllByOrders(Orders orders);
}
