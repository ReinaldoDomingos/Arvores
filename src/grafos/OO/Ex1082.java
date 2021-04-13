/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package grafos.OO;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;

public class Ex1082 {

    public static void main(String[] args) throws IOException {

        InputStreamReader is = new InputStreamReader(System.in);
        BufferedReader bf = new BufferedReader(is);
        String[] entrada;
        Grafo<Integer, Integer> grafo;
        int qtdTestes = Integer.parseInt(bf.readLine());
        int j = 0;
        while (j++ < qtdTestes) {
            Aresta<Integer, Integer> aresta;
            Vertice<Integer> v;
            grafo = new Grafo<>();
            entrada = bf.readLine().split(" ");
            int numeroDeVertice;
            int numeroDeAresta;
            int i;
            Integer verticeOrigem;
            Integer verticeDestino;
            numeroDeVertice = Integer.parseInt(entrada[0]);
            numeroDeAresta = Integer.parseInt(entrada[1]);

            for (i = 0; i <= numeroDeVertice - 1; i++) {
                v = new Vertice<Integer>(Integer.parseInt(String.valueOf('a' + i)));
                grafo.getListaDeVertices().add(v);
            }
            while (numeroDeAresta > 0) {
                entrada = bf.readLine().split(" ");
                verticeOrigem = Integer.valueOf(entrada[0].charAt(0));
                verticeDestino = Integer.valueOf(entrada[1].charAt(0));
                aresta = new Aresta<Integer, Integer>(grafo.buscaVertice(verticeOrigem), grafo.buscaVertice(verticeDestino));
                grafo.getListaDeArestas().add(aresta);
                aresta = new Aresta<>(grafo.buscaVertice(verticeDestino), grafo.buscaVertice(verticeOrigem));
                grafo.getListaDeArestas().add(aresta);
                numeroDeAresta--;
            }
            System.out.println();
            System.out.println("Case #" + j + ":");
            System.out.print(grafo.qtdComponentes() + " connected components");
            Vertice.zerarId();
            System.out.println();
        }
    }
}
