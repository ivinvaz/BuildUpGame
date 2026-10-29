# 1. Sobre o programa

O projeto consiste no desenvolvimento de um simulador de gerenciamento de inventário combinado com um jogo simples de criação de builds (conjuntos de equipamentos e itens utilizados pelo jogador durante os combates).

O programa permitirá que o usuário cadastre, visualize, edite e remova itens de seu inventário, além de organizar seus equipamentos para montar uma build que será utilizada em uma sequência de três batalhas contra inimigos gerados aleatoriamente.

O sistema será dividido em duas partes principais: **gerenciamento de inventário** e **modo de jogo**. A primeira será responsável pelo cadastro e pela organização dos itens, enquanto a segunda utilizará os atributos desses itens para determinar o desempenho do jogador durante os combates.

As principais funcionalidades do programa são:

- **Gerenciamento de itens (CRUD):** disponibilizar operações de criação, consulta, edição e exclusão dos itens cadastrados no inventário.

- **Categorias predefinidas:** os itens serão organizados em seis categorias: arma física, arma mágica, artefato comum, artefato mágico, artefato físico e poção. Essa classificação determinará as propriedades, limitações e possibilidades de utilização de cada item.

- **Sistema de inventário:** o jogador terá uma quantidade limitada de espaços na mochila. Cada item poderá ocupar uma quantidade específica desses espaços, exigindo que o usuário organize seus equipamentos antes de iniciar uma partida.

- **Sistema de builds:** o jogador poderá selecionar e organizar seus equipamentos, combinando armas e artefatos para obter atributos que favoreçam seu desempenho nas batalhas.

- **Sistema de combate:** após finalizar a preparação da build, o jogador poderá enfrentar três inimigos distintos, cada um com atributos de vida, dano e afinidade gerados aleatoriamente.

- **Sistema de progressão:** ao derrotar um inimigo, o jogador poderá receber artefatos que poderão ser associados ao personagem ou às suas armas, permitindo melhorar seus atributos para os próximos combates.

O objetivo principal será derrotar os três inimigos consecutivamente. Caso o jogador seja derrotado em qualquer uma das batalhas, a partida será encerrada. Caso consiga vencer todos os confrontos, receberá uma pontuação final calculada com base em seu desempenho.

# 2. Sobre os itens

Os itens são os principais elementos do sistema de inventário e possuem características próprias que determinam como podem ser utilizados durante a partida.

Cada item pertence obrigatoriamente a uma das seis categorias predefinidas. Essa classificação não serve apenas para organizar o inventário, mas também estabelece regras de utilização, como o espaço ocupado na mochila, os atributos que podem ser modificados e as condições necessárias para associar determinados itens a outros equipamentos.

A divisão em categorias permite que o sistema controle as interações entre os itens sem tornar sua utilização excessivamente complexa para o usuário.

## 2.1. Armas

As armas são os principais recursos ofensivos do jogador, sendo responsáveis por causar dano aos inimigos durante os combates.

Cada arma possui um valor de dano gerado aleatoriamente dentro de um intervalo previamente estabelecido. Para o balanceamento inicial, serão utilizados os seguintes intervalos: armas físicas causarão entre 18 e 24 pontos de dano, enquanto armas mágicas causarão entre 9 e 12 pontos de dano. Esses valores poderão ser reajustados conforme os resultados dos testes de jogabilidade.

O usuário poderá escolher livremente o nome de suas armas, enquanto a categoria determinará suas características e regras de utilização.

Existem duas categorias de armas:

**Arma física**

As armas físicas causam entre 18 e 24 pontos de dano e ocupam dois espaços na mochila. Seu dano individual é superior ao das armas mágicas, mas o maior peso exige mais planejamento na organização do inventário.

Sua estrutura padrão é:

```java

{

    nome: "",

    categoria: 1,

    dano: 0,

    peso: 2,

    tem_artefato: false,

    artefato: 0,

}

```

O atributo \`tem_artefato\` indica se a arma possui algum artefato associado. Já o atributo \`artefato\` armazena a referência ou identificação do artefato vinculado à arma.

**Arma mágica**

As armas mágicas causam entre 9 e 12 pontos de dano e ocupam apenas um espaço na mochila. Embora causem menos dano individualmente, sua menor ocupação permite carregar mais equipamentos e explorar melhor a capacidade do inventário.

Sua estrutura padrão é:

```java

