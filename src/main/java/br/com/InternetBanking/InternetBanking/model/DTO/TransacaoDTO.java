package br.com.InternetBanking.InternetBanking.model.DTO;

import lombok.Data;

import java.math.BigDecimal;
import java.util.Date;

@Data
public class TransacaoDTO {

    private BigDecimal valorTransacao;
    private BigDecimal valorJuros;
    private String tipoTransacao;
    private Date dataTransacao;

}
