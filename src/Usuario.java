package src;

import java.util.concurrent.ThreadLocalRandom;
import java.util.HashMap;

public class Usuario{

    protected String nome;
    protected int vida;
    protected int vidaMaxima;
    protected int moedas;
    protected Mochila mochila;

    public Usuario(String nome){
        this.nome = nome;
        this.vida = ThreadLocalRandom.current().nextInt(40,51);
        this.vidaMaxima = 50;
        this.moedas = ThreadLocalRandom.current().nextInt(1,3);
        this.mochila = new Mochila();
    }

    public Arma criarArma(String categoria){
        return new Arma(categoria);
    }

    public HashMap<String, Object> batalhar(Inimigo inimigo){

        int vidaInimigo = inimigo.getVida();
        int danoInimigo = inimigo.getDano();
        String nomeInimigo = inimigo.getNome();
        String categoriaInimigo = inimigo.getCategoria();

        int vidaUsuario = this.vida;
        int danoUsuario = this.mochila.getDanoArmas(categoriaInimigo);

        while(true){
            vidaInimigo -= danoUsuario;
            
            if(vidaInimigo <= 0){
                break;
            }

            vidaUsuario -= danoInimigo;

            if(vidaUsuario <= 0){
                break;
            }
        }

        HashMap<String, Object> resultado = new HashMap<>();

        if(vidaUsuario <= 0){
    
            resultado.put("venceu", false);
            resultado.put("vida", this.vida);
            resultado.put("mensagem", "O usuário não aguentou o embate contra " + nomeInimigo + " e perdeu.");
    
        }else{
            
            this.vida = vidaUsuario;
            resultado.put("venceu", true);
            resultado.put("vida", this.vida);
            resultado.put("mensagem", "O usuário venceu o embate contra " + nomeInimigo + " e avança para a próxima etapa com " + this.vida + " de vida.");
        }

        return resultado;

    }

    public int getVidaMaxima(){
        return this.vidaMaxima;
    }

    public Mochila getUsuarioMochila(){
        return this.mochila;
    }

    public int getVida(){
        return this.vida;
    }

    public int getMoedas(){
        return this.moedas;
    }

    public void useMoedas(int moedas){
        this.moedas --;
    }

    public void curar(int cura){
        this.vida += this.vidaMaxima * cura / 100;
        if (this.vida > this.vidaMaxima) {
            this.vida = this.vidaMaxima;
        }
    }
}
