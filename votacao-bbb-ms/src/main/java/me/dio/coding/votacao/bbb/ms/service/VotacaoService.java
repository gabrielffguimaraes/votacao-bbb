package me.dio.coding.votacao.bbb.ms.service;

import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.databind.JsonMappingException;
import com.fasterxml.jackson.databind.ObjectMapper;
import lombok.extern.slf4j.Slf4j;
import me.dio.coding.votacao.bbb.ms.model.Participante;
import me.dio.coding.votacao.bbb.ms.model.VotacaoModel;
import me.dio.coding.votacao.bbb.ms.repository.VotacaoRepository;
import org.apache.kafka.clients.consumer.ConsumerRecord;
import org.springframework.http.codec.multipart.Part;
import org.springframework.kafka.annotation.KafkaListener;
import org.springframework.stereotype.Service;

import java.time.LocalDateTime;

@Service
@Slf4j
public class VotacaoService {

    private final VotacaoRepository repository;

    public VotacaoService(VotacaoRepository repository) {
        this.repository = repository;
    }

    @KafkaListener(topics = "votacao", groupId = "micro_servico_votacao")
    private void executar(ConsumerRecord<String, String> registro) {
        Participante participante = null;
        try {
            participante = new ObjectMapper().readValue(registro.value(), Participante.class);
        }  catch (JsonProcessingException e) {
            log.info("Erro ao processar voto do participante = {}",registro.value());
        }
        log.info("Novo voto recebido = {}",participante);


        VotacaoModel votacaoModel = new VotacaoModel(null,participante, LocalDateTime.now());
        VotacaoModel saved = repository.save(votacaoModel);

        log.info("Voto registrado com sucesso [id={} , dataHora={}]",saved.getId(),saved.getDataHora());
    }
}
