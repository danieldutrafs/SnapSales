package br.com.snapsales.services;

import br.com.snapsales.services.ClienteService;
import br.com.snapsales.models.Clientes;
import org.junit.jupiter.api.AfterEach;
import org.junit.jupiter.api.AfterAll;
import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.BeforeAll;
import org.junit.jupiter.api.Test;
import static org.junit.jupiter.api.Assertions.*;

/**
 *
 * @author Daniel Dutra
 */
public class ClienteServiceTest {

    private ClienteService servico;
    private Clientes cliente;

    @BeforeEach
    public void setUp() {
        servico = new ClienteService();
        cliente = new Clientes();
    }

    @Test
    public void deveAprovarCliente() {
        //Arrange
        cliente.setNomeCliente("Daniel");
        cliente.setTelefone("51999999999");
        //Act
        boolean resultado = servico.salvar(cliente);
        //Assert
        Assertions.assertTrue(resultado, "O cliente deveria ser salvo pois os dados são válidos");
    }

    @Test
    public void deveBarraNomeCliente() {
        //Arrange
        cliente.setNomeCliente("");
        cliente.setTelefone("5188888888");
        //Act
        boolean resultado = servico.salvar(cliente);

        //Assert
        Assertions.assertFalse(resultado, "O cleinte deve ser rejeitado pois o nome está vazio");
    }

    @Test
    public void deveBarrarTelefoneVazio() {
        //Arrange 
        cliente.setNomeCliente("Arthur");
        cliente.setTelefone("");
        // Act
        boolean resultado = servico.salvar(cliente);
        //Assert
        Assertions.assertFalse(resultado, "O cliente deve ser reiejitado pois o telefone está em branco");
    }

    @Test
    public void deveBarrarTelefoneIncompleto() {
        //Arrange
        cliente.setNomeCliente("Ëduardo");
        cliente.setTelefone("51999");
        //Act
        boolean resultado = servico.salvar(cliente);
        //Assert
        Assertions.assertFalse(resultado, "O cleinte deve ser rejeitado pois o telefone está incompleto");
    }

}
