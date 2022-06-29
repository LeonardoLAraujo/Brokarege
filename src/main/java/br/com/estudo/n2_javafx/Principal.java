package br.com.estudo.n2_javafx;

import br.com.estudo.Banco.Banco;
import java.sql.SQLException;

public class Principal {
    
    public static void main(String agrs[]){
        
         try {
            System.out.println("Conectando ....");
            //testar a conexao
            Banco.conectar();
            System.out.println("Banco conectado com Sucesso!!!");
            
            System.out.println("Fechando ....");
            Banco.desconectar();
            System.out.println("Fechado!!!!");

        } catch (SQLException ex) {
            System.out.println("Erro ao Conectar com o Banco: " + 
                    ex.getMessage());
        }
        
    }
    
}
