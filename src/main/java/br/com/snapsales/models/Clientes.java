package br.com.snapsales.models;

/**
 *
 * @author auxiliar.tirs
 */
public class Clientes {
    private long id;
    private String nomeCliente;
    private String telefone;

    public Clientes(long id, String nomeCliente, String telefone) {
        this.id = id;
        this.nomeCliente = nomeCliente;
        this.telefone = telefone;
    }
    
    public Clientes(){
    }

    public long getId() {
        return id;
    }

    public void setId(long id) {
        this.id = id;
    }

    public String getNomeCliente() {
        return nomeCliente;
    }

    public void setNomeCliente(String nomeCliente) {
        this.nomeCliente = nomeCliente;
    }

    public String getTelefone() {
        return telefone;
    }

    public void setTelefone(String telefone) {
        this.telefone = telefone;
    }
}
