package arvores.avl;
public class AVL<T extends Comparable<T>> {
    private No<T> raiz, pai, avo;

    public AVL() {
        this.raiz = null;
    }

    private No<T> busca(T valor) {
        if (this.raiz == null || this.raiz.getValor() == null)
            return null;

        No<T> ponteiro = this.raiz;

        while (ponteiro.getValor() != null && !ponteiro.getValor().equals(valor)) {
            if (valor.compareTo(ponteiro.getValor()) > 0) {
                ponteiro = ponteiro.getDir();
            } else if (valor.compareTo(ponteiro.getValor()) < 0) {
                ponteiro = ponteiro.getEsq();
            }
        }

        return ponteiro;
    }

    public void insereRecursivo(T valor) {
        if (this.raiz == null) {
            this.raiz = new No(valor);
        } else {
            insereRecursivo(valor, this.raiz);
        }
    }

    public void insereRecursivo(T valor, No<T> ptr) {
        if (ptr == null) return;
        if (ptr.getValor() == null) {
            ocupar(ptr, valor);
            return;
        } else if (valor.compareTo(ptr.getValor()) < 0) {
            insereRecursivo(valor, ptr.getEsq());
        } else if (valor.compareTo(ptr.getValor()) > 0) {
            insereRecursivo(valor, ptr.getDir());
        }
        atualizarAltura(ptr);
        balancear(ptr);
    }

    public void remover(T valor) {
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
                buscado.setDir(buscado.getEsq().getDir());
                buscado.setEsq(buscado.getEsq().getEsq());
                break;

            case 1:
                buscado.setValor(buscado.getDir().getValor());
                buscado.setEsq(buscado.getDir().getEsq());
                buscado.setDir(buscado.getDir().getDir());
                break;

            case 2:
                No<T> menor = menorDir(buscado);
                T removido = menor.getValor();
                remover(menor.getValor());
                buscado.setValor(removido);

                atualizarAltura(buscado);
                int fat = buscado.getEsq().altura - buscado.getDir().altura;
                if (fat >= -1 && fat <= 1) {
                    balancear(buscado);
                }
                break;
        }

    }

    private void atualizarAltura(No<T> ptr) {
        int h1 = ptr.getEsq().altura;
        int h2 = ptr.getDir().altura;
        if (h1 > h2) {
            ptr.altura = h1 + 1;
        } else {
            ptr.altura = h2 + 1;
        }
    }

    private void ocupar(No<T> ptr, T valor) {
        ptr.setValor(valor);
        ptr.altura = 1;
        ptr.setEsq(new No<T>());
        ptr.setDir(new No<T>());
    }

    public void balancear(No<T> ptr) {
        int fat = ptr.getEsq().altura - ptr.getDir().altura;
        ptr.setFatBal(fat);
        if (fat == 0) return;
        if (ptr.getFatBal() == 2) {
            if (ptr.getEsq().getFatBal() == -1) {
                rotacaoEsquerda(ptr.getEsq());
                rotacaoDireita(ptr);
            } else {
                rotacaoDireita(ptr);
            }
        } else if (ptr.getFatBal() == -2) {
            if (ptr.getDir().getFatBal() == 1) {
                rotacaoDireita(ptr.getDir());
                rotacaoEsquerda(ptr);
            } else {
                rotacaoEsquerda(ptr);
            }
        }
    }

    private void rotacaoEsquerda(No<T> a) {
        No<T> b = a.getDir();
        No<T> c = b.getEsq();

        T aux = a.getValor();
        a.setValor(b.getValor());
        b.setValor(aux);

        b.setEsq(a.getEsq());
        a.setEsq(b);
        a.setDir(b.getDir());

        b.setDir(c);

        atualizarAltura(b);
        atualizarAltura(a);
    }

    private void rotacaoDireita(No<T> a) {
        No<T> b = a.getEsq();
        No<T> c = b.getDir();

        T aux = a.getValor();
        a.setValor(b.getValor());
        b.setValor(aux);

        b.setDir(a.getDir());
        a.setDir(b);
        a.setEsq(b.getEsq());

        b.setEsq(c);
        atualizarAltura(b);
        atualizarAltura(a);
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
        if (raiz == null || raiz.getValor() == null) return;
        emOrdem(raiz);
        System.out.println();
    }

    public void emOrdem(No<T> ptr) {
        if (ptr.getValor() == null) return;
        if (ptr.getEsq().getValor() != null) emOrdem(ptr.getEsq());
        imprimeNo(ptr);
        if (ptr.getDir().getValor() != null) emOrdem(ptr.getDir());
    }

    public void imprimeNo(No<T> no) {
        System.out.printf("[%d,%d] Filhos: %d %d\n", no.getValor(), no.altura, no.getEsq().getValor(), no.getDir().getValor());
    }

    private int max(int n1, int n2) {
        if (n1 > n2) return n1;
        return n2;
    }
}