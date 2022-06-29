package br.com.estudo.n2_javafx;

import br.com.estudo.DAO.OrderDAO;
import br.com.estudo.DAO.PapelDAO;
import br.com.estudo.DAO.TraderDAO;
import br.com.estudo.model.Order;
import br.com.estudo.model.Papel;
import br.com.estudo.model.Trader;
import java.io.IOException;
import java.net.URL;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;
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
import javafx.scene.control.TableColumn;
import javafx.scene.control.TableView;
import javafx.scene.control.TextField;
import javafx.scene.control.cell.CheckBoxTableCell;
import javafx.scene.control.cell.PropertyValueFactory;
import javafx.stage.Stage;

public class AddPapelController implements Initializable {
    
    private Stage tela = new Stage();
    
    @FXML
    private Button btnPapel;
    @FXML
    private TextField txtNome;
    @FXML
    private Label lblNome;
    @FXML
    private Label lblSetor;
    @FXML
    private Button volta;

    private List<Papel> listaPapel = new ArrayList<>();
    private ObservableList<Papel> obsPapel;
    
    private ObservableList<Papel> produtos = 
            FXCollections.observableArrayList();
    @FXML
    private Button btnAlterar;
    @FXML
    private Button btnApagar;
    @FXML
    private TableColumn<Papel, Boolean> colSelecionar;
    @FXML
    private TableColumn<Papel, Integer> colId;
    @FXML
    private TableColumn<Papel, String> colNomePapel;
    @FXML
    private TableView<Papel> tblPapel;
    
    private Papel selecionado;

    @Override
    public void initialize(URL url, ResourceBundle rb) {
        initTable();

        tblPapel.getSelectionModel().selectedItemProperty().addListener(new ChangeListener(){
           @Override
           public void changed(ObservableValue ov, Object t, Object t1) {
              selecionado = (Papel) t1;
           }
           
       });
    }    
    
    private void initTable(){
        colSelecionar.setCellValueFactory(
                new PropertyValueFactory<>("selecionado"));
        colId.setCellValueFactory(
                new PropertyValueFactory<>("IdPapel"));
        colNomePapel.setCellValueFactory(
                new PropertyValueFactory<>("nomePapel"));

       
       colSelecionar.setCellFactory(
                CheckBoxTableCell.forTableColumn(colSelecionar));

       tblPapel.setItems(preencheTabela());
    }
    
    private ObservableList<Papel> preencheTabela() {
        PapelDAO dao = new PapelDAO();
        ObservableList<Papel> papel
            = FXCollections.observableArrayList();
        
        try {
            papel.addAll(dao.lista(""));
        } catch (SQLException ex) {
            System.out.println("Erro: " + ex);
        }
        
        return papel;
    }
    
    @FXML
    private void btnPapelClick(ActionEvent event) {

        PapelDAO dao = new PapelDAO();
        
        //objeto para incluir no banco, dados que podem vir da tela
        Papel p = new Papel();
        
        p.setNomePapel(txtNome.getText());
        
        try {
            if(dao.insere(p)) {
                Alert alerta = new Alert(Alert.AlertType.INFORMATION,
                    "Trade Incluído com Sucesso!!", 
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
        
        initTable();

    }

    @FXML
    private void voltaClick(ActionEvent event) throws IOException {
         App.setRoot("menu");
    }

    @FXML
    private void btnAlterarClick(ActionEvent event) {
     
    }

    @FXML
    private void btnApagarClick(ActionEvent event) throws SQLException {
        if(selecionado != null){
            PapelDAO dao = new PapelDAO();
            dao.remove(selecionado);
            Alert alerta = new Alert(Alert.AlertType.CONFIRMATION,
                    "Papel Excluido com Sucesso!!", 
                    ButtonType.OK);
            alerta.showAndWait();
            tblPapel.setItems(preencheTabela());
        }else{
            Alert alerta = new Alert(Alert.AlertType.WARNING,
                    "Erro ao Excluir Order", 
                    ButtonType.OK);
            alerta.showAndWait();
        }   
    }
  
}
