import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.util.ArrayList;
import java.util.Collections;
import java.util.List;
import java.util.Random;

public class BallitChampionship extends JFrame {
    private static final int minTimes = 8;
    private static final int maxTimes = 16;
    private static List<Time> times = new ArrayList<>();
    private static List<Time> timesT = new ArrayList<>();

    private JTextField nroTimesField;
    private JTextArea resultArea;

    public BallitChampionship() { 
        setTitle("BALLIT Championship");
        setSize(500, 400);
        setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        setLayout(new BorderLayout());

        JPanel inputPanel = new JPanel();
        inputPanel.setLayout(new GridLayout(5, 2));

        inputPanel.add(new JLabel("Número de Times (8 a 16):"));
        nroTimesField = new JTextField();
        inputPanel.add(nroTimesField);

        JButton cadastrarButton = new JButton("Cadastrar Times");
        cadastrarButton.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                cadastroTimes();
            }
        });
        inputPanel.add(cadastrarButton);

        JButton iniciarButton = new JButton("Iniciar Campeonato");
        iniciarButton.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                inicioChampionship();
                mostrarResultados();
            }
        });
        inputPanel.add(iniciarButton);

        JButton adicionarAdvrunghsButton = new JButton("Adicionar Advrunghs");
        adicionarAdvrunghsButton.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                adicionarAdvrunghs();
            }
        });
        inputPanel.add(adicionarAdvrunghsButton);

        JButton mostrarTodosResultadosButton = new JButton("Mostrar Todos os Resultados");
        mostrarTodosResultadosButton.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                mostrarTodosOsResultados();
            }
        });
        inputPanel.add(mostrarTodosResultadosButton);

        resultArea = new JTextArea();
        resultArea.setEditable(false);
        JScrollPane scrollPane = new JScrollPane(resultArea);

        add(inputPanel, BorderLayout.NORTH);
        add(scrollPane, BorderLayout.CENTER);
    }
    //Verificador -> Se o dado esta incorreto
    final class IntVerifier extends InputVerifier {

        public IntVerifier() {
            super();
        }
    
        @Override
        public boolean verify(JComponent input) {
            
            String text =  ((JTextField) input).getText();
            try {
                Integer.parseInt(text);
                input.setForeground(Color.BLACK);
                return true;  // Entrada esta correta -> Integer
            } catch (NumberFormatException e) {
                input.setForeground(Color.RED);
                return false;  // Entrada esta incorreta 
            }
    
        }
        
    }
    // Verificador para string
    final class StringVerifier extends InputVerifier {
        @Override
        public boolean verify(JComponent input) {
            String text = ((JTextField) input).getText();
            if (text != null && !text.trim().isEmpty()) {
                input.setForeground(Color.BLACK); // Entrada esta correta -> String
                return true;
            } else {
                input.setForeground(Color.RED); // Entrada esta incorreta
                return false;
            }
        }
    }
    // 1 -> Cadastro dos times e dos jogadores
    private void cadastroTimes() {
        int nroTimes = Integer.parseInt(nroTimesField.getText());
        if (nroTimes >= minTimes && nroTimes <= maxTimes && nroTimes % 2 == 0) { //tem q ser para
            for (int i = 0; i < nroTimes; i++) {
                JDialog dialog = new JDialog(this, "Cadastro de Time", true);
                dialog.setSize(300, 200);
                dialog.setLayout(new GridLayout(4, 2));

                dialog.setFocusTraversalKeys(KeyboardFocusManager.FORWARD_TRAVERSAL_KEYS, 
                Collections.singleton(KeyStroke.getKeyStroke("TAB")));

                dialog.add(new JLabel("Nome do time:"));
                JTextField nomeField = new JTextField(10);
                nomeField.setInputVerifier(new StringVerifier());
                dialog.add(nomeField);

                dialog.add(new JLabel("Grito do time:"));
                JTextField gritoField = new JTextField(10);
                gritoField.setInputVerifier(new StringVerifier());
                dialog.add(gritoField);

                dialog.add(new JLabel("Ano do time:"));
                JTextField anoField = new JTextField(10);
                anoField.setInputVerifier(new IntVerifier());
                dialog.add(anoField);

                JButton addButton = new JButton("Adicionar");
                dialog.add(addButton);

                addButton.addActionListener(new ActionListener() {
                    @Override
                    public void actionPerformed(ActionEvent e) {
                        if (!nomeField.getInputVerifier().verify(nomeField) ||
                            !gritoField.getInputVerifier().verify(gritoField) ||
                            !anoField.getInputVerifier().verify(anoField)) {
                            JOptionPane.showMessageDialog(null, "Entrada inválida, tente outra vez.");
                            return;
                        }

                        Time time = new Time(nomeField.getText(), gritoField.getText(), Integer.parseInt(anoField.getText()));
                        cadastroJogadores(time);
                        times.add(time);
                        timesT.add(time);
                        dialog.dispose();
                    }
                });

                dialog.setLocationRelativeTo(this);
                dialog.setVisible(true);
            }
        } else {
            JOptionPane.showMessageDialog(this, "Número inválido de times. Tente outra vez.");
        }
    }
    // Cadastro dos jogadores de um time
    private void cadastroJogadores(Time time) {
        try {
            int nroJogadores = Integer.parseInt(JOptionPane.showInputDialog("Número de jogadores para o time " + time.getNome() + ":"));
            for (int i = 0; i < nroJogadores; i++) {
                String nomeJogador = JOptionPane.showInputDialog("Nome do jogador:");
                String posicaoJogador = JOptionPane.showInputDialog("Posição do jogador:");
                time.getJogadores().add(new Jogador(nomeJogador, posicaoJogador));
            }
        } catch (NumberFormatException e) {
            JOptionPane.showMessageDialog(this, "Entrada inválida! Por favor, insira um número válido.");
        }
    }

    // 2 -> Inicio do campeonato
    private void inicioChampionship() {
        while (times.size() > 1) { //até restar apenas uma equipe (a vencedora)
            proxFase();
        }
    }

    // 3 -> Próxima fase
    private void proxFase() {
        Collections.shuffle(times); // embaralhar os times 
        List<Time> vencedores = new ArrayList<>(); //vencedores de cada partida nessa fase
        for (int i = 0; i < times.size(); i += 2) { // loop pula 2 para ter uma dupla de times
            Time time1 = times.get(i);
            Time time2 = times.get(i + 1);
            Time vencedor = jogarPartida(time1, time2);
            vencedores.add(vencedor);
        }
        times = vencedores; // atualiza a lista de times para só os vencedores desta fase
    }

    // 4 -> Partida
    private Time jogarPartida(Time time1, Time time2) {
        resultArea.append("Partida: " + time1.getNome() + " vs " + time2.getNome() + "\n");
        registrarDesempenho(time1);
        registrarDesempenho(time2);

        if (time1.getPontos() > time2.getPontos()) {
            resultArea.append(time1.getNome() + " venceu a partida!\n");
            return time1;
        } else if (time2.getPontos() > time1.getPontos()) {
            resultArea.append(time2.getNome() + " venceu a partida!\n");
            return time2;
        } else {
            resultArea.append("\nHouve um empate!\n");
            return desempateGrusht(time1, time2);
        }
    }

    // 5 -> Registro do desempenho dos jogadores
    private void registrarDesempenho(Time time) {
        for (Jogador jogador : time.getJogadores()) {
            try {
                int blots = Integer.parseInt(JOptionPane.showInputDialog("Blots para " + jogador.getNome() + " (" + time.getNome() + "):"));
                int plifs = Integer.parseInt(JOptionPane.showInputDialog("Plifs para " + jogador.getNome() + " (" + time.getNome() + "):"));
                jogador.setBlots(jogador.getBlots() + blots);
                jogador.setPlifs(jogador.getPlifs() + plifs);
                time.setBlots(time.getBlots() + blots);
                time.setPlifs(time.getPlifs() + plifs);
                time.setPontos(time.getPontos() + blots * 5 + plifs);
            } catch (NumberFormatException e) {
                JOptionPane.showMessageDialog(this, "Entrada inválida! Por favor, insira um número válido.");
            }
        }
    }

    private Time desempateGrusht(Time time1, Time time2) {
        resultArea.append("Houve um empate! Torcedores de " + time1.getNome() + " e " + time2.getNome() + " mostrem seus gritos de guerra o mais alto que puderem!\n");
        Random random = new Random(); // objeto random para simular os gritos de guerra
        int decibelTime1 = random.nextInt(100);
        int decibelTime2 = random.nextInt(100);

        if (decibelTime1 > decibelTime2) {
            time1.setPontos(time1.getPontos() + 3);
            resultArea.append(time1.getNome() + " venceu o Grusht e a partida!\n");
            return time1;
        } else if (decibelTime2 > decibelTime1){
            time2.setPontos(time2.getPontos() + 3);
            resultArea.append(time2.getNome() + " venceu o Grusht e a partida!\n");
            return time2;
        }
        else {
            resultArea.append("Não foi possivel distinguir qual o grito mais alto! Iniciando o desempatade novamente!\n");
            return desempateGrusht(time1, time2);
        }
    }

    // Adicionar penalidade
    private void adicionarAdvrunghs() {
        String nomeTime = JOptionPane.showInputDialog("Nome do time que receberá a penalidade:");
        for (Time time : times) {
            if (time.getNome().equalsIgnoreCase(nomeTime)) {
                time.adicionarAdvrungh();
                resultArea.append(time.getNome() + " recebeu uma penalidade de advrunghs! Pontos: " + time.getPontos() + "\n");
                return;
            }
        }
        JOptionPane.showMessageDialog(this, "Time não encontrado.");
    }

    // 5 -> Mostrar os resultados do time vencedor
    private void mostrarResultados() {
        resultArea.append("Resultados da Ballit Championship:\n");
        times.sort((t1, t2) -> Integer.compare(t2.getPontos(), t1.getPontos()));
        for (Time time : times) {
            resultArea.append(time.getNome() + ": " + time.getPontos() + " pontos, " + time.getBlots() + " blots, " + time.getPlifs() + " plifs, " + time.getAdvrunghs() + " advrunghs\n");
            for (Jogador jogador : time.getJogadores()) {
                resultArea.append("\t" + jogador.getNome() + " (" + jogador.getPosicao() + "): " + jogador.getBlots() + " blots, " + jogador.getPlifs() + " plifs\n");
            }
        }
        resultArea.append("Campeão: " + times.get(0).getNome() + "\n");
        resultArea.append("Grito de guerra do campeão: " + times.get(0).getGritoGuerra() + "\n");
    }

    //6 -> Mostrar o resultado de todos os times
    private void mostrarTodosOsResultados() {
        resultArea.append("Estatísticas detalhadas de todos os times:\n");
        for (Time time : timesT) {
            resultArea.append("Time: " + time.getNome() + "\n");
            resultArea.append("Blots: " + time.getBlots() + "\n");
            resultArea.append("Plifs: " + time.getPlifs() + "\n");
            resultArea.append("Pontos: " + time.getPontos() + "\n");
            resultArea.append("Advrunghs: " + time.getAdvrunghs() + "\n");
            resultArea.append("Jogadores:\n");
            for (Jogador jogador : time.getJogadores()) {
                resultArea.append("\t" + jogador.getNome() + " (" + jogador.getPosicao() + ") - Blots: " + jogador.getBlots() + ", Plifs: " + jogador.getPlifs() + "\n");
            }
        }
    }

    public static void main(String[] args) {
        SwingUtilities.invokeLater(new Runnable() {
            @Override
            public void run() {
                Audio.musica();
                new BallitChampionship().setVisible(true);
            }
        });
    }
}
