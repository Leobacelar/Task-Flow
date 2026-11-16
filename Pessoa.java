public class Pessoa {
    private int id;
    private String nome;
    private int idade;
    private boolean infectado;

    public Pessoa(int id, String nome, int idade, boolean infectado) {
        this.id = id;
        this.nome = nome;
        this.idade = idade;
        this.infectado = infectado;
    }

    public int getId() { return id; }
    public String getNome() { return nome; }
    public int getIdade() { return idade; }
    public boolean isInfectado() { return infectado; }

    public void setInfectado(boolean infectado) { this.infectado = infectado; }
}
