package com.tecapro.demobinhan.controller;


import com.tecapro.demobinhan.model.CustomerInfo;
import com.tecapro.demobinhan.model.Notification;
import com.tecapro.demobinhan.model.auth.Role;
import com.tecapro.demobinhan.model.auth.User;
import com.tecapro.demobinhan.service.customerInfo.ICustomerInfoService;
import com.tecapro.demobinhan.service.notification.INotificationService;
import com.tecapro.demobinhan.service.role.IRoleService;
import com.tecapro.demobinhan.service.user.IUserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.HashSet;
import java.util.Optional;
import java.util.Set;

import static com.tecapro.demobinhan.model.auth.RoleName.ROLE_ADMIN;


@RestController
@CrossOrigin("*")
@RequestMapping("/users")
public class UserController {
    @Autowired
    private IUserService userService;

    @Autowired
    private IRoleService roleService;

    @Autowired
    private INotificationService notificationService;

    @Autowired
    private ICustomerInfoService customerInfoService;

    @GetMapping("/{id}/notifications")
    public ResponseEntity<Iterable<Notification>> getAllNotificationByUser(@PathVariable Long id) {
        Optional<User> userOptional = userService.findById(id);
        return userOptional.map(user -> new ResponseEntity<>(notificationService.findAllByStatusIsFalseAndUser(user), HttpStatus.OK)).orElseGet(() -> new ResponseEntity<>(HttpStatus.NOT_FOUND));
    }

    @GetMapping("/{id}/notifications-desc")
    public ResponseEntity<Iterable<Notification>> getAllNotificationByUserDateDesc(@PathVariable Long id) {
        Optional<User> userOptional = userService.findById(id);
        return userOptional.map(user -> new ResponseEntity<>(notificationService.findAllDateDesc(id), HttpStatus.OK)).orElseGet(() -> new ResponseEntity<>(HttpStatus.NOT_FOUND));
    }

    @GetMapping
    public ResponseEntity<?> getAllUser(@RequestParam Optional<String> role) {
        if (role.isPresent()) {
            return new ResponseEntity<>(userService.getAllUserHasRoleUser(), HttpStatus.OK);
        }
        return new ResponseEntity<>(userService.findAll(), HttpStatus.OK);
    }

    @GetMapping("/{id}")
    public ResponseEntity<User> getUser(@PathVariable Long id) {
        Optional<User> userOptional = userService.findById(id);
        return userOptional.map(user -> new ResponseEntity<>(user, HttpStatus.OK))
                .orElseGet(() -> new ResponseEntity<>(HttpStatus.NOT_FOUND));
    }

    @PostMapping
    public ResponseEntity<User> createNewUser(@RequestBody User user) {
        Role role = roleService.findByName(ROLE_ADMIN.toString());
        Set<Role> roles = new HashSet<>();
        roles.add(role);
        user.setRoles(roles);
        return new ResponseEntity<>(userService.save(user), HttpStatus.CREATED);
    }

    @PutMapping("/{id}")
    public ResponseEntity<User> updateUserInfo(@PathVariable Long id, @RequestBody User user) {
        Optional<User> userOptional = userService.findById(id);
        return userOptional.map(user1 -> {
            user.setId(user1.getId());
            user.setRoles(user1.getRoles());
            return new ResponseEntity<>(userService.save(user), HttpStatus.OK);
        }).orElseGet(() -> new ResponseEntity<>(HttpStatus.NOT_FOUND));
    }

    @DeleteMapping("/{id}")
    public ResponseEntity<User> deleteUser(@PathVariable Long id) {
        Optional<User> userOptional = userService.findById(id);
        return userOptional.map(user -> {
            userService.remove(id);
            return new ResponseEntity<>(user, HttpStatus.OK);
        }).orElseGet(() -> new ResponseEntity<>(HttpStatus.NOT_FOUND));
    }

    @GetMapping("/{id}/addresses")
    public ResponseEntity<Iterable<CustomerInfo>> getAllAddressByUser(@PathVariable Long id) {
        Optional<User> userOptional = userService.findById(id);
        return userOptional.map(user -> new ResponseEntity<>(customerInfoService.findAllByUser(user), HttpStatus.OK)).orElseGet(() -> new ResponseEntity<>(HttpStatus.NOT_FOUND));
    }
}
