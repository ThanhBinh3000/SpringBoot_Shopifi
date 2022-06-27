package com.tecapro.demobinhan.controller;


import com.tecapro.demobinhan.model.ShoppingCart;
import com.tecapro.demobinhan.model.auth.JwtResponse;
import com.tecapro.demobinhan.model.auth.User;
import com.tecapro.demobinhan.service.JwtService;
import com.tecapro.demobinhan.service.shoppingCart.IShoppingCartService;
import com.tecapro.demobinhan.service.user.IUserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.web.bind.annotation.*;


@CrossOrigin("*")
@RestController
public class AuthController {

    @Autowired
    private AuthenticationManager authenticationManager;

    @Autowired
    private JwtService jwtService;

    @Autowired
    private IUserService userService;

    @Autowired
    private IShoppingCartService shoppingCartService;

    @GetMapping("/login")
    public ResponseEntity<Iterable<User>> getAllUser() {
        return new ResponseEntity<>(userService.findAll(), HttpStatus.OK);
    }

    @PostMapping("/login")
    public ResponseEntity<?> login(@RequestBody User user) {
        Authentication authentication = authenticationManager.authenticate(
                new UsernamePasswordAuthenticationToken(user.getEmail(), user.getPassword()));

        SecurityContextHolder.getContext().setAuthentication(authentication);

        String jwt = jwtService.generateTokenLogin(authentication);
        UserDetails userDetails = (UserDetails) authentication.getPrincipal();
        User currentUser = userService.findByEmail(user.getEmail()).get();
        return ResponseEntity.ok(new JwtResponse(jwt, currentUser.getId(), userDetails.getUsername(), currentUser.getFullName(), userDetails.getAuthorities()));
    }

    @PostMapping("/register")
    public ResponseEntity<User> register(@RequestBody User user) {
        Iterable<User> users = userService.findAll();
        for (User currentUser : users) {
            if (currentUser.getEmail().equals(user.getEmail())) {
                return new ResponseEntity<>(HttpStatus.BAD_REQUEST);
            }
        }
        userService.save(user);
        ShoppingCart shoppingCart = new ShoppingCart();
        shoppingCart.setUser(user);
        shoppingCartService.save(shoppingCart);
        return new ResponseEntity<>(user, HttpStatus.OK);
    }
}
