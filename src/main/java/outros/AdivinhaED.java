/* package outros;

import java.util.ArrayList;
import java.io.*;

public class AdivinhaED {
    public static void main(String[] args) throws IOException {
        Heap heap = new Heap<>();
        // int[] v = { 1, 2, 3, 4, 5, 6, 7, 2 };
        int[] v = { 1, 1, 1, 1, 3, 2, 3, 2 };
        int[] c = { 100, 105, 111, 114, 101, 108, 112, 97 };
        for (int i = 0; i < v.length; i++) {
            System.out.println(heap);
            heap.inserir(v[i], c[i]);
        }
        System.out.println(heap);
    }

    static class No<T> {
        private int key;
        public int letra;
        private No esq, dir;

        public No(int key, int letra) {
            this.key = key;
            this.letra = letra;
        }

        public No(int key) {
            this.key = key;
        }

        public int getKey() {
            return key;
        }

        @Override
        public String toString() {
            return String.valueOf(key) + " " + ((char) letra);
        }
    }

    static class Heap<T extends Comparable<T>> {
        ArrayList<No> lista = new ArrayList<>();
        No raiz;

        boolean remover(int x) {
            if (x == lista.get(0).getKey()) {
                remover();
                return true;
            }
            return false;
        }

        void remover() {
            troca(0, lista.size() - 1);
            lista.remove(lista.size() - 1);
            descer(0);
        }

        void inserir(int key, int letra) {
            No novo = new No(key, letra);
            if (lista.size() == 0) {
                lista.add(novo);
            } else {
                raiz = novo;
                lista.add(raiz);
                subir(lista.size() - 1);
            }
        }

        void subir(int pos) {
            int pai = getPai(pos);
            System.out.println("pai " + lista.get(pai));
            if (lista.get(pai).getKey() > lista.get(pos).getKey()) {
                troca(pai, pos);
                subir(pai);
            }
        }

        void descer(int pos) {
            int esq, dir, maior = pos;
            esq = (pos > 0) ? (pos + 1) * 2 - 1 : 1;
            dir = (pos > 0) ? esq + 1 : 2;
            if (lista.size() > 2) {
                if (esq < lista.size())
                    maior = esq;
                if (dir < lista.size())
                    if (lista.get(dir).getKey() < lista.get(maior).getKey())
                        maior = dir;
                if (pos != maior && lista.get(maior).getKey() < lista.get(pos).getKey()) {
                    troca(pos, maior);
                    descer(maior);
                }
            }
        }

        void troca(Integer pos1, Integer pos2) {
            No p = lista.get(pos1);
            No pai = new No(p.getKey(), p.letra);
            pai.dir = p.dir;
            pai.esq = p.esq;

            No f = lista.get(pos2);
            No filho = new No(f.getKey(), f.letra);
            filho.dir = f.dir;
            filho.esq = f.esq;

            lista.set(pos1, filho);
            lista.set(pos2, pai);
        }

        int getPai(int pos) {
            if (pos > 2)
                pos /= 2;
            else
                pos = 0;
            return pos;
        }

        @Override
        public String toString() {
            return "" + this.lista;
        }
    }
}
 */