package grafos.arvores.outros.b;

import java.util.ArrayList;

public class No<T> {

    private T valor;
    private ArrayList<No<T>> esq;
    private ArrayList<No<T>> dir;

    public No() {
        this.esq = new ArrayList<>();
        this.dir = new ArrayList<>();
    }

    public No(T valor) {
        this.valor = valor;
        this.esq = new ArrayList<>();
        this.dir = new ArrayList<>();
    }

    public T getValor() {
        return valor;
    }

    public void setValor(T valor) {
        this.valor = valor;
    }

    public ArrayList<No<T>> getEsq() {
        return esq;
    }

    public void setEsq(ArrayList<No<T>> esq) {
        this.esq = esq;
    }

    public ArrayList<No<T>> getDir() {
        return dir;
    }

    public void setDir(ArrayList<No<T>> dir) {
        this.dir = dir;
    }

    @Override
    public String toString() {
        return valor.toString();
    }

}
