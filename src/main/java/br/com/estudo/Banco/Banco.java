package br.com.estudo.Banco;

import java.sql.DriverManager;
import java.sql.SQLException;


public class Banco {

    //criar atributos
    public static String bancoDados, usuario, senha, servidor;
    public static int porta;

    //variavel de conexao
    public static java.sql.Connection conexao = null;

    //define valores padrão
    static {
        //mysql e mariaDB
        bancoDados = "trade";
        usuario = "root";
        senha = "";
        servidor = "localhost";
        porta = 3306;

    }

    //métodos
    public static void conectar() throws SQLException {
        //MariaDB
        String url = "jdbc:mariadb://" + servidor +
                     ":" + porta + "/" + bancoDados;
        
        conexao = DriverManager.getConnection(url, usuario, senha);
    }

    public static void desconectar() throws SQLException {
        conexao.close();
    }

    public static java.sql.Connection obterConexao() 
                throws SQLException {
        if (conexao == null) {
            throw new SQLException("Conexão está fechada..");
        } else {
            return conexao;
        }
    }

}
