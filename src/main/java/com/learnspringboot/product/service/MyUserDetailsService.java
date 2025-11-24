package com.learnspringboot.product.service;

import com.learnspringboot.product.entity.User;
import com.learnspringboot.product.repository.UserRepository;
import com.learnspringboot.product.security.UserPrincipal;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.stereotype.Service;

import java.util.Optional;

@Service
public class MyUserDetailsService implements UserDetailsService {

    private final UserRepository userRepository;
    @Autowired
    public MyUserDetailsService(UserRepository userRepository){
        this.userRepository=userRepository;
    }
    public User createUser(User user){
        user.setPassword(new BCryptPasswordEncoder(12).encode(user.getPassword()));
        return userRepository.save(user);
    }

    @Override
    public UserDetails loadUserByUsername(String username) throws UsernameNotFoundException {
        Optional<User> user =userRepository.findByUsername(username);
        if(user.isEmpty()) throw new UsernameNotFoundException("User not found");
        return new UserPrincipal(user.get());
    }
}
