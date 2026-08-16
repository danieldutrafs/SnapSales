package br.com.connect.services;

import br.com.connect.models.MovimentacaoEstoque;
import br.com.connect.models.Produto;
import br.com.connect.repositories.MovimentacaoRepository;
import br.com.connect.utils.ValidadorUtil;

/**
 * @author Daniel Dutra
 */
public class MovimentacaoService {

    private MovimentacaoRepository movimentacaoRepository = new MovimentacaoRepository();
    private static long contadorIdMovimentacao = 1;

    public boolean salvar(Produto produto, int quantidade, boolean tipoMovimentacao) {
        
        if (produto == null) {
            System.out.println("Erro: A movimentação precisa estar associada a um produto.");
            return false;
        }

        if (!ValidadorUtil.validarCampoInt(quantidade)) {
            System.out.println("Erro: A quantidade da movimentação deve ser maior que zero.");
            return false;
        }

        MovimentacaoEstoque novaMovimentacao = new MovimentacaoEstoque(
                contadorIdMovimentacao++,
                produto,
                quantidade,
                tipoMovimentacao
        );

        movimentacaoRepository.salvarMovi(novaMovimentacao);
        System.out.println("Movimentação registrada com sucesso! ID: " + novaMovimentacao.getId());
        return true;
    }
}