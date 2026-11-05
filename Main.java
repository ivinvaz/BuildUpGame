import src.Arma;
import src.Artefato;
import src.Inimigo;
import src.Mochila;
import src.Pocao;
import src.Usuario;

import java.util.Scanner;
import java.util.ArrayList;
import java.util.HashMap;

public class Main{

    static Scanner scanner = new Scanner(System.in);
    static String nome;
    static Usuario usuario;
    static Mochila mochila;
    static int etapa = 1;
    static final int limiteNaMochila = 5;
    static HashMap<Integer,Inimigo> inimigosPorEtapa = new HashMap<>();

    public static void main(String[] args){

        System.out.print("Digite seu nome: ");
        nome = scanner.nextLine();

        novoJogo();

        System.out.print("\n\n");

        while(true){

            System.out.print(renderizarMenuInicial(usuario.getVida(), mochila.consultarEspacoUsado(), usuario.getMoedas()));
            int escolha = lerInt();

            if(!criarIntervalo(1,5).contains(escolha)){
                System.out.println("Escolha um número válido");
                continue;
            }

            switch(escolha){
                case 1:
                    adicionarArma();
                    break;

                case 2:
                    editarMochila();
                    break;

                case 3:
                    comprarPocao();
                    break;

                case 4:
                    iniciarJogo();
                    break;

                case 5:
                    System.out.println("\n\nObrigado por jogar!\n\n");
                    return;
            }
        }
    }

    public static void novoJogo(){
        inimigosPorEtapa.clear();
        usuario = new Usuario(nome);
        mochila = usuario.getUsuarioMochila();
        etapa = 1;
    }

    public static void adicionarArma(){
        while(true){
            if(mochila.consultarEspacoUsado() >= limiteNaMochila){
                System.out.println("Você não possui mais espaço na mochila");
                return;
            }

            System.out.println("1 - Mágica");
            System.out.println("2 - Física");
            System.out.println("# Escolha um tipo de arma para adicionar:");
            int inEscolha = lerInt();

            if(criarIntervalo(1,2).contains(inEscolha)){
                Arma novaArma = usuario.criarArma(inEscolha == 1 ? "magico" : "fisico");

                if(mochila.consultarEspacoUsado() + novaArma.getPeso() > limiteNaMochila){
                    System.out.println("Você não possui mais espaço na mochila");
                    return;
                }  
                   
                mochila.addItem(novaArma,null,null);
                System.out.println("> Parabéns!!! Você recebeu: " + novaArma.getNome() + ", que possui " + novaArma.getDano(mochila) + " de dano\n");
                return;
            }else{
                System.out.println("Escolha um número válido");
            }
        }
    }

    public static void comprarPocao(){
        Pocao novaPocao = new Pocao();
        if(mochila.consultarEspacoUsado() + nova.getPeso() > limiteNaMochila){
            System.out.println("\n\nVocê não possui mais espaço na mochila \n\n");
            return;
        }
        if(usuario.getMoedas() < 1){
            System.out.println("\n\nVocê não possui mais moedas \n\n");
            return;
        }
        usuario.useMoedas(1);
        mochila.addItem(null,null,novaPocao);
        System.out.println("\n\n> Uma poção foi adicionada na sua mochila!\n\n");
    }

