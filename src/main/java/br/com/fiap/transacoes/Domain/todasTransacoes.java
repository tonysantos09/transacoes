package br.com.fiap.transacoes.Domain;

import lombok.*;

@Getter
@Setter
@EqualsAndHashCode
@NoArgsConstructor
@AllArgsConstructor
public class todasTransacoes {
    private double sum;
    private double min;
    private double max;
    private double avg;
    private long count;
}
