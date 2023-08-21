package br.com.tech4me.vendas.httpclient;

import org.springframework.cloud.openfeign.FeignClient;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;

import br.com.tech4me.vendas.model.Produto;

@FeignClient("loja")
public interface LojaClient {
    @RequestMapping(method = RequestMethod.GET, value = "/produtos/{id}")
    Produto obterProduto(@PathVariable String id);
}
