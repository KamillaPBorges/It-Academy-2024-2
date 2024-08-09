# BALLIT Championship

## Descrição
BALLIT Championship é uma aplicação Java baseada em Swing que simula um campeonato esportivo fictício. A aplicação permite o cadastro de times e jogadores, 
a simulação de partidas, registro de desempenho, e a visualização dos resultados e vencedor. 
Trabalho realizado para o processo seletivo IT Aacademy Dell, edição 21. 

## Funcionalidades
1. **Cadastro de Times**: Permite cadastrar times com seus respectivos jogadores.
2. **Início do Campeonato**: Simula o campeonato até restar apenas um time vencedor.
3. **Adicionar Penalidades**: Adiciona penalidades (advrunghs) aos times.
4. **Mostrar Resultados**: Exibe os resultados do campeonato e detalhes do time vencedores e dos jogadores.
5. **Mostrar Todos os Resultados**: Exibe estatísticas detalhadas de todos os times cadastrados com seu respectivos jogadores.

## Estrutura do Código
- **Classe Principal (BallitChampionship)**: Contém a lógica da interface gráfica e das funcionalidades principais.
- **Métodos**:
  - `cadastroTimes()`: Cadastro de times e jogadores.
  - `cadastroJogadores(Time time)`: Cadastro de jogadores para um time específico.
  - `inicioChampionship()`: Início do campeonato com rodadas eliminatórias.
  - `proxFase()`: Realiza a próxima fase do campeonato.
  - `jogarPartida(Time time1, Time time2)`: Simula uma partida entre dois times.
  - `registrarDesempenho(Time time)`: Registra o desempenho dos jogadores de um time.
  - `desempateGrusht(Time time1, Time time2)`: Resolve empates baseados no grito de guerra dos times.
  - `adicionarAdvrunghs()`: Adiciona penalidades a um time específico.
  - `mostrarResultados()`: Mostra os resultados do campeonato, vencedor e jogadores do time que venceu.
  - `mostrarTodosOsResultados()`: Mostra as estatísticas detalhadas de todos os times.

## Como Executar
1. Compile o código-fonte usando um compilador Java.
2. Execute a BallitChampionship:  
2.1 Informe o número de times
2.2 Cadastre os times
2.3 Inicie o campeonato
2.4 Se necessário, adicione Advrunghs
2.5 Mostre todos os resultados 
   

## Autor
- [@kamipborges](https://www.github.com/kamipborges)
