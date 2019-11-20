package rbn.remocao;

public class RN<T extends Comparable<T>> {

    public static int RUBRO = 0;
    public static int NEGRO = 1;
    No<T> raiz, sentinela;

    public RN() {
        sentinela = new No<>();
        sentinela.setCor(NEGRO);
        raiz = sentinela;
    }

    public void inserir(T valor) {
        if (raiz.equals(sentinela)) {
            raiz = new No<>(valor);
            raiz.setEsq(sentinela);
            raiz.setDir(sentinela);
        } else {
            inserir(raiz, valor);
        }
        raiz.setCor(NEGRO);
    }

    private void inserir(No<T> no, T valor) {
        if (no.getPai() == null) {
            if (valor.compareTo(no.getValor()) == -1) {
                if (no.getEsq().equals(sentinela) && no.getDir().equals(sentinela)) {
                    No novo = new No<>(valor);
                    novo.setEsq(sentinela);
                    novo.setDir(sentinela);
                    novo.setPai(no);
                    no.setEsq(novo);
                } else {
                    inserir(no.getEsq(), valor);
                }
            } else if (no.getDir().equals(sentinela)) {
                No novo = new No<>(valor);
                novo.setEsq(sentinela);
                novo.setDir(sentinela);
                novo.setPai(no);
                no.setDir(novo);
            } else {
                if (valor.compareTo(no.getValor()) == -1) {
                    inserir(no.getEsq(), valor);
                } else if (valor.compareTo(no.getValor()) == 1) {
                    inserir(no.getDir(), valor);
                }
            }
        } else {
            No novo = new No<>(valor);
            novo.setEsq(sentinela);
            novo.setDir(sentinela);
            if (valor.compareTo(no.getValor()) == -1) {
                if (no.getEsq().equals(sentinela) && no.getDir().equals(sentinela)) {
                    no.setEsq(novo);
                    novo.setPai(no);
                    balancear(novo);
                } else {
                    inserir(no.getEsq(), valor);
                }
            } else {
                if (no.getDir().equals(sentinela)) {
                    no.setDir(novo);
                    novo.setPai(no);
                    balancear(novo);
                } else {
                    inserir(no.getDir(), valor);
                }
            }
        }
    }

    private void balancear(No no) {
        No pai = no.getPai();
        No tio = getIrmao(pai);
        if (pai.equals(raiz)) {
            return;
        }
        if (no.getCor() == RUBRO && pai.getCor() == RUBRO && tio.getCor() == RUBRO) {
            pai.setCor(NEGRO);
            tio.setCor(NEGRO);
            pai.getPai().setCor(RUBRO);
            balancear(pai);
        } else if (pai.getCor() == RUBRO && tio.getCor() == RUBRO) {
            pai.setCor(NEGRO);
            tio.setCor(NEGRO);
            pai.getPai().setCor(RUBRO);
            balancear(pai);
        } else if (no.getCor() == RUBRO && pai.getCor() == RUBRO && tio.getCor() == NEGRO) {
            pai.getPai().setCor(RUBRO);
            if (pai.equals(pai.getPai().getEsq())) {
                if (no.equals(pai.getEsq())) {
                    rodarDir(no.getPai());
                } else {
                    rodarEsq(no);

                    pai = no.getPai();
                    No avo = pai.getPai();
                    avo.setEsq(no);
                    no.setPai(avo);
                    pai.setEsq(no.getDir());
                    no.getDir().setPai(pai.getEsq());
                    no.setDir(pai);
                    pai.setPai(no);
                    no.setCor(NEGRO);
                }
            } else if (pai.equals(pai.getPai().getDir())) {
                if (no.equals(pai.getDir())) {
                    rodarEsq(no.getPai());
                } else {
                    rodarDir(no);

                    pai = no.getPai();
                    No avo = pai.getPai();
                    avo.setDir(no);
                    no.setPai(avo);
                    pai.setDir(no.getEsq());
                    no.getEsq().setPai(pai.getDir());
                    no.setEsq(pai);
                    pai.setPai(no);
                    no.setCor(NEGRO);
                }
            }
        } else if (pai.getCor() == RUBRO && pai.getPai().getCor() == RUBRO) {
            if (no.getCor() == NEGRO && pai.getPai().getDir().equals(pai) && pai.getPai().getEsq().getCor() == NEGRO) {
                No avo = pai.getPai();
                No bisavo = avo.getPai();
                if (bisavo.getEsq().equals(avo)) {
                    bisavo.setEsq(pai);
                    pai.setPai(bisavo);
                    avo.setDir(pai.getEsq());
                    avo.getDir().setPai(avo);
                    pai.setEsq(avo);
                    avo.setPai(pai);
                    balancear(no.getPai());
                } else {
                    System.out.println("outo jeito");
                }
            } else if (pai.getPai().getDir().equals(pai) && pai.getPai().getEsq().getCor() == NEGRO) {
                rodarEsq(pai);
                no.getPai().getPai().setCor(NEGRO);
                no.getPai().getPai().getPai().setCor(RUBRO);
                rodarDir(no.getPai().getPai());
            } else if (pai.getPai().getEsq().equals(pai) && pai.getPai().getDir().getCor() == NEGRO) {
                pai = no.getPai();
                No avo = pai.getPai();
                No bisavo = avo.getPai();
                if (bisavo.getDir().equals(avo)) {
                    bisavo.setDir(pai);
                    pai.setPai(bisavo);
                    avo.setEsq(pai.getDir());
                    avo.getEsq().setPai(avo);
                    pai.setDir(avo);
                    avo.setPai(pai);
                    balancear(no.getPai());
                } else {
                    System.out.println("outo jeito2");
                }
            }
        }
    }

