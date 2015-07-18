package br.com.devmedia.ci.controller;

import br.com.devmedia.ci.entity.User;
import br.com.devmedia.ci.service.UserService;
import com.fasterxml.jackson.core.type.TypeReference;
import com.fasterxml.jackson.databind.ObjectMapper;
import org.junit.Before;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.Mockito;
import org.mockito.runners.MockitoJUnitRunner;
import org.springframework.http.HttpStatus;
import org.springframework.http.MediaType;
import org.springframework.test.web.servlet.MockMvc;
import org.springframework.test.web.servlet.MvcResult;
import org.springframework.test.web.servlet.setup.MockMvcBuilders;

import java.util.ArrayList;
import java.util.List;

import static org.junit.Assert.assertEquals;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.get;

@RunWith(MockitoJUnitRunner.class)
public class UserControllerTest {

    @InjectMocks
    private UserController userController;

    @Mock
    private UserService userService;

    private static MockMvc mockMvc;
    private ObjectMapper jsonMapper = new ObjectMapper();

    @Before
    public void setUp() {
        mockMvc = MockMvcBuilders.standaloneSetup(userController).build();
    }

    @Test
    public void deveRetornarUsuarioParaId() throws Exception {
        Long id = 1L;
        Mockito.doReturn(new User(id, "Usuário " + id)).when(userService).findOne(id);

        // consome o endpoint
        MvcResult mvcResult = mockMvc.perform(get("/user/{id}", id.toString())
                .header("Content-Type", MediaType.APPLICATION_JSON_VALUE))
                .andReturn();

        // pega os dados de retorno
        String jsonRetornado = mvcResult.getResponse().getContentAsString();
        int statusRetornado = mvcResult.getResponse().getStatus();
        User userRetornado = jsonMapper.readValue(jsonRetornado, User.class);

        assertEquals(id, userRetornado.getId());
        assertEquals(HttpStatus.OK.value(), statusRetornado);

        Mockito.verify(userService, Mockito.times(1)).findOne(id);
    }

    @Test
    public void deveRetornarTodosUsuarios() throws Exception {
        List<User> users = new ArrayList<User>();

        int userSize = 10;
        for (int i = 0; i < userSize; i++) {
            users.add(new User(new Long(i), "Usuário "+i));
        }

        Mockito.doReturn(users).when(userService).findAll();

        // consome o endpoint
        MvcResult mvcResult = mockMvc.perform(get("/user/")
                .header("Content-Type", MediaType.APPLICATION_JSON_VALUE))
                .andReturn();

        // pega os dados de retorno
        String jsonRetornado = mvcResult.getResponse().getContentAsString();
        int statusRetornado = mvcResult.getResponse().getStatus();
        List<User> usersRetornados = jsonMapper.readValue(jsonRetornado, new TypeReference<List<User>>(){});

        assertEquals(userSize, usersRetornados.size());
        assertEquals(HttpStatus.OK.value(), statusRetornado);

        Mockito.verify(userService, Mockito.times(1)).findAll();
    }

}