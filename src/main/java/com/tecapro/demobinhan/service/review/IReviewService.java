package com.tecapro.demobinhan.service.review;


import com.tecapro.demobinhan.model.Product;
import com.tecapro.demobinhan.model.Review;
import com.tecapro.demobinhan.model.auth.User;
import com.tecapro.demobinhan.service.IGeneralService;

public interface IReviewService extends IGeneralService<Review> {
    Review findByUserAndProduct(User user, Product product);
    Iterable<Review> findAllByProduct(Product product);
}
