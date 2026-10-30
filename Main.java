import src.Arma;
import src.Artefato;
import src.Inimigo;
import src.ItemInterface;
import src.Mochila;
import src.Pocao;
import src.Usuario;

import java.util.Scanner;
import java.util.ArrayList;
import java.util.HashMap;

public class Main{

    public static void main(String[] args){
        int escolha;
        int inEscolha;
        int inInEscolha;

        Scanner scanner = new Scanner(System.in);
        System.out.print("Digite seu nome: ");
        String nome = scanner.nextLine();
        
        Usuario usuario = new Usuario(nome);
        Mochila mochila = usuario.getUsuarioMochila();

        System.out.print("\n\n");

        while(true){

            while(true){

                renderizarMenuInicial(usuario.getVida(), mochila.consultarEspacoUsado(), usuario.getMoedas());
                escolha = scanner.nextInt();
    
                if(!criarIntervalo(1,5).contains(escolha)){
                    System.out.print("Escolha um número válido");
                    continue;
                }else{
                    switch(escolha){
                        case 1:
                            while(true){
                                if(mochila.consultarEspacoUsado() >= 6){
                                    System.out.println("Você não possue mais espaço na mochila");
                                    break;
                                }

                                System.out.println("1 - Mágica");
                                System.out.println("2 - Física");
                                System.out.println("# Escolha um tipo de arma para adicionar:");
                                inEscolha = scanner.nextInt();

                                if(inEscolha == 2 && mochila.consultarEspacoUsado() >= 5){
                                    System.out.println("Você não possue mais espaço na mochila");
                                    break;
                                }

                                if(criarIntervalo(1,2).contains(inEscolha)){
                                    Arma novaArma = usuario.criarArma(inEscolha == 1 ? "magica" : "fisica");
                                    this.mochila.addItem(novaArma,null,null);
                                    System.out.println("> Parabens!!! Você recebeu uma " + novaArma.getNome() + "que possue " + novaArma.getDano() + " de dano");
                                    break;
                                }else{
                                    System.out.print("Escolha um número válido");
                                    continue;
                                }
                            }
                            continue;

                        case 2:
                            while(true){
                                renderizarMenuMochila();
                                inEscolha = scanner.nextInt();
                                if(criarIntervalo(1,3).contains(inEscolha)){
                                    switch(inEscolha){
                                        case 1:
                                            while(true){
                                                renderizarMochila1();
                                                inInEscolha = scanner.nextInt();
                                                if(criarIntervalo(1,mochila.consultarArmas().size()).contains(inInEscolha)){
                                                    mochila.removerItem(null,null,null);
                                                    break;
                                                }else{
                                                    System.out.print("Escolha um número válido");
                                                    continue;
                                                }
                                            }
                                        case 2:
                                            while(true){
                                                renderizarMochila2();
                                                inInEscolha = scanner.nextInt();
                                                if(criarIntervalo(1,mochila.consultarArtefatos().size()).contains(inInEscolha)){

                                                    //ADICIONAR LÓGICA DE SELEÇÃO AQUI

                                                    mochila.removerItem(null,null,null);
                                                    break;
                                                }else{
                                                    System.out.print("Escolha um número válido");
                                                    continue;
                                                }
                                            }
                                        case 3:
                                            while(true){
                                                renderizarMochila3();
                                                inInEscolha = scanner.nextInt();
                                                if(criarIntervalo(1,mochila.consultarPocoes().size()).contains(inInEscolha)){
                                                    mochila.removerItem(null,null,null);
                                                    break;
                                                }else{
                                                    System.out.print("Escolha um número válido");
                                                    continue;
                                                }
                                            }
                                    }
                                    break;
                                }else{
                                    System.out.print("Escolha um número válido");
                                    continue;
                                }
                            }
                            continue;

                        case 3:
                            if(mochila.consultarEspacoUsado() >= 6){
                                System.out.println("\n\nVocê não possue mais espaço na mochila \n\n");
                                continue;
                            }
                            if(usuario.getMoedas() < 1){
                                System.out.println("\n\nVocê não possue mais moedas na mochila \n\n");
                                continue;
                            }
                            usuario.useMoedas();
                            System.out.println("\n\n> Uma poção foi adicionada na sua mochila!\n\n");
                            continue;

                        case 4:

                        case 5:
                            System.out.println("\n\nObrigado por jogar!\n\n");
                            break;

                    }
                }

            }



        }

    }   

