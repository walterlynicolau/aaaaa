/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package br.com.wns.projetoloja.dao.impl;

import br.com.wns.projetoloja.dao.interfaces.IProdutoDao;
import br.com.wns.projetoloja.model.Produto;
import br.com.wns.projetoloja.util.ConnectionFactory;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.util.ArrayList;
import java.util.List;

/**
 
 * @author Hely
 */
public class ProdutoDao implements IProdutoDao{
    private Connection connection;

    public void inserir(Produto produto) throws Exception {
        try {
            
            connection = ConnectionFactory.getConnection();
         
            String sql = "INSERT INTO Produtos (codigo, nome, valor) values (?,?,?)";
            
            PreparedStatement st = connection.prepareStatement(sql);
            
            st.setString(1, produto.getCodigo()); 
            st.setString(2, produto.getNome());
            st.setString(3, produto.getValor());
            
        
            st.executeUpdate();

            connection.close();

        } catch (Exception e) {
            e.printStackTrace();
            throw new Exception("Erro ao inserir registro!");
        }
    }


    public void alterar(Produto produto) throws Exception {
        try {

            connection = ConnectionFactory.getConnection();

            String sql = "UPDATE Produtos SET codigo = ?, nome = ?, valor = ? WHERE id = ?";

            
            PreparedStatement st = connection.prepareStatement(sql);

            st.setString(1, produto.getCodigo());
            st.setString(2, produto.getNome());
            st.setString(3, produto.getValor());

            st.setLong(4, produto.getId());
            st.executeUpdate();

            connection.close();

        }
            catch (Exception e) {
            e.printStackTrace();
            throw new Exception("Erro ao alterar registro!");
        }
    }


    public Produto buscar(long id) throws Exception{
       try {

            Produto c = null;
            
            connection = ConnectionFactory.getConnection();

            String sql = "SELECT * FROM produtos WHERE id = ?";

            PreparedStatement st = connection.prepareStatement(sql);
            
            st.setLong(1, id);
         
            ResultSet rs = st.executeQuery();

            if(rs.next()){
                
                c = new Produto();
                
                c.setId(rs.getLong("id"));
                c.setCodigo(rs.getString("codigo"));
                c.setNome(rs.getString("nome"));
                c.setValor(rs.getString("valor"));

            }
            
            connection.close();
            
            return c;

        } catch (Exception e) {
            e.printStackTrace();
            throw new Exception("Erro ao excluir registro!");
        }
  
    }
    
    public void excluir(Produto produto) throws Exception {
        try {

            connection = ConnectionFactory.getConnection();

            
            String sql = "DELETE FROM Produtos WHERE id = ?";

            
            PreparedStatement st = connection.prepareStatement(sql);
            
            st.setLong(1, produto.getId());

            
            st.executeUpdate();

            connection.close();

        } catch (Exception e) {
            e.printStackTrace();
            throw new Exception("Erro ao excluir registro!");
        }
    }

    public List<Produto> listar() throws Exception {
        
        try {

            List<Produto> lista = new ArrayList<Produto>();
            
            connection = ConnectionFactory.getConnection();

            
            String sql = "SELECT * FROM produtos";

            
            PreparedStatement st = connection.prepareStatement(sql);
            
            
            ResultSet rs = st.executeQuery();

            while(rs.next()){
                
                Produto c = new Produto();
                
                c.setId(rs.getLong("id"));
                c.setCodigo(rs.getString("codigo"));
                c.setNome(rs.getString("nome"));
                c.setValor(rs.getString("valor"));
                
                             
                lista.add(c);
            }
            
            connection.close();
            
            return lista;

        } catch (Exception e) {
            e.printStackTrace();
            throw new Exception("Erro ao excluir registro!");
        }
    }
}