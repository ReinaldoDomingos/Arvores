/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package grafos.OO;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;

public class Ex1076 {

    public static void main(String[] args) throws IOException {

        InputStreamReader is = new InputStreamReader(System.in);
        BufferedReader bf = new BufferedReader(is);
        String[] entrada;
        Grafo<Integer, Integer> grafo;
        Aresta<Integer, Integer> aresta;
        Vertice<Integer> v;

        int qtdTestes = Integer.parseInt(bf.readLine());
        int j = 0;
        while (j++ < qtdTestes) {
            grafo = new Grafo<>();

            int inicio = Integer.parseInt(bf.readLine());
            String[] linha = bf.readLine().split(" ");
            int qtdVertices = Integer.parseInt(linha[0]);
            int qtdArestas = Integer.parseInt(linha[1]);
//            System.out.println(j);
//            System.out.println(inicio);
//            System.out.println(qtdVertices + " " + qtdArestas);

            for (int i = 0; i <= qtdVertices - 1; i++) {
                v = new Vertice<Integer>(Integer.parseInt(String.valueOf(i)));
                grafo.getListaDeVertices().add(v);
            }

            for (int k = 0; k < qtdArestas; k++) {
                linha = bf.readLine().split(" ");
                int vertice1 = Integer.parseInt(linha[0]);
                int vertice2 = Integer.parseInt(linha[1]);
//                System.out.println(vertice1 + " " + vertice2);
                aresta = new Aresta<Integer, Integer>(grafo.buscaVertice(vertice1), grafo.buscaVertice(vertice2));
                grafo.getListaDeArestas().add(aresta);
                aresta = new Aresta<>(grafo.buscaVertice(vertice2), grafo.buscaVertice(vertice1));
                grafo.getListaDeArestas().add(aresta);
            }
            grafo.buscaEmProfundidade(inicio);
            Vertice.zerarId();
        }
    }
}