    public static void iniciarJogo(){

        if(mochila.consultarArmas().isEmpty()){
            System.out.println("\n\nVocê precisa de ao menos uma arma na mochila para iniciar\n\n");
            return;
        }

        while(true){

            inimigosPorEtapa.computeIfAbsent(etapa, Inimigo::new);
            int continuar;

            while(true){
                System.out.println("\nVocê está prestes a enfrentar o inimigo " + inimigosPorEtapa.get(etapa).getNome() + " que possui " + inimigosPorEtapa.get(etapa).getVida() + " de vida, " + inimigosPorEtapa.get(etapa).getDano() + " de dano, e categoria " + inimigosPorEtapa.get(etapa).getCategoria());
                System.out.println("\n Você deseja continuar: \n 1. Sim \n 2. Não");
                continuar = lerInt();
                if(criarIntervalo(1,2).contains(continuar)){
                    break;
                }
                System.out.println("Escolha um número válido");
            }

            if(continuar == 2){
                return;
            }

            HashMap<String, Object> resultado = usuario.batalhar(inimigosPorEtapa.get(etapa));
            boolean resultadoVenceu = (boolean) resultado.get("venceu");
            String resultadoMensagem = (String) resultado.get("mensagem");

            System.out.println("\n" + resultadoMensagem + "\n");

            if(!resultadoVenceu){
                System.out.println("Fim de jogo! Uma nova partida será iniciada.\n\n");
                novoJogo();
                return;
            }

            if(etapa >= 3){
                System.out.println("\n\nParabéns, " + nome + "! Você venceu todas as etapas!\n\nObrigado por jogar!\n\n");
                System.exit(0);
            }

            etapa ++;

            Artefato premio = new Artefato();
            mochila.addItem(null,premio,null);
            System.out.println("> Prêmio da batalha: você recebeu o artefato " + premio.getNome() + " (+" + premio.getAumento() + " de dano quando associado a uma arma)\n");

            menuEtapa();
        }
    }

    public static void menuEtapa(){
        while(true){
            System.out.print(renderizarMenuEtapa(usuario.getVida(), mochila.consultarEspacoUsado(), usuario.getMoedas()));
            int escolha = lerInt();

            if(!criarIntervalo(1,6).contains(escolha)){
                System.out.println("Escolha um número válido");
                continue;
            }

            switch(escolha){
                case 1:
                    editarMochila();
                    break;

                case 2:
                    associarArtefato();
                    break;

                case 3:
                    removerAssociacao();
                    break;

                case 4:
                    usarPocao();
                    break;

                case 5:
                    return;

                case 6:
                    System.out.println("\n\nObrigado por jogar!\n\n");
                    System.exit(0);
            }
        }
    }

    public static void editarMochila(){
        while(true){
            System.out.print(renderizarMenuMochila(mochila));
            int escolha = lerInt();

            if(escolha == 0){
                return;
            }

            if(!criarIntervalo(1,3).contains(escolha)){
                System.out.println("Escolha um número válido");
                continue;
            }

            switch(escolha){
                case 1:
                    removerArma();
                    break;
                case 2:
                    removerArtefato();
                    break;
                case 3:
                    removerPocao();
                    break;
            }
            return;
        }
    }

    public static void removerArma(){
        ArrayList<Arma> armas = mochila.consultarArmas();
        if(armas.isEmpty()){
            System.out.println("\n\nVocê não possui armas na mochila\n\n");
            return;
        }

        while(true){
            System.out.print(renderizarMochila1(mochila));
            int inInEscolha = lerInt();

            if(inInEscolha == 0){
                return;
            }

            if(criarIntervalo(1,armas.size()).contains(inInEscolha)){
                Arma armaEscolhida = armas.get(inInEscolha - 1);
                mochila.removerItem(armaEscolhida,null,null);
                System.out.println("\n\n> " + armaEscolhida.getNome() + " foi removida da mochila\n\n");
                return;
            }else{
                System.out.println("Escolha um número válido");
            }
        }
    }

    public static void removerArtefato(){
        ArrayList<Artefato> artefatos = mochila.consultarArtefatos();
        if(artefatos.isEmpty()){
            System.out.println("\n\nVocê não possui artefatos na mochila\n\n");
            return;
        }

        while(true){
            System.out.print(renderizarMochila2(mochila));
            int inInEscolha = lerInt();

            if(inInEscolha == 0){
                return;
            }

            if(criarIntervalo(1,artefatos.size()).contains(inInEscolha)){
                Artefato artefatoEscolhido = mochila.findByIndexArtefato(inInEscolha - 1);
                mochila.removerItem(null,artefatoEscolhido,null);
                System.out.println("\n\n> " + artefatoEscolhido.getNome() + " foi removido da mochila\n\n");
                return;
            }else{
                System.out.println("Escolha um número válido");
            }
        }
    }

