package com.tecapro.demobinhan.repository;

import com.tecapro.demobinhan.model.Orders;
import com.tecapro.demobinhan.model.Product;
import com.tecapro.demobinhan.model.auth.User;
import com.tecapro.demobinhan.model.query.IProductImage;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;

@Repository
public interface IOrdersRepository extends JpaRepository<Orders, Long> {
    Iterable<Orders> findAllByUserAndStatus(User user, Integer status);

    @Query("select o from Orders as o where o.user = ?1 and  o.status <> ?2")
    Iterable<Orders> findAllByUserAndStatusIsNot(User user, Integer status);

    Iterable<Orders> findAllByStatus(Integer status);

    @Query("SELECT DISTINCT od.product FROM Orders o left join OrdersDetail od on o.id = od.orders.id" +
            " WHERE o.user = ?1 and o.status = 2")
    Iterable<Product> findAllProductUserBought(User user);

    @Query(value = "select sum(amount * price) " +
            "from orders " +
            "left join orders_detail od on orders.id = od.orders_id " +
            "left join product p on od.product_id = p.id " +
            "where month(create_date) = ?1 and year(create_date) = ?2", nativeQuery = true)
    Long sumTotalPriceInput(Integer month, Integer year);

    @Query(value = "select p.id, p.name, i.url, p.price " +
            "from orders " +
            "         left join orders_detail od on orders.id = od.orders_id " +
            "         left join product p on od.product_id = p.id " +
            "         left join image i on p.id = i.product_id " +
            "where orders_id = ?1", nativeQuery = true)
    Iterable<IProductImage> getAllProductByOrder(Long orderId);
}
