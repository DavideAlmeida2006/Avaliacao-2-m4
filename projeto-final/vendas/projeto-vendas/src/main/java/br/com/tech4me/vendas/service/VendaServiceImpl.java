package br.com.tech4me.vendas.service;

import java.util.List;
import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import br.com.tech4me.vendas.httpclient.LojaClient;
import br.com.tech4me.vendas.model.Venda;
import br.com.tech4me.vendas.model.Produto;
import br.com.tech4me.vendas.repository.VendaRepository;
import br.com.tech4me.vendas.shared.VendaCompletoDto;
import br.com.tech4me.vendas.shared.VendaDto;
import io.github.resilience4j.circuitbreaker.annotation.CircuitBreaker;

@Service
public class VendaServiceImpl implements VendaService {

    @Autowired
    private VendaRepository repositorio;

    @Autowired
    private LojaClient produtosClient;

    @Override
    public VendaCompletoDto cadastrarVenda(VendaCompletoDto dto) {
        Venda venda = new Venda(dto);
        repositorio.save(venda);
        return new VendaCompletoDto(venda.getId(), venda.getNomeCliente(), venda.getIdProduto());
    }

    @Override
    public List<VendaCompletoDto> obterVendas() {
        return repositorio.findAll()
            .stream()
            .map(p -> new VendaCompletoDto(p.getId(), p.getNomeCliente(), p.getIdProduto()))
            .toList();
    }

    @CircuitBreaker(name = "obterProduto", fallbackMethod = "fallbackVendasPorId")
    @Override
    public Optional<VendaDto> obterVendasPorId(String id) {
        Optional<Venda> venda = repositorio.findById(id);

        if (venda.isPresent()) {
            Produto produto = produtosClient.obterProduto(venda.get().getIdProduto());
            VendaDto vendaComProduto = new VendaDto(venda.get().getNomeCliente(), venda.get().getIdProduto(), produto);
            return Optional.of(vendaComProduto);
        } else {
            return Optional.empty();
        }
    }

    public Optional<VendaDto> fallbackVendasPorId(String id, Exception e) {
        Optional<Venda> venda = repositorio.findById(id);

        if (venda.isPresent()) {
            VendaDto vendaComProduto = new VendaDto(venda.get().getNomeCliente(), venda.get().getIdProduto(), null);
            return Optional.of(vendaComProduto);
        } else {
            return Optional.empty();
        }
    }
    

    @Override
    public void excluirVenda(String id) {
        repositorio.deleteById(id);
    }

    @Override
    public Optional<VendaDto> atualizarVendaPorId(String id, VendaDto dto) {
        Optional<Venda> venda = repositorio.findById(id);
        
        if (venda.isPresent()) {
            Venda vendaAtualizar = new Venda(dto);
            vendaAtualizar.setId(id);
            repositorio.save(vendaAtualizar);
            return Optional.of(dto);
        } else {
            return Optional.empty();
        }
    }
    
}
