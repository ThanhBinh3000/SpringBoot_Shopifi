package com.tecapro.demobinhan.controller;

import com.tecapro.demobinhan.model.Orders;
import com.tecapro.demobinhan.model.OrdersDetail;
import com.tecapro.demobinhan.model.Product;
import com.tecapro.demobinhan.model.auth.User;
import com.tecapro.demobinhan.model.query.IProductImage;
import com.tecapro.demobinhan.service.order.IOrdersService;
import com.tecapro.demobinhan.service.orderDetail.IOrdersDetailService;
import com.tecapro.demobinhan.service.user.IUserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.Date;
import java.util.Optional;

@RestController
@RequestMapping("/orders")
@CrossOrigin("*")
public class OrdersController {
    @Autowired
    private IOrdersService ordersService;

    @Autowired
    private IOrdersDetailService ordersDetailService;

    @Autowired
    private IUserService userService;

    @GetMapping
    public ResponseEntity<Iterable<Orders>> getAllOrders(@RequestParam Integer status) {
        if (status == null) {
            return new ResponseEntity<>(ordersService.findAll(), HttpStatus.OK);
        }
        return new ResponseEntity<>(ordersService.findAllByStatus(status), HttpStatus.OK);
    }

    @GetMapping("/{id}")
    public ResponseEntity<Orders> findById(@PathVariable Long id) {
        Optional<Orders> ordersOptional = ordersService.findById(id);
        return ordersOptional.map(value -> new ResponseEntity<>(value, HttpStatus.OK)).orElseGet(() -> new ResponseEntity<>(HttpStatus.NOT_FOUND));
    }

    @PostMapping
    public ResponseEntity<Orders> createOrders(@RequestBody Orders orders) {
        long milis = System.currentTimeMillis();
        Date date = new Date(milis);
        orders.setCreateDate(date);
        return new ResponseEntity<>(ordersService.save(orders), HttpStatus.OK);
    }

    @PutMapping("/{id}")
    public ResponseEntity<Orders> editOrders(@PathVariable Long id, @RequestBody Orders orders) {
        Optional<Orders> ordersOptional = ordersService.findById(id);
        if (!ordersOptional.isPresent()) {
            return new ResponseEntity<>(HttpStatus.NOT_FOUND);
        }
        orders.setId(id);
        return new ResponseEntity<>(ordersService.save(orders), HttpStatus.OK);
    }

    @DeleteMapping("/{id}")
    public ResponseEntity<Orders> deleteOrders(@PathVariable Long id) {
        Optional<Orders> ordersOptional = ordersService.findById(id);
        if (!ordersOptional.isPresent()) {
            return new ResponseEntity<>(HttpStatus.NOT_FOUND);
        }
        ordersService.remove(id);
        return new ResponseEntity<>(HttpStatus.OK);
    }

    @GetMapping("/users/{id}")
    public ResponseEntity<Iterable<Orders>> getAllOrderByUser(@PathVariable Long id, @RequestParam Integer status) {
        Optional<User> userOptional = userService.findById(id);
        return userOptional.map(user -> new ResponseEntity<>(ordersService.findAllByUserAndStatus(user, status),
                HttpStatus.OK)).orElseGet(() -> new ResponseEntity<>(HttpStatus.NOT_FOUND));
    }

    @GetMapping("/users/{id}/status-not-like")
    public ResponseEntity<Iterable<Orders>> getAllOrderByUserAndStatusNotLike(@PathVariable Long id, @RequestParam Integer status) {
        Optional<User> userOptional = userService.findById(id);
        return userOptional.map(user -> new ResponseEntity<>(ordersService.findAllByUserAndStatusIsNot(user, status),
                HttpStatus.OK)).orElseGet(() -> new ResponseEntity<>(HttpStatus.NOT_FOUND));
    }

    @GetMapping("/{id}/order-details")
    public ResponseEntity<Iterable<OrdersDetail>> findAllOrderDetailByOrder(@PathVariable Long id) {
        Optional<Orders> ordersOptional = ordersService.findById(id);
        return ordersOptional.map(orders -> new ResponseEntity<>(ordersDetailService.findAllByOrders(orders), HttpStatus.OK)).orElseGet(() -> new ResponseEntity<>(HttpStatus.NOT_FOUND));
    }

    @GetMapping("/users/{id}/products")
    public ResponseEntity<Iterable<Product>> getAllProductUserBought(@PathVariable Long id) {
        Optional<User> userOptional = userService.findById(id);
        return userOptional.map(user -> new ResponseEntity<>(ordersService.findAllProductUserBought(user), HttpStatus.OK))
                .orElseGet(() -> new ResponseEntity<>(HttpStatus.NOT_FOUND));
    }

    @GetMapping("/total-price")
    public ResponseEntity<Long> sumTotalPriceByMonthAndYear(@RequestParam(name = "month") Integer month, @RequestParam(name = "year") Integer year) {
        return new ResponseEntity<>(ordersService.sumTotalPriceInput(month, year), HttpStatus.OK);
    }

    @GetMapping("/{id}/products")
    public ResponseEntity<Iterable<IProductImage>> getAllProductByOrder(@PathVariable Long id) {
        Optional<Orders> ordersOptional = ordersService.findById(id);
        if (!ordersOptional.isPresent()) {
            return new ResponseEntity<>(HttpStatus.NOT_FOUND);
        }
        return new ResponseEntity<>(ordersService.getAllProductByOrder(id), HttpStatus.OK);
    }
}
