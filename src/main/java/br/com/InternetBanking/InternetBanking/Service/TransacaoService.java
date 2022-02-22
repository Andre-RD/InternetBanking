package br.com.InternetBanking.InternetBanking.Service;

import br.com.InternetBanking.InternetBanking.model.DTO.Converter;
import br.com.InternetBanking.InternetBanking.model.DTO.TransacaoDTO;
import br.com.InternetBanking.InternetBanking.model.Entitys.ClienteEntity;
import br.com.InternetBanking.InternetBanking.model.Entitys.TipoTransacaoEntity;
import br.com.InternetBanking.InternetBanking.model.Entitys.TransacaoEntity;
import br.com.InternetBanking.InternetBanking.repository.TipoTransacaoRepository;
import br.com.InternetBanking.InternetBanking.repository.TransacaoRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.math.BigDecimal;
import java.time.LocalDate;
import java.time.ZoneId;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;

@Service
public class TransacaoService {
    @Autowired
    private TransacaoRepository transacaoRepository;

    @Autowired
    private TipoTransacaoRepository tipoTransacaoRepository;

    Converter converter = new Converter();


    public List<TransacaoDTO> consultarTransacoe(Long idCliente) {
        List<TransacaoEntity>  ListEntity =  transacaoRepository.findByClienteIdCliente(idCliente);
        List<TransacaoDTO> dtos = new ArrayList<>();
        for(TransacaoEntity entity: ListEntity){
            dtos.add(converter.parseToDto(entity));
        }
        return dtos;
    }

    public TransacaoEntity gravaTransacao(ClienteEntity cliente, BigDecimal valor, Long tipoMovimentacao,BigDecimal valorJuros){
        Date date = new Date();
        TipoTransacaoEntity tipoTransacaoEntity = tipoTransacaoRepository.findByCdTipoTransacao(tipoMovimentacao);
        TransacaoEntity entity = new TransacaoEntity(cliente,valor,tipoTransacaoEntity,date,valorJuros);
        return transacaoRepository.save(entity);
    }

    public TransacaoEntity depositar(ClienteEntity cliente, BigDecimal valorDeposito){
          return gravaTransacao(cliente,valorDeposito,1L,BigDecimal.valueOf(0L));
    }

    public TransacaoEntity sacar(ClienteEntity cliente, BigDecimal valorSaque,BigDecimal valorJuros){
           return gravaTransacao(cliente,valorSaque,2L, valorJuros);
    }

    public BigDecimal calcularJuros(BigDecimal valorSaque){
        BigDecimal valorJuros = new BigDecimal(0l);
            if (valorSaque.longValue() > 100L && valorSaque.longValue() <= 300L){
                valorJuros = BigDecimal.valueOf(valorSaque.longValue() * 0.004);
            } else if(valorSaque.longValue() > 300L) {
                valorJuros = BigDecimal.valueOf(valorSaque.longValue() * 0.01);
            }
        return valorJuros;
    }

    public Date convertToDate(LocalDate localDate){
        return Date.from(localDate.atStartOfDay(ZoneId.systemDefault()).toInstant());
    }

    public List<TransacaoDTO> consultarTransacoePeriodo(Long id, LocalDate dataInicio, LocalDate dataFim) {
        List<TransacaoEntity>  ListEntity = transacaoRepository.findByClienteIdClienteAndDtTransacaoBetween(id, convertToDate(dataInicio), convertToDate(dataFim));
        List<TransacaoDTO> dtos = new ArrayList<>();
        for(TransacaoEntity entity: ListEntity){
            dtos.add(converter.parseToDto(entity));
        }
        return dtos;
    }
}
