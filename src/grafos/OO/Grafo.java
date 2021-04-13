/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package grafos.OO;

import java.util.*;

public class Grafo<T extends Comparable<T>, E extends Comparable<E>> {

    private List<Vertice<T>> listaDeVertices;
    private List<Aresta<T, E>> listaDeArestas;

    public Grafo() {
        listaDeVertices = new LinkedList<>();
        listaDeArestas = new LinkedList<>();
    }

    public Vertice<T> buscaVertice(T rotulo) {
        for (Vertice<T> vertice : listaDeVertices) {
            if (rotulo.compareTo(vertice.getRotulo()) == 0) {
                return vertice;
            }
        }
        return null;
    }

    public void adicionaVertice(Vertice<T> vertice) {
        listaDeVertices.add(vertice);
    }

    public void adicionaAresta(Vertice<T> origem, Vertice<T> destino, E info) {
        listaDeArestas.add(new Aresta<>(origem, destino, info));
    }

    public boolean isAresta(T origem, T destino) {
        return isAresta(this.buscaVertice(origem), this.buscaVertice(destino));
    }

    private boolean isAresta(Vertice<T> origem, Vertice<T> destino) {
        for (Aresta<T, E> aresta : listaDeArestas) {
            if (aresta.getOrigem().getRotulo().compareTo(origem.getRotulo()) == 0
                    && aresta.getDestino().getRotulo().compareTo(destino.getRotulo()) == 0) {
                return true;
            }
        }
        return false;
    }

    public Aresta<T, E> getAresta(T origem, T destino) {
        return getAresta(this.buscaVertice(origem), this.buscaVertice(destino));
    }

    private Aresta<T, E> getAresta(Vertice<T> origem, Vertice<T> destino) {
        for (Aresta<T, E> aresta : listaDeArestas) {
            if (aresta.getOrigem().getRotulo().compareTo(origem.getRotulo()) == 0
                    && aresta.getDestino().getRotulo().compareTo(destino.getRotulo()) == 0) {
                return aresta;
            }
        }
        return null;
    }

    public List<Vertice<T>> listaAdjacentes(T rotulo) {
        return listaAdjacentes(this.buscaVertice(rotulo));
    }

    private List<Vertice<T>> listaAdjacentes(Vertice<T> vertice) {
        List<Vertice<T>> vizinhosDoVertice = new LinkedList<>();

        for (Aresta<T, E> aresta : listaDeArestas) {
            if (aresta.getDestino().getRotulo().compareTo(vertice.getRotulo()) == 0 ||
                    aresta.getOrigem().getRotulo().compareTo(vertice.getRotulo()) == 0) {
                int i = 0;
                for (; i < vizinhosDoVertice.size(); i++) {
                    T rotuloDestino = aresta.getDestino().getRotulo();
                    T rotuloViziho = vizinhosDoVertice.get(i).getRotulo();
                    if (rotuloDestino.compareTo(rotuloViziho) != -1)
                        break;
                }
                if (aresta.getOrigem().equals(vertice))
                    vizinhosDoVertice.add(i, aresta.getDestino());
                else
                    vizinhosDoVertice.add(i, aresta.getOrigem());
            }
        }
        return vizinhosDoVertice;
    }

    public List<Aresta<T, E>> listaArestasDoVerticeResumida(Vertice<T> vertice) {
        List<Aresta<T, E>> arestasDoVertice = new LinkedList<>();

        for (Aresta<T, E> aresta : listaDeArestas) {
            if (aresta.getOrigem().getRotulo().compareTo(vertice.getRotulo()) == 0) {
                arestasDoVertice.add(aresta);
            }
        }
        return arestasDoVertice;
    }

    public List<Aresta<T, E>> listaArestasDoVertice(Vertice<T> vertice) {
        List<Aresta<T, E>> arestasDoVertice = new LinkedList<>();

        for (Aresta<T, E> aresta : listaDeArestas) {
            if (aresta.getOrigem().getRotulo().compareTo(vertice.getRotulo()) == 0
                    || aresta.getDestino().getRotulo().compareTo(vertice.getRotulo()) == 0) {
                arestasDoVertice.add(aresta);
            }
        }
        return arestasDoVertice;
    }

