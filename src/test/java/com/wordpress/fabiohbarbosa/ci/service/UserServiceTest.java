package com.wordpress.fabiohbarbosa.ci.service;

import com.wordpress.fabiohbarbosa.ci.entity.User;
import com.wordpress.fabiohbarbosa.ci.repository.UserRepository;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.Mockito;
import org.mockito.runners.MockitoJUnitRunner;

import java.util.ArrayList;
import java.util.List;

import static org.junit.Assert.assertEquals;

@RunWith(MockitoJUnitRunner.class)
public class UserServiceTest {
    @InjectMocks
    private UserService userService;

    @Mock
    private UserRepository userRepository;

    @Test
    public void deveRetornarUsuarioParaId() {
        Long id = 10L;
        Mockito.doReturn(new User(id, "Usuário "+id)).when(userRepository).findOne(id);

        User userRetornado = userService.findOne(id);

        assertEquals(id, userRetornado.getId());
        Mockito.verify(userRepository, Mockito.times(1)).findOne(id);
    }

    @Test
    public void deveRetornarTodosUsuarios() {
        List<User> users = new ArrayList<User>();

        int userSize = 10;
        for (int i = 0; i < userSize; i++) {
            users.add(new User(new Long(i), "Usuário "+i));
        }

        Mockito.doReturn(users).when(userRepository).findAll();

        List<User> usersRetornados = userService.findAll();
        assertEquals(userSize, usersRetornados.size());

        Mockito.verify(userRepository, Mockito.times(1)).findAll();
    }
}
