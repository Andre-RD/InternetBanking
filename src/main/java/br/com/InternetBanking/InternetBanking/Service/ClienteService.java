package br.com.InternetBanking.InternetBanking.Service;

import br.com.InternetBanking.InternetBanking.model.DTO.ClienteDTO;
import br.com.InternetBanking.InternetBanking.model.DTO.ClienteDTOPrint;
import br.com.InternetBanking.InternetBanking.model.DTO.Converter;
import br.com.InternetBanking.InternetBanking.model.DTO.TransacaoDTO;
import br.com.InternetBanking.InternetBanking.model.Entitys.ClienteEntity;
import br.com.InternetBanking.InternetBanking.model.Entitys.TransacaoEntity;
import br.com.InternetBanking.InternetBanking.repository.ClienteRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.math.BigDecimal;
import java.util.ArrayList;
import java.util.List;
import java.util.Random;

@Service
public class ClienteService {

    @Autowired
    private ClienteRepository repository;

    @Autowired
    private TransacaoService transacaoService;


    Converter converter = new Converter();

    public List<ClienteDTOPrint> listarClientes(){
        List<ClienteDTOPrint> dto = new ArrayList<>();
        for (ClienteEntity entity : repository.findAll()){
            dto.add(converter.parseToDto(entity));
        }
        return dto;
    }

    public ClienteDTOPrint buscarPorId(Long idCliente){
        ClienteDTOPrint dto = converter.parseToDto(repository.findByIdCliente(idCliente));
        return dto;
    }


    public ClienteDTOPrint cadastrarCliente(ClienteDTO dto){
        ClienteEntity clienteNovo = converter.parseToEntity(dto);
        clienteNovo.setNumeroConta(gerarNumeroConta());
        repository.save(clienteNovo);
        transacaoService.depositar(clienteNovo,clienteNovo.getSaldo());
        ClienteDTOPrint ClienteDto = converter.parseToDto(clienteNovo);
        return ClienteDto;
    }

    public TransacaoDTO depositar(Long idCliente, BigDecimal valorDeposito){
        ClienteEntity cliente = repository.getById(idCliente);
        cliente.setSaldo(cliente.getSaldo().add(valorDeposito));
        TransacaoEntity entity = transacaoService.depositar(cliente,valorDeposito);
        repository.save(cliente);
        TransacaoDTO dto = converter.parseToDto(entity);
        return dto ;
    }

    public TransacaoDTO sacar(Long idCliente, BigDecimal valorSaque) {
        ClienteEntity cliente = repository.getById(idCliente);
        BigDecimal valorSaqueTotal = valorSaque;
        BigDecimal valorJuros = new BigDecimal(0L);
        if(!cliente.isPlanoExclusivo()){
            System.out.println("Entrou no if");
           valorJuros = transacaoService.calcularJuros(valorSaque);
            System.out.println("Valor Juros: "+ valorJuros);
            valorSaqueTotal = valorSaque.add(valorJuros);
        }
        System.out.println("Valor Saque Total:"+ valorSaqueTotal);
        cliente.setSaldo(cliente.getSaldo().subtract(valorSaqueTotal));
        TransacaoEntity entity = transacaoService.sacar(cliente,valorSaque,valorJuros);
        repository.save(cliente);
        TransacaoDTO dto = converter.parseToDto(entity);
        return dto;
    }

    public String gerarNumeroConta(){
        String agencia = "0001";
        String numeroConta = agencia + "-";
        boolean bul = true;
        while (bul){
            Random random = new Random();
            int numero = random.nextInt(90000) + 10000;
            numeroConta += numero;
            if(contaDisponivel(numeroConta)){
                bul = false;
            }
        }
        System.out.println("Numero da conta: "+ numeroConta);
        return numeroConta;
    }

    public boolean contaDisponivel(String numeroConta){
        ClienteEntity result = repository.findByNumeroConta(numeroConta);
        if(result == null){
            return true;
        }else {
            return false;
        }

    }



}
