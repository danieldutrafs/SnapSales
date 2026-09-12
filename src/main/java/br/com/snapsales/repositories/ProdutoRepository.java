package br.com.snapsales.repositories;

import br.com.snapsales.models.Produto;
import java.util.ArrayList;
import java.util.List;

/**
 *
 * @author Daniel Dutra
 */
public class ProdutoRepository {

    private List<Produto> listaProduto = new ArrayList<>();

    public void salvarProduto(Produto produto) {
        listaProduto.add(produto);
        System.out.println("Produto criado");
    }
    //remove produto por id
    public void removerProduto(Long id) {
        listaProduto.removeIf(produto -> produto.getId() == id);
        System.out.println("Produto removido");
    }

    public List<Produto> listarProdutos() {
        return listaProduto;
    }

    // Método auxiliar de buscar por id
    public Produto buscarId(long id) {
        for (Produto produto : listaProduto) {
            if (produto.getId() == id) {
                return produto;
            }
        }
        return null;
    }

    public boolean editarProduto(Long id, Produto produtoAtualizado) {
        Produto produtoAntigo = buscarId(id); //busca produto

        if (produtoAntigo != null) {
            //Atualiza os dados
            produtoAntigo.setNomeProduto(produtoAtualizado.getNomeProduto());
            produtoAntigo.setQuantidade(produtoAtualizado.getQuantidade());
            produtoAntigo.setPreco(produtoAtualizado.getPreco());
            produtoAntigo.setCategoria(produtoAtualizado.getCategoria());
            return true; // edição realizada
        }
        return false; // produto mão encontrado
    }

}
