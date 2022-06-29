package br.com.estudo.model;

import javafx.beans.property.SimpleBooleanProperty;
import javafx.beans.property.SimpleIntegerProperty;
import javafx.beans.property.SimpleStringProperty;

public class Order {
    
    private final SimpleBooleanProperty selecionado;
    private final SimpleIntegerProperty IdOrder;
    private final SimpleStringProperty trader;
    private final SimpleStringProperty papel;
    private final SimpleIntegerProperty quantidade;
    private final SimpleStringProperty valor;

    
    //construtor
    public Order() {
        this.selecionado = new SimpleBooleanProperty(false);
        this.IdOrder = new SimpleIntegerProperty(0);
        this.trader = new SimpleStringProperty("");
        this.papel = new SimpleStringProperty("");
        this.quantidade = new SimpleIntegerProperty(0);
        this.valor = new SimpleStringProperty("");
    }
    
    public Order(int IdOrder,String trader, String papel, int quantidade, String valor) {
        this.selecionado = new SimpleBooleanProperty(false);
        this.IdOrder = new SimpleIntegerProperty(IdOrder);
        this.trader = new SimpleStringProperty(trader);
        this.papel = new SimpleStringProperty(papel);
        this.quantidade = new SimpleIntegerProperty(quantidade);
        this.valor = new SimpleStringProperty(valor);
    }
    
    //retorna as propriedades
    public SimpleBooleanProperty selecionadoProperty() {
        return selecionado;
    }

    public SimpleIntegerProperty IdOrderProperty() {
        return IdOrder;
    }
    
    public SimpleStringProperty traderProperty() {
        return trader;
    }
    
    public SimpleStringProperty papelProperty() {
        return papel;
    }
    
    public SimpleIntegerProperty quantidadeProperty() {
        return quantidade;
    }
    
    public SimpleStringProperty valorProperty() {
        return valor;
    }
    
     //getters e setters do model
    public void setTrader(String trader) {
        this.trader.set(trader);
    }

    public String getTrader() {
        return trader.get();
    }
    
    public void setIdOrder(int IdOrder) {
        this.IdOrder.set(IdOrder);
    }
    
    public int getIdOrder() {
        return IdOrder.get();
    }
    
    public void setPapel(String papel){
        this.papel.set(papel);
    }
    
    public String getPapel(){
        return papel.get();
    }
    
    public void setQuantidade(int quantidade){
        this.quantidade.set(quantidade);
    }
    
    public int getQuantidade(){
        return quantidade.get();
    }
    
    public void setValor(String valor){
        this.valor.set(valor);
    }
    
    public String getValor(){
        return valor.get();
    }
    
    public boolean isSelecionado() {
        return selecionado.get();
    }

    public void setSelecionado(boolean selecionado) {
        this.selecionado.set(selecionado);
    }
    
}
