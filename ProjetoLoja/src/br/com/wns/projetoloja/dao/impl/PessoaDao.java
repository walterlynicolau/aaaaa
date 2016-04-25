
package br.com.wns.projetoloja.dao.impl;

import br.com.wns.projetoloja.dao.factory.DaoFactory;
import br.com.wns.projetoloja.dao.interfaces.IContatoDao;
import br.com.wns.projetoloja.dao.interfaces.IEnderecoDao;
import br.com.wns.projetoloja.dao.interfaces.IPessoaDao;
import br.com.wns.projetoloja.model.Pessoa;
import br.com.wns.projetoloja.util.ConnectionFactory;
import br.com.wns.projetoloja.util.PropertiesUtil;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.Statement;
import java.util.ArrayList;
import java.util.List;

public class PessoaDao implements IPessoaDao{
   private Connection connection;
   private IEnderecoDao iDaoEndereco = null;
   private IContatoDao iDaoContato = null;
    
    public long inserir (Pessoa pessoa) throws Exception{
        try{
            
            //inserção do endereco no BD enderecos a a partir de pessoa
            iDaoEndereco = DaoFactory.createEnderecoDao();
            long idEndereco = iDaoEndereco.inserir(pessoa.getEndereco());
            //inserção do contato no BD contatos a a partir de pessoa
            iDaoContato = DaoFactory.createContatoDao();
            long idContato = iDaoContato.inserir(pessoa.getContato());
            
            connection = ConnectionFactory.getConnection();
            connection.setAutoCommit(false);
            String sqlPessoa = PropertiesUtil.getConfValue(PropertiesUtil.COMANDO_INSERIR_PESSOA);
            PreparedStatement psPessoa = connection.prepareStatement(sqlPessoa, Statement.RETURN_GENERATED_KEYS);
            psPessoa.setString(1, pessoa.getCpf());
            psPessoa.setString(2, pessoa.getNome());
            psPessoa.setString(3, pessoa.getSexo());
            psPessoa.setString(4, pessoa.getRg()); 
            psPessoa.setDate(5, new java.sql.Date(pessoa.getDataNascimento().getTime()));
            psPessoa.setLong(6, idEndereco);
            psPessoa.setLong(7, idContato);
            psPessoa.executeUpdate();
            
            ResultSet rsPessoa = psPessoa.getGeneratedKeys();
            long id = 0;
            if(rsPessoa.next()){
                id = rsPessoa.getLong(1);
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
    
    public void alterar (Pessoa pessoa) throws Exception{
        try{
            connection = ConnectionFactory.getConnection();
            connection.setAutoCommit(false);
            String sqlPessoa = PropertiesUtil.getConfValue(PropertiesUtil.COMANDO_ALTERAR_PESSOA);
            PreparedStatement psPessoa = connection.prepareStatement(sqlPessoa);
            psPessoa.setString(1, pessoa.getCpf());
            psPessoa.setString(2, pessoa.getNome());
            psPessoa.setString(3, pessoa.getSexo());
            psPessoa.setString(4, pessoa.getRg());
            psPessoa.setDate(5, new java.sql.Date(pessoa.getDataNascimento().getTime()));
            psPessoa.setLong(6, pessoa.getEndereco().getId());
            psPessoa.setLong(7, pessoa.getContato().getId());
            psPessoa.setLong(8, pessoa.getId());
            psPessoa.executeUpdate();
            iDaoEndereco = DaoFactory.createEnderecoDao();
            iDaoContato = DaoFactory.createContatoDao();
            iDaoEndereco.alterar(pessoa.getEndereco());
            iDaoContato.alterar(pessoa.getContato());
            connection.commit();
            connection.close();
        }catch(Exception e){
            connection.rollback();
            e.printStackTrace();
            throw new Exception(PropertiesUtil.getStringsValue(PropertiesUtil.MSG_ERRO_ALTERAR));
        }
    }
    
    public void excluir (Pessoa pessoa) throws Exception{
        try{
            
            connection = ConnectionFactory.getConnection();
            connection.setAutoCommit(false);
            String slqPessoa = PropertiesUtil.getConfValue(PropertiesUtil.COMANDO_DELETAR_PESSOA);
            PreparedStatement psPessoa = connection.prepareStatement(slqPessoa);
            psPessoa.setLong(1, pessoa.getId());
            psPessoa.executeUpdate();
            connection.commit();
            connection.close();
            iDaoEndereco = DaoFactory.createEnderecoDao();
            iDaoContato = DaoFactory.createContatoDao();
            iDaoEndereco.excluir(pessoa.getEndereco());
            iDaoContato.excluir(pessoa.getContato());
            
        }catch(Exception e){
            connection.rollback();
            e.printStackTrace();
            throw new Exception(PropertiesUtil.getStringsValue(PropertiesUtil.MSG_ERRO_EXCLUIR));
        }
    }
    
    public Pessoa buscar (long id) throws Exception{
        try{
            Pessoa pessoa = null;
            connection = ConnectionFactory.getConnection();
            connection.setAutoCommit(false);
            String sqlPessoa = PropertiesUtil.getConfValue(PropertiesUtil.COMANDO_BUSCAR_PESSOA);
            PreparedStatement psPessoa = connection.prepareStatement(sqlPessoa);
            psPessoa.setLong(1, id);
            ResultSet rsPessoa = psPessoa.executeQuery();
            if(rsPessoa.next()){
                pessoa = new Pessoa();
                iDaoEndereco = DaoFactory.createEnderecoDao();
                iDaoContato = DaoFactory.createContatoDao();
                pessoa.setId(rsPessoa.getLong("id"));
                pessoa.setCpf(rsPessoa.getString("cpf"));
                pessoa.setNome(rsPessoa.getString("nome"));
                pessoa.setSexo(rsPessoa.getString("sexo"));
                pessoa.setRg(rsPessoa.getString("rg"));
                 //conversão de datas da java.sql para a java.util que eh a usada em funcionario
                java.util.Date dataSql = rsPessoa.getDate("data_nascimento");
                 if(dataSql != null){
                 pessoa.setDataNascimento(new java.util.Date(dataSql.getTime()));
                 }
                pessoa.setEndereco(iDaoEndereco.buscar(rsPessoa.getInt("id_endereco")));
                pessoa.setContato(iDaoContato.buscar(rsPessoa.getInt("id_contato"))); 
            }
            connection.commit();
            connection.close();
            return pessoa;
        }catch(Exception e){
            connection.rollback();
            e.printStackTrace();
            throw new Exception(PropertiesUtil.getStringsValue(PropertiesUtil.MSG_ERRO_BUSCAR));  
        }
    }
    
     public List<Pessoa> listar () throws Exception{
         List<Pessoa> pessoas = null;
        try{
            Pessoa pessoa = null;
            pessoas = new ArrayList<>();
            connection = ConnectionFactory.getConnection();
            connection.setAutoCommit(false);
            String sqlPessoa = PropertiesUtil.getConfValue(PropertiesUtil.COMANDO_LISTAR_PESSOA);
            PreparedStatement psPessoa = connection.prepareStatement(sqlPessoa);
            ResultSet rsPessoa = psPessoa.executeQuery();
            while(rsPessoa.next()){
                pessoa = new Pessoa();
                iDaoEndereco = DaoFactory.createEnderecoDao();
                iDaoContato = DaoFactory.createContatoDao();
                pessoa.setId(rsPessoa.getLong("id"));
                pessoa.setCpf(rsPessoa.getString("cpf"));
                pessoa.setNome(rsPessoa.getString("nome"));
                pessoa.setSexo(rsPessoa.getString("sexo"));
                pessoa.setRg(rsPessoa.getString("rg"));
                 //conversão de datas da java.sql para a java.util que eh a usada em funcionario
                java.util.Date dataSql = rsPessoa.getDate("data_nascimento");
                 if(dataSql != null){
                 pessoa.setDataNascimento(new java.util.Date(dataSql.getTime()));
                 }
                pessoa.setEndereco(iDaoEndereco.buscar(rsPessoa.getInt("id_endereco")));
                pessoa.setContato(iDaoContato.buscar(rsPessoa.getInt("id_contato")));
                pessoas.add(pessoa);
            }
            connection.commit();
            connection.close();
            return pessoas;
        }catch(Exception e){
            connection.rollback();
            e.printStackTrace();
            throw new Exception(PropertiesUtil.getStringsValue(PropertiesUtil.MSG_ERRO_LISTAR));  
        }
    }
}