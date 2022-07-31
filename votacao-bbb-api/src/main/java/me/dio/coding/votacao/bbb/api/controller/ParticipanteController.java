package me.dio.coding.votacao.bbb.api.controller;

import me.dio.coding.votacao.bbb.api.model.ParticipanteModel;
import me.dio.coding.votacao.bbb.api.repository.ParticipanteRepository;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.Optional;

@RestController
@RequestMapping("/api/participantes")
public class ParticipanteController {
    private final ParticipanteRepository participanteRepository;

    public ParticipanteController(ParticipanteRepository participanteRepository) {
        this.participanteRepository = participanteRepository;
    }

    @PostMapping
    public ResponseEntity<ParticipanteModel> salvar(@RequestBody ParticipanteModel participante) {
        ParticipanteModel salvo = participanteRepository.save(participante);
        return ResponseEntity.status(HttpStatus.CREATED).body(salvo);
    }

    @GetMapping
    public ResponseEntity<List<ParticipanteModel>> consulta() {
        List<ParticipanteModel> participante = participanteRepository.findAll();

        return ResponseEntity.ok(participante);
    }
}
