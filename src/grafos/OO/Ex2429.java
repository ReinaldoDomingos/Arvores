/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package grafos.OO;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;

public class Ex2429 {

    public static void main(String[] args) throws IOException {

        InputStreamReader is = new InputStreamReader(System.in);
        BufferedReader bf = new BufferedReader(is);
        String[] entrada;
        Grafo<Integer, Integer> grafo;
        Aresta<Integer, Integer> aresta;
        Vertice<Integer> v;


        grafo = new Grafo<>();
        int qtdVertices = Integer.parseInt(bf.readLine());
        for (int i = 0; i <= qtdVertices - 1; i++) {
            v = new Vertice<Integer>(Integer.parseInt(String.valueOf(i + 1)));
            grafo.getListaDeVertices().add(v);
        }

        String[] linha;
        for (int k = 0; k < qtdVertices; k++) {
            linha = bf.readLine().split(" ");
            int vertice1 = Integer.parseInt(linha[0]);
            int vertice2 = Integer.parseInt(linha[1]);
            aresta = new Aresta<Integer, Integer>(grafo.buscaVertice(vertice1), grafo.buscaVertice(vertice2));
            grafo.getListaDeArestas().add(aresta);
        }

        Vertice.zerarId();
        Grafo<Integer, Integer> grafoRev = new Grafo<>();
        for (int i = 0; i <= qtdVertices - 1; i++) {
            v = new Vertice<Integer>(Integer.parseInt(String.valueOf(i + 1)));
            grafoRev.getListaDeVertices().add(v);
        }
        for (int k = 0; k < qtdVertices; k++) {
            Vertice<Integer> origem = grafo.getListaDeArestas().get(k).getOrigem();
            Vertice<Integer> destino = grafo.getListaDeArestas().get(k).getDestino();
            aresta = new Aresta<Integer, Integer>(grafoRev.buscaVertice(destino.getRotulo()), grafoRev.buscaVertice(origem.getRotulo()));
            grafoRev.getListaDeArestas().add(aresta);
        }
        System.out.println((grafo.isConexo() && grafoRev.isConexo()) ? 'S' : 'N');
        Vertice.zerarId();
    }
}
