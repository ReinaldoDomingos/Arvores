package b;

import java.util.ArrayList;

public class B<T extends Comparable<T>> {

    ArrayList<No<T>> raiz;

    public B() {
        this.raiz = new ArrayList<>();
    }

    public void inserir(T v) {
        No<T> no = new No<>(v);
        ArrayList<No<T>> r = this.raiz;

        if (r.isEmpty()) {//Arvore vazia
            r.add(no);
        } else if (isFolha(r.get(0))) {
            boolean estavaCheio = isCheio(r);

            if (compare(no, r.get(r.size() - 1)) > 0) {
                r.add(no);
            } else {
                trocar(r.get(r.size() - 1), no);
                r.add(no);
            }

            if (estavaCheio) {//Overflow
                this.raiz = split(r, 0, null);
            }
        } else {
            addNoInterno(r, 0, v);
        }
        imprimir();
        System.out.println("");
    }

    public boolean isCheio(ArrayList<No<T>> r) {
        return !(r.size() < 4);
    }

    public int compare(No<T> n1, No<T> n2) {
        return n1.getValor().compareTo(n2.getValor());
    }

    public int compare(T v1, T v2) {
        return v1.compareTo(v2);
    }

    public int compare(int v1, int v2) {
        if (v1 < v2) {
            return -1;
        }

        if (v1 > v2) {
            return 1;
        }
        return 0;
    }

    public ArrayList<No<T>> split(ArrayList<No<T>> r, int i, ArrayList<No<T>> pai) {
        int s = r.size();
        ArrayList<No<T>> novaRaiz = r;

        int meio = s / 2;
        r = new ArrayList<>();

        r.add(novaRaiz.get(meio));

        for (int j = 0; j < meio; j++) {
            r.get(0).getEsq().add(novaRaiz.get(j));
        }

        for (int j = meio + 1; j < novaRaiz.size(); j++) {
            r.get(0).getDir().add(novaRaiz.get(j));
        }

        if (pai != null && !isCheio(pai)) {
            r = subir(r, i, pai);
        }

        return r;
    }

    public ArrayList<No<T>> subir(ArrayList<No<T>> r, int i, ArrayList<No<T>> pai) {

        int pos = 0;
        while (pos < pai.size() && compare(pai.get(pos), r.get(0)) < 0) {
            pos++;
        }

        if (compare(pos, pai.size()) > 0) {
            pai.add(r.get(0));
        } else {
            pai.add(pos, r.get(0));
        }

        return r.get(0).getEsq();
    }

    private void addNoInterno(ArrayList<No<T>> n, int i, T v) {
        No<T> novo = new No<>(v);

        boolean estavaCheio = false;
        ArrayList<No<T>> rSub = null;
        int DIRECAO = 0;//0 esq e 1 dir

        if (n.size() > i + 1 && compare(novo, n.get(i + 1)) > 0) {//Filhos Menor;
            addNoInterno(n, i + 1, v);
            return;
        }

        if (compare(novo, n.get(i)) < 0) {//Filhos Menor
            estavaCheio = isCheio(n.get(i).getEsq());
            rSub = n.get(i).getEsq();
        } else if (compare(novo, n.get(i)) > 0) {//Filhos Maior
            estavaCheio = isCheio(n.get(i).getDir());
            rSub = n.get(i).getDir();
            DIRECAO = 1;
        }

        int pos = busca(rSub, v);

        if (compare(pos, rSub.size()) < 0) {//Adicona antes do fim
            if (rSub.get(pos).getEsq().size() > 0) {
                DIRECAO = 0;
                n = rSub;
                if (pos > 0 && compare(novo, rSub.get(pos - 1).getDir().get(0)) < 0) {
                    pos--;
                    rSub = rSub.get(pos).getEsq();
                    estavaCheio = isCheio(rSub);
                    pos = busca(rSub, v);
                } else {
                    rSub = rSub.get(pos).getEsq();
                    estavaCheio = isCheio(rSub);
                }
            }
            rSub.add(pos, novo);
        } else {//Adiciona no fim
            if (rSub.get(pos - 1).getEsq().size() > 0) {
                n = rSub;
                rSub = rSub.get(pos - 1).getEsq();
                estavaCheio = isCheio(rSub);
            }
            if (compare(novo, rSub.get(rSub.size() - 1)) < 0) {//Troca valor com ultimo
                trocar(rSub.get(rSub.size() - 1), novo);
            }
            rSub.add(novo);
        }
        if (estavaCheio) {//Overflow
            rSub = split(rSub, i, n);

            if (DIRECAO == 0) {
                n.get(i).setEsq(rSub);
                if (i < n.size()) {
                    n.get(i + 1).setEsq(n.get(i).getDir());
                }
            } else {
                n.get(i).setDir(rSub);
                if (i > 0) {
                    if (rSub.size() < 2) {
                        subir(rSub, 0, n);
                        n.get(i).setDir(n.get(i + 1).getEsq());
                    }
                    if (i + 2 < n.size()) {
                        n.get(i + 2).setEsq(n.get(i + 1).getDir());
                    }
                }
            }
        }
        if (this.raiz.size() > 4) {
            ArrayList<No<T>> novaRaiz = this.raiz;
            ArrayList<No<T>> r = new ArrayList<>();

            r.add(novaRaiz.get(2));

            r.get(0).setEsq(new ArrayList<>());
            r.get(0).setDir(new ArrayList<>());
            for (int j = 0; j < 2; j++) {
                r.get(0).getEsq().add(novaRaiz.get(j));
            }

            for (int j = 3; j < novaRaiz.size(); j++) {
                r.get(0).getDir().add(novaRaiz.get(j));
            }
            this.raiz = r;
        }
    }

    private void trocar(No<T> n1, No<T> n2) {
        T aux = n1.getValor();
        n1.setValor(n2.getValor());
        n2.setValor(aux);
    }

    public void imprimir() {
        System.out.println(this.raiz);
        raiz.stream().forEach((no) -> {
            System.out.println("e" + no + " " + no.getEsq() + " , d" + no + " " + no.getDir());
            no.getEsq().stream().forEach((n) -> {
                if (n.getEsq().size() > 0) {
                    System.out.println("e" + n + " " + n.getEsq() + " , d" + n + " " + n.getDir());
                }
            });
            no.getDir().stream().forEach((n) -> {
                if (n.getEsq().size() > 0) {
                    System.out.println("e" + n + " " + n.getEsq() + " , d" + n + " " + n.getDir());
                }
            });
        });
    }

    private int busca(ArrayList<No<T>> pag, T v) {

        No<T> ptr = pag.get(0);
        int i = 0;
        while (compare(v, ptr.getValor()) > 0 && i < pag.size()) {
            ptr = pag.get(i++);
        }
        return i;
    }

    private boolean isFolha(No<T> n) {
        return n.getEsq().isEmpty() && n.getDir().isEmpty();
    }

}
