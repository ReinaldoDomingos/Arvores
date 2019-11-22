/* import java.util.*;

public class Main {
    public static void main(String[] args) {
        String teste = "paralelepipedo";

        int[] frequenciaLetras = new int[256];
        for (char c : teste.toCharArray())
            frequenciaLetras[c]++;

        Huffman tree = create(frequenciaLetras);

        System.out.println("TABELA DE CÓDIGOS");
        System.out.println("SÍMBOLO\tQUANTIDADE\tCÓDIGO HUFFMAN");
        imprimir(tree, new StringBuffer());

        String encode = codificarEmBinario(tree, teste);

        System.out.println("\nTEXTO COMPACTADO");
        System.out.println(encode);

        System.out.println("\n\nTEXTO DECODIFICADO");
        System.out.println(decodificar(tree, encode));

    }

    public static Huffman create(int[] frquenciaLetras) {
        PriorityQueue<Huffman> heap = new PriorityQueue<Huffman>();

        // Cria as Folhas da árvore para cada letra
        for (int i = 0; i < frquenciaLetras.length; i++) {
            if (frquenciaLetras[i] > 0)
                // Insere os elementos em uma nova folha no heap
                heap.offer(new Folha(frquenciaLetras[i], (char) i));
        }

        // Percorre todos os elementos da fila
        while (heap.size() > 1) {
            // Pega os dois nós com menor frequência
            Huffman a = heap.poll();
            Huffman b = heap.poll();

            // Criar os nós da arvore binária
            heap.offer(new NoHuffman(a, b));
        }
        // Retorna o primeiro nó da árvore
        return heap.poll();
    }

    public static String codificarEmBinario(Huffman t, String encode) {
        assert t != null;

        String textoCodificado = "";
        for (char c : encode.toCharArray()) {
            textoCodificado += (getCodigoLetra(t, new StringBuffer(), c));
        }
        return textoCodificado; // Retorna o texto Compactado
    }

    public static String decodificar(Huffman t, String codigo) {
        assert t != null;

        String textoDecodificado = "";
        NoHuffman no = (NoHuffman) t;
        for (char cod : codigo.toCharArray()) {
            if (cod == '0') { // Esquerda
                if (no.esq instanceof Folha) {
                    textoDecodificado += ((Folha) no.esq).valor;
                    no = (NoHuffman) t; // Retorna para a raiz desse arvore ou subarvore
                } else {
                    no = (NoHuffman) no.esq; // Esquerda
                }
            } else if (cod == '1') { // Direita
                if (no.dir instanceof Folha) {
                    textoDecodificado += ((Folha) no.dir).valor;
                    no = (NoHuffman) t; // Retorna para a raiz desse arvore ou subarvore
                } else {
                    no = (NoHuffman) no.dir; // Direita
                }
            }
        } // End for
        return textoDecodificado; // Retorna o texto Decodificado
    }

    public static void imprimir(Huffman t, StringBuffer textoCododificado) {
        assert t != null;

        if (t instanceof Folha) {
            Folha leaf = (Folha) t;

            // Imprime Letra Frequencia Código
            System.out.println(leaf.valor + "\t" + leaf.frequencia + "\t\t" + textoCododificado);

        } else if (t instanceof NoHuffman) {
            NoHuffman node = (NoHuffman) t;

            // Esquerda
            textoCododificado.append('0');
            imprimir(node.esq, textoCododificado);
            textoCododificado.deleteCharAt(textoCododificado.length() - 1);

            // Direita
            textoCododificado.append('1');
            imprimir(node.dir, textoCododificado);
            textoCododificado.deleteCharAt(textoCododificado.length() - 1);
        }
    }

    public static String getCodigoLetra(Huffman t, StringBuffer letraCodificada, char letra) {
        assert t != null;

        if (t instanceof Folha) {
            Folha leaf = (Folha) t;

            // Retorna o texto compactado da letra
            if (leaf.valor == letra) {
                return letraCodificada.toString();
            }

        } else if (t instanceof NoHuffman) {
            NoHuffman node = (NoHuffman) t;

            // Percorre a esquerda
            letraCodificada.append('0');
            String esq = getCodigoLetra(node.esq, letraCodificada, letra);
            letraCodificada.deleteCharAt(letraCodificada.length() - 1);

            // Percorre a direita
            letraCodificada.append('1');
            String dir = getCodigoLetra(node.dir, letraCodificada, letra);
            letraCodificada.deleteCharAt(letraCodificada.length() - 1);

            if (esq == null)
                return dir;
            else
                return esq;
        }
        return null;
    }
}
 */