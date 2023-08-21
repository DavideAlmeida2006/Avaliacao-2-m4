package br.com.tech4me.tech4loja.shared;

import jakarta.validation.constraints.NotEmpty;
import jakarta.validation.constraints.Positive;

public record ProdutoCompletoDto (String id,
    @NotEmpty(message = "Informe o produto que deseja.")
    String nome,
    @Positive(message = "Informe um valor positivo para a produto")
    Double valor) { }
