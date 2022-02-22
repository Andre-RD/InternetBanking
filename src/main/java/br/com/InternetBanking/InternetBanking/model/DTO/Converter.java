package br.com.InternetBanking.InternetBanking.model.DTO;

import br.com.InternetBanking.InternetBanking.model.Entitys.ClienteEntity;
import br.com.InternetBanking.InternetBanking.model.Entitys.TransacaoEntity;
import lombok.Data;

@Data
public class Converter {
    public ClienteEntity parseToEntity(ClienteDTO dto){
        ClienteEntity entity = new ClienteEntity();
        entity.setNomeCliente(dto.getNomeCliente());
        entity.setPlanoExclusivo(dto.isPlanoExclusivo());
        entity.setSaldo(dto.getSaldo());
        entity.setDataNascimento(dto.getDataNascimento());
        return entity;
    }
    public ClienteDTOPrint parseToDto(ClienteEntity entity){
        ClienteDTOPrint dto = new ClienteDTOPrint();
        dto.setIdCliente(entity.getIdCliente());
        dto.setNumeroConta(entity.getNumeroConta());
        dto.setNomeCliente(entity.getNomeCliente());
        dto.setPlanoExclusivo(entity.isPlanoExclusivo());
        dto.setSaldo(entity.getSaldo());
        dto.setDataNascimento(entity.getDataNascimento());
        return dto;
    }
    public TransacaoDTO parseToDto(TransacaoEntity entity){
        TransacaoDTO dto = new TransacaoDTO();
        dto.setTipoTransacao(entity.getTipoTransacao().getDsTipoTransacao());
        dto.setValorTransacao(entity.getValorTransacao());
        dto.setDataTransacao(entity.getDtTransacao());
        dto.setValorJuros(entity.getValorJuros());
        return dto;
    }
}
