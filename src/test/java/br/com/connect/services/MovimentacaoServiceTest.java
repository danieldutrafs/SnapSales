package br.com.connect.services;

import br.com.connect.models.MovimentacaoEstoque;
import br.com.connect.models.Produto;
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
public class MovimentacaoServiceTest {
    
    private MovimentacaoService servico;
    private MovimentacaoEstoque movi;
    private Produto produto;
    
    @BeforeEach
    public void setUp() {
        servico = new MovimentacaoService();
        movi = new MovimentacaoEstoque();
        produto = new Produto();
        
        //Produto apra todos os testes
        produto.setNomeProduto("Coxinha");
        produto.setPreco(3.00);
        movi.setProduto(produto);
    }
    
    @Test
    public void deveAprovaMovimentacao(){        
        movi.setProduto(produto);
        movi.setQuantidade(50);
        movi.setTipoMovimentacao(true);
        
        boolean resultado = servico.salvar(movi);
        Assertions.assertTrue(resultado, "A movimentação deveria ser salva pois os dados são válidos");
    }
    
    @Test
    public void deveBarrarMovimentacaoEntradaQuantidadeZero(){
       movi.setProduto(produto);
       movi.setQuantidade(0);
       movi.setTipoMovimentacao(true);
       
       boolean resultado = servico.salvar(movi);
       Assertions.assertFalse(resultado, "A movimentação deve ser barrada pois a quantidade é zero");
    }
    
    @Test
    public void deveBarrarMovimentacaoEntradaQuantidadeNegativa(){
        movi.setProduto(produto);
        movi.setQuantidade(-25);
        movi.setTipoMovimentacao(true);
        
        boolean resultado = servico.salvar(movi);
        Assertions.assertFalse(resultado, "A movimentacao deve ser barrada pois a quantidade está negativa");
    }
    
    @Test
    public void deveBarrarMovimentacaoSaidaQuantidadeZero(){
       movi.setProduto(produto);
       movi.setQuantidade(0);
       movi.setTipoMovimentacao(false);
       
       boolean resultado = servico.salvar(movi);
       Assertions.assertFalse(resultado, "A movimentação deve ser barrada pois a quantidade está zero");
    }
    
    @Test
    public void deveBarrarMovimentacaoSaidaQuantidadeNegativa(){
        movi.setProduto(produto);
        movi.setQuantidade(-25);
        movi.setTipoMovimentacao(false);
        
        boolean resultado = servico.salvar(movi);
        Assertions.assertFalse(resultado, "A movimentacao deve ser barrada pois a quantidade está negativa");
    }
    
    @Test
    public void deveBarrarMovimentacaoProdutoNulo(){
        movi.setProduto(null);
        movi.setQuantidade(10);
        movi.setTipoMovimentacao(true);
        
        boolean resultado = servico.salvar(movi);
        Assertions.assertFalse(resultado, "A movimentacao deve ser barrada pois o produto está nulo");
    }
} 