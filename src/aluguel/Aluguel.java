package aluguel;

import cliente.Cliente;
import livro.Livro;

public class Aluguel {
    private int idAluguel;
    private Cliente idCliente;
    private Livro idLivro;
    private String dataAluguel;
    private String dataDevolucao;

    public Aluguel(int idAluguel, Cliente idCliente, Livro idLivro, String dataAluguel, String dataDevolucao) {
        this.idAluguel = idAluguel;
        this.idCliente = idCliente;
        this.idLivro = idLivro;
        this.dataAluguel = dataAluguel;
        this.dataDevolucao = dataDevolucao;
    }

    public int getIdAluguel() {
        return idAluguel;
    }

    public Cliente getIdCliente() {
        return idCliente;
    }

    public Livro getIdLivro() {
        return idLivro;
    }

    public String getDataAluguel() {
        return dataAluguel;
    }

    public String getDataDevolucao() {
        return dataDevolucao;
    }

    public void setIdAluguel(int idAluguel) {
        this.idAluguel = idAluguel;
    }

    public void setIdCliente(Cliente idCliente) {
        this.idCliente = idCliente;
    }

    public void setIdLivro(Livro idlivro) {
        this.idLivro = idLivro;
    }

    public void setDataAluguel(String dataAluguel) {
        this.dataAluguel = dataAluguel;
    }

    public void setDataDevolucao(String dataDevolucao) {
        this.dataDevolucao = dataDevolucao;
    }
    //Só para testes
    @Override
    public String toString() {
        return "Aluguel{" + "idAluguel=" + idAluguel + ", idCliente=" + idCliente + ", idLivro=" + idLivro + ", dataAluguel=" + dataAluguel + ", dataDevolucao=" + dataDevolucao + '}';
    }   
}