{

    nome: "",

    categoria: 2,

    dano: 0,

    peso: 1,

    tem_artefato: false,

    artefato: 0,

}

```

Assim como nas armas físicas, os atributos \`tem_artefato\` e \`artefato\` permitem controlar a associação de artefatos à arma, possibilitando aumentar seu poder ofensivo.

## 2.2. Artefatos

Os artefatos são itens especiais utilizados para aprimorar os atributos do jogador ou de suas armas.

Diferentemente das armas, eles não possuem uma função ofensiva independente. Sua principal finalidade é potencializar equipamentos ou atributos existentes, oferecendo vantagens estratégicas para as próximas batalhas.

Os artefatos serão obtidos como recompensa após a vitória contra um inimigo. Ao receber um artefato, o jogador poderá associá-lo ao personagem ou a uma arma, desde que a categoria do artefato seja compatível com o alvo escolhido.

Os valores de aprimoramento serão gerados aleatoriamente dentro de intervalos predefinidos, garantindo variações entre as recompensas recebidas. Para o balanceamento inicial, os artefatos comuns concederão entre 3 e 6 pontos adicionais de dano quando associados a uma arma, ou entre 5 e 10 pontos de vida máxima quando associados ao personagem. Artefatos mágicos e físicos concederão, respectivamente, entre 4 e 7 pontos adicionais de dano à arma compatível. Esses intervalos poderão ser ajustados após os testes.

Cada arma poderá ter somente um artefato associado por vez. Assim, não será possível acumular vários artefatos na mesma arma. Essa limitação mantém os aprimoramentos distribuídos e evita a concentração excessiva de poder em um único equipamento.

Existem três categorias de artefatos:

**Artefato comum**

É um item de aprimoramento versátil, podendo ser associado ao personagem, a uma arma física ou a uma arma mágica.

Seu efeito poderá aumentar o dano de uma arma em 3 a 6 pontos ou ampliar a vida máxima do personagem em 5 a 10 pontos, dependendo do alvo ao qual for associado. O valor exato do aprimoramento será determinado aleatoriamente dentro do intervalo correspondente.

Sua estrutura padrão é:

```java

{

    nome: "",

    categoria: 3,

    aumento: 0,

    peso: 0

}

```

**Artefato mágico**

É um item especializado no aprimoramento de armas mágicas. Quando associado a uma arma dessa categoria, aumenta seu valor de dano em uma quantidade aleatória entre 4 e 7 pontos, tornando-a mais eficiente durante os combates.

O valor do aumento será gerado aleatoriamente, respeitando os limites estabelecidos pelo balanceamento do jogo.

Sua estrutura padrão é:

```java

{

    nome: "",

    categoria: 4,

    aumento: 0,

    peso: 0

}

```

**Artefato físico**

É um item especializado no aprimoramento de armas físicas. Quando associado a uma arma dessa categoria, aumenta seu valor de dano em uma quantidade aleatória entre 4 e 7 pontos, ampliando sua capacidade ofensiva.

Assim como nas demais categorias, seu valor de aprimoramento será gerado aleatoriamente.

Sua estrutura padrão é:

```java

{

    nome: "",

    categoria: 5,

    aumento: 0,

    peso: 0

}

```

## 2.3. Poções

As poções são itens consumíveis utilizados para recuperar parte da vida perdida pelo jogador durante a sequência de combates.

O jogo contará com apenas um tipo de poção. Sua quantidade de cura será gerada aleatoriamente entre 25% e 35% da vida máxima do personagem. A recuperação nunca poderá fazer com que a vida atual ultrapasse a vida máxima.

Diferentemente dos artefatos, as poções não podem ser obtidas como recompensa de combate. Elas estarão disponíveis exclusivamente para compra no início da partida, utilizando as moedas recebidas pelo jogador.

Seu uso será permitido somente entre as batalhas, ou seja, após derrotar um inimigo e antes de iniciar o próximo confronto. Dessa forma, o jogador deverá administrar suas poções estrategicamente para aumentar suas chances de sobrevivência. Cada poção recuperará entre 25% e 35% da vida máxima do personagem, conforme o valor sorteado para aquela unidade, respeitando sempre o limite de vida máxima.

Sua estrutura padrão é:

```java

