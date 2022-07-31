package me.dio.coding.votacao.bbb.ms.model;


import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;
import org.springframework.data.annotation.Id;
import org.springframework.data.mongodb.core.mapping.Document;

import java.time.LocalDateTime;

@Data
@AllArgsConstructor
@NoArgsConstructor
@Document("votacao")
public class VotacaoModel {
    @Id
    private String id;
    private Participante idParticipante;
    private LocalDateTime dataHora;
}
