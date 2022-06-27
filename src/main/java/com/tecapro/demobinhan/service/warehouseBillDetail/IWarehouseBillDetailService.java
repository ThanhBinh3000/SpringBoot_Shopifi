package com.tecapro.demobinhan.service.warehouseBillDetail;


import com.tecapro.demobinhan.model.WareHouseBill;
import com.tecapro.demobinhan.model.WarehouseBillDetail;
import com.tecapro.demobinhan.service.IGeneralService;

public interface IWarehouseBillDetailService extends IGeneralService<WarehouseBillDetail> {
    Iterable<WarehouseBillDetail> findAllByWareHouseBill(WareHouseBill warehouseBill);

    Integer sumAllProduct(Long productId);
}
