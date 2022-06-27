package com.tecapro.demobinhan.service.product;


import com.tecapro.demobinhan.model.Category;
import com.tecapro.demobinhan.model.Product;
import com.tecapro.demobinhan.model.query.IProductImage;
import com.tecapro.demobinhan.model.query.IProductWarehouse;
import com.tecapro.demobinhan.service.IGeneralService;

public interface IProductService extends IGeneralService<Product> {
    Iterable<Product> findAllByCategory(Category category);

    Iterable<Product> findAllByCategory(Category category, int page, int size);

    Iterable<Product> findAllBySaleOffGreaterThanZero();

    Iterable<Product> findAllByPriceCondition(double minValue, double maxValue);

    Iterable<IProductImage> findAllProductOrderByDate();

    Iterable<Product> findAllByNameContaining(String name);

    Iterable<Product> findAll(int page, int size);

    Iterable<IProductWarehouse> findAllProductInventoryAmount();

    Iterable<IProductImage> getAllProductBestSell();

    Iterable<IProductImage> getAllProductMostLiked();
}
