package src;

import java.util.concurrent.ThreadLocalRandom;

public class Arma implements ItemInterface{

    protected String nome;
    protected String categoria;
    protected int dano;
    protected int peso;

    private static final String[] NOMES = {
        "Espada de balão","Banana Sem Casca","Cabo de Vassoura",
        "Espátula do Bob Esponja","Zhonya","Garrafa D'água Stanley",
        "Colher de Pedreiro","Machado de Isopor","Nunchaku de Salsicha",
        "Marreta Inflável","Pão de Queijo Contundente","Pepino de Guerra",
        "Placa de Pare","Cabo HDMI","Nugget de Frango",
        "Bola de Futebol Molhada","Pão Francês Velho",
        "Patinho de Borracha","Bambolê Verde Água","Trindade",
        "Bastão Desnecessariamente Grande","O Anel","Chapéu da Morte de Rabadon",
        "Death Note","Zangetsu","Manopla do Infinito",
        "Omnitrix","Sabre de Luz","Cubo Mágico De Uma Só Cor",
        "Portal Gun","Chinelo Esquerdo","Mjölnir",
        "Excalibur","Espada de Diamante","Bola Furada",
        "Chuchu","Coelho da Mônica","Tapete Do Aladin",
        "Panela Com O Fundo Queimado","Caneta Sem Tinta","Régua Torta"
    };


    public Arma(String categoria){
        this.nome = NOMES[ThreadLocalRandom.current().nextInt(0, NOMES.length)];
        this.categoria = categoria;
        this.dano = categoria.equals("fisico") ? ThreadLocalRandom.current().nextInt(18, 25) : ThreadLocalRandom.current().nextInt(9, 13);
        this.peso = categoria.equals("fisico") ? 2 : 1;
    }

    @Override
    public String getNome(){
        return this.nome;
    }

    @Override
    public String getCategoria(){
        return this.categoria;
    }

    public int getDano(Mochila mochila){
        Artefato artefato = mochila.consultarAssociados().get(this);
        return this.dano + (artefato != null ? artefato.getAumento() : 0);
    }

    @Override
    public int getPeso(){
        return this.peso;
    }

}