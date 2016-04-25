
package br.com.wns.projetoloja.main;

import br.com.wns.projetoloja.dao.factory.DaoFactory;
import br.com.wns.projetoloja.dao.impl.CidadeDao;
import br.com.wns.projetoloja.dao.impl.ClienteDao;
import br.com.wns.projetoloja.dao.impl.ContatoDao;
import br.com.wns.projetoloja.dao.impl.EnderecoDao;
import br.com.wns.projetoloja.dao.impl.EstadoDao;
import br.com.wns.projetoloja.dao.impl.FuncionarioDao;
import br.com.wns.projetoloja.dao.impl.PessoaDao;
import br.com.wns.projetoloja.dao.impl.ProdutoDao;
import br.com.wns.projetoloja.dao.interfaces.IFuncionarioDao;
import br.com.wns.projetoloja.model.Cidade;
import br.com.wns.projetoloja.model.Contato;
import br.com.wns.projetoloja.model.Endereco;
import br.com.wns.projetoloja.model.Funcionario;
import br.com.wns.projetoloja.model.Produto;
import br.com.wns.projetoloja.util.StringUtils;
import java.util.List;


public class Main {
    
     public static void main(String[] args) throws Exception{
         
         
    Funcionario funcionario = new Funcionario();
     IFuncionarioDao iDaoFuncionario = DaoFactory.createFuncionarioDao();
     
     
    // funcionario = iDaoFuncionario.buscar(12);
//     funcionario.setNome("Walterly N de Sousa");
//     funcionario.setCpf("000 000 000-00");
//     funcionario.setRg("00000-00");
//     funcionario.setSexo("Masculino");
//     funcionario.setDataNascimento(StringUtils.converterData("22/09/1994"));
//     funcionario.setMatricula("00000");
//     funcionario.setSalario("5.000,00");
//    // funcionario.setEndereco(new Endereco());
//     funcionario.getEndereco().setRua("Rua da pedra");
//     funcionario.getEndereco().setNumero("000");
//     funcionario.getEndereco().setBairro("novo");
//     funcionario.getEndereco().setCep("00000-000");
//   //  funcionario.getEndereco().setCidade(new Cidade());
//     //funcionario.setContato(new Contato());
//     funcionario.getContato().setTelefoneCelular("(00)0000-0000");
//     funcionario.getContato().setTelefoneFixo("(00)0000-0000");
//     funcionario.getContato().setEmail1("walter@gmail.com");
//     funcionario.getContato().setEmail2("walterlyninja@gmail.com");
//     funcionario.getEndereco().getCidade().setId(2);//tem q ter um metodo pra pegar esse id a partir do nome da cidade???
    Funcionario funcionario2 = iDaoFuncionario.buscar(11);
    
      System.out.println(funcionario2.getId()+"\n Nome: "+
                         funcionario2.getNome()+"\n Salario: "+
                         funcionario2.getSalario()+"\n Matricula: "+
                         funcionario2.getMatricula()+"\n CPF: "+
                         funcionario2.getCpf()+"\n RG: "+
                         funcionario2.getRg()+"\n Sexo: "+
                         funcionario2.getSexo()+"\n Data de Nascimento:"+ 
                         StringUtils.formatarData(funcionario2.getDataNascimento())+"\n"+
                         funcionario2.getEndereco()+"\n"+
                         funcionario2.getContato());
         
     
     
     
     
//     iDaoFuncionario.inserir(funcionario);
//     iDaoFuncionario.alterar(funcionario);
//  iDaoFuncionario.excluir(funcionario);
      
    
//     Funcionario funcionario2 = iDaoFuncionario.buscar(1);
//     System.out.println(funcionario2.getId()+"\n Nome: "+
//                        funcionario2.getNome()+"\n Salario: "+
//                        funcionario2.getSalario()+"\n Matricula: "+
//                        funcionario2.getMatricula()+"\n CPF: "+
//                        funcionario2.getCpf()+"\n RG: "+
//                        funcionario2.getRg()+"\n Sexo: "+
//                        funcionario2.getSexo()+"\n Data de Nascimento:"+
//                        funcionario2.getDataNascimento()+"\n"+
//                        funcionario2.getEndereco()+"\n"+
//                        funcionario2.getContato());
    
//     List <Funcionario> funcionarios = iDaoFuncionario.listar();
//         for( Funcionario funcionario2: funcionarios){ 
//             System.out.println(funcionario2.getId()+"\n Nome: "+
//                    funcionario2.getNome()+"\n Salario: "+
//                    funcionario2.getSalario()+"\n Matricula: "+
//                    funcionario2.getMatricula()+"\n CPF: "+
//                    funcionario2.getCpf()+"\n RG: "+
//                    funcionario2.getRg()+"\n Sexo: "+
//                    funcionario2.getSexo()+"\n Data de Nascimento:"+
//                    StringUtils.formatarData(funcionario2.getDataNascimento())+"\n"+
//                    funcionario2.getEndereco()+"\n"+
//                    funcionario2.getContato());
//         System.out.println(" \n\n");
//        }
     
        Produto p = new Produto();
        p.setNome("coca cola zero");
        p.setValor("60,00");
        p.setCodigo("0005004");
        p.setId(4);
        
        ProdutoDao daoProduto = new ProdutoDao();
//        daoProduto.inserir(p);
        
//        daoProduto.alterar(p);
        
//        Produto p2 = daoProduto.getPorId(4);
//        System.out.println(p2);
//        daoProduto.excluir(p);
        
//        List<Produto> lista = daoProduto.listar();
//        for(Produto c : lista){
//            System.out.println(c.toString());
//        }   
        
        
//        Cliente cliente = new Cliente();
//        Endereco endereco = new Endereco();
//        ClienteDao daoCliente = new ClienteDao();
//        cliente.setCpf("000 000 000-00");
//        cliente.setNome("rivaldo souza");
//        cliente.setTelefone("88996771");
//        endereco.setRua("fazenda da bala");
//       // endereco.setNumero(2002);
//        cliente.setEndereco(endereco);
//        cliente.setDataNasc(StringUtils.converterData("22/09/1992"));
//        cliente.setId(6);
        
      // daoCliente.inserir(cliente);
     // daoCliente.alterarCliente(cliente);
 
//        Cliente cliente2 = daoCliente.bucarCliente(4);
//        System.out.println(cliente2.toString());
        
//      daoCliente.excluir(cliente);
        
//          List<Cliente> clientes = daoCliente.listarClientes();
//         for(Cliente c: clientes){
//          System.out.println(c.toString());
//       }
      System.out.println("tranquilo... favoravel...");
     
     }
}
