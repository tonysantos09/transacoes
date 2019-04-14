package br.com.fiap.transacoes.Controller;

import br.com.fiap.transacoes.Domain.novaTransacao;
import br.com.fiap.transacoes.Domain.todasTransacoes;
import br.com.fiap.transacoes.Repository.novaTransacaoDAO;
import br.com.fiap.transacoes.Repository.todasTransacoesDAO;
import lombok.extern.slf4j.Slf4j;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.servlet.support.ServletUriComponentsBuilder;
import java.net.URI;
import java.time.LocalDateTime;
import io.swagger.annotations.Api;
import io.swagger.annotations.ApiOperation;

@Slf4j
@RestController
@Api(value = "Transacoes", description = "transacoes")
public class TransacaoController {
    public novaTransacaoDAO daoNovaTransacaoDao;
    public todasTransacoesDAO daoTodasTransacoesDao;

    public TransacaoController() {
        daoNovaTransacaoDao = new novaTransacaoDAO();
        daoTodasTransacoesDao = new todasTransacoesDAO();
    }

    @PostMapping("/transacoes")
    public ResponseEntity save(@RequestBody novaTransacao transacao){
        return new ResponseEntity(daoNovaTransacaoDao.save(transacao));
    }

    @GetMapping("/estatisticas")
    public ResponseEntity<todasTransacoes> get(){
        return new ResponseEntity(daoTodasTransacoesDao.resume(daoNovaTransacaoDao.getList()), HttpStatus.OK);
    }
}
