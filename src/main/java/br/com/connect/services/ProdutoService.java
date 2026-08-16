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

    public boolean salvar(String nome, int quantidade, double preco, Categoria categoria) {

        if (!ValidadorUtil.validarCampoTexto(nome)
            || !ValidadorUtil.validarCampoInt(quantidade)
            || !ValidadorUtil.validarCampoDouble(preco)) {
            System.out.println("Erro: dados do produtos inválidos");
            return false;
        }

        if (categoria == null || !ValidadorUtil.validarCampoTexto(categoria.getNomeCategoria())) {
            System.out.println("Erro: O produto precisa estar associado a uma categoria!");
            return false;
        }
        Produto produto = ProdutoFactory.criarProduto(nome, quantidade, preco, categoria);
        
        repository.salvarProduto(produto);
        System.out.println("Produto cadastrado junto a categoria!");
        return true;
    }
}
