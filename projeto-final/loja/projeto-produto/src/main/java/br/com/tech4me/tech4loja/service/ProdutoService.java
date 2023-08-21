package br.com.tech4me.tech4loja.service;

import java.util.List;
import java.util.Optional;

import br.com.tech4me.tech4loja.shared.ProdutoCompletoDto;
import br.com.tech4me.tech4loja.shared.ProdutoDto;

public interface ProdutoService {
    List<ProdutoDto> obterTodos();
    Optional<ProdutoCompletoDto> obterPorId(String id);
    ProdutoCompletoDto cadastrar(ProdutoCompletoDto dto);
    ProdutoCompletoDto atualizarPorId(String id, ProdutoCompletoDto dto);
    void excluirPorId(String id);
}