{

    nome: "Poção",

    categoria: 6,

    cura: 0,

    peso: 1

}

```

# 3. Sobre os inimigos

O modo de jogo será composto por três batalhas consecutivas. Para isso, o sistema gerará aleatoriamente três inimigos com base em modelos previamente definidos.

Cada inimigo possuirá atributos próprios de vida, dano e categoria. A categoria será sorteada aleatoriamente entre normal, mágico e físico, sem garantia de que as três categorias apareçam na mesma partida. Portanto, é possível enfrentar inimigos repetidos ou até três inimigos da mesma categoria. Essa aleatoriedade é intencional: o jogo propõe partidas curtas, imprevisíveis e potencialmente injustas, nas quais a sorte também faz parte do desafio. Os valores de vida e dano serão sorteados dentro de intervalos definidos para cada etapa, permitindo uma progressão geral de dificuldade ao longo das três batalhas, mas sem eliminar a variação aleatória.

A estrutura padrão de um inimigo será:

```java

{

    nome: "",

    categoria: "normal",

    vida: 0,

    dano: 0

}

```

O atributo \`categoria\` determina as afinidades de combate do inimigo, estabelecendo quais tipos de armas serão mais ou menos eficientes contra ele. Como a categoria é sorteada independentemente para cada inimigo, o jogador não terá garantia de enfrentar uma composição equilibrada de adversários e poderá encontrar uma sequência desfavorável à sua build.

O sistema contará com três categorias de inimigos:

- **Normal:** recebe apenas metade do dano causado por armas físicas. Não possui vantagem ou desvantagem específica contra armas mágicas.

- **Mágico:** recebe o dobro de dano de armas físicas, sendo vulnerável a esse tipo de equipamento.

- **Físico:** recebe o dobro de dano de armas mágicas, apresentando vulnerabilidade a ataques mágicos.

Essas afinidades fazem com que a escolha dos equipamentos tenha influência direta no resultado dos combates. Portanto, o jogador deverá considerar as características dos inimigos ao montar sua build e utilizar os recursos disponíveis entre as batalhas.

# 4. Sobre o jogador

Ao iniciar o programa, o usuário deverá criar seu personagem, informando o nome que deseja utilizar durante a partida.

Cada personagem começará com uma capacidade de seis espaços na mochila. Esse limite determinará quantos itens poderão ser carregados simultaneamente, considerando o peso individual de cada um. Armas físicas ocupam dois espaços, armas mágicas e poções ocupam um espaço cada, e artefatos não ocupam espaço na mochila.

A quantidade inicial de vida do personagem será gerada aleatoriamente dentro de um intervalo predefinido. O atributo `vida` representará a vida atual do personagem e será reduzido conforme ele receber ataques dos inimigos. Já o atributo `vida_maxima` representará o limite de vida que poderá ser alcançado, inclusive por meio dos aprimoramentos concedidos por artefatos comuns.

A estrutura padrão do personagem será:

```java

{

    nome: "",

    vida: 0,

    vida_maxima: 0

}

