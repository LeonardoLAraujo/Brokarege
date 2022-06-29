package br.com.estudo.n2_javafx;

import br.com.estudo.DAO.OrderDAO;
import br.com.estudo.model.Order;
import java.io.IOException;
import java.net.URL;
import java.sql.SQLException;
import java.util.ResourceBundle;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.fxml.Initializable;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.control.Alert;
import javafx.scene.control.Button;
import javafx.scene.control.ButtonType;
import javafx.scene.control.DatePicker;
import javafx.scene.control.Label;
import javafx.scene.control.TextField;
import javafx.stage.Stage;

public class AlterarController implements Initializable {

    private Stage tela = new Stage();
    @FXML
    private TextField txtPapel;
    @FXML
    private TextField txtTrader;
    @FXML
    private TextField txtQuantidade;
    @FXML
    private TextField txtValor;
    @FXML
    private Button btnAlterar;
    @FXML
    private Button btnVoltar;
    @FXML
    private Label lblId;
    
    private static Order order2;
    @FXML
    private TextField txtId;

    @Override
    public void initialize(URL url, ResourceBundle rb) {
        initOrder();
    }    
    
    public void initOrder(){
        txtId.setText(Integer.toString(order2.getIdOrder()));
        txtTrader.setText(order2.getTrader());
        txtPapel.setText(order2.getPapel());
        txtQuantidade.setText(Integer.toString(order2.getQuantidade()));
        txtValor.setText(order2.getValor());  
    }
    
    @FXML
    private void btnAlterarClick(ActionEvent event) {
        atualiza();
    }

    @FXML
    private void btnVoltarClick(ActionEvent event) throws IOException {
        fechar();
    }

    public static Order getOrder2() {
        return order2;
    }

    public static void setOrder2(Order order2) {
        AlterarController.order2 = order2;
    }
    
    public void fechar() throws IOException{
        tela.close();
    }
    
    public void atualiza(){
        OrderDAO dao = new OrderDAO();

        Order o = new Order();
        o.setIdOrder(Integer.parseInt(txtId.getText()));
        o.setTrader(txtTrader.getText());
        o.setPapel(txtPapel.getText());
        o.setQuantidade(Integer.parseInt(txtQuantidade.getText()));
        o.setValor(txtValor.getText());

        try{
            
            if(dao.altera(o)){
                Alert alerta = new Alert(Alert.AlertType.CONFIRMATION,
                        "Order Atualizada", 
                        ButtonType.OK);
                alerta.showAndWait();
            }else{
                Alert alerta = new Alert(Alert.AlertType.WARNING,
                        "Erro ao Atualizar a Order ", 
                        ButtonType.OK);
                alerta.showAndWait();
            }
        }catch(SQLException ex){
            System.out.println("Erro: " + ex);
        }
        
    }
    
    
}
