package br.com.InternetBanking.InternetBanking.model.Entitys;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;
import org.springframework.format.annotation.DateTimeFormat;

import javax.persistence.*;
import java.math.BigDecimal;
import java.time.LocalDate;
import java.util.List;


@Entity
@Table(name = "TB_CLIENTE")
@Data
@NoArgsConstructor
@AllArgsConstructor
public class ClienteEntity {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "ID_CLIENTE")
    private Long idCliente;

    @Column(name = "NOME_CLIENTE")
    private String nomeCliente;

    @Column(name = "PLANO_EXCLUSIVO")
    private boolean planoExclusivo;

    @Column(name = "SALDO")
    private BigDecimal saldo;

    @Column(name = "NUMERO_CONTA")
    private String numeroConta;

    @Column(name = "DATA_NASCIMENTO")
    @DateTimeFormat(pattern = "yyyy-MM-dd")
    private LocalDate dataNascimento;

    @OneToMany(mappedBy = "cliente", cascade = CascadeType.ALL)
    private List<TransacaoEntity> transacoes;


}
