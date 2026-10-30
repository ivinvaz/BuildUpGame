package src;

import java.util.ArrayList;
import java.util.HashMap;

public class Mochila{

    protected ArrayList<Arma> armas;
    protected ArrayList<Artefato> artefatos;
    protected ArrayList<Pocao> pocoes;
    protected HashMap<Arma,Artefato> associados;
    protected int espacoUsado = 0;

    public Mochila(){
        this.armas = new ArrayList<>();
        this.artefatos = new ArrayList<>();
        this.pocoes = new ArrayList<>();
        this.associados = new HashMap<>();
    }

    public ArrayList<Arma> consultarArmas(){
        return this.armas;
    }

    public ArrayList<Artefato> consultarArtefatos(){
        return this.artefatos;
    }

    public HashMap<Arma,Artefato> consultarAssociados(){
        return this.associados;
    }

    public ArrayList<Pocao> consultarPocoes(){
        return this.pocoes;
    }

    public int consultarEspacoUsado(){
        return this.espacoUsado;
    }
    
    public int getPosItem(Arma arma, Artefato artefato, Pocao pocao){
        if(arma != null){
            return armas.indexOf(arma);
        }
        if(artefato != null){
            return artefatos.indexOf(artefato);
        }
        if(pocao != null){
            return pocoes.indexOf(pocao);
        }

        return -1;
    }

    public void addItem(Arma arma, Artefato artefato, Pocao pocao){
        if(arma != null){
            this.espacoUsado += arma.getCategoria().contains("magico") ? 1 : 2;
            armas.add(arma);
        }
        if(artefato != null){
            artefatos.add(artefato);
        }
        if(pocao != null){
            this.espacoUsado ++;
            pocoes.add(pocao);
        }

    }

    public void removerItem(Arma arma, Artefato artefato, Pocao pocao){
        if(arma != null){
            int posicao = getPosItem(arma,null,null);
            armas.remove(posicao);
        }
        if(artefato != null){
            int posicao = getPosItem(null,artefato,null);
            artefatos.remove(posicao);
        }
        if(pocao != null){
            int posicao = getPosItem(null,null,pocao);
            pocoes.remove(posicao);
        }
    }

    public int getDanoArmas(String categoriaInimigo) {
        int dano = 0;
    
        for (Arma arma : armas) {
            String categoriaArma = arma.getCategoria();
            int danoArma = arma.getDano();
        
            if (categoriaInimigo.equals("normal") && categoriaArma.equals("fisico")) {
                dano += danoArma / 2;
        
            } else if (categoriaInimigo.equals("magico") && categoriaArma.equals("fisico")) {
                dano += danoArma * 2;
        
            } else if (categoriaInimigo.equals("fisico") && categoriaArma.equals("magico")) {
                dano += danoArma * 2;
        
            } else {
                dano += danoArma;
            }
        }
    
        return dano;
    }

    public void associarArtefato(Arma arma, Artefato artefato){
        associados.put(arma,artefato);
    }

}