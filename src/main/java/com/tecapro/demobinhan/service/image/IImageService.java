package com.tecapro.demobinhan.service.image;


import com.tecapro.demobinhan.model.Image;
import com.tecapro.demobinhan.model.Product;
import com.tecapro.demobinhan.service.IGeneralService;

public interface IImageService extends IGeneralService<Image> {
    Iterable<Image> findAllByProduct(Product product);
}
