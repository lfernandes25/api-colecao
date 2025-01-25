package com.lfernandes;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/api/login")
public class LoginController {

    @Autowired
    private LoginService loginService;

    @PostMapping
    public String login(@RequestBody User user) {
        if (user.getUsername() == null || user.getUsername().isEmpty()) {
            return "Erro: O nome de usuário não pode estar vazio";
        }

        if (user.getPassword() == null || user.getPassword().isEmpty()) {
            return "Erro: A senha não pode estar vazia";
        }

        if (loginService.authenticate(user)) {
            return "Login bem-sucedido!";
        } else {
            return "Falha no login!";
        }
    }
}
