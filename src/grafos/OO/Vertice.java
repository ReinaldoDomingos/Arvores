/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package grafos.OO;

public class Vertice<T> {

    private T rotulo;
    private static int id = 0;
    private final int index;
    private boolean impresso = false;

    public Vertice(T rotulo) {
        this.rotulo = rotulo;
        this.index = id++;
    }

    public void imprime() {
        System.out.print(rotulo);
    }

    /**
     * @return the rotulo
     */
    public T getRotulo() {
        return rotulo;
    }

    /**
     * @param rotulo the rotulo to set
     */
    public void setRotulo(T rotulo) {
        this.rotulo = rotulo;
    }

    public int getIndex() {
        return index;
    }

    public boolean jaImpresso() {
        return impresso;
    }

    public void setImpresso(boolean impresso) {
        this.impresso = impresso;
    }

    @Override
    public String toString() {
        return String.valueOf(rotulo);
    }

    public static void zerarId() {
        Vertice.id = 0;
    }
}