    private List<Vertice<T>> listaAdjacentesPorDestino(Vertice<T> vertice) {
        List<Vertice<T>> vizinhosDoVertice = new LinkedList<>();

        for (Aresta<T, E> aresta : listaDeArestas) {
            if (aresta.getDestino().getRotulo().compareTo(vertice.getRotulo()) == 0) {
                int i = 0;
                for (; i < vizinhosDoVertice.size(); i++) {
                    T rotuloDestino = aresta.getDestino().getRotulo();
                    T rotuloViziho = vizinhosDoVertice.get(i).getRotulo();
                    if (rotuloDestino.compareTo(rotuloViziho) != -1)
                        break;
                }
                vizinhosDoVertice.add(i, aresta.getDestino());
            }
        }
        return vizinhosDoVertice;
    }

    /**
     * @return the listaDeVertices
     */
    public List<Vertice<T>> getListaDeVertices() {
        return listaDeVertices;
    }

    /**
     * @param listaDeVertices the listaDeVertices to set
     */
    public void setListaDeVertices(List<Vertice<T>> listaDeVertices) {
        this.listaDeVertices = listaDeVertices;
    }

    /**
     * @return the listaDeArestas
     */
    public List<Aresta<T, E>> getListaDeArestas() {
        return listaDeArestas;
    }

    /**
     * @param listaDeArestas the listaDeArestas to set
     */
    public void setListaDeArestas(List<Aresta<T, E>> listaDeArestas) {
        this.listaDeArestas = listaDeArestas;
    }

    public int qtdComponentes() {
        boolean isDescobertos[] = new boolean[this.listaDeVertices.size()];
        return -1;
    }

    public void buscaEmProfundidade(int origem) {
        Stack<Vertice<T>> pilha;
        pilha = new Stack<>();
        boolean isDescobertos[] = new boolean[this.listaDeVertices.size()];

        Vertice<T> origemDaBusca = listaDeVertices.get(origem);
        pilha.push(origemDaBusca);
        int movimentos = 0;
        while (!pilha.isEmpty()) {
            Vertice<T> escolhido = pilha.pop();
            if (isDescobertos[escolhido.getIndex()] == false) {
                movimentos++;
                isDescobertos[escolhido.getIndex()] = true;
                List<Vertice<T>> vizinhos = listaAdjacentes(escolhido);
                for (Vertice<T> vizinho : vizinhos) {
                    pilha.push(vizinho);
                }
            }
        }
        System.out.println((movimentos - 1) * 2);
    }

    public boolean buscaEmLargura(int origem, boolean[] isDescobertos) {
        Queue<Vertice<T>> fila;
        fila = new LinkedList<>();

        Vertice<T> origemDaBusca = listaDeVertices.get(origem);
        fila.add(origemDaBusca);
        isDescobertos[origemDaBusca.getIndex()] = true;

        while (!fila.isEmpty()) {
            Vertice<T> escolhido = fila.remove();
            List<Vertice<T>> vizinhos = listaAdjacentes(escolhido);

            for (Vertice<T> vizinho : vizinhos) {
                if (!isDescobertos[vizinho.getIndex()]) {
                    isDescobertos[vizinho.getIndex()] = true;
                    fila.add(vizinho);
                }
            }
        }

        boolean todosEncontrados = true;
        int i = origem;
        for (; i < isDescobertos.length; i++) {
            if (!isDescobertos[i]) {
                todosEncontrados = false;
                break;
            }
        }
        return todosEncontrados;
    }

    public boolean isConexo() {
        boolean isDescobertos[] = new boolean[this.listaDeVertices.size()];
        return buscaEmLargura(0, isDescobertos);
    }

