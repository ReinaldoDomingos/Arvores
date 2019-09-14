package abb.exercicios.slides;

import java.util.ArrayList;

public class ABB<T extends Comparable<T>> {

    private No<T> raiz;
    private T ant;
    private boolean isABB = true;
    private ArrayList<Integer> vetorCrescente;

    public ABB() {
        this.raiz = null;
        vetorCrescente = new ArrayList();
    }

    public No<T> busca(T valor) {
        if (raiz == null) {
            return null;
        }
        No<T> ptr;
        ptr = raiz;
        while (ptr.getValor() != null && ptr.getValor() != valor) {
            if (valor.compareTo(ptr.getValor()) == -1) {
                ptr = ptr.getEsq();
            } else if (valor.compareTo(ptr.getValor()) == 1) {
                ptr = ptr.getDir();
            } else {
                break;
            }
        }
        return ptr;
    }

    public void insereRecursivo(T valor) {
        if (raiz == null) {
            raiz = new No<T>(valor);
        } else {
            insereRecursivo(valor, raiz);
        }
    }

    public void insereRecursivo(T valor, No<T> no) {
        if (no.getValor() == null) {
            no.setValor(valor);
            no.setEsq(new No<T>());
            no.setDir(new No<T>());
            return;
        } else if (valor.compareTo(no.getValor()) == -1) {
            insereRecursivo(valor, no.getEsq());
        } else if (valor.compareTo(no.getValor()) == 1) {
            insereRecursivo(valor, no.getDir());
        }
    }

    public No<T> insere(T valor) {
        if (raiz == null) {
            raiz = new No<T>(valor);
            return raiz;
        } else {
            No<T> buscado = busca(valor);
            if (buscado.getValor() == null) {
                buscado.setValor(valor);
                buscado.setEsq(new No<T>());
                buscado.setDir(new No<T>());
                return buscado;
            }
        }
        return null;
    }

    public void remove(T valor) {
        No<T> buscado = busca(valor);
        if (buscado == null || buscado.getValor() == null) {
            return;
        }
        switch (getNumeroDeFilhos(buscado)) {
            case 0:
                mataNo(buscado);
                break;
            case -1:
                buscado.setValor(buscado.getEsq().getValor());
                if (buscado.getEsq().getDir() != null) {
                    buscado.setDir(buscado.getEsq().getDir());
                }
                buscado.setEsq(buscado.getEsq().getEsq());
                break;
            case 1:
                buscado.setValor(buscado.getDir().getValor());
                if (buscado.getDir().getEsq() != null) {
                    buscado.setEsq(buscado.getDir().getEsq());
                }
                buscado.setDir(buscado.getDir().getDir());
                break;
            case 2:
                No<T> substituto = menorDir(buscado);
                T removido = substituto.getValor();
                remove(removido);
                buscado.setValor(removido);
        }
    }

    public No<T> menorDir(No<T> no) {
        No<T> ant = null;
        no = no.getDir();
        while (no.getValor() != null) {
            ant = no;
            no = no.getEsq();
        }
        return ant;
    }

    private int getNumeroDeFilhos(No<T> no) {
        int quantidade = 0;
        if (no.getEsq().getValor() != null) {
            quantidade = -1;
        }
        if (no.getDir().getValor() != null) {
            quantidade = quantidade * (-1) + 1;
        }
        return quantidade;
    }

    private void mataNo(No<T> no) {
        no.setValor(null);
        no.setDir(null);
        no.setEsq(null);
    }

    public void imprimeEmOrdem() {
        if (raiz == null || raiz.getValor() == null) {
            return;
        }
        emOrdem(raiz);
        System.out.println();
    }

    public void emOrdem(No<T> ptr) {
        if (ptr.getEsq().getValor() != null) {
            emOrdem(ptr.getEsq());
        }
        imprimeNo(ptr);
        if (ptr.getDir().getValor() != null) {
            emOrdem(ptr.getDir());
        }
    }

    public void getVetorCrescente() {
        if (raiz == null || raiz.getValor() == null) {
            return;
        }
        getVetorCrescente(raiz);
        System.out.println(vetorCrescente);
    }

    private void getVetorCrescente(No<T> ptr) {
        if (ptr.getEsq().getValor() != null) {
            emOrdem(ptr.getEsq());
        }
        addValorVetorCrescente(ptr);
        if (ptr.getDir().getValor() != null) {
            emOrdem(ptr.getDir());
        }
    }

    public void addValorVetorCrescente(No<T> no) {
        vetorCrescente.add(Integer.parseInt(no.getValor().toString()));
    }

    public void imprimeNo(No<T> no) {
        System.out.print(no + " ");
    }

    public void isABB() {
        if (raiz == null || raiz.getValor() == null) {
            return;
        }
        isABB(raiz);
        if (isABB) {
            System.out.println("É uma arvore binaria de busca!");
        } else {
            System.out.println("Não é uma arvore binaria de busca!");
        }
    }

    public void isABB(No<T> ptr) {
        ant = null;
        if (ptr.getEsq().getValor() != null) {
            isABB(ptr.getEsq());
        }
        if (!isMaiorQueAnterior(ptr) || !isABB) {
            return;
        }
        if (ptr.getDir().getValor() != null) {
            isABB(ptr.getDir());
        }
    }

    private boolean isMaiorQueAnterior(No<T> no) {
        boolean ret = true;
        if (ant != null) {
            ret = no.getValor().compareTo(ant) == 1;
        }
        if (isABB && !ret) {
            isABB = ret;
        }
        ant = no.getValor();
        return ret;
    }

    public void imprimeAlturaNo(T valor) {
        No<T> ptr = raiz;

        while (ptr.getValor() != valor) {
            if (valor.compareTo(ptr.getValor()) == -1) {
                ptr = ptr.getEsq();
            } else if (valor.compareTo(ptr.getValor()) == 1) {
                ptr = ptr.getDir();
            }
        }
        System.out.println(getAltura(ptr));
    }

    public int getAltura(No<T> ptr) {
        int h1 = 0, h2 = 0;
        if (ptr.getEsq().getValor() != null) {
            h1 = getAltura(ptr.getEsq());
        } else {
            h1 = 0;
        }
        if (ptr.getDir().getValor() != null) {
            h2 = getAltura(ptr.getDir());
        } else {
            h2 = 0;
        }
        return 1 + max(h1, h2);
    }

    private int max(int n1, int n2) {
        if (n1 > n2) {
            return n1;
        }
        return n2;
    }

    public void imprimeNivelNo(T valor) {
        No<T> ptr = raiz;
        int nivel = 0;

        while (ptr.getValor() != valor) {
            if (valor.compareTo(ptr.getValor()) == -1) {
                ptr = ptr.getEsq();
            } else if (valor.compareTo(ptr.getValor()) == 1) {
                ptr = ptr.getDir();
            }
            nivel++;
        }
        System.out.println(nivel);
    }
}
