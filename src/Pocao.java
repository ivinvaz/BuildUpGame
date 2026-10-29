package src;

import java.util.concurrent.ThreadLocalRandom;

public class Pocao{

    protected String nome;
    protected String categoria;
    protected int cura;
    protected int peso;
    
    public Pocao(){
        this.nome = "Poção";
        this.categoria = "Pocao";
        this.cura = ThreadLocalRandom.current().nextInt(25,36);
        this.peso = 1;
    }
}