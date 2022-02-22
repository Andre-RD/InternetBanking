package br.com.InternetBanking.InternetBanking.repository;

import br.com.InternetBanking.InternetBanking.model.Entitys.ClienteEntity;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface ClienteRepository extends JpaRepository<ClienteEntity,Long> {

    List<ClienteEntity> findAll();
    ClienteEntity findByNumeroConta(String numeroConta);

    ClienteEntity findByIdCliente(Long idCliente);


}