    public int menorCaminhoDijkstra(T origem, T destino) {
        boolean[] isDescobertos = new boolean[listaDeVertices.size()];
        int[] distancias = new int[listaDeVertices.size()];
        for (int i = 0; i < distancias.length; i++) {
            distancias[i] = 999999999;
        }

        Queue<Vertice<T>> fila;
        fila = new LinkedList<>();
        fila.add(buscaVertice(origem));
        distancias[0] = 0;
        while (!fila.isEmpty()) {
            Vertice<T> selecionado = fila.remove();
            if (selecionado.getRotulo().compareTo(destino) == 0)
                return distancias[selecionado.getIndex()];
            if (!isDescobertos[selecionado.getIndex()]) {
                isDescobertos[selecionado.getIndex()] = true;
                List<Aresta<T, E>> listaArestasDoVerticeSelecionado = listaArestasDoVertice(selecionado);
                int menor = -1;
                for (int i = 0; i < listaArestasDoVerticeSelecionado.size(); i++) {
                    Aresta<T, E> aresta = listaArestasDoVerticeSelecionado.get(i);
                    boolean descoberto = isDescobertos[aresta.getDestino().getIndex()];
                    int novaDistancia = distancias[selecionado.getIndex()] + Integer.parseInt(aresta.getChave().toString());
                    if (!descoberto && aresta.getDestino() != selecionado
                            && novaDistancia < distancias[aresta.getDestino().getIndex()]) {
                        distancias[aresta.getDestino().getIndex()] = novaDistancia;
                    }
                }
                for (int i = 0; i < listaArestasDoVerticeSelecionado.size(); i++) {
                    Aresta<T, E> aresta = listaArestasDoVerticeSelecionado.get(i);
                    Vertice<T> destinoAresta = aresta.getDestino();
                    boolean descoberto = isDescobertos[destinoAresta.getIndex()];
                    if (!descoberto && destinoAresta != selecionado) {
                        if (destinoAresta.getRotulo() != origem && (menor == -1
                                || distancias[destinoAresta.getIndex()] < distancias[menor])) {
                            menor = destinoAresta.getIndex();
                        }
                    }
                }
                if (menor != -1) {
                    fila.add(listaDeVertices.get(menor));
                }
            }

        }
        return -1;
    }

    public int ordenarTopologicamente() {
        Queue<Vertice<T>> listaDeVerticesGrauZero = new LinkedList<>();

        Vertice.zerarId();
        Grafo<Integer, Integer> grafoCopia = new Grafo<>();
        Aresta<Integer, Integer> aresta;
        Vertice<Integer> v;
        for (int i = 0; i < listaDeVertices.size(); i++) {
            v = new Vertice<Integer>(Integer.parseInt(String.valueOf(listaDeVertices.get(i).getRotulo())));
            grafoCopia.getListaDeVertices().add(v);
            if (listaAdjacentes(listaDeVertices.get(i)).size() == 0)
                listaDeVerticesGrauZero.add(listaDeVertices.get(i));
        }

        for (int i = 0; i < listaDeArestas.size(); i++) {
            Integer origem = Integer.parseInt(String.valueOf(listaDeArestas.get(i).getOrigem().getRotulo()));
            Integer destino = Integer.parseInt(String.valueOf(listaDeArestas.get(i).getDestino().getRotulo()));
            aresta = new Aresta<Integer, Integer>(grafoCopia.buscaVertice(origem), grafoCopia.buscaVertice(destino));
            grafoCopia.getListaDeArestas().add(aresta);
        }

        if (listaDeVerticesGrauZero.size() == 0)
            return -1;

        Queue<Vertice<T>> fila = new LinkedList<>();
        int qtdMinutosAMais = 0;
        while (listaDeVerticesGrauZero.size() > 0) {
            Vertice<T> escolhido = listaDeVerticesGrauZero.remove();
            fila.add(escolhido);
            grafoCopia.removerArestas((Vertice<Integer>) escolhido);
            grafoCopia.removerVertice((Integer) escolhido.getRotulo());

            if (listaDeVerticesGrauZero.size() == 0)
                for (int i = 0; i < grafoCopia.listaDeVertices.size(); i++) {
                    if (grafoCopia.listaAdjacentesPorDestino(grafoCopia.listaDeVertices.get(i)).size() == 0) {
                        listaDeVerticesGrauZero.add((Vertice<T>) grafoCopia.listaDeVertices.get(i));
                        qtdMinutosAMais++;
                    }
                }
        }
        return qtdMinutosAMais;
    }

    private void removerVertice(T rotulo) {
        Iterator<Vertice<T>> iterator = listaDeVertices.iterator();
        while (iterator.hasNext()) {
            Vertice<T> vertice = iterator.next();
            if (vertice.getRotulo().compareTo(rotulo) == 0)
                iterator.remove();
        }
    }

    private void removerArestas(Vertice<T> v) {
        Iterator<Aresta<T, E>> iterator = listaDeArestas.iterator();
        while (iterator.hasNext()) {
            Aresta<T, E> aresta = iterator.next();
            if (aresta.getOrigem().getRotulo().compareTo(v.getRotulo()) == 0
                    || aresta.getDestino().getRotulo().compareTo(v.getRotulo()) == 0) {
                iterator.remove();
            }
        }
    }

}
