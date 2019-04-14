package br.com.fiap.transacoes.Domain;

import lombok.*;
import java.time.LocalDateTime;

@Getter
@Setter
@EqualsAndHashCode
@NoArgsConstructor
@AllArgsConstructor
public class novaTransacao {
    private double amount ;
    private long timestamp;
    private long dataInsert;
}
