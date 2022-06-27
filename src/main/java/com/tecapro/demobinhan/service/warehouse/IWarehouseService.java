package com.tecapro.demobinhan.service.warehouse;


import com.tecapro.demobinhan.model.Warehouse;
import com.tecapro.demobinhan.model.query.IProductWarehouse;
import com.tecapro.demobinhan.service.IGeneralService;

public interface IWarehouseService extends IGeneralService<Warehouse> {
    Iterable<IProductWarehouse> getProductInventory(Long warehouseId);
}
