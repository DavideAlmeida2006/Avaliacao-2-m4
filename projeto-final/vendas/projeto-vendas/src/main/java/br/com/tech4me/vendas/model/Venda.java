package br.com.tech4me.vendas.model;

import org.springframework.data.annotation.Id;
import org.springframework.data.mongodb.core.mapping.Document;

import br.com.tech4me.vendas.shared.VendaCompletoDto;
import br.com.tech4me.vendas.shared.VendaDto;

@Document("pedidos")
public class Venda {
    @Id
    private String id;
    private String nomeCliente;
    private String idProduto;

    public Venda() {}

    public Venda(VendaCompletoDto dto) {
        this.id = dto.id();
        this.nomeCliente = dto.nomeCliente();
        this.idProduto = dto.idProduto();
    }

    public Venda(VendaDto dto) {
        this.nomeCliente = dto.nomeCliente();
        this.idProduto = dto.idProduto();
    }

    public String getId() {
        return id;
    }
    public void setId(String id) {
        this.id = id;
    }
    public String getNomeCliente() {
        return nomeCliente;
    }
    public void setNomeCliente(String nomeCliente) {
        this.nomeCliente = nomeCliente;
    }
    public String getIdProduto() {
        return idProduto;
    }
    public void setIdPizza(String idProduto) {
        this.idProduto = idProduto;
    }
}
