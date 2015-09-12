package com.wordpress.fabiohbarbosa.ci.service;

import com.wordpress.fabiohbarbosa.ci.entity.User;
import com.wordpress.fabiohbarbosa.ci.repository.UserRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class UserService {

    @Autowired
    private UserRepository userRepository;

    public User findOne(Long id) {
        return userRepository.findOne(id);
    }

    public List<User> findAll() {
        return userRepository.findAll();
    }

}
