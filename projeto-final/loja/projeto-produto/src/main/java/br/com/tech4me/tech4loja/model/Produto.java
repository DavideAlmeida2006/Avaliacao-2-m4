package br.com.tech4me.tech4loja.model;

import org.springframework.data.annotation.Id;
import org.springframework.data.mongodb.core.mapping.Document;

import br.com.tech4me.tech4loja.shared.ProdutoCompletoDto;

@Document("produtos")
public class Produto {
    @Id
    private String id;
    private String nome;
    private Double valor;

    public Produto() {}

    public Produto(ProdutoCompletoDto dto) {
        this.id = dto.id();
        this.nome = dto.nome();
        this.valor = dto.valor();
    }

    public String getId() {
        return id;
    }

    public void setId(String id) {
        this.id = id;
    }

    public String getNome() {
        return nome;
    }

    public void setNome(String nome) {
        this.nome = nome;
    }

    public Double getValor() {
        return valor;
    }

    public void setValor(Double valor) {
        this.valor = valor;
    }

}
