package aluguel;

import cliente.Cliente;
import livro.Livro;

public class Aluguel {
    private int idAluguel;
    private Cliente cliente;
    private Livro livro;
    private String dataAluguel;
    private String dataDevolucao;

    public Aluguel(int idAluguel, Cliente cliente, Livro livro, String dataAluguel, String dataDevolucao) {
        this.idAluguel = idAluguel;
        this.cliente = cliente;
        this.livro = livro;
        this.dataAluguel = dataAluguel;
        this.dataDevolucao = dataDevolucao;
    }


    public Aluguel(Cliente cliente, Livro livro, String dataAluguel, String dataDevolucao) {
      this.cliente = cliente;
      this.livro = livro;
      this.dataAluguel = dataAluguel;
      this.dataDevolucao = dataDevolucao;
   }

    
    public Aluguel(){
        
    }
    
    public int getIdAluguel() {
        return idAluguel;
    }

    public Cliente getCliente() {
        return cliente;
    }

    public Livro getLivro() {
        return livro;
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

    public void setCliente(Cliente cliente) {
        this.cliente = cliente;
    }

    public void setLivro(Livro livro) {
        this.livro = livro;
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
        return "Aluguel{" + "idAluguel=" + idAluguel + ", idCliente=" + cliente + ", idLivro=" + livro + ", dataAluguel=" + dataAluguel + ", dataDevolucao=" + dataDevolucao + '}';
    }   

}
