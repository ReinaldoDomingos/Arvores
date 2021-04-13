package grafos.arvores.avl;
public class No<T> {
    private int fatBal;
    public int altura;
    private T valor;
    private No esq;
    private No dir;

    public No() {
        this.valor = null;
        this.esq = this.dir = null;
        this.altura = 0;
    }

    public No(T valor) {
        this.valor = valor;
        esq = new No<T>();
        dir = new No<T>();
        this.altura = 1;
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

    public int getFatBal() {
        return fatBal;
    }

    public void setFatBal(int fatBal) {
        this.fatBal = fatBal;
    }

/*    @Override
    public String toString() {
//        return String.valueOf(valor) + " ";
        return String.valueOf(valor) + " " + String.valueOf(esq.valor) + " " + String.valueOf(dir.valor);
    }*/
}