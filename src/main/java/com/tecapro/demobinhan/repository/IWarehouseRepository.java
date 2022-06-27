package com.tecapro.demobinhan.repository;

import com.tecapro.demobinhan.model.Warehouse;
import com.tecapro.demobinhan.model.query.IProductWarehouse;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;

@Repository
public interface IWarehouseRepository extends JpaRepository<Warehouse, Long> {
    @Query(value = "call getProductInventory(?1)", nativeQuery = true)
    Iterable<IProductWarehouse> getProductInventory(Long warehouseId);
}
