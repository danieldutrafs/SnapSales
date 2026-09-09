package br.com.connect;

import br.com.connect.factories.ProdutoFactory;
import br.com.connect.models.Categoria;
import br.com.connect.models.Clientes;
import br.com.connect.models.Encomenda;
import br.com.connect.models.Entrega;
import br.com.connect.models.ItemEncomenda;
import br.com.connect.models.MovimentacaoEstoque;
import br.com.connect.models.Pagamentos;
import br.com.connect.models.Produto;
import br.com.connect.repositories.CategoriaRepository;
import br.com.connect.repositories.ProdutoRepository;
import br.com.connect.services.ClienteService;
import br.com.connect.services.EncomendaService;
import br.com.connect.services.MovimentacaoService;
import br.com.connect.services.ProdutoService;
import java.time.LocalDate;
import java.util.ArrayList;
import java.util.List;
import java.util.Scanner;

/**
 *
 * @author Daniel Dutra
 */
public class SaborEmOrdemConnect {

    public static void main(String[] args) {

        ClienteService clienteService = new ClienteService();
        ProdutoService produtoService = new ProdutoService();
        EncomendaService encomendaService = new EncomendaService();
        MovimentacaoService moviService = new MovimentacaoService();

        System.out.println("=====Teste Cliente=====");
        Clientes cliente = new Clientes(9L, "Maria", "5199999999");
        clienteService.salvar(cliente);

        System.out.println("=====Teste Categoria=====");
        Categoria categoria = new Categoria(1L, "Fritos");
        CategoriaRepository repo = new CategoriaRepository();
        repo.salvarCategoria(categoria);

        System.out.println("=====Teste Produto=====");
        Produto produto = new Produto(0L, "Coxinha", 100, 1.60, categoria);
        produtoService.salvar(produto);

        System.out.println("=====Teste Movimentação=====");
        MovimentacaoEstoque moviEstoque = new MovimentacaoEstoque(0L, produto, 10, true);
        moviService.salvar(moviEstoque);

        System.out.println("\n--- Testando Encomenda ---");

        ItemEncomenda item = new ItemEncomenda(1L, 2, 50.0);
        item.setProduto(produto);

        List<ItemEncomenda> itens = new ArrayList<>();
        itens.add(item);

        Pagamentos pagamento = new Pagamentos(1L, "Cartão de Crédito");
        Entrega entrega = new Entrega(1L, "Rua Exemplo, 123", "Maria Silva", 15.0, LocalDate.now().plusDays(2));

        Encomenda novaEncomendaTeste = new Encomenda(0L, "Em preparo", cliente, 4.0, 115.0, false);
        novaEncomendaTeste.setItens(itens);
        novaEncomendaTeste.setPagamento(pagamento);
        novaEncomendaTeste.setEntrega(entrega);

        boolean sucesso = encomendaService.salvar(novaEncomendaTeste);

        if (sucesso) {
            System.out.println("\n=== TESTE FINALIZADO COM SUCESSO! ===");
        } else {
            System.out.println("\n=== FALHA NO TESTE DA ENCOMENDA ===");
        }
        
        //Resultados
        
        System.out.println("\n====Cliente====");   
        System.out.println("Nome: " + cliente.getNomeCliente());
        System.out.println("Telefone: " + cliente.getTelefone());
        
        System.out.println("\n====Produto====");
        System.out.println("Nome: " + produto.getNomeProduto());
        System.out.println("Quantidade: " + produto.getQuantidade());
        System.out.println("Valor: " + produto.getPreco());
        System.out.println("Categoria: " + produto.getCategoria());
        
        System.out.println("\n====Movimentação de Estoque====");
        System.out.println("ID: " + moviEstoque.getId());
        System.out.println("Produto: " + moviEstoque.getProduto().getNomeProduto());
        System.out.println("Quantidade movimentada: " + moviEstoque.getQuantidade());
        System.out.println("É entrada?: " + moviEstoque.isTipoMovimentacao());
        
        System.out.println("\n====Movimentação de Estoque====");
        System.out.println("ID: " + moviEstoque.getId());
        System.out.println("Produto: " + moviEstoque.getProduto().getNomeProduto());
        System.out.println("Quantidade movimentada: " + moviEstoque.getQuantidade());
        System.out.println("É entrada?: " + moviEstoque.isTipoMovimentacao());
        
        System.out.println("\n====Encomenda====");
        System.out.println("ID: " + novaEncomendaTeste.getId());
        System.out.println("Cliente: " + novaEncomendaTeste.getCliente().getNomeCliente());
        System.out.println("Status: " + novaEncomendaTeste.getStatus());
        System.out.println("Valor Total: R$ " + novaEncomendaTeste.getValorTotal());
        System.out.println("Valor de Entrada: R$ " + novaEncomendaTeste.getValorEntrada());
        System.out.println("Forma de Pagamento: " + novaEncomendaTeste.getPagamento().getFormaPagamento());
        
        System.out.println("\n---Itens da Encomenda---");
        for (ItemEncomenda itemEncomenda : novaEncomendaTeste.getItens()) {
            System.out.println("- Produto: " + itemEncomenda.getProduto().getNomeProduto() 
                             + " | Qtd: " + itemEncomenda.getQuantidadeItem() 
                             + " | Preço un: R$ " + itemEncomenda.getPrecoMomento());
        }

        System.out.println("\n---Tipo de Recebimento---");
        if (novaEncomendaTeste.isRetirada()) {
            System.out.println("Tipo: Retirada no Local");
        } else {
            System.out.println("Tipo: Entrega");
            System.out.println("Endereço: " + novaEncomendaTeste.getEntrega().getEndereco());
            System.out.println("Recebedor: " + novaEncomendaTeste.getEntrega().getDestinatario());
            System.out.println("Taxa de Frete: R$ " + novaEncomendaTeste.getEntrega().getFrete());
            System.out.println("Data Prevista: " + novaEncomendaTeste.getEntrega().getDataEntrega());
        } 
    }
}