    public String renderizarMenuInicial(int vida, int espaco, int moedas){
        return "==========Menu Inicial==========\n"+
        "Vida: "+vida+"/100"+"     "+"Espaços na mochila: "+espaco+"/6"+"     "+"Moedas: "+moedas+"\n\n"+
        "1 - Adicionar arma (Adiciona uma arma à sua mochila)\n"+
        "2 - Editar Mochila\n"+
        "3 - Comprar Poção\n"+
        "4 - Iniciar Jogo\n"+
        "5 - Sair\n\n"+
        "# Escolha uma opção acima\n\n";
    }

    public String renderizarMenuInicial1(){
        return "==========Adicionar Arma==========\n\n"+
        "1 - Adicionar arma mágica (dão mais dano a inimigos físico)\n"+
        "2 - Adicionar arma física (dão mais dano a inimigos mágicos)\n\n"+
        "# Escolha uma opção acima\n\n";
    }

    public String renderizarMenuMochila(){
        return "==========Editar Mochila==========\n\n"+
        renderizarMochila() +
        "1 - Armas\n"+
        "2 - Artefatos\n"+
        "3 - Poções\n\n"+
        "# Escolha uma opção que queira editar acima\n\n";
    }

    public String renderizarMochila1(){
        return "==========Editar Mochila==========\n\n"+
        renderizarArmas() +
        "# Digite o número da arma que queira remover: "
    }

    public String renderizarMochila2(){
        return "==========Editar Mochila==========\n\n"+
        renderizarArtefatos() +
        "# Digite o número do artefatos que queira remover: "
    }

    public String renderizarMochila3(){
        return "==========Editar Mochila==========\n\n"+
        renderizarPocoes() +
        "# Digite o número da poção que queira remover: "
    }

    public String renderizarMenuEtapa(int vida, int espaco, int moedas){
        return "==========Menu Etapa==========\n"+
        "Vida: "+vida+"/100"+"     "+"Espaços na mochila: "+espaco+"/6"+"     "+"Moedas: "+moedas+"\n\n"
        "1 - Editar Mochila\n"+
        "2 - Associar Artefato\n"+
        "3 - Remover Artefato\n"+
        "3 - Usar Poção\n"+
        "4 - Próxima rodada\n"+
        "5 - Sair\n\n"+
        "# Escolha uma opção acima\n\n";
    }

    public String renderizarAssociacao(Mochila mochila, boolean associacao){
        String stringArmas;
        ArrayList<Arma> armas = mochila.consultarArmas();
        HashMap<Arma,Artefato> associados = mochila.consultarAssociados();
        ArrayList<Arma> associadosOuNaoAssociados = new ArrayList<>();

        for (Arma arma : armas) {
            if (associados.containsKey(arma) == associacao) {
                associadosOuNaoAssociados.add(arma);
            }
        }

        if(associadosOuNaoAssociados.isEmpty()){
            stringArmas = "Não possui armas para associar na mochila...";
        }else{
            stringArmas = "";
            for(int i = 0; i < associadosOuNaoAssociados.size(); i++){
                stringArmas += i + ". " + associadosOuNaoAssociados.get(i).getNome() + "\n";
            } 
        }

        return "========== ASSOCIAR ARTEFATO ==========\n" +
        stringArmas +
        "# Digite o número da arma que queira associar: ";
    }

