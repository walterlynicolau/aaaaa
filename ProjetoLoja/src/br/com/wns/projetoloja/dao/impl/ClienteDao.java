package br.com.wns.projetoloja.dao.impl;

import br.com.wns.projetoloja.dao.interfaces.IClienteDao;
import br.com.wns.projetoloja.model.Cliente;
import br.com.wns.projetoloja.model.Endereco;
import br.com.wns.projetoloja.util.ConnectionFactory;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.util.ArrayList;
import java.util.List;

public class ClienteDao implements IClienteDao{

    private Connection con;
    private PreparedStatement st;

    public void inserir(Cliente cliente) throws Exception {

        try {
            con = ConnectionFactory.getConnection();
            String sql = "INSERT INTO clientes (cpf, nome, telefone, rua, numero, data_nascimento) values (?,?,?,?,?,?)";
            st = con.prepareStatement(sql);

            st.setString(1, cliente.getCpf()); //diciona valores as ?
            st.setString(2, cliente.getNome());
            st.setString(3, cliente.getTelefone());
            st.setString(4, cliente.getEndereco().getRua());
            st.setInt(5, cliente.getEndereco().getNumero());
            st.setDate(6, new java.sql.Date(cliente.getDataNasc().getTime())); //erro akii
            
            st.executeUpdate();
            con.close();
            
            System.out.println("cliente inserido");
        } catch (Exception e) {
            e.printStackTrace();
            throw new Exception("erro ao inserir cliente");
        }
    }

    public void excluir(Cliente cliente) throws Exception {

        try {
            con = ConnectionFactory.getConnection();
            String sql = "DELETE FROM clientes WHERE id = ?";
            st = con.prepareStatement(sql);
            st.setLong(1, cliente.getId());
            st.executeUpdate();
            con.close();
            System.out.println("cliente excluido");
        } catch (Exception e) {
            e.printStackTrace();
            throw new Exception("erro ao excluir o cliente");
        }
    }

    public Cliente buscar(int id) throws Exception {

        try {
            Cliente cliente = null;
            Endereco endereco = null;
            con = ConnectionFactory.getConnection();
            String sql = "SELECT * FROM clientes WHERE id = ?";
            PreparedStatement st = con.prepareStatement(sql);
            st.setInt(1, id);

            ResultSet rs = st.executeQuery();

            if (rs.next()) {
                cliente = new Cliente();
                endereco = new Endereco();
                cliente.setId(rs.getLong("id"));
                cliente.setCpf(rs.getString("cpf"));
                cliente.setNome(rs.getString("nome"));
                cliente.setTelefone(rs.getString("telefone"));
                endereco.setRua(rs.getString("rua"));
                endereco.setNumero(rs.getInt("numero"));
                cliente.setEndereco(endereco);
                java.util.Date dataUtil = rs.getDate("data_nascimento");
                if(dataUtil != null){
                 cliente.setDataNasc(new java.util.Date(dataUtil.getTime()));
                }
               
            }
            con.close();
            return cliente;
            
        } catch (Exception e) {
            e.printStackTrace();
            throw new Exception("erro ao buscar cliente");
        }
    }

    public void alterar(Cliente cliente) throws Exception {
        try {
            con = ConnectionFactory.getConnection();
            String sql = "UPDATE clientes SET cpf =?, nome =?, telefone =?, rua =?, numero =?, data_nascimento =? WHERE id = ?";
            PreparedStatement st = con.prepareStatement(sql);

            st.setString(1, cliente.getCpf()); //diciona valores as ?
            st.setString(2, cliente.getNome());
            st.setString(3, cliente.getTelefone());
            st.setString(4, cliente.getEndereco().getRua());
            st.setInt(5, cliente.getEndereco().getNumero());
            st.setDate(6, new java.sql.Date(cliente.getDataNasc().getTime()));
            st.setLong(7, cliente.getId());
            
            st.executeUpdate();
            con.close();
        } catch (Exception e) {
            e.printStackTrace();
            throw new Exception(" erro ao alterar!!!");
        }
    }

    public List<Cliente> listar() throws Exception {

        try {
            List<Cliente> listaClientes = new ArrayList<>();
            con = ConnectionFactory.getConnection();
            String sql = "SELECT * FROM clientes";
            PreparedStatement st = con.prepareStatement(sql);

            ResultSet rs = st.executeQuery();

            while (rs.next()) {
                Cliente cliente = new Cliente();
                Endereco endereco = new Endereco();
                
                cliente.setId(rs.getLong("id"));
                cliente.setCpf(rs.getString("cpf"));
                cliente.setNome(rs.getString("nome"));
                cliente.setTelefone(rs.getString("telefone"));
                endereco.setRua(rs.getString("rua"));
                endereco.setNumero(rs.getInt("numero"));
                java.util.Date dataUtil = rs.getDate("data_nascimento");
                if(dataUtil != null){
                    cliente.setDataNasc(new java.util.Date(dataUtil.getTime()));
                }
                listaClientes.add(cliente);
            }
            con.close();
            return listaClientes;
        } catch (Exception e) {
            e.printStackTrace();
            throw new Exception("erro ao buscar cliente");
        }
    }
}
