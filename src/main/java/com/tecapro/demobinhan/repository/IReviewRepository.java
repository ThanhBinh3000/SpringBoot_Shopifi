package com.tecapro.demobinhan.repository;

import com.tecapro.demobinhan.model.Product;
import com.tecapro.demobinhan.model.Review;
import com.tecapro.demobinhan.model.auth.User;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface IReviewRepository extends JpaRepository<Review, Long> {
    Review findByUserAndProduct(User user, Product product);

    Iterable<Review> findAllByProduct(Product product);
}
