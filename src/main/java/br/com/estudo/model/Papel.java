package br.com.estudo.model;

import br.com.estudo.n2_javafx.AlterarController;
import javafx.beans.property.SimpleBooleanProperty;
import javafx.beans.property.SimpleIntegerProperty;
import javafx.beans.property.SimpleStringProperty;

public class Papel {
    
    
    private final SimpleBooleanProperty selecionado;
    private final SimpleIntegerProperty idPapel;
    private final SimpleStringProperty nomePapel;
 
    
    public Papel() {
        this.selecionado = new SimpleBooleanProperty(false);
        this.idPapel = new SimpleIntegerProperty(0);
        this.nomePapel = new SimpleStringProperty("");
    }
    
    public Papel(int idPapel, String nomePapel) {
        this.selecionado = new SimpleBooleanProperty(false);
        this.idPapel = new SimpleIntegerProperty(idPapel);
        this.nomePapel = new SimpleStringProperty(nomePapel);
    }
    
    public SimpleBooleanProperty selecionadoProperty() {
        return selecionado;
    }

    public SimpleIntegerProperty idPapelProperty() {
        return idPapel;
    }
    
    public SimpleStringProperty nomePapelPrroperty() {
        return nomePapel;
    }
    
    public void setNomePapel(String nomePapel) {
        this.nomePapel.set(nomePapel);
    }

    public String getNomePapel() {
        return nomePapel.get();
    }
    
    public void setIdPapel(int idPapel) {
        this.idPapel.set(idPapel);
    }
    
    public int getIdPapel() {
        return idPapel.get();
    }
    
     public boolean isSelecionado() {
        return selecionado.get();
    }

    public void setSelecionado(boolean selecionado) {
        this.selecionado.set(selecionado);
    }

    @Override
    public String toString() {
        return getNomePapel();
    }
    
    
    
}