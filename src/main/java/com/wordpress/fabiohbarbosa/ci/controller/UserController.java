package com.wordpress.fabiohbarbosa.ci.controller;

import com.wordpress.fabiohbarbosa.ci.entity.User;
import com.wordpress.fabiohbarbosa.ci.service.UserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/user")
public class UserController {

    @Autowired
    private UserService userService;

    @RequestMapping(method = RequestMethod.GET)
    public @ResponseBody List<User> findAll() {
        return userService.findAll();
    }

    @RequestMapping(value = "/{id}", method = RequestMethod.GET)
    public @ResponseBody User findOne(@PathVariable Long id) {
        return userService.findOne(id);
    }

}
