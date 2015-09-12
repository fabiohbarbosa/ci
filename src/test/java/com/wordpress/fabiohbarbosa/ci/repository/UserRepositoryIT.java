package com.wordpress.fabiohbarbosa.ci.repository;

import com.wordpress.fabiohbarbosa.ci.config.Application;
import com.wordpress.fabiohbarbosa.ci.entity.User;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.SpringApplicationConfiguration;
import org.springframework.test.context.junit4.SpringJUnit4ClassRunner;

import java.util.List;

import static org.junit.Assert.assertEquals;
import static org.junit.Assert.assertNotNull;

@RunWith(SpringJUnit4ClassRunner.class)
@SpringApplicationConfiguration(classes = Application.class)
public class UserRepositoryIT {

    @Autowired
    private UserRepository userRepository;

    @Test
    public void deveRetornarUsuarioId1() {
        User user = userRepository.findOne(1L);
        assertNotNull(user);
    }

    @Test
    public void deveRetornarCincoUsuarios() {
        List<User> users = userRepository.findAll();
        assertEquals(5, users.size());
    }

}
