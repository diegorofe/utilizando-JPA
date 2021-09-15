package classes;

import javax.persistence.*;

@Entity
public class Vendas {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private int id;

    @Column
    private String descricao;

    @Column
    private int quantidade;
    @Column
    private double valor;

    @ManyToOne(fetch = FetchType.LAZY)
    private Cliente cliente;


    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public String getDescricao() {
        return descricao;
    }

    public void setDescricao(String descricao) {
        this.descricao = descricao;
    }

    public int getQuantidade() {
        return quantidade;
    }

    public void setQuantidade(int quantidade) {
        this.quantidade = quantidade;
    }

    public double getValor() {
        return valor;
    }

    public void setValor(double valor) {
        this.valor = valor;
    }

    public Cliente getCliente() {
        return cliente;
    }

    public void setCliente(Cliente cliente) {
        this.cliente = cliente;
    }

    public Vendas(int id, String descricao, int quantidade, double valor, Cliente cliente) {
        this.id = id;
        this.descricao = descricao;
        this.quantidade = quantidade;
        this.valor = valor;
        this.cliente = cliente;
    }

    public Vendas(String descricao, int quantidade, double valor, Cliente cliente) {
        this.descricao = descricao;
        this.quantidade = quantidade;
        this.valor = valor;
        this.cliente = cliente;
    }

    @Override
    public String toString() {
        return "classes.Vendas{" +
                "id=" + id +
                ", descricao='" + descricao + '\'' +
                ", quantidade=" + quantidade +
                ", valor=" + valor +
                ", cliente=" + cliente +
                '}';
    }
}
