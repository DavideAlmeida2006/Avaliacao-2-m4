package br.com.tech4me.vendas.controller;

import java.util.List;
import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import br.com.tech4me.vendas.service.VendaService;
import br.com.tech4me.vendas.shared.VendaCompletoDto;
import br.com.tech4me.vendas.shared.VendaDto;

@RestController
@RequestMapping("/vendas")
public class VendaController {
    @Autowired
    private VendaService servico;

    @PostMapping
    public ResponseEntity<VendaCompletoDto> cadastrarVenda(@RequestBody VendaCompletoDto venda) {
        return new ResponseEntity<>(servico.cadastrarVenda(venda), HttpStatus.CREATED);
    }

    @GetMapping
    public ResponseEntity<List<VendaCompletoDto>> obterPedidos() {
        return new ResponseEntity<>(servico.obterVendas(), HttpStatus.OK);
    }

    @GetMapping("/{id}")
    public ResponseEntity<VendaDto> obterPedidoPorId(@PathVariable String id) {
        Optional<VendaDto> retorno = servico.obterVendasPorId(id);

        if (retorno.isPresent()) {
            return new ResponseEntity<>(retorno.get(), HttpStatus.OK);
        } else {
            return new ResponseEntity<>(HttpStatus.NOT_FOUND);
        }
    }

    @DeleteMapping("/{id}")
    public ResponseEntity<Void> excluirPedido(@PathVariable String id) {
        servico.excluirVenda(id);
        return new ResponseEntity<>(HttpStatus.NO_CONTENT);
    }

    @PutMapping("/{id}")
    public ResponseEntity<VendaDto> atualizarPedido(@PathVariable String id, @RequestBody VendaDto venda) {
        Optional<VendaDto> retorno = servico.atualizarVendaPorId(id, venda);

        if (retorno.isEmpty()) {
            return new ResponseEntity<>(retorno.get(), HttpStatus.ACCEPTED);
        } else {
            return new ResponseEntity<>(HttpStatus.NOT_ACCEPTABLE);
        }
    }
}
