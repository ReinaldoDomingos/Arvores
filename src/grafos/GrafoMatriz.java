package grafos;

import java.util.*;

public class GrafoMatriz {

    private Integer[][] matrizAdjacencia;
    private int numeroDeVertices;

    public int getNumeroDeVertices() {
        return this.numeroDeVertices;
    }

    public void addAresta(int origem, int destino) {
        this.matrizAdjacencia[origem][destino] = 1;
        this.matrizAdjacencia[destino][origem] = 1;
    }

    public boolean existeAresta(int origem, int destino) {
        return this.matrizAdjacencia[origem][destino] == 1;
    }

    public boolean removeAresta(int origem, int destino) {
        if (!existeAresta(origem, destino)) {
            return false;
        }
        this.matrizAdjacencia[origem][destino] = 0;
        this.matrizAdjacencia[destino][origem] = 0;
        return true;
    }

    public GrafoMatriz(int numeroDeVertices) {
        this.numeroDeVertices = numeroDeVertices;
        matrizAdjacencia = new Integer[numeroDeVertices][numeroDeVertices];
        this.inicializaMatrizAdjacencia();
    }

    private void inicializaMatrizAdjacencia() {
        for (int i = 0; i < this.numeroDeVertices; i++) {
            for (int j = 0; j < this.numeroDeVertices; j++) {
                this.matrizAdjacencia[i][j] = 0;
            }
        }
    }

    /**
     *
     * @param origem
     * @return 
     */
    public Vertice[] percursoEmLargura(int origem) {
        Queue<Integer> fila;
        fila = new LinkedList<>();
        Vertice[] vetorDeVerticesDescobertos;
        vetorDeVerticesDescobertos = new Vertice[this.numeroDeVertices];
        for (int j = 0; j < this.numeroDeVertices; j++) {
            vetorDeVerticesDescobertos[j] = new Vertice(j);
        }
        if (origem < this.numeroDeVertices) {
            fila.add(origem);
            vetorDeVerticesDescobertos[origem].setDescoberto(true);
            Integer descobertoEscolhido;
            while (!fila.isEmpty()) {
                descobertoEscolhido = fila.remove();
                for (int adjacente = 0; adjacente < this.numeroDeVertices; adjacente++) {
                    if (this.matrizAdjacencia[descobertoEscolhido][adjacente] == 1 && (!vetorDeVerticesDescobertos[adjacente].isDescoberto())) {
                        fila.add(adjacente);
                        vetorDeVerticesDescobertos[adjacente].descoberto = true;
                    }
                }

            }
        }
        return vetorDeVerticesDescobertos;
    }
}

class Vertice {

    Integer numero;
    boolean descoberto;

    public Vertice(Integer numero) {
        this.numero = numero;
        this.descoberto = false;
    }
    
    public Vertice(Integer numero, boolean descoberto) {
        this.numero = numero;
        this.descoberto = descoberto;
    }
    
   

    public Integer getNumero() {
        return numero;
    }

    public boolean isDescoberto() {
        return descoberto;
    }

    public void setDescoberto(boolean descoberto) {
        this.descoberto = descoberto;
    }

}
