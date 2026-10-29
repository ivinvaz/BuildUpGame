package src;

import java.util.concurrent.ThreadLocalRandom;

public class Artefato implements ItemInterface{

    protected String nome;
    protected String categoria;
    protected int aumento;
    protected int peso;

    private static final String[] CATEGORIAS = {"normal","magico","fisico"};
    private static final String[] NOMES = {
        "Chiclete Novo","Cueca Calvin Klein","Óleo para pele",
        "Dorflex","Amolador","Parafuso",
        "Chaveiro","Figurinha da Copa","Autoestima",
        "Amor próprio","Namorada","Namorado",
        "Elogio","Paçoquita","Strogonoff com Coca Gelada",
        "Batata-frita","Terapia Nos Dias Úteis","Camiseta do Flamengo",
        "Pedra Filosofal","Cálice Sagrado","Coca-Cola Gelada",
        "Efeito do Barão","Feriado Em Dia Útil","13º Salário",
        "Netherite","Estabilidade Financeira","3 Bolas de Gude",
        "Nota de Dois Reais","Meio Sabonete Dove","Dignidade Perdida",
        "Conhecimento Empírico Sobre O Demiurgo","Segunda Via do CPF","Volante Que Sabe Virar Jogo",
    };

    public Artefato(){
        this.nome = NOMES[ThreadLocalRandom.current().nextInt(0, NOMES.length)];
        this.categoria = CATEGORIAS[ThreadLocalRandom.current().nextInt(0, CATEGORIAS.length)];
        this.aumento = ThreadLocalRandom.current().nextInt(4, 7);
        this.peso = 0;
    }

    public int getAumento(){
        return this.aumento;
    }
}
