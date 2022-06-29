package br.com.estudo.DAO;

import br.com.estudo.Banco.Banco;
import br.com.estudo.model.Order;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.Collection;
import java.util.List;


public class OrderDAO implements DAO<Order>{
    
    private java.sql.PreparedStatement pst;
    private java.sql.ResultSet rs;
    private Order order;
    
    @Override
    public boolean insere(Order obj) throws SQLException {
         String sql = "INSERT INTO Orde (papel,trader,quantidade, valor) " +
                " VALUES (?,?,?,?)"; //a ? indica parametros

        Banco.conectar();

        pst = Banco.obterConexao().prepareStatement(sql);
        
        pst.setString(1, obj.getTrader()); //Nome da Pessoa
        pst.setString(2, obj.getPapel()); //Nome da empresa
        pst.setInt(3, obj.getQuantidade()); //Quantidade de moeda
        pst.setString(4, obj.getValor()); //Preço da Ação

        int res = pst.executeUpdate();
        
        //fecha a conexao
        Banco.desconectar();

        return res != 0;
    }

    @Override
    public boolean remove(Order obj) throws SQLException {
        String sql = "DELETE FROM orde WHERE IdOrder = ?"; //a ? indica parametros
        
        //abre a conexao com o banco
        Banco.conectar();
        //preparar o comando PST
        pst = Banco.obterConexao().prepareStatement(sql);
        
        //associar os dados do objeto Proprietario com o comando DELETE
        pst.setInt(1, obj.getIdOrder());
        
        //executar o comando
        int res = pst.executeUpdate(); //esse método serve para Insert, delete e update
        
        //fecha a conexao
        Banco.desconectar();
        
        //devolve se funcionoou ou nao
        return res != 0;
    }

    @Override
    public boolean altera(Order obj) throws SQLException {
       String sql = "UPDATE orde SET trader = ?, papel = ?, quantidade = ?, valor = ? "
                + "WHERE IdOrder = ?"; //a ? indica parametros
        
        //abre a conexao com o banco
        Banco.conectar();
        //preparar o comando PST
        pst = Banco.obterConexao().prepareStatement(sql);
        
        //associar os dados do objeto Proprietario com o comando UPDATE
        pst.setString(1, obj.getTrader()); //Nome da Pessoa
        pst.setString(2, obj.getPapel()); //Nome da empresa
        pst.setInt(3, obj.getQuantidade()); //Quantidade de moeda
        pst.setString(4, obj.getValor()); //Preço da Ação
        pst.setInt(5, obj.getIdOrder()); //Preço da Ação
        
        //executar o comando
        int res = pst.executeUpdate(); //esse método serve para Insert, delete e update
        
        //fecha a conexao
        Banco.desconectar();
        
        //devolve se funcionoou ou nao
        return res != 0;
    }

    @Override
    public Order buscaID(Order obj) throws SQLException {
       String sql = "SELECT * FROM Orde "
                + "WHERE IdOrder = ?"; //a ? indica parametros
        
        //abre a conexao com o banco
        Banco.conectar();
        //preparar o comando PST
        pst = Banco.obterConexao().prepareStatement(sql);
        
        //associar os dados do objeto Proprietario com o comando UPDATE
        pst.setString(1, obj.getPapel());
        
        //executar o comando
        rs = pst.executeQuery(); //esse método serve para SELECT
        
        //verificar se trouxe algum registro
        //rs.next() faz a leitura do próximo registro, se existir devolve true
        //se nao devolve false
        if(rs.next()) {
            //mover os dados(campos da tab) do resultSet para o objeto proprietário
            order = new Order();
            order.setTrader(rs.getString("Trader"));
            order.setPapel(rs.getString("Papel"));
            order.setQuantidade(rs.getInt("Quantidade"));
            order.setValor(rs.getString("Valor"));
        }
        else {
            //não encontrou o registro solicitado
            order = null;
        }
                
        //fecha a conexao
        Banco.desconectar();
        
        //devolve o objeto proprietario
        return order;
    }

    @Override
    public Collection<Order> lista(String criterio) throws SQLException {
        //cria uma lista para armazenar os dados vindos do banco
        ArrayList<Order> lista = new ArrayList<>();
        
        String sql = "SELECT * FROM v_media";

        //precisa fazer filtro para listagem
        if(criterio != null && criterio.length() > 0) {
            sql += " WHERE " + criterio;
        }
        
        //abre a conexao com o banco
        Banco.conectar();
        
        //preparar o comando PST
        pst = Banco.obterConexao().prepareStatement(sql);
        
        //executar o comando
        rs = pst.executeQuery(); //esse método serve para SELECT
        
        //Varre todo o resultado da consulta e coloca cada registro dentro
        //de um objeto e coloca o objeto dentro da coleção
        while(rs.next()) {
            //criar o objeto
            order = new Order();
            
            //mover os dados do resultSet para o objeto proprietário
            order.setIdOrder(rs.getInt("IdOrder"));
            order.setTrader(rs.getString("Trader"));
            order.setPapel(rs.getString("Papel"));
            order.setQuantidade(rs.getInt("Quantidade"));
            order.setValor(rs.getString("Valor"));
            
            //move o objeto para a coleção
            lista.add(order);
        }
                
        //fecha a conexao
        Banco.desconectar();
        
        //devolve o objeto proprietario
        return lista;
        
    }

    public void removeOrders(int id) {
        throw new UnsupportedOperationException("Not supported yet."); // Generated from nbfs://nbhost/SystemFileSystem/Templates/Classes/Code/GeneratedMethodBody
    }
    
    
}
