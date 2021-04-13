package grafos.arvores.b;

public class Chave<T extends Comparable<T>> {

    private Pagina esq, pai;
    private int valor = -1, pos = -1;

    public Chave(int valor) {
        this.valor = valor;
    }

    public Chave() {
        esq = B.sentinela;
    }

    public Pagina getPage() {
        return pai;
    }

    public void setPage(Pagina pai) {
        this.pai = pai;
    }

    public Pagina getPai() {
        return pai;
    }

    public void setPai(Pagina pai) {
        this.pai = pai;
    }

    public int getPos() {
        return pos;
    }

    public void setPos(int pos) {
        this.pos = pos;
    }

    public int getValor() {
        return valor;
    }

    public void setValor(int valor) {
        this.valor = valor;
    }

    public Pagina getEsq() {
        return esq;
    }

    public void setEsq(Pagina esq) {
        this.esq = esq;
    }

    @Override
    public String toString() {
        return String.valueOf(valor);
    }

}
