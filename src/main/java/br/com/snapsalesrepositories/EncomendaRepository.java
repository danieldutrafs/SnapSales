package br.com.snapsalesrepositories;

import br.com.snapsales.models.Encomenda;
import java.util.ArrayList;
import java.util.List;

/**
 *
 * @author auxiliar.tirs
 */
public class EncomendaRepository {
   
    private List<Encomenda> listaEncomenda = new ArrayList<>();
    
    public void salvarEncomenda(Encomenda encomenda){
        listaEncomenda.add(encomenda);
        System.out.println("Encomenda salva");
    }
    
    public void removerEncomenda(Long id){
        listaEncomenda.removeIf(encomenda -> encomenda.getId() == id);
        System.out.println("Encomenda removida");
    }
    
    public List<Encomenda> listarEcomendas(){
        return listaEncomenda;
    }
    
    //metodo auxiliar de buscar por id
    public Encomenda buscarId(long id){
        for (Encomenda encomenda : listaEncomenda) {
            if (encomenda.getId() == id) {
                return encomenda;
            }
        }
        return null;
    }
    
    public boolean editarEncomenda(Long id, Encomenda encomendaAtualizada) {
        Encomenda encomendaAntiga = buscarId(id); //busca enocmenda

        if (encomendaAntiga != null) {
            //Atualiza os dados
            encomendaAntiga.setCliente(encomendaAtualizada.getCliente());
            encomendaAntiga.setValorEntrada(encomendaAtualizada.getValorEntrada());
            encomendaAntiga.setValorTotal(encomendaAtualizada.getValorTotal());
            encomendaAntiga.setRetirada(encomendaAtualizada.isRetirada());
            encomendaAntiga.setEntrega(encomendaAtualizada.getEntrega());
            encomendaAntiga.setPagamento(encomendaAtualizada.getPagamento());
            encomendaAntiga.setItens(encomendaAtualizada.getItens());
            return true; // edição realizada
        }
        return false; // encomenda mão encontrado
    }
}
