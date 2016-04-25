
package br.com.wns.projetoloja.dao.impl;

import br.com.wns.projetoloja.dao.interfaces.IContatoDao;
import br.com.wns.projetoloja.model.Contato;
import br.com.wns.projetoloja.util.ConnectionFactory;
import br.com.wns.projetoloja.util.PropertiesUtil;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.Statement;
import java.util.ArrayList;
import java.util.List;


public class ContatoDao implements IContatoDao{
    private Connection connection;
    
    public long inserir(Contato contato) throws Exception{
        try{
            
          connection = ConnectionFactory.getConnection();
          connection.setAutoCommit(false);
          
          String sqlContato = PropertiesUtil.getConfValue(PropertiesUtil.COMANDO_INSERIR_CONTATO);
          PreparedStatement psContato = connection.prepareStatement(sqlContato, Statement.RETURN_GENERATED_KEYS);
          psContato.setString(1, contato.getTelefoneCelular());
          psContato.setString(2, contato.getTelefoneFixo());
          psContato.setString(3, contato.getEmail1());
          psContato.setString(4, contato.getEmail2());
          psContato.executeUpdate();
          
          ResultSet rsContato = psContato.getGeneratedKeys();
          long id = 0;
          if(rsContato.next()){
              id = rsContato.getLong(1);
          }
          connection.commit();
          connection.close();
          return id;
        }catch(Exception e){
            connection.rollback();
            e.printStackTrace();
            throw new Exception(PropertiesUtil.getStringsValue(PropertiesUtil.MSG_ERRO_SALVAR));
        }   
    }
    
    public void alterar (Contato contato) throws Exception{
        try{
            
            connection = ConnectionFactory.getConnection();
            connection.setAutoCommit(false);
            String sqlContato = PropertiesUtil.getConfValue(PropertiesUtil.COMANDO_ALTERAR_CONTATO);
            PreparedStatement psContato = connection.prepareStatement(sqlContato);
            psContato.setString(1, contato.getTelefoneCelular());
            psContato.setString(2, contato.getTelefoneFixo());
            psContato.setString(3, contato.getEmail1());
            psContato.setString(4, contato.getEmail2());
            psContato.setLong(5, contato.getId());
            psContato.executeUpdate();
            connection.commit();
            connection.close();
            
        }catch(Exception e){
          connection.rollback();
          e.printStackTrace();
          throw new Exception(PropertiesUtil.getStringsValue(PropertiesUtil.MSG_ERRO_ALTERAR));
        }
    }
    
    public void excluir (Contato contato) throws Exception{
        try{
            
            connection = ConnectionFactory.getConnection();
            connection.setAutoCommit(false);
            String sqlContato = PropertiesUtil.getConfValue(PropertiesUtil.COMANDO_DELETAR_CONTATO);
            PreparedStatement psContato = connection.prepareStatement(sqlContato);
            psContato.setLong(1, contato.getId());
            psContato.executeUpdate();
            connection.commit();
            connection.close();
            
        }catch(Exception e){
          connection.rollback();
          e.printStackTrace();
          throw new Exception(PropertiesUtil.getStringsValue(PropertiesUtil.MSG_ERRO_EXCLUIR));
        }
    }
    
    public Contato buscar(long id) throws Exception{
        try{
            Contato contato = null;
            connection = ConnectionFactory.getConnection();
            connection.setAutoCommit(false);
            String sqlContato = PropertiesUtil.getConfValue(PropertiesUtil.COMANDO_BUSCAR_CONTATO);
            PreparedStatement psEndereco = connection.prepareStatement(sqlContato);
            psEndereco.setLong(1, id);
            ResultSet rsEndereco = psEndereco.executeQuery();
            if(rsEndereco.next()){
                contato = new Contato();
                contato.setId(rsEndereco.getLong("id"));
                contato.setTelefoneCelular(rsEndereco.getString("telefone_celular"));
                contato.setTelefoneFixo(rsEndereco.getString("telefone_fixo"));
                contato.setEmail1(rsEndereco.getString("email_1"));
                contato.setEmail2(rsEndereco.getString("email_2"));
            }
            connection.commit();
            connection.close();
            return contato;
        }catch(Exception e){
            connection.rollback();
            e.printStackTrace();
            throw new Exception(PropertiesUtil.getStringsValue(PropertiesUtil.MSG_ERRO_BUSCAR));
        }
    }
    
    public List<Contato> listar() throws Exception{
        List<Contato> contatos = null;
        try{
            Contato contato = null;
            contatos = new ArrayList<>();
            connection = ConnectionFactory.getConnection();
            connection.setAutoCommit(false);
            String sqlContato = PropertiesUtil.getConfValue(PropertiesUtil.COMANDO_LISTAR_CONTATO);
            PreparedStatement psEndereco = connection.prepareStatement(sqlContato);
            ResultSet rsEndereco = psEndereco.executeQuery();
            while(rsEndereco.next()){
                contato = new Contato();
                contato.setId(rsEndereco.getLong("id"));
                contato.setTelefoneCelular(rsEndereco.getString("telefone_celular"));
                contato.setTelefoneFixo(rsEndereco.getString("telefone_fixo"));
                contato.setEmail1(rsEndereco.getString("email_1"));
                contato.setEmail2(rsEndereco.getString("email_2"));
                contatos.add(contato);
            }
            connection.commit();
            connection.close();
            return contatos;
        }catch(Exception e){
            connection.rollback();
            e.printStackTrace();
            throw new Exception(PropertiesUtil.getStringsValue(PropertiesUtil.MSG_ERRO_LISTAR));
        }
    }
        
}
