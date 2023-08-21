package br.com.tech4me.tech4loja.controller;

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

import br.com.tech4me.tech4loja.service.ProdutoService;
import br.com.tech4me.tech4loja.shared.ProdutoDto;
import br.com.tech4me.tech4loja.shared.ProdutoCompletoDto;
import jakarta.validation.Valid;

@RestController
@RequestMapping("/produtos")
public class ProdutoController {

    @Autowired
    private ProdutoService servico;  
    
    @GetMapping
    private ResponseEntity<List<ProdutoDto>> obterProdutos() {
        return new ResponseEntity<>(servico.obterTodos(), HttpStatus.OK);
    }

    @GetMapping("/{id}")
    private ResponseEntity<ProdutoCompletoDto> obterProdutoPorId(@PathVariable String id) {
        Optional<ProdutoCompletoDto> produto = servico.obterPorId(id);

        if (produto.isPresent()) {
            return new ResponseEntity<>(produto.get(), HttpStatus.OK);
        } else {
            return new ResponseEntity<>(HttpStatus.NOT_FOUND);
        }
    }

    @PostMapping
    private ResponseEntity<ProdutoCompletoDto> cadastrarProduto(@RequestBody @Valid ProdutoCompletoDto produto) {
        return new ResponseEntity<>(servico.cadastrar(produto), HttpStatus.CREATED);
    }

    @DeleteMapping("/{id}")
    private ResponseEntity<Void> excluirProdutoPorId(@PathVariable String id) {
        servico.excluirPorId(id);
        return new ResponseEntity<>(HttpStatus.NO_CONTENT);
    }

    @PutMapping("/{id}")
    private ResponseEntity<ProdutoCompletoDto> atualizarProduto(@PathVariable String id, @RequestBody @Valid ProdutoCompletoDto produto) {
        ProdutoCompletoDto produtoAtualizado = servico.atualizarPorId(id, produto);

        if (produtoAtualizado != null) {
            return new ResponseEntity<>(produtoAtualizado, HttpStatus.OK);
        } else {
            return new ResponseEntity<>(HttpStatus.NOT_FOUND);
        }
    }
}
