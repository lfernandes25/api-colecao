package com.lfernandes.Controller;

import com.lfernandes.Class.Editora;
import com.lfernandes.Response;
import com.lfernandes.Service.EditoraService;
import org.apache.log4j.Logger;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("/api/cadastroEditora")
public class EditoraController {

    private static final Logger logger = Logger.getLogger(String.valueOf(EditoraController.class));

    @Autowired
    private EditoraService editoraService;

    @PostMapping
    public Response cadastroEditora(@RequestBody Editora editora) {

        if (editora.getNomeEditora() == null || editora.getNomeEditora().isEmpty()) {
            logger.error("O campo nome editora não pode estar vazio");
            return Response.error("O campo nome editora não pode estar vazio","Nome Editora");
        } else {
            String resultado = editoraService.adicionarEditora(editora.getNomeEditora());
            if ("Registro duplicado".equals(resultado)) {
                logger.error("Editora já existente: " + editora.getNomeEditora());
                return Response.error("Editora já existente","Nome Editora");
            } else {
                editoraService.adicionarEditora(editora.getNomeEditora());
                logger.info("Editora cadastrada com sucesso: " + editora.getNomeEditora());
                return Response.success("Editora cadastrada com sucesso");
            }
        }

    }
}
