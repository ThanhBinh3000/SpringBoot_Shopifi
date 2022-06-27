package com.tecapro.demobinhan.service.customerInfo;


import com.tecapro.demobinhan.model.CustomerInfo;
import com.tecapro.demobinhan.model.auth.User;
import com.tecapro.demobinhan.service.IGeneralService;

public interface ICustomerInfoService extends IGeneralService<CustomerInfo> {
    Iterable<CustomerInfo> findAllByUser(User user);
}
