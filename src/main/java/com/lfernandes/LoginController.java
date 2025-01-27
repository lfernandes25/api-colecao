package com.lfernandes;


import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.util.logging.Logger;

@RestController
@RequestMapping("/api/login")
public class LoginController {

    private static final Logger logger = Logger.getLogger(String.valueOf(LoginController.class));

    @Autowired
    private LoginService loginService;

    @PostMapping
    public Response login(@RequestBody User user) {
        Response response = new Response();

        if (user.getUsername() == null || user.getUsername().isEmpty()) {
            logger.info("O nome de usuário não pode estar vazio");
            response.setError("O nome de usuário não pode estar vazio");
            response.setField("username");
            return response;
        }

        if (user.getPassword() == null || user.getPassword().isEmpty()) {
            logger.info("A senha não pode estar vazia");
            response.setError("A senha não pode estar vazia");
            response.setField("password");
            return response;
        }

        if (loginService.authenticate(user)) {
            logger.info("Login bem-sucedido!");
            response.setMessage("Login bem-sucedido!");
        } else {
            logger.info("Falha no login!");
            response.setError("Falha no login!");
        }

        return response;
    }
}
