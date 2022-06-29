package br.com.estudo.n2_javafx;

import br.com.estudo.model.Order;
import javafx.application.Application;
import javafx.fxml.FXMLLoader;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.stage.Stage;

public class AlterarOrder extends Application {

    public static Stage tela;
    private String recebido;
    Order o1 = new Order();
    
    public AlterarOrder(Order o1){
        AlterarController.setOrder2(o1);
    }
    
    public static void setStage(Stage t) {
        tela = t;
    }
    
    @Override
    public void start(Stage tela) throws Exception {
        //passa o Stage recebido para a variavel
        //local da classe
        setStage(tela);
        
        //carrega o próximo FXML
        FXMLLoader fxmlLoader = new FXMLLoader(
                App.class.getResource("Alterar.fxml"));
        //efetua o carregamento na memória
        Parent root = fxmlLoader.load();
        
        //obtem o acesso ao objeto controller do formulario
        AlterarController controler = fxmlLoader.getController();
        
        //passa os dados necessários para a próxima tela
        controler.setOrder2(o1);
        //controler.atualiza();
        
        Scene scene = new Scene(root, 640, 480);
        
        tela.setScene(scene);
        tela.show();     
    }
}
