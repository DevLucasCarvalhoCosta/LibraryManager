package com.librarymanager.service;

import com.librarymanager.model.User;
import com.librarymanager.repository.UserRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class UserService {
    @Autowired
    private UserRepository userRepository;

    public List<User> getAllUsers() {
        return userRepository.findAll();
    }
    
    public List<User> getInadimplentes() {
        return userRepository.findByLoansStatus("atrasado");
    }
    
    public User saveUser(User user) {
        return userRepository.save(user);
    }
}
