package br.com.snapsales.factories;

import br.com.snapsales.factories.EncomendaFactory;
import br.com.snapsales.models.Clientes;
import br.com.snapsales.models.Encomenda;
import br.com.snapsales.models.Entrega;
import br.com.snapsales.models.ItemEncomenda;
import br.com.snapsales.models.Pagamentos;
import br.com.snapsales.services.EncomendaService;
import java.time.LocalDate;
import java.util.ArrayList;
import java.util.List;
import org.junit.jupiter.api.AfterEach;
import org.junit.jupiter.api.AfterAll;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.BeforeAll;
import org.junit.jupiter.api.Test;
import static org.junit.jupiter.api.Assertions.*;

/**
 *
 * @author Daniel Dutra
 */
public class EncomendaFactoryTest {

    private Encomenda encomenda = new Encomenda();
    private EncomendaService servico = new EncomendaService();
    private Clientes cliente = new Clientes();
    private Pagamentos pagamento = new Pagamentos();
    private List<ItemEncomenda> itens;

    public EncomendaFactoryTest() {
    }

    @BeforeEach
    public void setUp() {
        encomenda = new Encomenda();
        servico = new EncomendaService();
        cliente = new Clientes();
        pagamento = new Pagamentos(1L, "Dinheiro");
        itens = new ArrayList<>();

        itens.add(new ItemEncomenda(1L, 2, 10.0));

        encomenda.setCliente(cliente);
        encomenda.setPagamento(pagamento);
        encomenda.setItens(itens);
        encomenda.setValorEntrada(5.0);
        encomenda.setRetirada(true);
    }
    
    @Test
    public void deveSalvarEncomendaComSucesso() {
        boolean resultado = servico.salvar(encomenda);
        assertTrue(resultado);
    }

    @Test
    public void deveBarraEncomendaSemCliente() {
        encomenda.setCliente(null);
        boolean resultado = servico.salvar(encomenda);
        assertFalse(resultado);
    }

    @Test
    public void deveBarraValorEntradaNegativo() {
        encomenda.setValorEntrada(-10.0);
        boolean resultado = servico.salvar(encomenda);
        assertFalse(resultado);
    }

    @Test
    public void deveBarraEncomendaSemItens() {
        encomenda.setItens(new ArrayList<>());
        boolean resultado = servico.salvar(encomenda);
        assertFalse(resultado);
    }

    @Test
    public void deveBarraItemComQuantidadeOuPrecoInvalido() {
        itens.get(0).setQuantidadeItem(0);
        boolean resultado = servico.salvar(encomenda);
        assertFalse(resultado);
    }

    @Test
    public void deveBarraPagamentoNuloOuInvalido() {
        encomenda.setPagamento(null);
        boolean resultado = servico.salvar(encomenda);
        assertFalse(resultado);
    }

    @Test
    public void deveBarraEntregaIncompletaQuandoNaoForRetirada() {
        encomenda.setRetirada(false);
        encomenda.setEntrega(null);
        boolean resultado = servico.salvar(encomenda);
        assertFalse(resultado);
    }

    @Test
    public void deveSalvarEntregaCompletaComSucesso() {
        encomenda.setRetirada(false);
        Entrega entrega = new Entrega(1L, "Rua A", "Destinatario", 10.0, LocalDate.now());
        encomenda.setEntrega(entrega);
        boolean resultado = servico.salvar(encomenda);
        assertTrue(resultado);
    }

    @Test
    public void deveBarraValorEntradaMaiorQueTotal() {
        encomenda.setValorEntrada(50.0);
        boolean resultado = servico.salvar(encomenda);
        assertFalse(resultado);
    }
}

