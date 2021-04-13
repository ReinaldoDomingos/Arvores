package grafos.arvores.outros;

class No extends Huffman {
    public final Huffman esq, dir;
 
    public No(Huffman esq, Huffman dir) {
        super(esq.frequencia + dir.frequencia);
        this.esq = esq;
        this.dir = dir;
    }
}