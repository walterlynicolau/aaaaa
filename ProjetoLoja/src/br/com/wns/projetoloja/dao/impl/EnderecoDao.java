
package br.com.wns.projetoloja.dao.impl;

import br.com.wns.projetoloja.dao.factory.DaoFactory;
import br.com.wns.projetoloja.dao.interfaces.ICidadeDao;
import br.com.wns.projetoloja.dao.interfaces.IEnderecoDao;
import br.com.wns.projetoloja.model.Endereco;
import br.com.wns.projetoloja.util.ConnectionFactory;
import br.com.wns.projetoloja.util.PropertiesUtil;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.Statement;
import java.util.ArrayList;
import java.util.List;

public class EnderecoDao implements IEnderecoDao{
    private Connection connection;
    private ICidadeDao iDaoCidade = null;
    public long inserir (Endereco endereco) throws Exception{
        try{
            //inserção da cidade no BD cidades a partir de endereco
            //daoCidade = new CidadeDao();
           // long idCidade = daoCidade.inserir(endereco.getCidade());
 
            connection = ConnectionFactory.getConnection();
            connection.setAutoCommit(false);
            String sqlEndereco = PropertiesUtil.getConfValue(PropertiesUtil.COMANDO_INSERIR_ENDERECO);
            
            PreparedStatement psEndereco = connection.prepareStatement(sqlEndereco, Statement.RETURN_GENERATED_KEYS);
            psEndereco.setString(1, endereco.getRua());
            psEndereco.setString(2, endereco.getNumero());
            psEndereco.setString(3, endereco.getBairro());
            psEndereco.setString(4, endereco.getCep());
            psEndereco.setLong(5, endereco.getCidade().getId());
            psEndereco.executeUpdate();
            
            ResultSet rsEndereco = psEndereco.getGeneratedKeys();
            long id = 0;
            if(rsEndereco.next()){
                id = rsEndereco.getLong(1);
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
    
    public void alterar (Endereco endereco) throws Exception{
        try{
            connection = ConnectionFactory.getConnection();
            connection.setAutoCommit(false);
            String sqlEndereco = PropertiesUtil.getConfValue(PropertiesUtil.COMANDO_ALTERAR_ENDERECO);
            PreparedStatement psEndereco = connection.prepareStatement(sqlEndereco);
            psEndereco.setString(1, endereco.getRua());
            psEndereco.setString(2, endereco.getNumero());
            psEndereco.setString(3, endereco.getBairro());
            psEndereco.setString(4, endereco.getCep());
            psEndereco.setLong(5, endereco.getCidade().getId());
            psEndereco.setLong(6, endereco.getId());
            psEndereco.executeUpdate();
            
            connection.commit();
            connection.close();
        }catch(Exception e){
            connection.rollback();
           e.printStackTrace();
           throw new Exception(PropertiesUtil.getStringsValue(PropertiesUtil.MSG_ERRO_ALTERAR));
        } 
    }
    
    public void excluir (Endereco endereco) throws Exception{
        try{
            
            connection = ConnectionFactory.getConnection();
            connection.setAutoCommit(false);
            String sqlEndereco = PropertiesUtil.getConfValue(PropertiesUtil.COMANDO_DELETAR_ENDERECO);
            PreparedStatement psEndereco = connection.prepareStatement(sqlEndereco);
            psEndereco.setLong(1, endereco.getId());
            psEndereco.executeUpdate();
            connection.commit();
            connection.close();
            
        }catch(Exception e){
            connection.rollback();
            e.printStackTrace();
            throw new Exception(PropertiesUtil.getStringsValue(PropertiesUtil.MSG_ERRO_EXCLUIR));
        }
    }
    
    public Endereco buscar(long id) throws Exception{
        try{
            Endereco endereco = null;
            connection = ConnectionFactory.getConnection();
            connection.setAutoCommit(false);
            String sqlEndereco = PropertiesUtil.getConfValue(PropertiesUtil.COMANDO_BUSCAR_ENDERECO);
            PreparedStatement psEndereco = connection.prepareStatement(sqlEndereco);
            psEndereco.setLong(1, id);
            ResultSet rsEndereco = psEndereco.executeQuery();
            if(rsEndereco.next()){
                endereco = new Endereco();
                iDaoCidade = DaoFactory.createCidadeDao();
                endereco.setId(rsEndereco.getLong("id"));
                endereco.setRua(rsEndereco.getString("rua"));
                endereco.setNumero(rsEndereco.getString("numero"));
                endereco.setBairro(rsEndereco.getString("bairro"));
                endereco.setCep(rsEndereco.getString("cep"));
                endereco.setCidade(iDaoCidade.buscar(rsEndereco.getInt("id_cidade")));
            }
            connection.commit();
            connection.close();
            return endereco;
        }catch(Exception e){
            connection.rollback();
            e.printStackTrace();
            throw new Exception(PropertiesUtil.getStringsValue(PropertiesUtil.MSG_ERRO_BUSCAR));
        }
    }
    
    public List<Endereco> listar() throws Exception{
         List<Endereco> enderecos = null;
        try{
            Endereco endereco = null;
            enderecos = new ArrayList<>();
            connection = ConnectionFactory.getConnection();
            connection.setAutoCommit(false);
            String sqlEndereco = PropertiesUtil.getConfValue(PropertiesUtil.COMANDO_LISTAR_ENDERECO);
            PreparedStatement psEndereco = connection.prepareStatement(sqlEndereco);
            ResultSet rsEndereco = psEndereco.executeQuery();
            while(rsEndereco.next()){
                endereco = new Endereco();
                iDaoCidade = DaoFactory.createCidadeDao();
                endereco.setId(rsEndereco.getLong("id"));
                endereco.setRua(rsEndereco.getString("rua"));
                endereco.setNumero(rsEndereco.getString("numero"));
                endereco.setBairro(rsEndereco.getString("bairro"));
                endereco.setCep(rsEndereco.getString("cep"));
                endereco.setCidade(iDaoCidade.buscar(rsEndereco.getInt("id_cidade")));
                enderecos.add(endereco);
            }
            connection.commit();
            connection.close();
            return enderecos;
        }catch(Exception e){
            connection.rollback();
            e.printStackTrace();
            throw new Exception(PropertiesUtil.getStringsValue(PropertiesUtil.MSG_ERRO_LISTAR));
        }
    }
}
