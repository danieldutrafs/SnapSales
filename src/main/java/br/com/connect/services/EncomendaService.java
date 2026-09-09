package br.com.connect.services;

import br.com.connect.models.Clientes;
import br.com.connect.models.Encomenda;
import br.com.connect.models.Entrega;
import br.com.connect.models.ItemEncomenda;
import br.com.connect.models.Pagamentos;
import br.com.connect.factories.EncomendaFactory;
import br.com.connect.repositories.EncomendaRepository;
import br.com.connect.utils.ValidadorUtil;
import java.util.List;

/**
 * @author Daniel Dutra
 */
public class EncomendaService {

    private EncomendaRepository encomendaRepository = new EncomendaRepository();
    private static long contadorIdEncomenda = 1;

    public boolean salvar(Encomenda encomenda) {

        if (encomenda.getCliente() == null) {
            System.out.println("Erro: A encomenda precisa estar vinculada a um cliente.");
            return false;
        }

        if (encomenda.getValorEntrada() < 0) {
            System.out.println("Erro: O valor de entrada não pode ser negativo.");
            return false;
        }

        if (!ValidadorUtil.validarLista(encomenda.getItens())) {
            System.out.println("Erro: A encomenda deve conter pelo menos um item.");
            return false;
        }

        for (ItemEncomenda item : encomenda.getItens()) {
            if (item == null || !ValidadorUtil.validarCampoInt(item.getQuantidadeItem()) || !ValidadorUtil.validarCampoDouble(item.getPrecoMomento())) {
                System.out.println("Erro: Há itens na encomenda com quantidade ou preço inválidos.");
                return false;
            }
        }

        if (encomenda.getPagamento() == null || !ValidadorUtil.validarCampoTexto(encomenda.getPagamento().getFormaPagamento())) {
            System.out.println("Erro: A forma de pagamento é obrigatória.");
            return false;
        }

        if (!encomenda.isRetirada()) {
            if (encomenda.getEntrega() == null || !ValidadorUtil.validarCampoTexto(encomenda.getEntrega().getEndereco()) || !ValidadorUtil.validarCampoTexto(encomenda.getEntrega().getDestinatario())) {
                System.out.println("Erro: Endereço e destinatário são obrigatórios para encomendas do tipo entrega.");
                return false;
            }
            if (encomenda.getEntrega().getFrete() < 0) {
                System.out.println("Erro: O valor do frete não pode ser negativo.");
                return false;
            }
            if (encomenda.getEntrega().getDataEntrega() == null) {
                System.out.println("Erro: A data de entrega é obrigatória.");
                return false;
            }
        }

        Encomenda novaEncomenda = EncomendaFactory.criarEncomenda(
                contadorIdEncomenda++,
                encomenda.getCliente(),
                "Em preparo",
                encomenda.getValorEntrada(),
                encomenda.isRetirada(),
                encomenda.getItens(),
                encomenda.getPagamento(),
                encomenda.getEntrega()
        );

        if (encomenda.getValorEntrada() > novaEncomenda.getValorTotal()) {
            System.out.println("Erro: O valor de entrada não pode ser maior que o valor total da encomenda.");
            return false;
        }

        encomendaRepository.salvarEncomenda(novaEncomenda);
        System.out.println("Encomenda cadastrada com sucesso! ID: " + novaEncomenda.getId());
        return true;
    }
}