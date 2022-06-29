package br.com.estudo.n2_javafx;

import br.com.estudo.DAO.OrderDAO;
import br.com.estudo.DAO.PapelDAO;
import br.com.estudo.model.Order;
import br.com.estudo.model.Papel;
import br.com.estudo.model.Trader;
import java.io.IOException;
import java.net.URL;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;
import java.util.ResourceBundle;
import java.util.Vector;
import javafx.beans.value.ChangeListener;
import javafx.beans.value.ObservableValue;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.control.Alert;
import javafx.scene.control.Button;
import javafx.scene.control.ButtonType;
import javafx.scene.control.ComboBox;
import javafx.scene.control.DatePicker;
import javafx.scene.control.Label;
import javafx.scene.control.TextField;
import javafx.stage.Stage;

public class AddOrderController implements Initializable {

    
    @FXML
    private Label lblTicker;
    @FXML
    private Label lblTrader;
    @FXML
    private Button btnOrder;
    @FXML
    private TextField txtQuantidade;
    @FXML
    private TextField txtValor;
    @FXML
    private ComboBox<Papel> cbPapel;
    @FXML
    private ComboBox<Trader> cbTrader;
    @FXML
    private Label idOrder;
    @FXML
    private TextField txtidOrder;
    
    private List<Papel> listaPapel = new ArrayList<>();
    private ObservableList<Papel> obsPapel;
    private List<Trader> listaTrader = new ArrayList<>();
    private ObservableList<Trader> obsTrader;
    @FXML
    private Button btnCancelar;
    
    private ObservableList<Papel> produtos = 
            FXCollections.observableArrayList();

    @Override
    public void initialize(URL url, ResourceBundle rb) {
       carregarPapel();
       carregarTrader();
    }    

    @FXML
    private void btnOrderClick(ActionEvent event) {
        OrderDAO dao = new OrderDAO();
        
        //objeto para incluir no banco, dados que podem vir da tela
        Order o = new Order();
        
        o.setPapel(cbPapel.getSelectionModel().getSelectedItem().getNomePapel());
        o.setTrader(cbTrader.getSelectionModel().getSelectedItem().getNomeTrader());
        o.setQuantidade(Integer.parseInt(txtQuantidade.getText()));
        o.setValor(txtValor.getText());
        
        
        try {
            if(dao.insere(o)) {
                Alert alerta = new Alert(Alert.AlertType.INFORMATION,
                    "Order Cadastrada com Sucesso!!", 
                    ButtonType.OK);
                alerta.showAndWait();
            }
            else {
                Alert alerta = new Alert(Alert.AlertType.ERROR,
                    "Ocorreu erro: ", 
                    ButtonType.OK);
                    alerta.showAndWait();   
            }
        } catch (SQLException ex) {
            System.out.println("Erro de Inclusão: " + ex.getMessage());
        }
    }
    
    
    public void carregarPapel(){  

        Papel p1 = new Papel(1, "MAGAZINE LUIZA - MGLU3");
        Papel p2 = new Papel(2, "OI - OIBR3");
        Papel p3 = new Papel(3, "COGNA EDUCAÇÃO - COGN3");
        Papel p4 = new Papel(4, "HAPVIDA - HAPV3");
        Papel p5 = new Papel(5, "VIA VAREJO - VIIA3");
        Papel p6 = new Papel(6, "CIELO - CIEL3");
        Papel p7 = new Papel(7, "BANCO DO BRASIL - BBAS3");
        listaPapel.add(p1);
        listaPapel.add(p2);
        listaPapel.add(p3);
        listaPapel.add(p4);
        listaPapel.add(p5);
        listaPapel.add(p6);
        listaPapel.add(p7);

        obsPapel = FXCollections.observableArrayList(listaPapel);
        
        cbPapel.setItems(obsPapel);
        
    }
    
    public void carregarTrader(){
        
        Trader t1 = new Trader("Antonio Rodrigues");
        Trader t2 = new Trader("Pedro Henrique");
        Trader t3 = new Trader("Astolfo da Silva");
        Trader t4 = new Trader("Casemiro Santos");
        Trader t5 = new Trader("Felipe da Souza");
        listaTrader.add(t1);
        listaTrader.add(t2);
        listaTrader.add(t3);
        listaTrader.add(t4);
        listaTrader.add(t5);

        obsTrader = FXCollections.observableArrayList(listaTrader);
        
        cbTrader.setItems(obsTrader);
    }

    @FXML
    private void btnCancelarClick(ActionEvent event) throws IOException {
        App.setRoot("menu");
    }

    @FXML
    private void cbTraderAction(ActionEvent event){
        
    }

    @FXML
    private void cbPapelAction(ActionEvent event) {
        
    }
    
}
