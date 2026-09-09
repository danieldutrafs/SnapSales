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

    public boolean salvar(MovimentacaoEstoque movi) {
        
        if (movi.getProduto()  == null) {
            System.out.println("Erro: A movimentação precisa estar associada a um produto.");
            return false;
        }

        if (!ValidadorUtil.validarCampoInt(movi.getQuantidade())) {
            System.out.println("Erro: A quantidade da movimentação deve ser maior que zero.");
            return false;
        }

        MovimentacaoEstoque novaMovimentacao = new MovimentacaoEstoque(
                contadorIdMovimentacao++,
                movi.getProduto(),
                movi.getQuantidade(),
                movi.isTipoMovimentacao()
        );

        movimentacaoRepository.salvarMovi(novaMovimentacao);
        System.out.println("Movimentação registrada com sucesso! ID: " + novaMovimentacao.getId());
        return true;
    }
}