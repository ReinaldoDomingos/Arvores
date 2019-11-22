package huffman;

import static huffman.Arquivo.escrever;
import static huffman.Arquivo.escreverEmBinario;
import java.io.BufferedReader;
import java.io.FileReader;
import java.util.ArrayList;

public class ArvoreHuffman {

    int[] frequenciaLetras = new int[256];
    ArrayList<No> heapTabelaDeCodigos = new ArrayList<>();
    ArrayList<No> tabelaCodificadaEmNivel = new ArrayList<>();
    ArrayList<No> heapEmOrdem = new ArrayList<>();
    String textoInicial, textoCabecalho = "", tabelaDeCodigos = "", textoBinarioCompatado = "";
    No arvoreHuffman;

    public ArvoreHuffman(String texto) {
        this.textoInicial = texto;
        criar();
    }

    public ArvoreHuffman() {
    }

    public void contarFrequencias() {
        for (char c : textoInicial.toCharArray()) {
            frequenciaLetras[c]++;
        }
    }

    void criar() {
        gerarHeap();
        System.out.println("Heap " + getHeap());
        System.out.println();
        criarArvoreHuffman();
        gerarTabelaCodigos();
        System.out.println(tabelaDeCodigos);
        gerarTextoBinarioCompatado();
    }

    No criarArvoreHuffman() {
        // System.out.println(list);
        while (heapTabelaDeCodigos.size() > 1) {
            No a = heapTabelaDeCodigos.remove(heapTabelaDeCodigos.size() - 1);
            No b = heapTabelaDeCodigos.remove(heapTabelaDeCodigos.size() - 1);
            No novo = new No(b, a);
            add(novo);
        }
        arvoreHuffman = heapTabelaDeCodigos.get(0);
        return arvoreHuffman;
    }

    void gerarTabelaCodigos() {
        ArrayList<No> fila = new ArrayList<>();

        // Percurso em nivel
        fila.add(arvoreHuffman);
        while (!fila.isEmpty()) {
            No atual = fila.remove(0);
            if (atual.letra.length() == 1) {
                tabelaCodificadaEmNivel.add(atual);
            }
            if (atual.esq != null) {
                fila.add(atual.esq);
            }
            if (atual.dir != null) {
                fila.add(atual.dir);
            }
        }

        setCodigoHuffman();
    }

    void setCodigoHuffman() {
        for (int i = 0; i < tabelaCodificadaEmNivel.size(); i++) {
            String letra = tabelaCodificadaEmNivel.get(i).letra;
            setCodigoHuffman(letra, "");
        }
    }

    void setCodigoHuffman(String letra, String codigo) {
        No ptr = arvoreHuffman;
        while (!ptr.letra.equals(letra)) {
            if (ptr.esq != null && ptr.esq.letra.contains(letra)) {
                codigo += "1";
                ptr = ptr.esq;
            } else if (ptr.dir != null && ptr.dir.letra.contains(letra)) {
                codigo += "0";
                ptr = ptr.dir;
            }
        }
        ptr.cod = codigo;
        tabelaDeCodigos += ptr.letra + " " + ptr.cod + "\n";
        heapEmOrdem.add(ptr);
    }

    public void gerarTextoBinarioCompatado() {
        for (No no : tabelaCodificadaEmNivel) {
            for (int i = 0; i < no.freq; i++) {
                textoBinarioCompatado += no.cod;
            }
        }
    }

    void compactar() {
        gerarCodigoEmPreOrdem(arvoreHuffman);

        System.out.println("Texto Cabecalho Huffman: " + textoCabecalho.length());
        System.out.println(textoCabecalho);
        System.out.println();

        System.out.println("Texto em binário após compactar:" + textoBinarioCompatado.length() + "\n"
                + textoBinarioCompatado + "\n");
        System.out.println();
        escreverEmBinario(textoCabecalho + "\r" + textoBinarioCompatado, "binario.txt");
        escrever(textoCabecalho + "\r" + textoBinarioCompatado, "legivel.txt");
    }

