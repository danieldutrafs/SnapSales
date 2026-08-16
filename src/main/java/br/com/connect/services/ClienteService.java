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

    public boolean salvar(String nomeCliente, String telefone) {
        
        if (!ValidadorUtil.validarCampoTexto(nomeCliente)) {
            System.out.println("Erro: O nome do cliente não pode estar vazio.");
            return false;
        }
        
        String telefoneLimpo = limparTelefone(telefone);
        if (telefoneLimpo.length() != 10 && telefoneLimpo.length() != 11) {
            System.out.println("Erro: Telefone inválido. Deve conter 10 ou 11 dígitos numéricos.");
            return false;
        }
        
        Clientes novoCliente = new Clientes(geradorId++, nomeCliente.trim(), telefoneLimpo);
        
        clienteRepository.salvarCliente(novoCliente);
        
        System.out.println("Cliente cadastrado com sucesso! ID: " + novoCliente.getId());
        return true;
    }

    private String limparTelefone(String telefone) {
        if (telefone == null) {
            return "";
        }
        return telefone.replaceAll("\\D", "");
    }
}