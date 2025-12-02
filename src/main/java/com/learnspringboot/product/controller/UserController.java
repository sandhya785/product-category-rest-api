package com.learnspringboot.product.controller;

import com.learnspringboot.product.DTO.UserDTO;
import com.learnspringboot.product.entity.User;
import com.learnspringboot.product.repository.UserRepository;
import com.learnspringboot.product.security.JwtUtil;
import com.learnspringboot.product.service.MyUserDetailsService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.GrantedAuthority;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.web.bind.annotation.*;

import java.util.List;
@SuppressWarnings("unused")
@RestController
@RequestMapping("/user")
public class UserController {
    @Autowired
    private JwtUtil jwtUtil;
    @Autowired
    private AuthenticationManager authenticationManager;
    @Autowired
    private UserRepository userRepository;
    @Autowired
    private MyUserDetailsService userDetailsService;
    @PostMapping("/register")
    @SuppressWarnings("unused")
    public User register(@RequestBody User user){

        return userDetailsService.createUser(user);

    }
    @SuppressWarnings("unused")
    @PostMapping("/login")
    public String login(@RequestBody UserDTO user){
        Authentication authentication =authenticationManager.authenticate(
                new UsernamePasswordAuthenticationToken(user.getUsername(),user.getPassword()));
        SecurityContextHolder.getContext().setAuthentication(authentication);

        List<String> roles=authentication.getAuthorities().stream()
                .map(GrantedAuthority::getAuthority)
                .toList();
        UserDetails userDetails=userDetailsService.loadUserByUsername(user.getUsername());
        return jwtUtil.generateToken(userDetails.getUsername(),roles);

    }
    @GetMapping
    public List<User> getAllUsers() {
        return userRepository.findAll();
    }


}
