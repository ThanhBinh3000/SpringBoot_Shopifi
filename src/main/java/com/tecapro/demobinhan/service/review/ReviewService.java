package com.tecapro.demobinhan.service.review;

import com.tecapro.demobinhan.model.Product;
import com.tecapro.demobinhan.model.Review;
import com.tecapro.demobinhan.model.auth.User;
import com.tecapro.demobinhan.repository.IReviewRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.Optional;


@Service
public class ReviewService implements IReviewService {
    @Autowired
    private IReviewRepository reviewRepository;

    @Override
    public Iterable<Review> findAll() {
        return reviewRepository.findAll();
    }

    @Override
    public Optional<Review> findById(Long id) {
        return reviewRepository.findById(id);
    }

    @Override
    public Review save(Review review) {
        return reviewRepository.save(review);
    }

    @Override
    public void remove(Long id) {
        reviewRepository.deleteById(id);
    }

    @Override
    public Review findByUserAndProduct(User user, Product product) {
        return reviewRepository.findByUserAndProduct(user, product);
    }

    @Override
    public Iterable<Review> findAllByProduct(Product product) {
        return reviewRepository.findAllByProduct(product);
    }
}