    public static void removerPocao(){
        ArrayList<Pocao> pocoes = mochila.consultarPocoes();
        if(pocoes.isEmpty()){
            System.out.println("\n\nVocê não possui poções na mochila\n\n");
            return;
        }

        while(true){
            System.out.print(renderizarMochila3(mochila));
            int inInEscolha = lerInt();

            if(inInEscolha == 0){
                return;
            }

            if(criarIntervalo(1,pocoes.size()).contains(inInEscolha)){
                Pocao pocaoEscolhida = pocoes.get(inInEscolha - 1);
                mochila.removerItem(null,null,pocaoEscolhida);
                System.out.println("\n\n> A poção foi removida da mochila\n\n");
                return;
            }else{
                System.out.println("Escolha um número válido");
            }
        }
    }

    public static void associarArtefato(){
        ArrayList<Arma> armasLivres = new ArrayList<>();
        for(Arma a : mochila.consultarArmas()){
            if(!mochila.consultarAssociados().containsKey(a)){
                armasLivres.add(a);
            }
        }
        ArrayList<Artefato> artefatosLivres = new ArrayList<>();
        for(Artefato a : mochila.consultarArtefatos()){
            if(!mochila.consultarAssociados().containsValue(a)){
                artefatosLivres.add(a);
            }
        }

        if(armasLivres.isEmpty() || artefatosLivres.isEmpty()){
            System.out.println("\n\nVocê precisa ter ao menos uma arma e um artefato sem associação\n\n");
            return;
        }

        Arma armaEscolhida;
        while(true){
            System.out.print(renderizarAssociacao(mochila, false));
            int inInEscolha = lerInt();

            if(inInEscolha == 0){
                return;
            }

            if(criarIntervalo(1, armasLivres.size()).contains(inInEscolha)){
                armaEscolhida = armasLivres.get(inInEscolha - 1);
                break;
            }else{
                System.out.println("Escolha um número válido");
            }
        }

        Artefato artefatoEscolhido;
        while(true){
            System.out.print(renderizarAssociacao2(mochila, false));
            int inInEscolha = lerInt();

            if(inInEscolha == 0){
                return;
            }

            if(criarIntervalo(1, artefatosLivres.size()).contains(inInEscolha)){
                artefatoEscolhido = artefatosLivres.get(inInEscolha - 1);
                break;
            }else{
                System.out.println("Escolha um número válido");
            }
        }

        mochila.associarArtefato(armaEscolhida, artefatoEscolhido);
        System.out.println("\n\n> " + artefatoEscolhido.getNome() + " foi associado à arma " + armaEscolhida.getNome() + "\n\n");
    }

    public static void removerAssociacao(){
        ArrayList<Arma> armasAssociadas = new ArrayList<>();
        for(Arma a : mochila.consultarArmas()){
            if(mochila.consultarAssociados().containsKey(a)){
                armasAssociadas.add(a);
            }
        }

        if(armasAssociadas.isEmpty()){
            System.out.println("\n\nNenhuma arma possui artefato associado\n\n");
            return;
        }

        while(true){
            System.out.print(renderizarAssociacao(mochila, true));
            int inInEscolha = lerInt();

            if(inInEscolha == 0){
                return;
            }

            if(criarIntervalo(1, armasAssociadas.size()).contains(inInEscolha)){
                Arma armaDesassociada = armasAssociadas.get(inInEscolha - 1);
                mochila.consultarAssociados().remove(armaDesassociada);
                System.out.println("\n\n> O artefato foi removido da arma " + armaDesassociada.getNome() + "\n\n");
                return;
            }else{
                System.out.println("Escolha um número válido");
            }
        }
    }

