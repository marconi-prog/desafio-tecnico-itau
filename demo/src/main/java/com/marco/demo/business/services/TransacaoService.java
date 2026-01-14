package com.marco.demo.business.services;

import com.marco.demo.controller.dtos.TransacaoRequestDTO;
import com.marco.demo.infrastructure.exceptions.UnprocessableEntity;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Service;

import java.time.OffsetDateTime;
import java.util.ArrayList;
import java.util.List;

@Service
@RequiredArgsConstructor
@Slf4j
public class TransacaoService {

    private final List<TransacaoRequestDTO> listarTransacao = new ArrayList<>();

    public void adicionarTransacoes(TransacaoRequestDTO dto){

        log.info("iniciado o processamento de gravar transacoes");

        if (dto.datahora().isAfter(OffsetDateTime.now())){
            log.error("data e hora maiores que a data e hora");
            throw new UnprocessableEntity("data e horas maiores que a data e hora");
        }
        if (dto.valor() < 0){
            log.error("valor menor que 0");
            throw new UnprocessableEntity("valor nao pode ser menor que 0");
        }
    }

    public void limparTransacoes(){
        listarTransacao.clear();
    }

    public List <TransacaoRequestDTO> buscarTransacoes(Integer intervaloBusca){

        OffsetDateTime dataHoraIntervalo = OffsetDateTime.now().minusSeconds(60);
        return listarTransacao.stream().filter(transacoes -> transacoes.datahora().isAfter(dataHoraIntervalo)).toList();
    }
}
