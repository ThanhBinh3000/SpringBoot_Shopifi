package com.tecapro.demobinhan.service.warehousingbill;


import com.tecapro.demobinhan.model.WareHouseBill;
import com.tecapro.demobinhan.service.IGeneralService;

public interface IWarehousingBillService extends IGeneralService<WareHouseBill> {
    Long sumTotalPriceHaveBought(Integer month, Integer year);
}