    public static void usarPocao(){
        if(mochila.consultarPocoes().isEmpty()){
            System.out.println("\n\nVocê não possui poções na mochila\n\n");
            return;
        }
        if(usuario.getVida() >= usuario.getVidaMaxima()){
            System.out.println("\n\nSua vida já está cheia\n\n");
            return;
        }

        Pocao pocaoUsada = mochila.consultarPocoes().get(0);
        usuario.curar(pocaoUsada.getCura());
        mochila.removerItem(null,null,pocaoUsada);
        System.out.println("\n\n> Você usou uma poção e agora está com " + usuario.getVida() + " de vida!\n\n");
    }

    public static String renderizarMenuInicial(int vida, int espaco, int moedas){
        return "==========Menu Inicial==========\n"+
        "Vida: "+vida+"/50"+"     "+"Espaços na mochila: "+espaco+"/5"+"     "+"Moedas: "+moedas+"\n\n"+
        "1 - Adicionar arma (Adiciona uma arma à sua mochila)\n"+
        "2 - Editar Mochila\n"+
        "3 - Comprar Poção\n"+
        "4 - Iniciar Jogo\n"+
        "5 - Sair\n\n"+
        "# Escolha uma opção acima\n\n";
    }

    public static String renderizarMenuMochila(Mochila mochila){
        return "==========Editar Mochila==========\n\n"+
        renderizarMochila(mochila) + "\n\n" +
        "1 - Armas\n"+
        "2 - Artefatos\n"+
        "3 - Poções\n"+
        "0 - Voltar\n\n"+
        "# Escolha uma opção que queira editar acima\n\n";
    }

    public static String renderizarMochila1(Mochila mochila){
        return "==========Editar Mochila==========\n\n"+
        renderizarArmas(mochila) + "\n" +
        "# Digite o número da arma que queira remover (0 para voltar): ";
    }

    public static String renderizarMochila2(Mochila mochila){
        return "==========Editar Mochila==========\n\n"+
        renderizarArtefatos(mochila) + "\n" +
        "# Digite o número do artefato que queira remover (0 para voltar): ";
    }

    public static String renderizarMochila3(Mochila mochila){
        return "==========Editar Mochila==========\n\n"+
        renderizarPocoes(mochila) + "\n" +
        "# Digite o número da poção que queira remover (0 para voltar): ";
    }

    public static String renderizarMenuEtapa(int vida, int espaco, int moedas){
        return "==========Menu Etapa==========\n"+
        "Vida: "+vida+"/50"+"     "+"Espaços na mochila: "+espaco+"/5"+"     "+"Moedas: "+moedas+"\n\n"+
        "1 - Editar Mochila\n"+
        "2 - Associar Artefato\n"+
        "3 - Remover Artefato\n"+
        "4 - Usar Poção\n"+
        "5 - Próxima rodada\n"+
        "6 - Sair\n\n"+
        "# Escolha uma opção acima\n\n";
    }

    public static String renderizarAssociacao(Mochila mochila, boolean associacao){
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
            stringArmas = associacao ? "Nenhuma arma possui artefato associado...\n" : "Não possui armas para associar na mochila...\n";
        }else{
            stringArmas = listaArmas(associadosOuNaoAssociados, associados);
        }

