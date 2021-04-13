package grafos.arvores.outros;

import java.util.ArrayList;

public class ArvoreHuffmanOld {

    int[] frequenciaLetras = new int[256];
    ArrayList<No> heapTabelaDeCodigos = new ArrayList<>();
    ArrayList<No> tabelaCodificadaEmNivel = new ArrayList<>();
    String texto;
    String textoCabecalho = "";
    No arvoreHuffman;
    String tabelaDeCodigos = "";
    String textoBinarioCompatado = "";

    public ArvoreHuffmanOld(String texto) {
        this.texto = texto;
        criar();
    }

    void criar() {
        gerarHeap();
        // getTextoBinarioSemCompatar();
        criarArvoreHuffman();
        gerarTabelaCodigos();
        gerarTextoBinarioCompatado();
    }

    void escrever() {

    }

    public void gerarTextoBinarioCompatado() {
        for (No no : tabelaCodificadaEmNivel) {
            for (int i = 0; i < no.freq; i++) {
                textoBinarioCompatado += no.cod;
            }
        }
    }

    public String compactar() {
        String textoCompactado = "";

        for (int i = 0; i < texto.length(); i++) {
            char letra = texto.charAt(i);
            No ptr = null;

            for (int j = 0; j < tabelaCodificadaEmNivel.size(); j++) {
                No no = tabelaCodificadaEmNivel.get(j);
                if (no.letra.charAt(0) == letra) {
                    ptr = no;
                    textoCompactado += ptr.cod + " ";
                    textoCompactado += getBinarioFormatado(Integer.toBinaryString(letra), 8) + " ";
                    int k = i;
                    for (; i < k + ptr.freq; i++) {
                    }
                    j--;
                    break;
                }
            }
        }
        return textoCompactado;
    }

    String getTextoCabecalho() {
        return textoCabecalho + "\r\n";
    }

    void compactarComHuffman() {
        gerarCodigoEmPreOrdem(arvoreHuffman);
        System.out.println(textoCabecalho);
    }

    void gerarCodigoEmPreOrdem(No no) {
        if (no.letra.length() == 1) {
            textoCabecalho += "1 " + getBinarioFormatado(Integer.toBinaryString(no.letra.charAt(0)), 8) + " ";
        } // textoCompactadoEmPreOrdemParaEscrita += "1 " + no.letra + " ";
        else {
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
        gerarHeap();
        return heapTabelaDeCodigos;
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
        for (int i = 0; i < texto.length(); i++) {
            char letra = texto.charAt(i);
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
        int cod = 0;
        for (int i = 0; i < frequenciaLetras.length; i++) {
            if (frequenciaLetras[i] > 0) {
                // System.out.println(tabela_codigos);
                String codigo = Integer.toBinaryString(cod++);
                No novo = new No(String.valueOf((char) i), getBinarioFormatado(codigo, 3), frequenciaLetras[i]);
                add(novo);
            }
        }
    }

    public void contarFrequencias() {
        for (char c : texto.toCharArray()) {
            frequenciaLetras[c]++;
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

        public No(String letra, String cod, int freq) {
            this.letra = letra;
            this.cod = cod;
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
            // return freq + " " + letra + " " + cod + " a" + aresta;
        }
    }
}
