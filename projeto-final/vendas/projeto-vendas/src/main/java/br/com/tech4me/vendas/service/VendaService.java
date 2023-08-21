package br.com.tech4me.vendas.service;

import java.util.List;
import java.util.Optional;

import br.com.tech4me.vendas.shared.VendaCompletoDto;
import br.com.tech4me.vendas.shared.VendaDto;

public interface VendaService {
    VendaCompletoDto cadastrarVenda(VendaCompletoDto dto);
    List<VendaCompletoDto> obterVendas();
    Optional<VendaDto> obterVendasPorId(String id);
    void excluirVenda(String id);
    Optional<VendaDto> atualizarVendaPorId(String id, VendaDto dto);
}
