package br.com.estudo.n2_javafx;

import java.io.IOException;
import java.net.URL;
import java.util.ResourceBundle;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.control.Button;
import javafx.scene.control.Label;
import javafx.stage.Stage;


public class MenuController implements Initializable {

    private Stage tela = new Stage();
    
    @FXML
    private Label lblAddTrader;
    @FXML
    private Label lblAddOrder;
    @FXML
    private Label lblAddTrade;
    @FXML
    private Label lblConsultar;
    @FXML
    private Label lblAddSair;
    @FXML
    private Button btnAddTrader;
    @FXML
    private Button btnAddTrade;
    @FXML
    private Button btnAddOrder;
    @FXML
    private Button btnSair;
    @FXML
    private Button btnConsulta;

    @Override
    public void initialize(URL url, ResourceBundle rb) {
        
    }    

    @FXML
    private void btnAddTraderClick(ActionEvent event) throws IOException{
      App.setRoot("AddTrader");
    }
    
    
    @FXML
    private void btnAddTradeClick(ActionEvent event) throws IOException {
        App.setRoot("AddPapel");
    }

    @FXML
    private void btnAddOrderClick(ActionEvent event) throws IOException {
        App.setRoot("AddOrder");
    }

    @FXML
    private void btnConsultaClick(ActionEvent event) throws IOException {
        App.setRoot("Consulta");
    }
    
    @FXML
    private void btnSairClick(ActionEvent event) {
        System.exit(1);
    }
   
}
