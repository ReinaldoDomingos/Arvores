package grafos.arvores.huffman;

public class Main {

    public static void main(String args[]) {

        String teste = Arquivo.ler("teste.txt");
        ArvoreHuffman tree = new ArvoreHuffman(teste);
        tree.compactar();

        ArvoreHuffman arvoreHuffman = new ArvoreHuffman();
        arvoreHuffman.descompactar("legivel.txt");
        System.out.println();
    }

}
