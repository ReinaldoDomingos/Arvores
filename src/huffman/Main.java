package huffman;

import static huffman.Arquivo.ler;

public class Main {

    public static void main(String args[]) {

//        String teste = ler("teste.txt");
//        ArvoreHuffman tree = new ArvoreHuffman(teste);
//        tree.compactar();

        ArvoreHuffman arvoreHuffman = new ArvoreHuffman();
        arvoreHuffman.descompactar("legivel.txt");
        System.out.println();
    }

}
