package com.lfernandes;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/api/login")
public class LoginController {

    @Autowired
    private LoginService loginService;

    @PostMapping
    public Response login(@RequestBody User user) {
        Response response = new Response();

        if (user.getUsername() == null || user.getUsername().isEmpty()) {
            response.setError("O nome de usuário não pode estar vazio");
            response.setField("username");
            return response;
        }

        if (user.getPassword() == null || user.getPassword().isEmpty()) {
            response.setError("A senha não pode estar vazia");
            response.setField("password");
            return response;
        }

        if (loginService.authenticate(user)) {
            response.setMessage("Login bem-sucedido!");
        } else {
            response.setError("Falha no login!");
        }

        return response;
    }
}
