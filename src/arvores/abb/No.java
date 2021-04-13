package arvores.abb;

public class No<T> {
    private T valor;
    private No esq;
    private No dir;

    public No() {
        this.valor = null;
        this.esq = this.dir = null;
    }

    public No(T valor) {
        this.valor = valor;
        esq = new No();
        dir = new No();
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


    @Override
    public String toString() {
        return String.valueOf(valor);
    }
}