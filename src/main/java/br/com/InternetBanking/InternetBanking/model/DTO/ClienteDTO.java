package br.com.InternetBanking.InternetBanking.model.DTO;

import lombok.Data;
import org.springframework.format.annotation.DateTimeFormat;

import java.math.BigDecimal;
import java.time.LocalDate;

@Data
public class ClienteDTO {

    private String nomeCliente;

    private boolean planoExclusivo;



    private BigDecimal saldo;

    @DateTimeFormat(pattern = "yyyy-MM-dd")
    private LocalDate dataNascimento;







}
