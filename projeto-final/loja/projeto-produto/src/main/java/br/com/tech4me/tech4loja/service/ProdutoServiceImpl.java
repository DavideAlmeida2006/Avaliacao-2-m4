package br.com.tech4me.tech4loja.service;

import java.util.List;
import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import br.com.tech4me.tech4loja.model.Produto;
import br.com.tech4me.tech4loja.repository.ProdutoRepository;
import br.com.tech4me.tech4loja.shared.ProdutoCompletoDto;
import br.com.tech4me.tech4loja.shared.ProdutoDto;

@Service
public class ProdutoServiceImpl implements ProdutoService {

    @Autowired
    private ProdutoRepository repositorio;

    @Override
    public List<ProdutoDto> obterTodos() {
        return repositorio.findAll()
            .stream()
            .map(p -> new ProdutoDto(p.getNome(), p.getValor()))
            .toList();
    }

    @Override
    public Optional<ProdutoCompletoDto> obterPorId(String id) {
        Optional<Produto> produto = repositorio.findById(id);

        if (produto.isPresent()) {
            return Optional.of(new ProdutoCompletoDto(produto.get().getId(),
                produto.get().getNome(),
                produto.get().getValor()));
        } else {
            return Optional.empty();
        }
    }

    @Override
    public ProdutoCompletoDto cadastrar(ProdutoCompletoDto dto) {
        Produto produto = new Produto(dto);
        repositorio.save(produto);

        return new ProdutoCompletoDto(produto.getId(),
            produto.getNome(),
            produto.getValor());
    }

    @Override
    public ProdutoCompletoDto atualizarPorId(String id, ProdutoCompletoDto dto) {
        Produto produto = repositorio.findById(id).orElse(null);

        if (produto != null) {
            Produto produtoAtualizar = new Produto(dto);
            produtoAtualizar.setId(id);
            repositorio.save(produtoAtualizar);
            return new ProdutoCompletoDto(produtoAtualizar.getId(),
                produtoAtualizar.getNome(),
                produtoAtualizar.getValor());
        } else {
            return null;
        }
    }

    @Override
    public void excluirPorId(String id) {
        repositorio.deleteById(id);
    }
    
}