    public String renderizarAssociacao2(Mochila mochila, boolean associacao){
        String stringArtefatos;
        ArrayList<Artefato> artefatos = mochila.consultarArtefatos();
        HashMap<Arma,Artefato> associados = mochila.consultarAssociados();
        ArrayList<Artefato> associadosOuNaoAssociados = new ArrayList<>();

        for (Artefato artefato : artefatos) {
            if (associados.containsValue(artefato) == !associacao) {
                associadosOuNaoAssociados.add(artefato);
            }
        }

        if(associadosOuNaoAssociados.isEmpty()){
            stringArtefatos = "Não possui artefatos para associar na mochila...";
        }else{
            stringArtefatos = "";
            for(int i = 0; i < associadosOuNaoAssociados.size(); i++){
                stringArtefatos += i + ". " + associadosOuNaoAssociados.get(i).getNome() + "\n";
            } 
        }

        return "========== ASSOCIAR ARTEFATO ==========\n" +
        stringArtefatos +
        "# Digite o número da arma que queira associar: ";
    }

    public String renderizarMochila(Mochila mochila){
        ArrayList<Pocao> pocoes = mochila.consultarPocoes();
        ArrayList<Arma> armas = mochila.consultarArmas();
        ArrayList<Artefato> artefatos = mochila.consultarArtefatos();
        
        String stringArmas;
        String stringArtefatos;
        String stringPocoes;

        if(armas.isEmpty()){
            stringArmas = "Não possui armas na mochila...";
        }else{
            stringArmas = "";
            for(int i = 0; i < armas.size(); i++){
                stringArmas += i + ". " + armas.get(i).getNome() + "\n";
            } 
        }

        if(artefatos.isEmpty()){
            stringArtefatos = "Não possui artefatos na mochila...";
        }else{
            stringArtefatos = "";
            for(int i = 0; i < artefatos.size(); i++){
                stringArtefatos += i + ". " + artefatos.get(i).getNome() + "\n";
            } 
        }

        if(pocoes.isEmpty()){
            stringPocoes = "Não possui Poções na mochila...";
        }else{
            stringPocoes = "";
            for(int i = 0; i < pocoes.size(); i++){
                stringPocoes += i + ". " + pocoes.get(i).getNome() + "\n";
            } 
        }

        return = "========== MOCHILA ==========\n"
        + "\n--- ARMAS ---\n" + stringArmas
        + "\n--- ARTEFATOS ---\n" + stringArtefatos
        + "\n--- POÇÕES ---\n" + stringPocoes
        + "\n=============================";
    }

    public String renderizarArtefatos(Mochila mochila){
        ArrayList<Artefato> artefatos = mochila.consultarArtefatos();

        if(artefatos.isEmpty()){
            String stringArtefatos = "Não possui Artefatos na mochila...";
        }else{
            String stringArtefatos = "";
            for(int i = 0; i < artefatos.size(); i++){
                stringArtefatos += i + ". " + artefatos.get(i).getNome() + "\n";
            } 
        }

        return "========== MOCHILA ==========\n"
        + "\n--- ARTEFATOS ---\n" + stringArtefatos
        + "\n=============================";
    }

    public String renderizarPocoes(Mochila mochila){
        ArrayList<Pocao> pocoes = mochila.consultarPocoes();

        if(pocoes.isEmpty()){
            String stringPocoes = "Não possui Poções na mochila...";
        }else{
            String stringPocoes = "";
            for(int i = 0; i < pocoes.size(); i++){
                stringPocoes += i + ". " + pocoes.get(i).getNome() + "\n";
            } 
        }

        return "========== MOCHILA ==========\n"
        + "\n--- POÇÕES ---\n" + stringPocoes
        + "\n=============================";
    }

    public String renderizarArmas(Mochila mochila){
        ArrayList<Arma> armas = mochila.consultarArmas();

        if(armas.isEmpty()){
            String stringArmas = "Não possui armas na mochila...";
        }else{
            String stringArmas = "";
            for(int i = 0; i < armas.size(); i++){
                stringArmas += i + ". " + armas.get(i).getNome() + "\n";
            } 
        }

        return "========== MOCHILA ==========\n"
        + "\n--- ARMAS ---\n" + stringArmas
        + "\n=============================";
    }

    public void IniciarJogo(int etapa){
        
    }
    
    public ArrayList<Integer> criarIntervalo(int inicio, int fim) {
        ArrayList<Integer> numeros = new ArrayList<>();
    
        for (int i = inicio; i <= fim; i++) {
            numeros.add(i);
        }
    
        return numeros;
    }
}