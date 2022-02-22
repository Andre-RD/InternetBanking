package br.com.InternetBanking.InternetBanking.model.Entitys;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import javax.persistence.*;

@Entity
@Table(name = "TB_TIPO_TRANSACAO")
@Data
@NoArgsConstructor
@AllArgsConstructor
public class TipoTransacaoEntity {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "CD_TIPO_TRANSACAO")
    private Long cdTipoTransacao;

    @Column(name = "DS_TIPO_TRANSACAO")
    private String dsTipoTransacao;


}
