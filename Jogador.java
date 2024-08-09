public class Jogador {
    private String nome;
    private String posicao;
    private int blots;
    private int plifs;

    public Jogador(String nome, String posicao) {
        this.nome = nome;
        this.posicao = posicao;
        this.blots = 0;
        this.plifs = 0;
    }

    public String getNome() {
        return nome;
    }

    public String getPosicao() {
        return posicao;
    }

    public int getBlots() {
        return blots;
    }

    public int getPlifs() {
        return plifs;
    }

    public void setNome(String nome) {
        this.nome = nome;
    }

    public void setPosicao(String posicao) {
        this.posicao = posicao;
    }

    public void setBlots(int blots) {
        this.blots = blots;
    }

    public void setPlifs(int plifs) {
        this.plifs = plifs;
    }
}