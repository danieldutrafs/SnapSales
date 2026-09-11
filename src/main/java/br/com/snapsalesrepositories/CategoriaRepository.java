package br.com.snapsalesrepositories;

import br.com.snapsales.models.Categoria;
import java.util.ArrayList;
import java.util.List;

/**
 *
 * @author auxiliar.tirs
 */
public class CategoriaRepository {
    
    
    private List<Categoria> listaCategoria = new ArrayList<>();
            
    public void salvarCategoria(Categoria categoria){
        listaCategoria.add(categoria);
        System.out.println("categoria criada");
    }
    
     public void removerCategoria(Long id) {
        listaCategoria.removeIf(categoria -> categoria.getId() == id);
        System.out.println("Encomenda removida");
    }
     
     public List<Categoria> listarCtageoria(){
         return listaCategoria;
     }
     
     public Categoria buscarId(long id){
         for(Categoria categoria : listaCategoria){
             if(categoria.getId() == id){
                 return categoria;
             }
         }
         return null;
     }
     
     public boolean editarCategoria(Long id, Categoria categoriaAtualizada){
         Categoria categoriaAntiga =  buscarId(id);
         
         if(categoriaAntiga != null){
             categoriaAntiga.setNomeCategoria(categoriaAtualizada.getNomeCategoria());
             return true;
         }
         return false;
     }
}
