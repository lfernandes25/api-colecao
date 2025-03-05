package com.lfernandes.Service;

import com.lfernandes.Repository.EditoraRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
public class EditoraService {

    private final EditoraRepository editoraRepository;

    @Autowired
    public EditoraService(EditoraRepository editoraRepository) {
        this.editoraRepository = editoraRepository;
    }

    public String adicionarEditora(String nomeEditora) {
        if (editoraRepository.existsByNomeEditora(nomeEditora)) {
            return "Registro duplicado";
        } else {
            editoraRepository.insertEditora(nomeEditora);
            return "Registro inserido com sucesso";
        }
    }
}