    void rodarEsq(No<T> no) {
        No pai = no.getPai();
        No avo = pai.getPai();
        if (!pai.equals(raiz)) {
            avo.setEsq(no);
            no.setPai(avo);
            pai.setDir(no.getDir());
            no.getEsq().setPai(pai);
            no.setEsq(pai);
            pai.setPai(no);
        } else {
//            no.setDir(no.getEsq());
//            no.getEsq().setPai(raiz);
            raiz = no;
            raiz.setPai(null);
            pai.setDir(raiz.getEsq());
            raiz.getEsq().setPai(pai);
            pai.setPai(raiz);
            raiz.setEsq(pai);
        }
    }

    void rodarDir(No<T> no) {
        No pai = no.getPai();
        No avo = pai.getPai();
        if (!pai.equals(raiz)) {
            avo.setDir(no);
            no.setPai(avo);
            pai.setEsq(no.getEsq());
            no.getDir().setPai(pai);
            no.setDir(pai);
            pai.setPai(no);
        } else {
            raiz = no;
            raiz.setPai(null);
            pai.setEsq(raiz.getDir());
            raiz.getDir().setPai(pai);
            pai.setPai(raiz);
            raiz.setDir(pai);
        }
    }

    private No<T> getIrmao(No<T> ptr) {
        No<T> pai = ptr.getPai();
        if (pai == null) {
            return null;
        }
        if (pai.getEsq().equals(ptr)) {
            return pai.getDir();
        } else {
            return pai.getEsq();
        }
    }

    public void imprimeEmOrdem() {
        if (raiz == null || raiz.getValor() == null) {
            return;
        }
        System.out.println();
        emOrdem(raiz);
        System.out.println();
    }

    public void emOrdem(No<T> ptr) {
        if (ptr.getValor() == null) {
            return;
        }
        if (!ptr.getEsq().equals(sentinela)) {
            emOrdem(ptr.getEsq());
        }
        System.out.println(ptr);
        if (!ptr.getDir().equals(sentinela)) {
            emOrdem(ptr.getDir());
        }
    }

    void remove(int valor) {

    }

}

/*
 * 1 30 1 21 1 71 1 20 1 25 1 47 1 86 1 11 1 77 1 87
 * 
 * 1 30 1 21 1 80 1 20 1 25 1 71 1 86 1 11
 * 
 * 
 * 1 33 1 15 1 47 1 10 1 20 1 38 1 51 1 5 1 18 1 36 1 39 1 49
 * 
 * 1 33 1 20 1 40 1 39 1 41 1 42 2 42
 * 
 * 1 33 1 30 1 40 1 11 2 11
 * 
 * 1 33 1 15 1 47 1 10 1 20 1 38 1 51 1 5 1 18 1 36 1 39 1 49
 * 
 * 1 33 1 15 1 51 1 10 1 20 1 38 1 47 1 5 1 18 1 36 1 39 1 53
 * 
 */
