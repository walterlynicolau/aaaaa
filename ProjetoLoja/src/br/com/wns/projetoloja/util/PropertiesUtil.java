
package br.com.wns.projetoloja.util;

import java.util.ResourceBundle;

public class PropertiesUtil {
    private static final String FILE = "conf";
    private static final String FILE_STRINGS = "strings";
    private static final ResourceBundle resource = ResourceBundle.getBundle(FILE);
    private static final ResourceBundle resourceStrings = ResourceBundle.getBundle(FILE_STRINGS);
    
    //chaves de configuração
    public static final String JDBC_URL = "jdbc_url";
    public static final String JDBC_USERNAME = "jdbc_username";
    public static final String JDBC_PASSWORD = "jdbc_password";
    //chaves das mensagens
    public static final String MSG_ERRO_SALVAR = "msg_erro_salvar";
    public static final String MSG_ERRO_ALTERAR = "msg_erro_alterar";
    public static final String MSG_ERRO_BUSCAR = "msg_erro_buscar";
    public static final String MSG_ERRO_EXCLUIR = "msg_erro_excluir";
    public static final String MSG_ERRO_LISTAR = "msg_erro_listar";
    
    public static final String COMANDO_INSERIR_FUNCIONARIO = "comando_insert_funcionarios";
    public static final String COMANDO_INSERIR_PESSOA = "comando_insert_pessoas";
    public static final String COMANDO_INSERIR_ENDERECO = "comando_insert_enderecos";
    public static final String COMANDO_INSERIR_CONTATO = "comando_insert_contatos";
    public static final String COMANDO_INSERIR_CIDADE = "comando_insert_cidades";
    public static final String COMANDO_INSERIR_ESTADO = "comando_insert_estados";
    public static final String COMANDO_INSERIR_PRODUTO = "comando_insert_produtos";
    public static final String COMANDO_INSERIR_CLIENTE = "comando_insert_clientes";
    public static final String COMANDO_INSERIR_CATEGORIA = "comando_insert_categorias";
    
    public static final String COMANDO_ALTERAR_FUNCIONARIO = "comando_update_funcionarios";
    public static final String COMANDO_ALTERAR_PESSOA = "comando_update_pessoas";
    public static final String COMANDO_ALTERAR_ENDERECO = "comando_update_enderecos";
    public static final String COMANDO_ALTERAR_CONTATO = "comando_update_contatos";
    public static final String COMANDO_ALTERAR_CIDADE = "comando_update_cidades";
    public static final String COMANDO_ALTERAR_ESTADO = "comando_update_estados";
    public static final String COMANDO_ALTERAR_PRODUTO = "comando_update_produtos";
    public static final String COMANDO_ALTERAR_CLIENTE = "comando_update_clientes";
    public static final String COMANDO_ALTERAR_CATEGORIA = "comando_update_categorias";
    
    public static final String COMANDO_DELETAR_FUNCIONARIO = "comando_delete_funcionarios";
    public static final String COMANDO_DELETAR_PESSOA = "comando_delete_pessoas";
    public static final String COMANDO_DELETAR_ENDERECO = "comando_delete_enderecos";
    public static final String COMANDO_DELETAR_CONTATO = "comando_delete_contatos";
    public static final String COMANDO_DELETAR_CIDADE = "comando_delete_cidades";
    public static final String COMANDO_DELETAR_ESTADO = "comando_delete_estados";
    public static final String COMANDO_DELETAR_PRODUTO = "comando_delete_produtos";
    public static final String COMANDO_DELETAR_CLIENTE = "comando_delete_clientes";
    public static final String COMANDO_DELETAR_CATEGORIA = "comando_delete_categorias";
    
    public static final String COMANDO_BUSCAR_FUNCIONARIO = "comando_buscar_funcionarios";
    public static final String COMANDO_BUSCAR_PESSOA = "comando_buscar_pessoas";
    public static final String COMANDO_BUSCAR_ENDERECO = "comando_buscar_enderecos";
    public static final String COMANDO_BUSCAR_CONTATO = "comando_buscar_contatos";
    public static final String COMANDO_BUSCAR_CIDADE = "comando_buscar_cidades";
    public static final String COMANDO_BUSCAR_ESTADO = "comando_buscar_estados";
    public static final String COMANDO_BUSCAR_PRODUTO = "comando_buscar_produtos";
    public static final String COMANDO_BUSCAR_CLIENTE = "comando_buscar_clientes";
    public static final String COMANDO_BUSCAR_CATEGORIA = "comando_buscar_categorias";
    
    public static final String COMANDO_LISTAR_FUNCIONARIO = "comando_listar_funcionarios";
    public static final String COMANDO_LISTAR_PESSOA = "comando_listar_pessoas";
    public static final String COMANDO_LISTAR_ENDERECO = "comando_listar_enderecos";
    public static final String COMANDO_LISTAR_CONTATO = "comando_listar_contatos";
    public static final String COMANDO_LISTAR_CIDADE = "comando_listar_cidades";
    public static final String COMANDO_LISTAR_ESTADO = "comando_listar_estados";
    public static final String COMANDO_LISTAR_PRODUTO = "comando_listar_produtos";
    public static final String COMANDO_LISTAR_CLIENTE = "comando_listar_clientes";
    public static final String COMANDO_LISTAR_CATEGORIA = "comando_listar_categorias";
            
            
    
    
    public static String getConfValue(String key){
        return resource.getString(key);        
    }
    
    public static String getStringsValue(String key){
        return resourceStrings.getString(key);        
    }
}
