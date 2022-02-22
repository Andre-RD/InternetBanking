package br.com.InternetBanking.InternetBanking.Controller;

import br.com.InternetBanking.InternetBanking.Service.ClienteService;
import br.com.InternetBanking.InternetBanking.Service.TransacaoService;
import br.com.InternetBanking.InternetBanking.model.DTO.ClienteDTO;
import br.com.InternetBanking.InternetBanking.model.DTO.ClienteDTOPrint;
import br.com.InternetBanking.InternetBanking.model.DTO.ResultData;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.format.annotation.DateTimeFormat;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.math.BigDecimal;
import java.time.LocalDate;

@RestController
@RequestMapping(value = "/Cliente")
public class ClienteController {
    @Autowired
    private ClienteService service;

    @Autowired
    private TransacaoService transacaoService;

    @GetMapping("/listarClientes")
    public ResponseEntity ListarClientes(){
        return ResponseEntity.ok().body(service.listarClientes());
    }

    @GetMapping("/{id}")
    public ResponseEntity consutarClientePorId(@PathVariable("id") Long id){
        return ResponseEntity.ok().body(service.buscarPorId(id));
    }
    @GetMapping("/{id}/listarTransacoes")
    public ResponseEntity ListarTransacoes(@PathVariable("id") Long id){
        return ResponseEntity.ok().body(transacaoService.consultarTransacoe(id));
    }

    @RequestMapping(value = "/{id}/listarTransacoes/periodo", method =  RequestMethod.GET)
    public ResponseEntity ListarTransacoesPeriodo(@PathVariable("id") Long id, @RequestParam(value = "dataInicio", required=false) @DateTimeFormat(pattern = "yyyy-MM-dd")LocalDate dataInicio, @RequestParam(value = "dataFim", required=false) @DateTimeFormat(pattern = "yyyy-MM-dd") LocalDate dataFim){
        return ResponseEntity.ok().body(transacaoService.consultarTransacoePeriodo(id,dataInicio,dataFim));
    }

    @PutMapping("/{id}/depositar")
    public ResponseEntity depositar(@PathVariable("id") Long id, @RequestParam(value = "valorDeposito", required=true)BigDecimal valorDeposito){
        return ResponseEntity.ok().body(service.depositar(id,valorDeposito));
    }

    @PutMapping("/{id}/sacar")
    public ResponseEntity sacar(@PathVariable("id") Long id, @RequestParam(value = "valorSaque", required=true)BigDecimal valorSaque){
        return ResponseEntity.ok().body(service.sacar(id,valorSaque));
    }


    @PostMapping("/cadastrarCliente")
    public ResponseEntity<Object> CadastrarCliente(@RequestBody ClienteDTO clienteDto){
        ResultData resultData = null;
        try {
            ClienteDTOPrint novoCliente = service.cadastrarCliente(clienteDto);
            resultData = new ResultData<ClienteDTOPrint>(HttpStatus.OK.value(),
                    "Cliente registrada com sucesso!", novoCliente);
            return ResponseEntity.status(HttpStatus.CREATED).body(resultData);
        }catch (Exception e){
            resultData = new ResultData(HttpStatus.INTERNAL_SERVER_ERROR.value(),
                    "Ocorreu um erro ao registrar cliente", e.getMessage());
            return ResponseEntity.status(HttpStatus.INTERNAL_SERVER_ERROR.value()).body(resultData);
        }
    }








}
