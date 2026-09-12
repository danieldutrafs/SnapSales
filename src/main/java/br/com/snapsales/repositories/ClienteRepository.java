package br.com.snapsales.repositories;

import br.com.snapsales.models.Clientes;
import java.util.ArrayList;
import java.util.List;

/**
 *
 * @author Daniel Dutra
 */
public class ClienteRepository {

    private List<Clientes> listaCliente = new ArrayList<>();

    public void salvarCliente(Clientes cliente) {
        listaCliente.add(cliente);
        System.out.println("Cliente Salvo");
    }

    //remove cliente por id
    public void removerCliente(Long id) {
        listaCliente.removeIf(cliente -> cliente.getId() == id);
        System.out.println("Cliente removido");
    }
    
    public List<Clientes> listarTodos(){
        return listaCliente;
    }
    
    // método auxilixar de busca
    public Clientes buscarId(long id){
        for(Clientes cliente : listaCliente){
            if(cliente.getId() == id){
                return cliente;
            }
        }
        return null;
    }
    
      public boolean editarCliente(Long id, Clientes clienteAtualizado) {
        Clientes clienteAntigo = buscarId(id); //busca cleinte

        if (clienteAntigo != null) {
            //Atualiza os dados
            clienteAntigo.setNomeCliente(clienteAtualizado.getNomeCliente());
            clienteAntigo.setTelefone(clienteAtualizado.getTelefone());
          
            return true; // edição realizada
        }
        return false; // cliente mão encontrado
    }
}
