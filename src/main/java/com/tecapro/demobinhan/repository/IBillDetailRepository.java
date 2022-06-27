package com.tecapro.demobinhan.repository;

import com.tecapro.demobinhan.model.Bill;
import com.tecapro.demobinhan.model.BillDetail;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface IBillDetailRepository extends JpaRepository<BillDetail, Long> {
    Iterable<BillDetail> findAllByBill(Bill bill);
}
