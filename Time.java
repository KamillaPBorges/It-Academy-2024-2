import java.util.ArrayList;
import java.util.List;

public class Time {
    private String nome;
    private String gritoGuerra;
    private int ano;  // ano de fundacao
    private int pontos;
    private int blots;
    private int plifs;
    private int advrunghs;
    private List<Jogador> jogadores; // lista dos jogadores do time

    public Time(String nome, String gritoGuerra, int ano) {
        this.nome = nome;
        this.gritoGuerra = gritoGuerra;
        this.ano = ano;
        this.pontos = 50; // cada time comeca com 50 pontos
        this.blots = 0;
        this.plifs = 0;
        this.advrunghs = 0;
        this.jogadores = new ArrayList<>();
    }

    public void adicionarAdvrungh() {
        this.advrunghs += 1;
        this.pontos -= 10;
    }

    @Override
    public String toString() {
        return nome;
    }

    //getters
    public String getNome() {
        return nome;
    }

    public String getGritoGuerra() {
        return gritoGuerra;
    }

    public int getAno() {
        return ano;
    }

    public int getPontos() {
        return pontos;
    }

    public int getBlots() {
        return blots;
    }

    public int getPlifs() {
        return plifs;
    }

    public int getAdvrunghs() {
        return advrunghs;
    }

    public List<Jogador> getJogadores() {
        return jogadores;
    }

    //setters
    public void setNome(String nome) {
        this.nome = nome;
    }

    public void setGritoGuerra(String gritoGuerra) {
        this.gritoGuerra = gritoGuerra;
    }

    public void setAno(int ano) {
        this.ano = ano;
    }

    public void setPontos(int pontos) {
        this.pontos = pontos;
    }

    public void setBlots(int blots) {
        this.blots = blots;
    }

    public void setPlifs(int plifs) {
        this.plifs = plifs;
    }

    public void setAdvrunghs(int advrunghs) {
        this.advrunghs = advrunghs;
    }

    public void setJogadores(List<Jogador> jogadores) {
        this.jogadores = jogadores;
    }
}