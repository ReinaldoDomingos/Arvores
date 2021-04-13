/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package grafos.OO;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.List;

public class Teste {

    public static void main(String[] args) throws IOException {
        
        Grafo<Integer, Integer> grafo = new Grafo<>();
        InputStreamReader is = new InputStreamReader(System.in);
        BufferedReader bf = new BufferedReader(is);
        
        String[] entrada = bf.readLine().split(" ");
        int numeroDeVertice, numeroDeAresta, i, verticeOrigem, verticeDestino;
        
        numeroDeVertice = Integer.parseInt(entrada[0]);
        numeroDeAresta = Integer.parseInt(entrada[1]);
        
        Vertice<Integer> v;
        for(i = 1; i<=numeroDeVertice; i++){
            v = new Vertice<>(i);
            grafo.getListaDeVertices().add(v);
        }
        
        while(numeroDeAresta>0){
            entrada = bf.readLine().split(" ");
            verticeOrigem = Integer.parseInt(entrada[0]);
            verticeDestino = Integer.parseInt(entrada[1]);
            Aresta<Integer, Integer> aresta;
            aresta = new Aresta<>(grafo.buscaVertice(verticeOrigem), grafo.buscaVertice(verticeDestino));
            grafo.getListaDeArestas().add(aresta);
            aresta = new Aresta<>(grafo.buscaVertice(verticeDestino), grafo.buscaVertice(verticeOrigem));
            grafo.getListaDeArestas().add(aresta);
            numeroDeAresta--;
        }
        
//        for(Vertice<Integer> vertice: grafo.getListaDeVertices()){
//            vertice.imprime();
//            System.out.print(" ");
//        }
//        
//        for(Aresta<Integer, Integer> aresta: grafo.getListaDeArestas()){
//            aresta.imprime();
//            System.out.print(" ");
//        }
        
        List<Vertice<Integer>> vizinhosDoVertice;
        vizinhosDoVertice = grafo.listaAdjacentes(4);
        
//        for(Vertice<Integer> vertice: vizinhosDoVertice){
//            vertice.imprime();
//            System.out.print(" ");
//        }
        
//        System.out.println(grafo.isAresta(2, 5));
        
//        System.out.println(grafo.isConexo());
        System.out.println("");
        System.out.println(grafo.qtdComponentes());
        
        
    }
}
