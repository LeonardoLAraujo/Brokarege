package br.com.estudo.n2_javafx;

import br.com.estudo.Banco.Banco;
import br.com.estudo.DAO.OrderDAO;
import br.com.estudo.model.Order;
import java.io.File;
import java.io.FileNotFoundException;
import java.io.FileOutputStream;
import java.io.IOException;
import java.net.URL;
import java.sql.SQLException;
import java.util.HashSet;
import java.util.List;
import java.util.ResourceBundle;
import java.util.Set;
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
import javafx.scene.control.TableColumn;
import javafx.scene.control.TableView;
import javafx.scene.control.TextField;
import javafx.scene.control.cell.CheckBoxTableCell;
import javafx.scene.control.cell.PropertyValueFactory;
import javafx.scene.input.MouseEvent;
import javafx.stage.Stage;
import org.w3c.dom.Attr;
import org.w3c.dom.CDATASection;
import org.w3c.dom.Comment;
import org.w3c.dom.DOMConfiguration;
import org.w3c.dom.DOMException;
import org.w3c.dom.DOMImplementation;
import org.w3c.dom.Document;
import org.w3c.dom.DocumentFragment;
import org.w3c.dom.DocumentType;
import org.w3c.dom.Element;
import org.w3c.dom.EntityReference;
import org.w3c.dom.NamedNodeMap;
import org.w3c.dom.Node;
import org.w3c.dom.NodeList;
import org.w3c.dom.ProcessingInstruction;
import org.w3c.dom.Text;
import org.w3c.dom.UserDataHandler;

public class ConsultaController implements Initializable {
    
    private Stage tela = new Stage();
    
    @FXML
    private Button btnAlterar;
    @FXML
    private Button btnApagar;
    @FXML
    private Button btnVoltar;
    @FXML
    private TableView<Order> tblOrder;
    @FXML
    private TableColumn<Order, Boolean> colSelecionado;
    @FXML
    private TableColumn<Order, Integer> colId;
    @FXML
    private TableColumn<Order, String> colTrader;
    @FXML
    private TableColumn<Order, String> colPapel;
    @FXML
    private TableColumn<Order, Integer> colQuantidade;
    @FXML
    private TableColumn<Order, String>colValor;
    @FXML
    private TextField txtPesquisar;
    @FXML
    private Button btnPesquisar;
    
    private java.sql.PreparedStatement pst;
    private java.sql.ResultSet rs;
    private Order selecionado;
    private ObservableList<Order> orders = FXCollections.observableArrayList();
    @FXML
    private Button btnAtualizar;
    
    @Override
    public void initialize(URL url, ResourceBundle rb) {
       
       initTable();
       
       tblOrder.getSelectionModel().selectedItemProperty().addListener(new ChangeListener(){
           @Override
           public void changed(ObservableValue ov, Object t, Object t1) {
              selecionado = (Order) t1;
           }
           
       });
    }    
    
    private void initTable(){
        colSelecionado.setCellValueFactory(
                new PropertyValueFactory<>("selecionado"));
        colId.setCellValueFactory(
                new PropertyValueFactory<>("IdOrder"));
        colTrader.setCellValueFactory(
                new PropertyValueFactory<>("trader"));
        colPapel.setCellValueFactory(
                new PropertyValueFactory<>("papel"));
        colQuantidade.setCellValueFactory(
                new PropertyValueFactory<>("quantidade"));
        colValor.setCellValueFactory(
                new PropertyValueFactory<>("valor"));
       
       colSelecionado.setCellFactory(
                CheckBoxTableCell.forTableColumn(colSelecionado));

       tblOrder.setItems(preencheTabela());
    }
    
    private ObservableList<Order> preencheTabela() {
        OrderDAO dao = new OrderDAO();
        ObservableList<Order> order
            = FXCollections.observableArrayList();
        
        try {
            order.addAll(dao.lista(""));
        } catch (SQLException ex) {
            System.out.println("Erro: " + ex);
        }
        
        return order;
    }
    
    
    @FXML
    private void btnPesquisarClick(ActionEvent event) {

        OrderDAO dao = new OrderDAO();

        Order p = new Order();

        //joga os dados da tela para o obj
        p.setPapel(txtPesquisar.getText());

        try {
            p = dao.buscaID(p);
            
            if (p != null) { //achou
                //joga para a tela os dados do bancp
                colTrader.setText(p.getTrader());
            }
            else {
                Alert alerta = new Alert(Alert.AlertType.WARNING);
                alerta.setTitle("Mensagem");
                alerta.setHeaderText("Order");
                alerta.setContentText("Order nao existe");

                alerta.showAndWait(); //exibe a mensagem
            }
        } catch (SQLException ex) {
                Alert alerta = new Alert(Alert.AlertType.ERROR);
                alerta.setTitle("Mensagem");
                alerta.setHeaderText("Order");
                alerta.setContentText("Erro na Pesquisa: " +
                            ex.getMessage());

                alerta.showAndWait(); //exibe a mensagem
        }

    }
   

    @FXML
    private void btnAlterarClick(ActionEvent event) throws IOException {
        
        if(selecionado != null){
            AlterarOrder alt = new AlterarOrder(selecionado);
            try{
                alt.start(new Stage());
            }catch(Exception ex){
                System.out.println("Erro: " + ex);
            }
        }else{
            Alert alerta = new Alert(Alert.AlertType.WARNING,
                    "Selecione uma Order", 
                    ButtonType.OK);
            alerta.showAndWait();
        }

    }

    @FXML
    private void btnApagarClick(ActionEvent event) throws SQLException {  
        if(selecionado != null){
            OrderDAO dao = new OrderDAO();
            dao.remove(selecionado);
            Alert alerta = new Alert(Alert.AlertType.CONFIRMATION,
                    "Order Excluido com Sucesso!!", 
                    ButtonType.OK);
            alerta.showAndWait();
            tblOrder.setItems(preencheTabela());
        }else{
            Alert alerta = new Alert(Alert.AlertType.WARNING,
                    "Erro ao Excluir Order", 
                    ButtonType.OK);
            alerta.showAndWait();
        }          
    }

    @FXML
    private void btnVoltarClick(ActionEvent event) throws IOException {
        App.setRoot("menu");
    }
    
    private ObservableList<Order> busca(){
        ObservableList<Order> pesquisaOrder = FXCollections.observableArrayList();
        
        return pesquisaOrder;
    }

    @FXML
    private void btnAtualizarClick(ActionEvent event) {
        initTable();
    }
    
}
