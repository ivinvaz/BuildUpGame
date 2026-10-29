package src;

import java.util.concurrent.ThreadLocalRandom;

public class Inimigo{

    protected String nome;
    protected String categoria;
    protected int vida;
    protected int dano;
    protected int etapa;

    private static final String[] CATEGORIAS = {"normal","magico","fisico"};
    private static final String[] NOMES = {
        "Zé do caroço","Juninho malvadeza","Trem-bala",
        "Bob O Muito Mal","Thomas O Trem","Vascaíno",
        "Atleticano","Palmerense","Minotauro",
        "Medusa","Autoestima Baixa","Leão Marinho Dourado",
        "Goleiro Muralha 2017","Graves Feedado Full Build","Rayquaza Shiny IV/EV perfeito",
        "Simba Na Pedra Do Reino","Joelho do Ronaldo Fenômeno","Chefe Chato",
        "Cerveja Quente","Comida Fria","Peugeot 206",
        "Amor Não Correspondido","Feriado Em Final De Semana","Agiota Que Bate",
        "Cleiton Rasta","Cantor Pablo Tocando Somente As Melhores","Barões Da Pisadinha",
        "Goku de Crocs","Primo Rico","Taylor Swift",
        "Carnaval Em Casa","Jogador De LOL","Jogador de DOTA",
        "Heineken","Pinguim Caolho MUITO Bravo","Sono Acumulado"
    };

    public Inimigo(int etapa){
        this.nome = NOMES[ThreadLocalRandom.current().nextInt(0, NOMES.length)];
        this.categoria = CATEGORIAS[ThreadLocalRandom.current().nextInt(0, CATEGORIAS.length)];
        
        if(etapa <= 1){
            this.vida = ThreadLocalRandom.current().nextInt(35,46);
        }else if(etapa == 2){
            this.vida = ThreadLocalRandom.current().nextInt(50,66);
        }else{
            this.vida = ThreadLocalRandom.current().nextInt(75,96);
        }

        if(etapa <= 1){
            this.dano = ThreadLocalRandom.current().nextInt(6,10);
        }else if(etapa == 2){
            this.dano = ThreadLocalRandom.current().nextInt(9,13);
        }else{
            this.dano = ThreadLocalRandom.current().nextInt(12,17);
        }

        this.etapa = etapa;
    }

    public int getDano(){
        return this.dano;
    }
    
    public int getVida(){
        return this.vida;
    }
    
    public String getCategoria(){
        return this.categoria;
    }
    
    public String getNome(){
        return this.nome;
    }
}