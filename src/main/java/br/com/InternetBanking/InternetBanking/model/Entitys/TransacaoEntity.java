package br.com.InternetBanking.InternetBanking.model.Entitys;


import com.fasterxml.jackson.annotation.JsonIgnore;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import javax.persistence.*;
import java.math.BigDecimal;
import java.util.Date;

@Entity
@Table(name = "TB_TRANSACOES")
@NoArgsConstructor
@AllArgsConstructor
@Data
public class TransacaoEntity {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "CODIGO_TRANSACAO")
    private Long codigoTransacao;

    @ManyToOne(fetch = FetchType.LAZY)
    @JsonIgnore
    @JoinColumn(name = "ID_CLIENTE")
    private ClienteEntity cliente;

    @Column(name ="VALOR_TRANSACAO" )
    private BigDecimal valorTransacao;

    @Column(name= "VALOR_JUROS")
    private BigDecimal valorJuros;

    @ManyToOne
    @JoinColumn(name = "CD_TIPO_TRANSACAO")
    private TipoTransacaoEntity tipoTransacao;

    @Column(name = "DT_TRANSACAO")
    private Date dtTransacao;

    public TransacaoEntity(ClienteEntity cliente, BigDecimal saldo, TipoTransacaoEntity tipoTransacao , Date date, BigDecimal valorJuros) {
        this.cliente = cliente;
        this.valorTransacao = saldo;
        this.tipoTransacao = tipoTransacao;
        this.dtTransacao = date;
        this.valorJuros = valorJuros;
    }


}
