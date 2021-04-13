package grafos.OO;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;

public class Ex2545 {

    public static void main(String[] args) throws IOException {

        InputStreamReader is = new InputStreamReader(System.in);
        BufferedReader bf = new BufferedReader(is);
        String[] entrada;
        Grafo<Integer, Integer> grafo;
        Aresta<Integer, Integer> aresta;
        Vertice<Integer> v;

        int j = 0;
        String linha = null;
        do {
            grafo = new Grafo<>();
            linha = bf.readLine();
            if (linha.equals("-1"))
                break;
            int qtdVertices = Integer.parseInt(String.valueOf(linha));

            for (int i = 0; i <= qtdVertices - 1; i++) {
                v = new Vertice<Integer>(Integer.parseInt(String.valueOf(i + 1)));
                grafo.getListaDeVertices().add(v);
            }

            for (int k = 0; k < qtdVertices; k++) {
                String[] quemArquivoDepente = bf.readLine().split(" ");
                int qtdDepedencia = Integer.parseInt(quemArquivoDepente[0]);
                for (int i = 1; i < quemArquivoDepente.length; i++) {
                    aresta = new Aresta<>(grafo.buscaVertice(Integer.parseInt(quemArquivoDepente[i])), grafo.buscaVertice(k + 1));
                    grafo.getListaDeArestas().add(aresta);
                }
            }
            Vertice.zerarId();
            System.out.println(grafo.ordenarTopologicamente());
        }while (bf.ready());
    }
}
