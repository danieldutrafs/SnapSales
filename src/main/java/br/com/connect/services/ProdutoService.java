package br.com.connect.services;

import br.com.connect.factories.ProdutoFactory;
import br.com.connect.models.Categoria;
import br.com.connect.models.Produto;
import br.com.connect.repositories.ProdutoRepository;
import br.com.connect.utils.ValidadorUtil;

/**
 *
 * @author Daniel Dutra
 */
public class ProdutoService {

    private ProdutoRepository repository = new ProdutoRepository();
    private static long geradorId = 1;

    public boolean salvar(Produto produto) {

        if (!ValidadorUtil.validarCampoTexto(produto.getNomeProduto())
            || !ValidadorUtil.validarCampoInt(produto.getQuantidade())
            || !ValidadorUtil.validarCampoDouble(produto.getPreco())) {
            System.out.println("Erro: dados do produtos inválidos");
            return false;
        }

        if (produto.getCategoria() == null || !ValidadorUtil.validarCampoTexto(produto.getCategoria().getNomeCategoria())) {
            System.out.println("Erro: O produto precisa estar associado a uma categoria!");
            return false;
        }
        produto.setId(geradorId++);
        repository.salvarProduto(produto);
        System.out.println("Produto cadastrado junto a categoria!");
        return true;
    }
}
