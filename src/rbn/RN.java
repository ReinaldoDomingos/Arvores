package rbn;

public class RN<T extends Comparable<T>> {

    public static int RUBRO = 0;
    public static int NEGRO = 1;
    No<T> raiz;

    private No<T> busca(T valor) {
        if (this.raiz == null || this.raiz.getValor() == null) {
            return null;
        }
        No<T> pai = null;
        No<T> ponteiro = this.raiz;

        while (ponteiro.getValor() != null && !ponteiro.getValor().equals(valor)) {
            if (valor.compareTo(ponteiro.getValor()) > 0) {
                pai = ponteiro;
                ponteiro = ponteiro.getDir();
            } else if (valor.compareTo(ponteiro.getValor()) < 0) {
                pai = ponteiro;
                ponteiro = ponteiro.getEsq();
            }
        }
        ponteiro.setPai(pai);
        return ponteiro;
    }

    public void insere(T valor) {
        if (this.raiz == null) {
            raiz = new No(valor);
            raiz.setCor(NEGRO);
        } else {
            No ptr = busca(valor);
            ptr.setValor(valor);
            ptr.setCor(RUBRO);

            No esq = new No();
            esq.setCor(NEGRO);
            ptr.setEsq(esq);

            No dir = new No();
            dir.setCor(NEGRO);
            ptr.setDir(dir);

            balanceamento(ptr);
        }
    }

    public void rotacaoDir(No<T> x) {
        No<T> y = x.getEsq();
        x.setEsq(y.getDir());
        y.getDir().setPai(x);
        y.setPai(x.getPai());

        if (x.getPai() == null) {
            raiz = y;
        } else if (x.getPai().getEsq() == x) {
            x.getPai().setEsq(y);
        } else {
            x.getPai().setDir(y);
        }

        y.setDir(x);
        x.setPai(y);
    }

    public void rotacaoEsq(No<T> x) {
        No<T> y = x.getDir();
        x.setDir(y.getEsq());
        y.getEsq().setPai(x);
        y.setPai(x.getPai());

        if (x.getPai() == null) {
            raiz = y;
        } else if (x.getPai().getDir() == x) {
            x.getPai().setDir(y);
        } else {
            x.getPai().setEsq(y);
        }

        y.setEsq(x);
        x.setPai(y);
    }

    /*      rotacaoEsq(x){
        y = x.dir;
        x.ddir = y.esq;
        y.esq.pai = x;
        y.pai = x.pai
        
        if(x.pai == null)
            raiz = y;
        else if(x.pai.dir == x)
            x.pai.dir = y;
        else
            x.pai.esq = y;
        
        y.esq = x;
        x.pai = y;
        }
     */
    public void balanceamento(No<T> x) {
//        while () {
        if (x.getPai() != null && x.getPai().getCor() == RUBRO && x.getPai().getPai() != null) {
            No<T> avo = x.getPai().getPai();
            if (x.getPai() == avo.getEsq()) {
                No<T> tio = avo.getDir();
                if (tio.getCor() == RUBRO) {
                    x.getPai().setCor(NEGRO);
                    tio.setCor(NEGRO);
                    avo.setCor(RUBRO);
                } else {
                    if (x == x.getPai().getDir()) {
                        x = x.getPai();
                        rotacaoEsq(x);
                    }
                    x.getPai().setCor(NEGRO);
                    avo.setCor(RUBRO);
                    rotacaoDir(avo);
                }
            } else {
                No<T> tio = avo.getEsq();
                if (tio.getCor() == RUBRO) {
                    x.getPai().setCor(NEGRO);
                    tio.setCor(NEGRO);
                    avo.setCor(RUBRO);
                } else {
                    if (x == x.getPai().getEsq()) {
                        x = x.getPai();
                        rotacaoDir(x);
                    }
                    x.getPai().setCor(NEGRO);
                    avo.setCor(RUBRO);
                    rotacaoEsq(avo);
                }
            }
            raiz.setCor(NEGRO);
        }
    }

    public void imprimeEmOrdem() {
        if (raiz == null || raiz.getValor() == null) {
            return;
        }
        emOrdem(raiz);
        System.out.println();
    }

    public void emOrdem(No<T> ptr) {
        if (ptr.getValor() == null) {
            return;
        }
        if (ptr.getEsq().getValor() != null) {
            emOrdem(ptr.getEsq());
        }
        System.out.println(ptr);
        if (ptr.getDir().getValor() != null) {
            emOrdem(ptr.getDir());
        }
    }

}
