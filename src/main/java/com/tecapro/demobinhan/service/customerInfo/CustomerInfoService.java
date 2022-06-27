package com.tecapro.demobinhan.service.customerInfo;

import com.tecapro.demobinhan.model.CustomerInfo;
import com.tecapro.demobinhan.model.auth.User;
import com.tecapro.demobinhan.repository.ICustomerInfoRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.Optional;


@Service
public class CustomerInfoService implements ICustomerInfoService {
    @Autowired
    private ICustomerInfoRepository customerInfoRepository;

    @Override
    public Iterable<CustomerInfo> findAll() {
        return customerInfoRepository.findAll();
    }

    @Override
    public Optional<CustomerInfo> findById(Long id) {
        return customerInfoRepository.findById(id);
    }

    @Override
    public CustomerInfo save(CustomerInfo customerInfo) {
        return customerInfoRepository.save(customerInfo);
    }

    @Override
    public void remove(Long id) {
        customerInfoRepository.deleteById(id);
    }

    @Override
    public Iterable<CustomerInfo> findAllByUser(User user) {
        return customerInfoRepository.findAllByUser(user);
    }
}
