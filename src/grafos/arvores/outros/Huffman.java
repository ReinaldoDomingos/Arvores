package grafos.arvores.outros;

abstract class Huffman implements Comparable<Huffman> {
    public final int frequencia; 
    
    public Huffman(int freq) { 
    	frequencia = freq; 
    }
    
    public int compareTo(Huffman t) {
        return frequencia - t.frequencia;
    }
}