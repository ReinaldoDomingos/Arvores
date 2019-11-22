package outros;

class Folha extends Huffman {
    public final char valor;

    public Folha(int frequencia, char valor) {
        super(frequencia);
        this.valor = valor;
    }
}