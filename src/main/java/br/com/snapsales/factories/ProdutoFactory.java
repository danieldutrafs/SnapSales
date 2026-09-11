package br.com.snapsales.factories;

import br.com.snapsales.models.Categoria;
import br.com.snapsales.models.Produto;

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

