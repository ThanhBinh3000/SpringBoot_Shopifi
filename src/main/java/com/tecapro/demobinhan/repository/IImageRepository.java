package com.tecapro.demobinhan.repository;

import com.tecapro.demobinhan.model.Image;
import com.tecapro.demobinhan.model.Product;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface IImageRepository extends JpaRepository<Image, Long> {
    Iterable<Image> findAllByProduct(Product product);
}
