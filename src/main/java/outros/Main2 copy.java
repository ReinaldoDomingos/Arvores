// import java.util.*;

// public class Main2 {
//     static int[] frequenciaLetras = new int[256];
//     static String[] codigos_binario;
//     static String texto_inicial = "";
//     static String texto_em_binario = "";
//     static String texto_em_asci = "";
//     static String texto_legivel = "";

//     public static void main(String[] args) {
//         String teste = "paralelepipedo";
//         // String teste = "AAAAAABBBBBCCCCDDDEEF";

//         getFrequenciaCharacteres(teste);
//         // gerarCodigosBinarioCaracteres(teste);
//         Heap.No arvore = create(frequenciaLetras);
//         // imprimePreOrdem(arvore);
//         imprimeEmOrdem(arvore);
//         System.out.println(texto_legivel);
//         System.out.println(texto_em_asci);
//         System.out.println(texto_em_binario);
//     }

//     private static void gerarCodigosBinarioCaracteres(String teste) {
//         String letra = "", codigo = "";
//         int cod = 0;
//         for (int i = 0; i < teste.length(); i++) {
//             char c = teste.charAt(i);
//             letra += (char) c + "  ";
//             // i += frequenciaLetras[c] - 1;
//             int j = i;

//             for (; i <= j + frequenciaLetras[c] - 1; i++) {
//                 if (i - j == 0)
//                     codigo += Integer.toBinaryString(cod++) + " ";
//             }
//             i--;
//         }
//         System.out.println("\n" + letra);
//         System.out.println(codigo);
//     }

//     private static void getFrequenciaCharacteres(String teste) {
//         for (char c : teste.toCharArray())
//             frequenciaLetras[c]++;
//     }

//     public static void imprimeEmOrdem(Heap.No raiz) {
//         System.out.println();
//         if (raiz == null)
//             return;
//         emOrdem(raiz);
//     }

//     public static void imprimePreOrdem(Heap.No raiz) {
//         System.out.println();
//         if (raiz == null)
//             return;
//         emOrdem(raiz);
//     }

//     public static void preOrdem(Heap.No ptr) {
//         // System.out.println(ptr.esq + " - " + ptr.dir);
//         imprimeNo(ptr);
//         if (ptr.esq != null)
//             emOrdem(ptr.esq);
//         if (ptr.dir != null)
//             emOrdem(ptr.dir);
//     }

//     public static void emOrdem(Heap.No ptr) {
//         // System.out.println(ptr.esq + " - " + ptr.dir);
//         if (ptr.esq != null)
//             emOrdem(ptr.esq);
//         imprimeNo(ptr);
//         if (ptr.dir != null)
//             emOrdem(ptr.dir);
//     }

//     public static void imprimeNo(Heap.No no) {
//         if (no.esq == null && no.dir == null) {
//             texto_em_binario += (no + ",");
//             texto_legivel += ((char) no.letra) + ",";
//             texto_em_asci += (no.letra) + ",";
//         } else {
//             // texto_em_binario += "0,";
//         }
//     }

//     public static Heap.No create(int[] frequenciaLetras) {
//         // PriorityQueue<Huffman> heap = new PriorityQueue<Huffman>();
//         Heap heap = new Heap();

//         // Cria as Folhas da árvore para cada letra
//         // Insere os elementos em uma nova folha no heap
//         for (int i = 0; i < frequenciaLetras.length; i++) {
//             if (frequenciaLetras[i] > 0) {
//                 // System.out.println("------------------");
//                 heap.inserir(new Heap.No(frequenciaLetras[i], i));
//                 // System.out.println(heap);
//                 // System.out.println("------------------");
//             }
//             // heap.inserir(new Folha(frequenciaLetras[i], (char) i));
//         }
//         // System.out.println(heap);

//         // int n = 1;
//         int n = 1;
//         while (heap.lista.size() > n) {
//             // System.out.println(heap.lista.get(0) + " , " +
//             // heap.lista.get(heap.lista.size() - 1));
//             // Pega os dois nós com menor frequência
//             Heap.No a = heap.remover();
//             // System.out.println(heap);
//             Heap.No b = heap.remover();
//             // System.out.println(heap);
//             // System.out.println(a + " " + b);

