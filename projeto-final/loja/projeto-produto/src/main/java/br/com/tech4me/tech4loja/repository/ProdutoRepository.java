package br.com.tech4me.tech4loja.repository;

import org.springframework.data.mongodb.repository.MongoRepository;

import br.com.tech4me.tech4loja.model.Produto;

public interface ProdutoRepository extends MongoRepository<Produto, String> {
    
}
