package me.dio.coding.votacao.bbb.api.service;



import com.fasterxml.jackson.databind.ObjectMapper;
import me.dio.coding.votacao.bbb.api.model.ParticipanteModel;
import org.springframework.http.HttpStatus;
import org.springframework.kafka.core.KafkaTemplate;
import org.springframework.stereotype.Service;
import org.springframework.web.server.ResponseStatusException;

@Service
public class VotacaoService {

    private static final String TOPICO_VOTACAO = "votacao";
    private final KafkaTemplate<Object,Object> template;

    public VotacaoService(KafkaTemplate<Object, Object> template) {
        this.template = template;
    }

    public void adicionarEvento(ParticipanteModel participante) {
        try {
            template.send(TOPICO_VOTACAO, new ObjectMapper().writeValueAsString(participante));
        } catch (Exception e) {
            throw new ResponseStatusException(HttpStatus.BAD_REQUEST,"Erro ao computar voto");
        }
    }
}
