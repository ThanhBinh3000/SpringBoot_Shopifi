package com.tecapro.demobinhan.repository;

import com.tecapro.demobinhan.model.WareHouseBill;
import com.tecapro.demobinhan.model.WarehouseBillDetail;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;

@Repository
public interface IWarehouseBillDetailRepository extends JpaRepository<WarehouseBillDetail, Long> {
    Iterable<WarehouseBillDetail> findAllByWareHouseBill(WareHouseBill warehouseBill);

    @Query("SELECT SUM(d.amount) From Product p left join WarehouseBillDetail d on d.product.id = p.id where p.id = ?1 group by p.id")
    Integer sumAllProduct(Long productId);
}