    public void add(No novo) {
        int tamanho = heapTabelaDeCodigos.size();
        int pos = busca(novo.freq);
        if (tamanho == 0 || novo.freq < heapTabelaDeCodigos.get(tamanho - 1).freq) {
            heapTabelaDeCodigos.add(novo);
        } else {
            if (novo.cod != null) {
                heapTabelaDeCodigos.add(pos + 1, novo);
            } else {
                if (pos > 0 && heapTabelaDeCodigos.get(pos - 1).freq <= novo.freq) {
                    heapTabelaDeCodigos.add(pos - 1, novo);
                } else {
                    heapTabelaDeCodigos.add(pos, novo);
                }
            }
        }
    }

    void descompactar(String nomeArquivo) {
        buildHuffmanCompactada(nomeArquivo);
        System.out.println("");
//        System.out.println(tabelaDeCodigos);
    }

    private void buildHuffmanCompactada(String nomeArquivo) {
        String textoCabecalho = "", textoComapactado = "";
        // Leirura do txt
        try {
            FileReader arq = new FileReader(nomeArquivo);
            BufferedReader lerArq = new BufferedReader(arq);

            textoCabecalho = lerArq.readLine();
            textoComapactado = lerArq.readLine();

            arq.close();
        } catch (Exception e) {
            System.err.printf("Erro na abertura do arquivo: %s.\n", e.getMessage());
        }

        System.out.println("Cabeçalho");
        System.out.println(textoCabecalho);
        System.out.println();
        System.out.println("Texto compactado");
        System.out.println(textoComapactado);
        System.out.println();

        // Formatando codigos para ficar legivel
        ArrayList<String> letras = new ArrayList<>(), caminho = new ArrayList<>();
        for (int i = 0; i < textoCabecalho.length(); i++) {
            String codigo = "", path = "";
            while (i < textoCabecalho.length() && textoCabecalho.charAt(i) != '1') {
                path += "0";
                i++;
            }
            i++;
            if (i + 8 <= textoCabecalho.length()) {
                path += "1";
                caminho.add(path);

                codigo = textoCabecalho.substring(i, i + 8);
                int c = Integer.parseInt(codigo, 2);
                letras.add((char) c + "");
                i += 7;
            }
        }
        // System.out.println("00001 01000101 01 01001001 1 010010000 1 01000111 1
        // 01000110 01 01000100 1 01000011 01 01000001 1 01000010");
        // System.out.println(letras);
        // System.out.println(caminho);
        criarArvoreHuffmanDescomapactando(caminho, letras, textoCabecalho);
        gerarTabelaCodigos();
        traduzirTextoCompactado(textoComapactado);
    }

    private void traduzirTextoCompactado(String textoComapactado) {
        int pos = 0;
        for (int i = 0; i < heapEmOrdem.size(); i++) {
            No letraAtual = heapEmOrdem.get(i);
            int inicio = pos, fim = inicio + letraAtual.cod.length();
            while (inicio < textoComapactado.length()
                    && textoComapactado.substring(inicio, fim).equals(letraAtual.cod)) {
                System.out.print(letraAtual.letra);
                textoInicial += letraAtual.letra;

                inicio = fim;
                fim += letraAtual.cod.length();
            }
            pos = inicio;
        }
    }

