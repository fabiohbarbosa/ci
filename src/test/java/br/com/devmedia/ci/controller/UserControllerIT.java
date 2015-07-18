package br.com.devmedia.ci.controller;

import br.com.devmedia.ci.config.Application;
import br.com.devmedia.ci.entity.User;
import org.junit.Before;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.boot.test.IntegrationTest;
import org.springframework.boot.test.SpringApplicationConfiguration;
import org.springframework.boot.test.TestRestTemplate;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.test.context.junit4.SpringJUnit4ClassRunner;
import org.springframework.test.context.web.WebAppConfiguration;
import org.springframework.web.client.RestTemplate;

import java.net.URL;
import java.util.Arrays;
import java.util.List;

import static org.junit.Assert.assertEquals;
import static org.junit.Assert.assertNull;

@RunWith(SpringJUnit4ClassRunner.class)
@SpringApplicationConfiguration(classes = Application.class)
@WebAppConfiguration
@IntegrationTest({"server.port=0"})
public class UserControllerIT {
    private RestTemplate restTemplate;

    @Value("${local.server.port}")
    private int port;

    private URL serverUrl;

    @Before
    public void setUp() throws Exception {
        serverUrl = new URL("http://localhost:" + port + "/user/");
        restTemplate = new TestRestTemplate();
    }

    @Test
    public void getUsuarioPorIdExistente() {
        int id = 1;
        ResponseEntity<User> response = restTemplate.getForEntity(serverUrl.toString() + id, User.class);
        HttpStatus statusRetornado = response.getStatusCode();
        User user = response.getBody();

        assertEquals(HttpStatus.OK, statusRetornado);
        assertEquals(new Long(id), user.getId());
    }

    @Test
    public void getUsuarioPorIdInexistente() {
        int id = 10;
        ResponseEntity<User> response = restTemplate.getForEntity(serverUrl.toString() + id, User.class);
        HttpStatus statusRetornado = response.getStatusCode();
        User user = response.getBody();

        assertEquals(HttpStatus.OK, statusRetornado);
        assertNull(user);
    }

    @Test
    public void geTodosUsuarios() {
        ResponseEntity<User[]> response = restTemplate.getForEntity(serverUrl.toString(), User[].class);
        HttpStatus statusRetornado = response.getStatusCode();
        List<User> users = Arrays.asList(response.getBody());

        assertEquals(HttpStatus.OK, statusRetornado);
        assertEquals(5, users.size());
    }
}
