
package br.com.wns.projetoloja.dao.interfaces;

import br.com.wns.projetoloja.model.Cliente;
import java.util.List;

public interface IClienteDao {
    
     public void inserir(Cliente cliente)throws Exception;
     
     public void excluir(Cliente cliente)throws Exception;
     
     public Cliente buscar(long id)throws Exception;
     
     public void alterar(Cliente cliente)throws Exception;
     
     public List<Cliente> listar()  throws Exception;
    
}
