package com.tecapro.demobinhan.service.billDetail;


import com.tecapro.demobinhan.model.Bill;
import com.tecapro.demobinhan.model.BillDetail;
import com.tecapro.demobinhan.service.IGeneralService;

public interface IBillDetailService extends IGeneralService<BillDetail> {
    Iterable<BillDetail> findAllByBill(Bill bill);
}
