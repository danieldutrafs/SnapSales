package br.com.connect.factories;

import br.com.connect.models.Categoria;
import br.com.connect.models.Produto;

/**
 *
 * @author Daniel Dutra
 */
public class ProdutoFactory {

    private static long contadorId = 1; // Contador estático

    public static Produto criarProduto(String nome, int qtd, double preco, Categoria cat) {
        // O objeto já nasce com ID gerado
        return new Produto(contadorId++, nome, qtd, preco, cat);
    }
}