```

Além dos atributos apresentados na estrutura, o sistema deverá controlar os espaços disponíveis na mochila e os demais recursos necessários para o funcionamento da partida, como as moedas e os itens associados ao personagem.

# 5. Sobre a criação da build

A preparação da build será a etapa responsável por permitir que o jogador organize seus equipamentos antes de iniciar os combates.

Ao executar o programa, será apresentada inicialmente uma breve introdução explicando o funcionamento do jogo e seus principais objetivos. Em seguida, o jogador receberá uma quantidade aleatória de moedas, que poderá utilizar para comprar poções antes de iniciar a partida.

Após essa introdução, será exibido um painel de gerenciamento no CLI (Command Line Interface), contendo as informações atuais do personagem e as opções de interação disponíveis.

Na parte superior do painel serão apresentados os dados principais do jogador, como:

- Nome do personagem;

- Quantidade atual de vida;

- Espaços disponíveis na mochila;

- Quantidade de moedas disponíveis.

Abaixo dessas informações, serão disponibilizadas as seguintes opções:

**1. Adicionar arma ao arsenal**

Permite cadastrar uma nova arma no inventário, informando seu nome e categoria. Os atributos de dano e peso serão definidos automaticamente pelo sistema de acordo com as regras da categoria selecionada.

**2. Editar arsenal**

Abre um submenu com opções para administrar os itens já cadastrados:

- **Remover item:** permite selecionar e excluir um item existente do inventário.

- **Editar item:** permite modificar as informações editáveis de um item já cadastrado.

**3. Comprar poção**

Permite adquirir poções utilizando as moedas disponíveis. O sistema deverá verificar se o jogador possui saldo suficiente e espaço disponível na mochila antes de concluir a compra.

**4. Iniciar jogo**

Finaliza a etapa de preparação e inicia a sequência de três batalhas. Após selecionar essa opção, o jogador passará a utilizar os itens e recursos organizados em seu inventário durante a partida.

**5. Sair**

Encerra a execução do programa.

O gerenciamento do inventário deverá respeitar a capacidade máxima de seis espaços. Portanto, não será permitido adicionar ou comprar itens caso o peso total ultrapasse esse limite. Armas físicas ocupam dois espaços; armas mágicas e poções ocupam um espaço; artefatos possuem peso zero e não consomem espaço da mochila.

# 6. Sobre o funcionamento do jogo

Ao selecionar a opção de iniciar o jogo, o sistema utilizará os equipamentos cadastrados e os atributos do personagem para iniciar a sequência de batalhas.

Cada confronto será apresentado individualmente no CLI, exibindo as informações do inimigo correspondente, como nome, categoria e quantidade de vida atual.

O combate será realizado por meio de uma simulação que considera o dano das armas do jogador, as afinidades entre as categorias de armas e inimigos e os valores de dano e vida envolvidos no confronto.

Após a resolução da batalha, o sistema verificará se o personagem conseguiu derrotar o inimigo ou se foi derrotado.

**Em caso de derrota:**

Caso a vida do personagem chegue a zero antes de derrotar o inimigo, será apresentada uma mensagem de Game Over, indicando o encerramento da partida. Após isso, o programa será finalizado.

**Em caso de vitória:**

Caso o jogador consiga derrotar o inimigo, será encaminhado para uma etapa de preparação antes do próximo confronto.

Nessa etapa, o jogador poderá:

- Utilizar uma ou mais poções para recuperar parte da vida perdida, respeitando a quantidade disponível no inventário e sem ultrapassar sua vida máxima.

- Associar os artefatos recebidos como recompensa ao personagem ou a uma arma compatível, aumentando seus atributos. Cada arma poderá possuir no máximo um artefato associado.

- Consultar e administrar os recursos disponíveis antes de prosseguir.



Os artefatos recebidos não ocupam espaço na mochila. Entretanto, eles precisam ser associados a um alvo compatível durante a etapa de preparação entre as batalhas. Caso o jogador opte por não utilizar um artefato, ele será descartado e desaparecerá, não podendo ser guardado para uma batalha posterior.

Após concluir a preparação, o jogador poderá avançar para a próxima batalha, na qual o processo será repetido.

Esse ciclo continuará até que o jogador seja derrotado ou consiga vencer os três inimigos gerados no início da partida.

# 7. Condição de vitória e sistema de pontuação

A vitória será alcançada quando o jogador conseguir derrotar os três inimigos consecutivamente, sem que sua vida chegue a zero durante os confrontos.

Ao concluir a última batalha, será exibida uma mensagem de vitória acompanhada da pontuação final obtida pelo jogador.

O score será calculado considerando três fatores principais:

- **Dano total dos equipamentos:** corresponde à soma dos valores de dano das armas pertencentes à build, considerando os aprimoramentos recebidos por meio dos artefatos.

- **Vida recuperável restante:** corresponde à soma dos valores de cura das poções que ainda estiverem disponíveis na mochila ao final da partida.

- **Vida restante do personagem:** corresponde à quantidade de vida que o jogador ainda possui após derrotar o último inimigo.

Dessa forma, a pontuação final será utilizada como uma representação do desempenho do jogador, recompensando não apenas a capacidade de vencer os confrontos, mas também a eficiência na preparação da build e no gerenciamento dos recursos disponíveis.

O objetivo do sistema de pontuação é permitir que diferentes partidas sejam comparadas, incentivando o jogador a experimentar combinações de equipamentos e estratégias para alcançar resultados cada vez melhores.