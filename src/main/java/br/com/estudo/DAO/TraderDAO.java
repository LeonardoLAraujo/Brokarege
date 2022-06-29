package br.com.estudo.DAO;

import br.com.estudo.Banco.Banco;
import br.com.estudo.model.Trader;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.Collection;

public class TraderDAO implements DAO<Trader>{
    
    private java.sql.PreparedStatement pst;
    private java.sql.ResultSet rs;
    private Trader trader; 
    
    @Override
    public boolean insere(Trader obj) throws SQLException {
       String sql = "INSERT INTO Trader (nome) " +
                " VALUES (?)"; //a ? indica parametros

        Banco.conectar();

        pst = Banco.obterConexao().prepareStatement(sql);

        pst.setString(1, obj.getNomeTrader());

        int res = pst.executeUpdate();
        
        //fecha a conexao
        Banco.desconectar();
        
        //devolve se funcionou ou nao
        return res != 0;
    }

    @Override
    public boolean remove(Trader obj) throws SQLException {
       String sql = "DELETE FROM trader WHERE NomeTrader = ?"; //a ? indica parametros
        
        //abre a conexao com o banco
        Banco.conectar();
        //preparar o comando PST
        pst = Banco.obterConexao().prepareStatement(sql);
        
        pst.setString(1, obj.getNomeTrader());
        
        //executar o comando
        int res = pst.executeUpdate(); //esse método serve para Insert, delete e update
        
        //fecha a conexao
        Banco.desconectar();
        
        //devolve se funcionoou ou nao
        return res != 0;
    }

    @Override
    public boolean altera(Trader obj) throws SQLException {
        String sql = "UPDATE Trader SET NomeTrader = ? "
                + "WHERE NomeTrader = ?"; 

        Banco.conectar();

        pst = Banco.obterConexao().prepareStatement(sql);
        
        pst.setString(1, obj.getNomeTrader());
        
        int res = pst.executeUpdate(); 

        Banco.desconectar();

        return res != 0;
    }

    @Override
    public Trader buscaID(Trader obj) throws SQLException {
        throw new UnsupportedOperationException("Not supported yet."); //To change body of generated methods, choose Tools | Templates.
    }

    @Override
    public Collection<Trader> lista(String criterio) throws SQLException {
        ArrayList<Trader> lista = new ArrayList<>();
        
        String sql = "SELECT * FROM Trader ";

        if(criterio != null && criterio.length() > 0) {
            sql += " WHERE " + criterio;
        }

        Banco.conectar();

        pst = Banco.obterConexao().prepareStatement(sql);

        rs = pst.executeQuery();

        while(rs.next()) {

            trader = new Trader();
            
            trader.setNomeTrader(rs.getString("NomeTrader"));
            
            lista.add(trader);
        }
                
        Banco.desconectar();
        
        return lista;
    }
    
}