    void criarArvoreHuffmanDescomapactando(ArrayList<String> caminho, ArrayList<String> letras, String textoCabecalho) {
        No raiz = new No("1", 0), ptr;
        ArrayList<No> pais = new ArrayList<>();
        ptr = raiz;
        pais.add(raiz);
        int i = 1;
        while (textoCabecalho.charAt(i++) == '0') {
            No novo = new No(i + "", 0);
            ptr.esq = novo;
            pais.add(ptr);
            ptr = novo;
        }

        int j = 0, k = pais.size() - 1;
        No novo = new No(letras.get(j++) + "", 0);
        ptr.esq = novo;

        for (; k >= 0; k--) {
            novo = new No(k + 1 + "0", 0);
            ptr.dir = novo;
            String letra1 = "", letra2 = "";

            if (j < letras.size()) {
                letra1 = letras.get(j++);
            }
            if (j < letras.size()) {
                letra2 = letras.get(j++);
            }

            if (letra1 != "" && letra2 != "" && letra1.compareTo(letra2) == 1) {
                String aux = letra1;
                letra1 = letra2;
                letra2 = aux;
            }

            novo.letra = letra1 + "" + letra2;
            ptr.letra = ptr.esq.letra + ptr.dir.letra;
            if (letra1 != "") {
                novo.esq = new No(letra1, 0);
            }
            if (letra2 != "") {
                novo.dir = new No(letra2, 0);
            }
            ptr = pais.get(k);
        }
        arvoreHuffman = raiz;
    }

    void imprimirEmOrdem(No raiz) {
        if (raiz.esq != null) {
            imprimirEmOrdem(raiz.esq);
        }

        if (raiz.letra.length() == 1) {
            System.out.print(raiz + " ");
        }

        if (raiz.dir != null) {
            imprimirEmOrdem(raiz.dir);
        }
    }

    void gerarCodigoEmPreOrdem(No no) {
        if (no.letra.length() == 1) {
            textoCabecalho += "1" + getBinarioFormatado(Integer.toBinaryString(no.letra.charAt(0)), 8) + "";
        } else {
            textoCabecalho += "0";
        }
        if (no.esq != null) {
            gerarCodigoEmPreOrdem(no.esq);
        }
        if (no.esq != null) {
            gerarCodigoEmPreOrdem(no.dir);
        }
    }

    public ArrayList<No> getTabelaCodificadaEmNivel() {
        return tabelaCodificadaEmNivel;
    }

    public No getTree() {
        return arvoreHuffman;
    }

    public ArrayList<No> getHeap() {
        return heapTabelaDeCodigos;
    }

    String getTextoCabecalho() {
        return textoCabecalho + "\r\n";
    }

    int busca(char c) {
        int pos = -1;
        for (No no : heapTabelaDeCodigos) {
            pos++;
            if (no.letra.length() == 1 && no.letra.charAt(0) == c) {
                break;
            }
        }
        return pos;
    }

    int busca(int freq) {
        int pos = -1;
        for (No no : heapTabelaDeCodigos) {
            pos++;
            if (no.freq < freq) {
                break;
            }
        }
        return pos;
    }

    String getTextoBinarioSemCompatar() {
        String s = "";
        for (int i = 0; i < textoInicial.length(); i++) {
            char letra = textoInicial.charAt(i);
            int pos = busca(letra);
            No no = heapTabelaDeCodigos.get(pos);
            int j = i;
            for (; i < j + no.freq; i++) {
                s += no.cod;
            }
            i--;
        }
        return s;
    }

    public String getBinarioFormatado(String key, int tamanho) {
        return getBinarioFormatado(Integer.parseInt(key), tamanho);
    }

    public void gerarHeap() {
        contarFrequencias();
        for (int i = 0; i < frequenciaLetras.length; i++) {
            if (frequenciaLetras[i] > 0) {
                No novo = new No(String.valueOf((char) i), frequenciaLetras[i]);
                add(novo);
            }
        }
    }

    public String getBinarioFormatado(int key, int tamanho) {
        String s = "" + (int) key;

        while (s.length() < tamanho) {
            s = "0" + s;
        }
        return s;
    }

    class No {

        String letra;
        String cod;
        int freq;
        No esq, dir;

        public No(String letra, int freq) {
            this.letra = letra;
            this.freq = freq;
        }

        public No(No a, No b) {
            this.freq = a.freq + b.freq;
            this.letra = a.letra + b.letra;
            this.esq = a;
            this.dir = b;
        }

        @Override
        public String toString() {
             return letra + "(" + freq + ")";
            // return freq + " " + letra + " " + cod;
//            return letra;
        }
    }
}
