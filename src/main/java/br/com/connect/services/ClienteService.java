package br.com.connect.services;

import br.com.connect.models.Clientes;
import br.com.connect.repositories.ClienteRepository;
import br.com.connect.utils.ValidadorUtil;

/**
 *
 * @author Daniel Dutra
 */
public class ClienteService {
    
    private ClienteRepository clienteRepository = new ClienteRepository();
    private static long geradorId = 1;

    public boolean salvar(Clientes cliente) {
        
        if (!ValidadorUtil.validarCampoTexto(cliente.getNomeCliente())) {
            System.out.println("Erro: O nome do cliente não pode estar vazio.");
            return false;
        }
        
        String telefoneLimpo = limparTelefone(cliente.getTelefone());
        if (telefoneLimpo.length() != 10 && telefoneLimpo.length() != 11) {
            System.out.println("Erro: Telefone inválido. Deve conter 10 ou 11 dígitos numéricos.");
            return false;
        }
        cliente.setId(geradorId++);
        cliente.setTelefone(telefoneLimpo);        
        clienteRepository.salvarCliente(cliente);
        
        System.out.println("Cliente cadastrado com sucesso! ID: " + cliente.getId());
        return true;
    }

    private String limparTelefone(String telefone) {
        if (telefone == null) {
            return "";
        }
        return telefone.replaceAll("\\D", "");
    }
}