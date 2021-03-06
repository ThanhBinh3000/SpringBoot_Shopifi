package com.tecapro.demobinhan.service.billDetail;

import com.tecapro.demobinhan.model.Bill;
import com.tecapro.demobinhan.model.BillDetail;
import com.tecapro.demobinhan.repository.IBillDetailRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.Optional;


@Service
public class BillDetailService implements IBillDetailService {
    @Autowired
    private IBillDetailRepository billDetailRepository;

    @Override
    public Iterable<BillDetail> findAll() {
        return billDetailRepository.findAll();
    }

    @Override
    public Optional<BillDetail> findById(Long id) {
        return billDetailRepository.findById(id);
    }

    @Override
    public BillDetail save(BillDetail billDetail) {
        return billDetailRepository.save(billDetail);
    }

    @Override
    public void remove(Long id) {
        billDetailRepository.deleteById(id);
    }

    @Override
    public Iterable<BillDetail> findAllByBill(Bill bill) {
        return billDetailRepository.findAllByBill(bill);
    }
}
