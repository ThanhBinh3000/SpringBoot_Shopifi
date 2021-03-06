package com.tecapro.demobinhan.repository;

import com.tecapro.demobinhan.model.Category;
import com.tecapro.demobinhan.model.Product;
import com.tecapro.demobinhan.model.query.IProductImage;
import com.tecapro.demobinhan.model.query.IProductWarehouse;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;

@Repository
public interface IProductRepository extends JpaRepository<Product, Long> {
    Page<Product> findAllByCategory(Category category, Pageable pageable);

    Iterable<Product> findAllByCategory(Category category);

    @Query("select p from Product p where p.saleOff>0")
    Iterable<Product> findAllBySaleOffGreaterThanZero();

    @Query("select p from Product  p where p.price >= ?1 and p.price <= ?2")
    Iterable<Product> findAllByPriceCondition(double minValue, double maxValue);

    @Query(value = "select * from product_latest", nativeQuery = true)
    Iterable<IProductImage> findAllProductOrderByDate();

    Iterable<Product> findAllByNameContaining(String name);

    @Query(value = "call productTotalAmount()",nativeQuery = true)
    Iterable<IProductWarehouse> findAllProductInventoryAmount();

    @Query(value = "select product_best_sell_view.id, product_best_sell_view.name, image.url, price " +
            "from product_best_sell_view left join image on product_best_sell_view.id = image.id;",
    nativeQuery = true)
    Iterable<IProductImage> getAllProductBestSell();

    @Query(value = "select id,name,price,url from product_most_like " +
            "where id in (select id from product_most_liked_id)", nativeQuery = true)
    Iterable<IProductImage> getAllProductMostLiked();
}
