package br.com.estudo.DAO;

import br.com.estudo.Banco.Banco;
import br.com.estudo.model.Order;
import br.com.estudo.model.Papel;
import java.sql.PreparedStatement;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.Collection;
import java.util.List;

public class PapelDAO implements DAO<Papel>{
    
    private java.sql.PreparedStatement pst;
    private java.sql.ResultSet rs;
    private Papel papel;
    
    @Override
    public boolean insere(Papel obj) throws SQLException {
       String sql = "INSERT INTO trade (nomePapel) " +
                " VALUES (?)"; //a ? indica parametros

        Banco.conectar();

        pst = Banco.obterConexao().prepareStatement(sql);
        
        pst.setString(1, obj.getNomePapel()); //Nome da Pessoa

        int res = pst.executeUpdate();
        
        //fecha a conexao
        Banco.desconectar();

        return res != 0;
    }

    @Override
    public boolean remove(Papel obj) throws SQLException {
        String sql = "DELETE FROM trade WHERE idPapel = ?"; 

        Banco.conectar();

        pst = Banco.obterConexao().prepareStatement(sql);

        pst.setInt(1, obj.getIdPapel());
 
        int res = pst.executeUpdate(); 

        Banco.desconectar();

        return res != 0;
    }

    @Override
    public boolean altera(Papel obj) throws SQLException {
      String sql = "UPDATE trade SET nomePapel = ? "
                + "WHERE IdPapel = ?"; //a ? indica parametros
        
        //abre a conexao com o banco
        Banco.conectar();
        //preparar o comando PST
        pst = Banco.obterConexao().prepareStatement(sql);
        
        pst.setString(1, obj.getNomePapel()); //Nome da Pessoa
        pst.setInt(5, obj.getIdPapel()); //Preço da Ação
        
        //executar o comando
        int res = pst.executeUpdate(); //esse método serve para Insert, delete e update
        
        //fecha a conexao
        Banco.desconectar();
        
        //devolve se funcionoou ou nao
        return res != 0;
    }

    @Override
    public Papel buscaID(Papel obj) throws SQLException {
        throw new UnsupportedOperationException("Not supported yet."); //To change body of generated methods, choose Tools | Templates.
    }

    @Override
    public Collection<Papel> lista(String criterio) throws SQLException {
        ArrayList<Papel> lista = new ArrayList<>();
        
        String sql = "SELECT * FROM trade ";

        if(criterio != null && criterio.length() > 0) {
            sql += " WHERE " + criterio;
        }

        Banco.conectar();

        pst = Banco.obterConexao().prepareStatement(sql);
        rs = pst.executeQuery();

        while(rs.next()) {

            papel = new Papel();
            
            papel.setIdPapel(rs.getInt("IdPapel"));
            papel.setNomePapel(rs.getString("NomePapel"));
            
            lista.add(papel);
        }
 
        Banco.desconectar();
        
        return lista;
    }
    
    
}