//             Heap.No inserido = new Heap.No(a, b);
//             // Criar os nós da arvore binária
//             heap.inserir(inserido);
//             // System.out.println("inserido " + inserido + " " + inserido.esq + " " +
//             // inserido.dir);
//             // System.out.println(heap.lista.get(0) + " , " +
//             // heap.lista.get(heap.lista.size() - 1));
//             // System.out.println(heap);
//         }
//         // Retorna o primeiro nó da árvore
//         // Heap.No huffmanTree = heap.remover();
//         Heap.No huffmanTree = heap.get();

//         // System.out.println("removido " + huffmanTree + " " + huffmanTree.esq + " " +
//         // huffmanTree.dir);
//         return huffmanTree;
//     }

//     void imprimirFrequencias() {
//         for (int i = 0; i < frequenciaLetras.length; i++) {
//             System.out.println(frequenciaLetras[i]);
//         }
//     }
// }

// class Heap {
//     ArrayList<No> lista = new ArrayList<>();
//     No raiz;

//     boolean remover(int x) {
//         if (x == lista.get(0).getKey()) {
//             remover();
//             return true;
//         }
//         return false;
//     }

//     No get() {
//         return lista.get(0);
//     }

//     No remover() {
//         troca(0, lista.size() - 1);
//         No ret = lista.remove(lista.size() - 1);
//         descer(0);
//         return ret;
//     }

//     void inserir(No novo) {
//         if (lista.size() == 0) {
//             lista.add(novo);
//         } else {
//             raiz = novo;
//             // System.out.println(raiz);
//             lista.add(raiz);
//             for (int i = 0; i < lista.size(); i++) {
//                 // subir(pos);
//                 subir(lista.size() - 1);
//             }
//         }
//         // System.out.println(novo.esq + " ... " + novo.dir);
//     }

//     void inserir(int key) {
//         No novo = new No(key);
//         inserir(novo);
//     }

//     void subir(int pos) {
//         int pai = getPai(pos);
//         // System.out.println("pai " + lista.get(pai));
//         if (lista.get(pai).getKey() > lista.get(pos).getKey()) {
//             troca(pai, pos);
//             subir(pai);
//         } else {
//             // System.out.println("subido");
//             // System.out.println(lista);
//         }
//     }

//     void descer(int pos) {
//         int esq, dir, menor = pos;
//         esq = (pos > 0) ? (pos + 1) * 2 - 1 : 1;
//         dir = (pos > 0) ? esq + 1 : 2;
//         if (lista.size() > 2) {
//             if (esq < lista.size())
//                 menor = esq;
//             if (dir < lista.size())
//                 if (lista.get(dir).getKey() > lista.get(menor).getKey())
//                     menor = dir;
//             if (pos != menor && lista.get(menor).getKey() > lista.get(pos).getKey()) {
//                 troca(pos, menor);
//                 descer(menor);
//             }
//         }
//     }

//     void troca(Integer pos1, Integer pos2) {
//         // System.out.println(pos1 + " " + pos2);
//         No p = lista.get(pos1);
//         No pai = new No(p.getKey(), p.letra);
//         pai.dir = p.dir;
//         pai.esq = p.esq;

//         No f = lista.get(pos2);
//         No filho = new No(f.getKey(), f.letra);
//         filho.dir = f.dir;
//         filho.esq = f.esq;

//         lista.set(pos1, filho);
//         lista.set(pos2, pai);
//     }

//     int getPai(int pos) {
//         if (pos > 2)
//             pos /= 2;
//         else
//             pos = 0;
//         return pos;
//     }

//     @Override
//     public String toString() {
//         return "" + this.lista;
//     }

//     static class No {
//         private int key = -1;
//         int letra;
//         No esq, dir;

//         public No(int key) {
//             this.key = key;
//         }

//         public No(No no1, No no2) {
//             this.key = no1.key + no2.key;
//             this.esq = no1;
//             this.dir = no2;
//         }

//         public No(int key, int letra) {
//             this.key = key;
//             this.letra = letra;
//         }

//         public int getKey() {
//             return key;
//         }

//         @Override
//         public String toString() {
//             // return String.valueOf(key + " " + esq + " " + dir);
//             return String.valueOf(key);
//             // return key + "";
//             // return ((char) letra) + "(" + key + ") ";
//         }
//     }
// }