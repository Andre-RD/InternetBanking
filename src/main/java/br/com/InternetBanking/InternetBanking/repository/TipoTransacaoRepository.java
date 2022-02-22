package br.com.InternetBanking.InternetBanking.repository;

import br.com.InternetBanking.InternetBanking.model.Entitys.TipoTransacaoEntity;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface TipoTransacaoRepository extends JpaRepository<TipoTransacaoEntity, Long> {

    TipoTransacaoEntity findByCdTipoTransacao (Long cdTipoTransacao);

}
