/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package grafos.OO;

public class Aresta<T, E> {

    private E chave;
    private Vertice<T> origem, destino;

    public Aresta(Vertice<T> origem, Vertice<T> destino) {
        this(origem, destino, null);
    }

    public Aresta(Vertice<T> origem, Vertice<T> destino, E chave) {
        this.origem = origem;
        this.destino = destino;
        this.chave = chave;
    }

    public void imprime() {
        System.out.print("(");
        this.origem.imprime();
        System.out.print(", ");
        this.destino.imprime();
        System.out.print(")");
    }

    /**
     * @return the chave
     */
    public E getChave() {
        return chave;
    }

    /**
     * @param chave the chave to set
     */
    public void setChave(E chave) {
        this.chave = chave;
    }

    /**
     * @return the origem
     */
    public Vertice<T> getOrigem() {
        return origem;
    }

    /**
     * @param origem the origem to set
     */
    public void setOrigem(Vertice<T> origem) {
        this.origem = origem;
    }

    /**
     * @return the destino
     */
    public Vertice<T> getDestino() {
        return destino;
    }

    /**
     * @param destino the destino to set
     */
    public void setDestino(Vertice<T> destino) {
        this.destino = destino;
    }


    @Override
    public String toString() {
        if (chave == null)
            return "{" + origem.getRotulo() + "," + destino.getRotulo() + '}';
        else
            return "{" + origem.getRotulo() + "," + destino.getRotulo() + "," + chave + '}';
    }
}
