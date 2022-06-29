package br.com.estudo.model;

public class Trader {
    
    private String nomeTrader;
    
    public Trader(){
        
    }

    public Trader (String nomeTrader) {
        this.nomeTrader = nomeTrader;
    }
    

    public String getNomeTrader() {
        return nomeTrader;
    }

    public void setNomeTrader(String nomeTrader) {
        this.nomeTrader = nomeTrader;
    }
    
    @Override
    public String toString(){
        return getNomeTrader();
    }
    
    
}
