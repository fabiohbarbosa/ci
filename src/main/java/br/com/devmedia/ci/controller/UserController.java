package br.com.devmedia.ci.controller;

import br.com.devmedia.ci.entity.User;
import br.com.devmedia.ci.service.UserService;
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
