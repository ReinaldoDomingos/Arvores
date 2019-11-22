package rbn.remocao;

import static rbn.remocao.RN.NEGRO;
import static rbn.remocao.RN.RUBRO;
import static rbn.remocao.RN.sentinela;

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
        String cor_formatada = (this.cor == 0) ? "RED  " : "BLACK";
        Object valorPai = null;
        if (pai != null) {
            valorPai = pai.getValor();
        }
        String valEsq, valDir;
        if (esq == null || esq.equals(sentinela)) {
            valEsq = "Sentinela";
        } else {
            valEsq = esq.valor.toString() + "        ";
        }
        if (dir == null || dir.equals(sentinela)) {
            valDir = "Sentinela";
        } else {
            valDir = dir.valor.toString() + "           ";
        }
        return "[" + String.valueOf(valor) + " \"" + cor_formatada + "\"] - Filhos: " + String.valueOf(valEsq) + " | "
                + String.valueOf(valDir) + " - Pai: " + valorPai;
    }
}
