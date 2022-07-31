package me.dio.coding.votacao.bbb.api.controller;

import me.dio.coding.votacao.bbb.api.model.ParametroModel;
import me.dio.coding.votacao.bbb.api.repository.ParametroRepository;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.Optional;

//FALTOU IMPLEMENTAR
//@RestController
//@RequestMapping("/api/parametros")
public class ParametroController {
    private final ParametroRepository parametroRepository;

    public ParametroController(ParametroRepository parametroRepository) {
        this.parametroRepository = parametroRepository;
    }

    @PostMapping
    public ResponseEntity<ParametroModel> salvar(@RequestBody ParametroModel parametro) {
        ParametroModel salvo = parametroRepository.save(parametro);
        return ResponseEntity.status(HttpStatus.CREATED).body(salvo);
    }

    @GetMapping
    public ResponseEntity<ParametroModel> consulta(@RequestParam(name= "chave") String chave) {
        Optional<ParametroModel> parametro = parametroRepository.findById(chave);
        if(parametro.isEmpty()) {
            return ResponseEntity.notFound().build();
        }
        return ResponseEntity.ok(parametro.get());
    }
}
