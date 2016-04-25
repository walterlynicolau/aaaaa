
package br.com.wns.projetoloja.dao.impl;

import br.com.wns.projetoloja.dao.interfaces.IEstadoDao;
import br.com.wns.projetoloja.model.Estado;
import br.com.wns.projetoloja.util.ConnectionFactory;
import br.com.wns.projetoloja.util.PropertiesUtil;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.Statement;
import java.util.ArrayList;
import java.util.List;

public class EstadoDao implements IEstadoDao{
    Connection connection;
    
    public void inserir(Estado estado) throws Exception{
        try{
            connection = ConnectionFactory.getConnection();
            connection.setAutoCommit(false);
            
            String sqlEstado = PropertiesUtil.getConfValue(PropertiesUtil.COMANDO_INSERIR_ESTADO);
            
            PreparedStatement ps = connection.prepareStatement(sqlEstado, Statement.RETURN_GENERATED_KEYS);
            
            ps.setString(1, estado.getNome());
            ps.setString(2, estado.getUf());
            ps.executeUpdate();
            ResultSet rsEstado = ps.getGeneratedKeys();
            long id = 0;
            if (rsEstado.next()){
               id = rsEstado.getLong(1);
            }
            connection.commit();
            connection.close();
            //return id;
        }catch(Exception e){
             connection.rollback();
            e.printStackTrace();
            throw new Exception(PropertiesUtil.getStringsValue(PropertiesUtil.MSG_ERRO_SALVAR));   
        }   
    }
    
    public void alterar (Estado estado) throws Exception{
        try{
            connection = ConnectionFactory.getConnection();
            connection.setAutoCommit(false);
            String sqlEstado = PropertiesUtil.getConfValue(PropertiesUtil.COMANDO_ALTERAR_ESTADO);
            PreparedStatement psEstado = connection.prepareStatement(sqlEstado);
            psEstado.setString(1, estado.getNome());
            psEstado.setString(2, estado.getUf());
            psEstado.setLong(3, estado.getId());
            psEstado.executeUpdate();
            connection.commit();
            connection.close();
            
        }catch(Exception e){
            connection.rollback();
            e.printStackTrace();
            throw new Exception(PropertiesUtil.getStringsValue(PropertiesUtil.MSG_ERRO_ALTERAR));
        }
    }
    
    public void excluir (Estado estado) throws Exception{
        try{
            connection = ConnectionFactory.getConnection();
            connection.setAutoCommit(false);
            String sqlEstado = PropertiesUtil.getConfValue(PropertiesUtil.COMANDO_DELETAR_ESTADO);
            PreparedStatement psEstado = connection.prepareStatement(sqlEstado, Statement.RETURN_GENERATED_KEYS);
            psEstado.setLong(1, estado.getId());
            psEstado.executeUpdate();
            connection.commit();
            connection.close();
        }catch(Exception e){
            connection.rollback();
            e.printStackTrace();
            throw new Exception(PropertiesUtil.getStringsValue(PropertiesUtil.MSG_ERRO_EXCLUIR));
        }
    }
    
    public Estado buscar(long id) throws Exception{
        try{
            Estado estado = null;
            connection = ConnectionFactory.getConnection();
            connection.setAutoCommit(false);
            String sqlEstado = PropertiesUtil.getConfValue(PropertiesUtil.COMANDO_BUSCAR_ESTADO);
            PreparedStatement psEstado = connection.prepareStatement(sqlEstado);
            psEstado.setLong(1, id);
            ResultSet rs = psEstado.executeQuery();
            if(rs.next()){
                estado = new Estado();
                estado.setId(rs.getLong("id"));
                estado.setNome(rs.getString("nome"));
                estado.setUf(rs.getString("uf"));
            }
            connection.commit();
            connection.close();
            return estado;
        }catch(Exception e){
            connection.rollback();
            e.printStackTrace();
            throw new Exception(PropertiesUtil.getStringsValue(PropertiesUtil.MSG_ERRO_BUSCAR));
        }
    }
    
    public List<Estado> listar() throws Exception{
        List<Estado> estados = null;
        try{
            Estado estado = null;
            estados = new ArrayList<>();
            connection = ConnectionFactory.getConnection();
            connection.setAutoCommit(false);
            String sqlEstado = PropertiesUtil.getConfValue(PropertiesUtil.COMANDO_LISTAR_ESTADO);
            PreparedStatement psEstado = connection.prepareStatement(sqlEstado);
            ResultSet rsEstado = psEstado.executeQuery();
            while(rsEstado.next()){
                estado = new Estado();
                estado.setId(rsEstado.getLong("id"));
                estado.setNome(rsEstado.getString("nome"));
                estado.setUf(rsEstado.getString("uf"));
                estados.add(estado);
            }
            connection.commit();
            connection.close();
            return estados;
        }catch(Exception e){
            connection.rollback();
            e.printStackTrace();
            throw new Exception(PropertiesUtil.getStringsValue(PropertiesUtil.MSG_ERRO_LISTAR));
        }
    }
}

