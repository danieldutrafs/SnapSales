package br.com.snapsalesrepositories;

import br.com.snapsales.models.MovimentacaoEstoque;
import java.util.ArrayList;
import java.util.List;

/**
 *
 * @author auxiliar.tirs
 */
public class MovimentacaoRepository {

    private List<MovimentacaoEstoque> listaMovi = new ArrayList<>();

    public void salvarMovi(MovimentacaoEstoque movi) {
        listaMovi.add(movi);
        System.out.println("Movimentacao salva");
    }

    public void removerMovi(Long id) {
        listaMovi.removeIf(movi -> movi.getId() == id);
        System.out.println("Encomenda removida");
    }

    public List<MovimentacaoEstoque> listarMovi() {
        return listaMovi;
    }

    public MovimentacaoEstoque buscarId(long id) {
        for (MovimentacaoEstoque movi : listaMovi) {
            if(movi.getId() == id){
                return movi;
            }
        }
        return null;
    }
    
    public boolean editarMovi(Long id, MovimentacaoEstoque moviAtualizada){
        MovimentacaoEstoque moviAntiga = buscarId(id);
        
         if (moviAntiga != null) {
            //Atualiza os dados
            moviAntiga.setProduto(moviAtualizada.getProduto());
            moviAntiga.setQuantidade(moviAtualizada.getQuantidade());
            return true; // edição realizada
        }
        return false; // movimentação não encontrada
    }
}
