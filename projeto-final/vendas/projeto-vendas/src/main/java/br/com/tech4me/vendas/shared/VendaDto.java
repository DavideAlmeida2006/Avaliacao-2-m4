package br.com.tech4me.vendas.shared;

import br.com.tech4me.vendas.model.Produto;

public record VendaDto(String nomeCliente, String idProduto, Produto dadosProduto) {
    
}