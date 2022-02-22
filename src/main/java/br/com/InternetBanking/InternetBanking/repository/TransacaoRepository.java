package br.com.InternetBanking.InternetBanking.repository;

import br.com.InternetBanking.InternetBanking.model.Entitys.TransacaoEntity;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.Date;
import java.util.List;

@Repository
public interface TransacaoRepository extends JpaRepository<TransacaoEntity, Long> {

    List<TransacaoEntity> findByClienteIdCliente (Long idCliente);
    List<TransacaoEntity> findByClienteIdClienteAndDtTransacaoBetween (Long idCliente, Date dataInicio, Date dataFim);

}
