package com.tecapro.demobinhan.controller;



import com.tecapro.demobinhan.model.WareHouseBill;
import com.tecapro.demobinhan.model.WarehouseBillDetail;
import com.tecapro.demobinhan.service.warehouseBillDetail.IWarehouseBillDetailService;
import com.tecapro.demobinhan.service.warehousingbill.IWarehousingBillService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.Date;
import java.util.Optional;

@RestController
@CrossOrigin("*")
@RequestMapping("/warehousebills")
public class WarehouseBillController {

    @Autowired
    private IWarehousingBillService warehousingBillService;

    @Autowired
    private IWarehouseBillDetailService warehouseBillDetailService;

    @PostMapping
    public ResponseEntity<WareHouseBill> createWarehousingBill(@RequestBody WareHouseBill wareHouseBill) {
        long milis = System.currentTimeMillis();
        Date date = new Date(milis);
        wareHouseBill.setCreateDate(date);
        return new ResponseEntity<>(warehousingBillService.save(wareHouseBill), HttpStatus.OK);
    }

    @GetMapping
    public ResponseEntity<Iterable<WareHouseBill>> getAllWarehousingBill() {
        return new ResponseEntity<>(warehousingBillService.findAll(), HttpStatus.OK);
    }

    @PutMapping("/{id}")
    public ResponseEntity<WareHouseBill> editWarehousingBill(@PathVariable Long id, @RequestBody WareHouseBill wareHouseBill) {
        Optional<WareHouseBill> warehousingBillOptional = warehousingBillService.findById(id);
        if (!warehousingBillOptional.isPresent()) {
            return new ResponseEntity<>(HttpStatus.NOT_FOUND);
        }
        wareHouseBill.setId(id);
        return new ResponseEntity<>(warehousingBillService.save(wareHouseBill), HttpStatus.OK);
    }

    @DeleteMapping("/{id}")
    public ResponseEntity<WareHouseBill> deleteWarehousingBill(@PathVariable Long id) {
        Optional<WareHouseBill> warehousingBillOptional = warehousingBillService.findById(id);
        if (!warehousingBillOptional.isPresent()) {
            return new ResponseEntity<>(HttpStatus.NOT_FOUND);
        }
        warehousingBillService.remove(id);
        return new ResponseEntity<>(HttpStatus.OK);
    }

    @GetMapping("/{id}")
    public ResponseEntity<WareHouseBill> getWarehousingBill(@PathVariable Long id) {
        Optional<WareHouseBill> warehousingBillOptional = warehousingBillService.findById(id);
        if (!warehousingBillOptional.isPresent()) {
            return new ResponseEntity<>(HttpStatus.NOT_FOUND);
        }
        return new ResponseEntity<>(warehousingBillOptional.get(), HttpStatus.OK);
    }

    @GetMapping("/{id}/warehouse-bill-details")
    public ResponseEntity<Iterable<WarehouseBillDetail>> getAllWarehouseBillDetail(@PathVariable Long id) {
        Optional<WareHouseBill> warehousingBillOptional = warehousingBillService.findById(id);
        if (!warehousingBillOptional.isPresent()) {
            return new ResponseEntity<>(HttpStatus.NOT_FOUND);
        }
        return new ResponseEntity<>(warehouseBillDetailService.findAllByWareHouseBill(warehousingBillOptional.get()), HttpStatus.OK);
    }

    @GetMapping("/total-price")
    public ResponseEntity<Long> sumTotalPrice(@RequestParam(name = "month") Integer month, @RequestParam(name = "year") Integer year) {
        return new ResponseEntity<>(warehousingBillService.sumTotalPriceHaveBought(month, year), HttpStatus.OK);
    }
}
