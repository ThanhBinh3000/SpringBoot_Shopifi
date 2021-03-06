package com.tecapro.demobinhan.service.warehouseBillDetail;


import com.tecapro.demobinhan.model.WareHouseBill;
import com.tecapro.demobinhan.model.WarehouseBillDetail;
import com.tecapro.demobinhan.repository.IWarehouseBillDetailRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.Optional;

@Service
public class WarehouseBillDetailService implements IWarehouseBillDetailService {
    @Autowired
    private IWarehouseBillDetailRepository warehouseBillDetailRepository;

    @Override
    public Iterable<WarehouseBillDetail> findAll() {
        return warehouseBillDetailRepository.findAll();
    }

    @Override
    public Optional<WarehouseBillDetail> findById(Long id) {
        return warehouseBillDetailRepository.findById(id);
    }

    @Override
    public WarehouseBillDetail save(WarehouseBillDetail warehouseBillDetail) {
        return warehouseBillDetailRepository.save(warehouseBillDetail);
    }

    @Override
    public void remove(Long id) {
        warehouseBillDetailRepository.deleteById(id);
    }

    @Override
    public Iterable<WarehouseBillDetail> findAllByWareHouseBill(WareHouseBill warehouseBill) {
        return warehouseBillDetailRepository.findAllByWareHouseBill(warehouseBill);
    }

    @Override
    public Integer sumAllProduct(Long productId) {
        return warehouseBillDetailRepository.sumAllProduct(productId);
    }
}
