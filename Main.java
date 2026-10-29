public class Main{

    protected final Usuario usuario;

    public static void Main(String[] args){

    }

    public String renderizarMenuInicial(int vida, int espaco, int moedas){
        return "==========Menu Inicial==========\n"+
        "Vida: "+vida+"/100"+"     "+"Espaços na mochila: "+espaco+"/6"+"     "+"Moedas: "+moedas+"\n\n"
        "# Escolha uma opção a seguir\n\n"+
        "1 - Adicionar arma (Adiciona uma arma à sua mochila)\n"+
        "2 - Editar Mochila\n"+
        "3 - Comprar Poção\n"+
        "4 - Iniciar Jogo\n"+
        "5 - Sair\n\n";
    }

    public String renderizarMenuInicial1(){
        return "==========Adicionar Arma==========\n\n"+
        "# Escolha uma opção a seguir\n\n"+
        "1 - Adicionar arma mágica (dão mais dano a inimigos físico)\n"+
        "2 - Adicionar arma física (dão mais dano a inimigos mágicos)\n\n"+
    }

    public String renderizarMenuMochila(){
        return "==========Editar Mochila==========\n\n"+
        renderizarMochila() +
        "# Escolha uma opção que queira editar\n\n"+
        "1 - Armas\n"+
        "2 - Artefatos\n"+
        "3 - Poções\n\n"+
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
        "# Escolha uma opção a seguir\n\n"+
        "1 - Editar Mochila\n"+
        "2 - Associar Artefato\n"+
        "3 - Remover Artefato\n"+
        "3 - Usar Poção\n"+
        "4 - Próxima rodada\n"+
        "5 - Sair\n\n";
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
    
}