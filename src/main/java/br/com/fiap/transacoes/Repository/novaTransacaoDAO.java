package br.com.fiap.transacoes.Repository;

import br.com.fiap.transacoes.Domain.novaTransacao;
import org.springframework.http.HttpStatus;

import java.time.LocalDateTime;
import java.time.temporal.ChronoUnit;
import java.util.ArrayList;
import java.util.TimerTask;
import java.util.concurrent.TimeUnit;


public class novaTransacaoDAO {
    public ArrayList<novaTransacao> transactionArray = null;

    public novaTransacaoDAO(){
        transactionArray = new ArrayList<>();
    }

    public ArrayList<novaTransacao> getList(){
        return transactionArray;
    }

    public HttpStatus save(novaTransacao transaction){

        long millisSecondsAtual = System.currentTimeMillis();
        long timeSpanSave = transaction.getTimestamp();

        long sobra = TimeUnit.MILLISECONDS.toSeconds(millisSecondsAtual - timeSpanSave);

        transaction.setDataInsert(millisSecondsAtual);


        HttpStatus status = HttpStatus.CREATED;
        transactionArray.add(transaction);
        if(sobra >= 60) {
            status = HttpStatus.NO_CONTENT;
        }
        return  status;
    }

}
