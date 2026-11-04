# Jogo de Batalha e Gerenciamento de Mochila

## Sobre o jogo

Este projeto é um jogo de aventura em texto, desenvolvido em Java. Nele, o jogador monta sua mochila com armas, artefatos e poções antes de enfrentar uma sequência de inimigos.

O objetivo é superar **três etapas de batalha**. Para isso, é importante escolher os equipamentos, administrar o espaço disponível na mochila e aproveitar as recompensas recebidas ao longo da partida.

## Como jogar

Ao iniciar o jogo, informe seu nome. Em seguida, será apresentado o menu principal, no qual é possível preparar os equipamentos antes de começar as batalhas.

### Preparação

Antes de iniciar uma batalha, o jogador pode:

- **Adicionar armas:** escolher entre armas físicas e mágicas. As armas possuem valores de dano e ocupam espaço na mochila.
- **Editar a mochila:** consultar os itens armazenados e remover armas, artefatos ou poções.
- **Comprar poções:** gastar moedas para adquirir poções de cura, respeitando o espaço disponível.
- **Acompanhar os atributos:** visualizar a vida, o espaço ocupado na mochila e a quantidade de moedas.

A mochila possui um limite de **6 espaços**. Armas físicas ocupam mais espaço que armas mágicas, então é importante planejar os equipamentos que serão levados.

### Batalhas e etapas

Para começar, é necessário ter pelo menos uma arma na mochila. Antes de cada confronto, o jogo apresenta o inimigo e permite que o jogador confirme se deseja enfrentá-lo.

O objetivo é vencer três inimigos, avançando uma etapa a cada vitória. Ao vencer uma batalha, o jogador recebe um **artefato**, que pode ser associado a uma arma para aumentar seu dano.

Entre as batalhas, o menu de etapa permite:

- Consultar e editar a mochila.
- Associar um artefato a uma arma.
- Remover a associação entre uma arma e um artefato.
- Usar uma poção para recuperar vida, caso não esteja com a vida cheia.
- Avançar para a próxima batalha.

Se o jogador perder uma batalha, a partida termina e um novo jogo é iniciado. Ao vencer as três etapas, o jogador conclui o jogo.

## Requisitos

- Java JDK instalado.
- Terminal ou prompt de comando.

## Como executar

Na pasta raiz do projeto, onde está localizado o arquivo `Main.java`:

1. Compile os arquivos:

   ```bash
   javac -d out Main.java src/*.java
   ```

2. Execute o jogo:

   ```bash
   java -cp out Main
   ```

3. Digite seu nome e siga as opções apresentadas nos menus.

# Bom jogo!