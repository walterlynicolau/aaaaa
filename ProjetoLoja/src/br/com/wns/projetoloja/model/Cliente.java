
package br.com.wns.projetoloja.model;

public class Cliente extends Pessoa  {
    
    private long idClente;
    
    public Cliente (String cpf, String nome, String telefone){ 

    }

    public Cliente() {
           
    }

    public long getIdClente() {
        return idClente;
    }

    public void setIdClente(long idClente) {
        this.idClente = idClente;
    }
}