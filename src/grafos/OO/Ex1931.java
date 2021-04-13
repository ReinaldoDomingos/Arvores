package grafos.OO;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.List;

public class Ex1931 {

    public static void main(String[] args) throws IOException {

        InputStreamReader is = new InputStreamReader(System.in);
        BufferedReader bf = new BufferedReader(is);
        String[] entrada;
        Grafo<Integer, Integer> grafo, grafoAux;
        Aresta<Integer, Integer> aresta;
        Vertice<Integer> v;


        grafo = new Grafo<>();
        grafoAux = new Grafo<>();
        entrada = bf.readLine().split(" ");
        int qtdVertices = Integer.parseInt(entrada[0]);
        int qtdArestas = Integer.parseInt(entrada[1]);

        for (int i = 0; i <= qtdVertices - 1; i++) {
            v = new Vertice<Integer>(Integer.parseInt(String.valueOf(i + 1)));
            grafo.getListaDeVertices().add(v);
            grafoAux.getListaDeVertices().add(v);
        }

        String[] linha;
        for (int k = 0; k < qtdArestas; k++) {
            linha = bf.readLine().split(" ");
            int vertice1 = Integer.parseInt(linha[0]);
            int vertice2 = Integer.parseInt(linha[1]);
            int peso = Integer.parseInt(linha[2]);
            aresta = new Aresta<Integer, Integer>(grafo.buscaVertice(vertice1), grafo.buscaVertice(vertice2), peso);
            grafo.getListaDeArestas().add(aresta);
        }


        Vertice.zerarId();

        for (int k = 0; k < grafo.getListaDeVertices().size(); k++) {
            int rotulo = grafo.getListaDeVertices().get(k).getRotulo();
            List<Aresta<Integer, Integer>> listaArestasDoVertice = grafo.listaArestasDoVertice(grafo.buscaVertice(rotulo));
            for (int i = 0; i < listaArestasDoVertice.size(); i++) {
                Vertice<Integer> vizinho;
                Aresta<Integer, Integer> aresta2 = listaArestasDoVertice.get(i);
                if (aresta2.getOrigem().getRotulo().compareTo(rotulo) != 0)
                    vizinho = aresta2.getOrigem();
                else
                    vizinho = aresta2.getDestino();

                List<Aresta<Integer, Integer>> listaArestasDoVizinho = grafo.listaArestasDoVertice(vizinho);
                for (int j = 0; j < listaArestasDoVizinho.size(); j++) {
                    Vertice<Integer> vizinhoDoVizinho;
                    Aresta<Integer, Integer> aresta1 = listaArestasDoVizinho.get(j);
                    if (aresta1.getOrigem().equals(vizinho))
                        vizinhoDoVizinho = aresta1.getDestino();
                    else
                        vizinhoDoVizinho = aresta1.getOrigem();
                    if (vizinhoDoVizinho.getRotulo().compareTo(rotulo) != 0) {
                        aresta = null;
                        aresta = grafoAux.getAresta(rotulo, vizinhoDoVizinho.getRotulo());
                        if (aresta == null)
                            aresta = grafoAux.getAresta(vizinhoDoVizinho.getRotulo(), rotulo);

                        int pesoTotal = aresta2.getChave() + aresta1.getChave();
                        if (aresta == null || aresta.getChave().compareTo(pesoTotal) == 1) {
                            int peso = pesoTotal;
                            if (aresta == null) {
                                aresta = new Aresta<Integer, Integer>(grafoAux.buscaVertice(rotulo), grafoAux.buscaVertice(vizinhoDoVizinho.getRotulo()), peso);
                                grafoAux.getListaDeArestas().add(aresta);
                                aresta = new Aresta<Integer, Integer>(grafoAux.buscaVertice(vizinhoDoVizinho.getRotulo()), grafoAux.buscaVertice(rotulo), peso);
                                grafoAux.getListaDeArestas().add(aresta);
                            } else {
                                aresta.setOrigem(grafoAux.buscaVertice(rotulo));
                                aresta.setDestino(grafoAux.buscaVertice(vizinhoDoVizinho.getRotulo()));
                                aresta.setChave(pesoTotal);
                            }
                        }
                    }
                }
            }
        }

        System.out.println(grafoAux.menorCaminhoDijkstra(1, qtdVertices));
    }
}
