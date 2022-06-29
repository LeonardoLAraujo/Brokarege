package br.com.estudo.n2_javafx;

import br.com.estudo.DAO.TraderDAO;
import br.com.estudo.model.Trader;
import java.io.IOException;
import java.net.URL;
import java.sql.SQLException;
import java.util.ResourceBundle;
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
import javafx.scene.control.Label;
import javafx.scene.control.TextField;


public class AddTraderController implements Initializable {
    
    private ObservableList<Trader> trader = 
            FXCollections.observableArrayList();
    
    @FXML
    private Label lblName;
    @FXML
    private TextField txtName;
    @FXML
    private Button btnInsert;
    @FXML
    private Button btnCancelar;

    @Override
    public void initialize(URL url, ResourceBundle rb) {
        
    }    

    @FXML
    private void btnInsertClick(ActionEvent event) throws IOException {

        TraderDAO dao = new TraderDAO();
        
        //objeto para incluir no banco, dados que podem vir da tela
        Trader t = new Trader();
        
        t.setNomeTrader(txtName.getText());
        
        try {
            if(dao.insere(t)) {
                Alert alerta = new Alert(Alert.AlertType.INFORMATION,
                    "Trader Incluído com Sucesso!!", 
                    ButtonType.OK);
                alerta.showAndWait();
            }else if(txtName.getText().trim().isEmpty()){
                Alert alerta = new Alert(Alert.AlertType.WARNING,
                    "Campos Vázios: ", 
                    ButtonType.OK);
                alerta.showAndWait();
            }else {
                Alert alerta = new Alert(Alert.AlertType.ERROR,
                    "Ocorreu erro: ", 
                    ButtonType.OK);
                    alerta.showAndWait();   
            }
        } catch (SQLException ex) {
            System.out.println("Erro de Inclusão: " + ex.getMessage());
        }

    }

    @FXML
    private void btnCancelarClick(ActionEvent event) throws IOException {
        App.setRoot("Menu");
    }
    
}
