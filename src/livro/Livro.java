package livro;

public class Livro {
    private int idLivro;
    private String anoPublicacao;
    private String titulo;
    private String autor;
    private String genero;
    private boolean disponivel;

    public Livro(int idLivro, String anoPublicacao, String titulo, String autor, String genero) {
        this.idLivro = idLivro;
        this.anoPublicacao = anoPublicacao;
        this.titulo = titulo;
        this.autor = autor;
        this.genero = genero;
        this.disponivel = true;
    }
    public Livro(int idLivro, String anoPublicacao, String titulo, String autor, String genero, boolean disponivel) {
        this.idLivro = idLivro;
        this.anoPublicacao = anoPublicacao;
        this.titulo = titulo;
        this.autor = autor;
        this.genero = genero;
        this.disponivel = disponivel;
    }

    public Livro(String anoPublicacao, String titulo, String autor, String genero) {
        this.anoPublicacao = anoPublicacao;
        this.titulo = titulo;
        this.autor = autor;
        this.genero = genero;
        this.disponivel = true; 
    }
    

    public int getIdLivro() {
        return idLivro;
    }

    public String getAnoPublicacao() {
        return anoPublicacao;
    }

    public String getTitulo() {
        return titulo;
    }

    public String getAutor() {
        return autor;
    }

    public String getGenero() {
        return genero;
    }

    public boolean isDisponivel() {
        return disponivel;
    }

    public void setIdLivro(int idLivro) {
        this.idLivro = idLivro;
    }

    public void setAnoPublicacao(String anoPublicacao) {
        this.anoPublicacao = anoPublicacao;
    }

    public void setTitulo(String titulo) {
        this.titulo = titulo;
    }

    public void setAutor(String autor) {
        this.autor = autor;
    }

    public void setGenero(String genero) {
        this.genero = genero;
    }

    public void setDisponivel(boolean disponivel) {
        this.disponivel = disponivel;
    }
    
    // Só para testes
    @Override
    public String toString() {
        return "Livro{" + "idLivro=" + idLivro + ", anoPublicacao=" + anoPublicacao + ", titulo=" + titulo + ", autor=" + autor + ", genero=" + genero + ", disponivel=" + disponivel + '}';
    }
}
