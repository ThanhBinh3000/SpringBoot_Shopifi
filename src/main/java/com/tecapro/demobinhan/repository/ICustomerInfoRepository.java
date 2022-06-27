package com.tecapro.demobinhan.repository;

import com.tecapro.demobinhan.model.CustomerInfo;
import com.tecapro.demobinhan.model.auth.User;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface ICustomerInfoRepository extends JpaRepository<CustomerInfo, Long> {
    Iterable<CustomerInfo> findAllByUser(User user);
}