        return (associacao ? "========== REMOVER ARTEFATO ==========\n" : "========== ASSOCIAR ARTEFATO ==========\n") +
        stringArmas +
        (associacao ? "# Digite o número da arma que queira remover o artefato (0 para voltar): " : "# Digite o número da arma que queira associar (0 para voltar): ");
    }

    public static String renderizarAssociacao2(Mochila mochila, boolean associacao){
        String stringArtefatos;
        ArrayList<Artefato> artefatos = mochila.consultarArtefatos();
        HashMap<Arma,Artefato> associados = mochila.consultarAssociados();
        ArrayList<Artefato> associadosOuNaoAssociados = new ArrayList<>();

        for (Artefato artefato : artefatos) {
            if (associados.containsValue(artefato) == associacao) {
                associadosOuNaoAssociados.add(artefato);
            }
        }

        if(associadosOuNaoAssociados.isEmpty()){
            stringArtefatos = "Não possui artefatos para associar na mochila...\n";
        }else{
            stringArtefatos = listaArtefatos(associadosOuNaoAssociados);
        }

        return "========== ASSOCIAR ARTEFATO ==========\n" +
        stringArtefatos +
        "# Digite o número do artefato que queira associar (0 para voltar): ";
    }

    public static String renderizarMochila(Mochila mochila){
        return "========== MOCHILA ==========\n"
        + "\n--- ARMAS ---\n" + stringArmas(mochila)
        + "\n--- ARTEFATOS ---\n" + stringArtefatos(mochila)
        + "\n--- POÇÕES ---\n" + stringPocoes(mochila)
        + "\n=============================";
    }

    public static String renderizarArtefatos(Mochila mochila){
        return "========== MOCHILA ==========\n"
        + "\n--- ARTEFATOS ---\n" + stringArtefatos(mochila)
        + "\n=============================";
    }

    public static String renderizarPocoes(Mochila mochila){
        return "========== MOCHILA ==========\n"
        + "\n--- POÇÕES ---\n" + stringPocoes(mochila)
        + "\n=============================";
    }

    public static String renderizarArmas(Mochila mochila){
        return "========== MOCHILA ==========\n"
        + "\n--- ARMAS ---\n" + stringArmas(mochila)
        + "\n=============================";
    }

    public static String stringArmas(Mochila mochila){
        if(mochila.consultarArmas().isEmpty()){
            return "Não possui armas na mochila...\n";
        }
        return listaArmas(mochila.consultarArmas(), mochila.consultarAssociados());
    }

    public static String stringArtefatos(Mochila mochila){
        if(mochila.consultarArtefatos().isEmpty()){
            return "Não possui artefatos na mochila...\n";
        }
        return listaArtefatos(mochila.consultarArtefatos());
    }

    public static String stringPocoes(Mochila mochila){
        if(mochila.consultarPocoes().isEmpty()){
            return "Não possui Poções na mochila...\n";
        }
        String resultado = "";
        ArrayList<Pocao> pocoes = mochila.consultarPocoes();
        for(int i = 0; i < pocoes.size(); i++){
            resultado += (i + 1) + ". " + "Poção de cura" + " [cura " + pocoes.get(i).getCura() + "]\n";
        }
        return resultado;
    }

    public static String listaArmas(ArrayList<Arma> armas, HashMap<Arma,Artefato> associados){
        String resultado = "";
        for(int i = 0; i < armas.size(); i++){
            Arma arma = armas.get(i);
            resultado += (i + 1) + ". " + arma.getNome() + " [" + arma.getCategoria() + ", dano " + arma.getDano(mochila) + "]";
            if(associados.containsKey(arma)){
                resultado += " + " + associados.get(arma).getNome();
            }
            resultado += "\n";
        }
        return resultado;
    }

    public static String listaArtefatos(ArrayList<Artefato> artefatos){
        String resultado = "";
        for(int i = 0; i < artefatos.size(); i++){
            resultado += (i + 1) + ". " + artefatos.get(i).getNome() + " [+" + artefatos.get(i).getAumento() + " de dano]\n";
        }
        return resultado;
    }

    public static int lerInt(){
        String linha = scanner.nextLine().trim();
        try{
            return Integer.parseInt(linha);
        }catch(NumberFormatException e){
            return -1;
        }
    }

    public static ArrayList<Integer> criarIntervalo(int inicio, int fim) {
        ArrayList<Integer> numeros = new ArrayList<>();

        for (int i = inicio; i <= fim; i++) {
            numeros.add(i);
        }

        return numeros;
    }
}