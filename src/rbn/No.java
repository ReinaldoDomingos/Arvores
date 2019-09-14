package rbn;

import static rbn.RN.NEGRO;
import static rbn.RN.RUBRO;

public class No<T> {

    private T valor;
    private No pai;
    private No dir;
    private No esq;
    private int cor;

    public No() {
        this.valor = null;
        this.esq = this.dir = this.pai = null;
        this.cor = RUBRO;
    }

    public No(T valor) {
        this.valor = valor;
        this.pai = null;

        No<T> esq = new No();
        esq.setCor(NEGRO);
        this.esq = esq;

        No<T> dir = new No();
        dir.setCor(NEGRO);
        this.dir = dir;
        this.dir.setPai(this);
    }

    public T getValor() {
        return valor;
    }

    public void setValor(T valor) {
        this.valor = valor;
    }

    public No<T> getEsq() {
        return esq;
    }

    public void setEsq(No<T> esq) {
        this.esq = esq;
    }

    public No<T> getDir() {
        return dir;
    }

    public void setDir(No<T> dir) {
        this.dir = dir;
    }

    public No<T> getPai() {
        return pai;
    }

    public void setPai(No<T> pai) {
        this.pai = pai;
    }

    public int getCor() {
        return cor;
    }

    public void setCor(int cor) {
        this.cor = cor;
    }

    @Override
    public String toString() {
//        [87 "Black"] - Filhos: 86 | 90 - Pai: 85  
        String cor_formatada = (this.cor == 0) ? "Red" : "Black";
        Object valorPai = null;
        if (pai != null) {
            valorPai = pai.getValor();
        }
        return "[" + String.valueOf(valor) + " \"" + cor_formatada + "\"] - Filhos: "
                + String.valueOf(esq.valor) + " | " + String.valueOf(dir.valor)
                + " - Pai: " + valorPai;
    }
}
