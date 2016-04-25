/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package br.com.wns.projetoloja.facade;

import br.com.wns.projetoloja.dao.factory.DaoFactory;
import br.com.wns.projetoloja.dao.interfaces.ICategoriaDao;
import br.com.wns.projetoloja.dao.interfaces.ICidadeDao;
import br.com.wns.projetoloja.dao.interfaces.IClienteDao;
import br.com.wns.projetoloja.dao.interfaces.IContatoDao;
import br.com.wns.projetoloja.dao.interfaces.IEnderecoDao;
import br.com.wns.projetoloja.dao.interfaces.IEstadoDao;
import br.com.wns.projetoloja.dao.interfaces.IFuncionarioDao;
import br.com.wns.projetoloja.dao.interfaces.IPessoaDao;
import br.com.wns.projetoloja.dao.interfaces.IProdutoDao;
import br.com.wns.projetoloja.model.Cliente;
import br.com.wns.projetoloja.model.Endereco;
import br.com.wns.projetoloja.model.Funcionario;
import java.util.List;

/**
 *
 * @author Marly
 */
public class FacadeLoja {
      public IFuncionarioDao daoFuncionario;
      public IClienteDao daoCliente;
      public IPessoaDao daoPessoa;
      public IEnderecoDao daoEndereco;
      public IContatoDao daoContato;
      public ICidadeDao daoCidade;
      public IEstadoDao daoEstado;
      public IProdutoDao daoProduto;
      public ICategoriaDao daoCategoria;
    
    public FacadeLoja(){
        daoFuncionario = DaoFactory.createFuncionarioDao();
        daoCliente = DaoFactory.createClienteDao();
        daoPessoa = DaoFactory.createPessoaDao();
        daoEndereco = DaoFactory.createEnderecoDao();
        daoContato = DaoFactory.createContatoDao();
        daoCidade = DaoFactory.createCidadeDao();
        daoEstado = DaoFactory.createEstadoDao();
        daoProduto = DaoFactory.createProdutoDao();
        daoCategoria = DaoFactory.createCategoriaDao();
    }
 //********************************************************************************* 
    public void inserirFuncionario(Funcionario funcionario) throws Exception{
        daoFuncionario.inserir(funcionario);
    }
    public void alterarFuncionario(Funcionario funcionario) throws Exception{
        daoFuncionario.alterar(funcionario);
    }
    public void excluirFuncionario(Funcionario funcionario) throws Exception{
        daoFuncionario.excluir(funcionario);
    }
    public Funcionario buscarFuncionario(long id) throws Exception{
        return daoFuncionario.buscar(id);
    }
    public List<Funcionario> listarFuncionarios() throws Exception{
       return daoFuncionario.listar();
    }

//**********************************************************************************
    
    public void inserirCliente(Cliente cliente) throws Exception{
        daoCliente.inserir(cliente);
    }
    public void alterarCliente(Cliente cliente) throws Exception{
        daoCliente.alterar(cliente);
    }
    public void excluirCliente(Cliente cliente) throws Exception{
        daoCliente.excluir(cliente);
    }
    public Cliente buscarCliente(long id) throws Exception{
        return daoCliente.buscar(id);
    }
    public List<Cliente> listarClientes() throws Exception{
       return daoCliente.listar();
    }
    
//***********************************************************************************   
    public void inserirEndereco(Endereco endereco) throws Exception{
        daoEndereco.inserir(endereco);
    }
    public void alterarEndereco(Endereco endereco) throws Exception{
        daoEndereco.alterar(endereco);
    }
    public void excluirEndereco(Endereco endereco) throws Exception{
         daoEndereco.excluir(endereco);
    }
    public Endereco buscarEndereco(long id) throws Exception{
        return  daoEndereco.buscar(id);
    }
    public List<Endereco> listarEnderecos() throws Exception{
       return  daoEndereco.listar();
       
       
/////
    }
    
   
    
}
