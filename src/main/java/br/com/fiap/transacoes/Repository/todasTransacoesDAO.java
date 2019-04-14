package br.com.fiap.transacoes.Repository;

import br.com.fiap.transacoes.Domain.novaTransacao;
import br.com.fiap.transacoes.Domain.todasTransacoes;
import java.time.LocalDateTime;
import java.util.ArrayList;
import java.util.List;
import java.util.stream.Collectors;

public class todasTransacoesDAO {
    public todasTransacoes resume(ArrayList<novaTransacao> transactionArray){
        long dataHoraAtual = System.currentTimeMillis();
        int segundos = 60000;
        todasTransacoes allTransaction = new todasTransacoes();
        List<novaTransacao> result = transactionArray.stream().filter(a -> (dataHoraAtual - a.getDataInsert())  <= segundos).collect(Collectors.toList());
        allTransaction.setSum(result.stream().mapToDouble(a -> a.getAmount()).sum());
        allTransaction.setAvg(result.stream().mapToDouble(a -> a.getAmount()).average().getAsDouble());
        allTransaction.setMin(result.stream().mapToDouble(a -> a.getAmount()).min().getAsDouble());
        allTransaction.setMax(result.stream().mapToDouble(a -> a.getAmount()).max().getAsDouble());
        allTransaction.setCount(result.size());
        return allTransaction;
    }
}
