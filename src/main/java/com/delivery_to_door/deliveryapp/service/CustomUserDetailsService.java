package com.delivery_to_door.deliveryapp.service;


import com.delivery_to_door.deliveryapp.repository.UserRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.userdetails.User;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class CustomUserDetailsService implements UserDetailsService {

    private final UserRepository userRepository;
    @Autowired
    public CustomUserDetailsService(UserRepository repo){
        this.userRepository = repo;
    }

    @Override
    public UserDetails loadUserByUsername(String username) throws UsernameNotFoundException {
        com.delivery_to_door.deliveryapp.model.User user = userRepository.findById(username).orElseThrow(()-> new UsernameNotFoundException("Cannot Find User"));
        return new User(user.getId(),user.getPassword(),List.of());
    }

    public UserDetails loadUserById(String id) throws UsernameNotFoundException{
        com.delivery_to_door.deliveryapp.model.User user = userRepository.findById(id).orElseThrow(()-> new UsernameNotFoundException("Cannot Find User"));
        return new User(user.getId(), user.getId(), List.of());
    }
}
