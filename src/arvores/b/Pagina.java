package arvores.b;

import java.util.ArrayList;

/**
 *
 * @author reinaldo
 */
public class Pagina<T> {

    private ArrayList<Chave> chaves;
    private Chave ultimaChave = new Chave(), pai;
    private int MAX_CHAVES;

    Pagina(int ordem) {
        MAX_CHAVES = (2 * ordem) - 1;
        chaves = new ArrayList<>(MAX_CHAVES);//Capacidade inicial

        int pos = 0;
        ultimaChave.setPage(this);
    }

    Pagina() {
    }

    public Chave getPai() {
        return pai;
    }

    public void setPai(Chave pai) {
        this.pai = pai;
    }

    void getProxima() {
        int i = 0;
        for (; i < pai.getPage().size(); i++) {
            if (pai.getPage().getChave(i).equals(this)) {
                break;
            }
        }
        System.out.println("prox " + i);
    }

    Chave getChave(int pos) {
        if (pos == chaves.size()) {
            return ultimaChave;
        }
        return chaves.get(pos);
    }

    void add(Chave chave) {
        if (chaves != null && this.chaves.size() > 0) {
            int pos = busca(chave.getValor());
            chaves.add(pos, chave);
            chave.setPos(pos);
        } else {
            chaves = new ArrayList<>(MAX_CHAVES);
            if (chave.getValor() != -1) {
                chave.setPos(chaves.size());
                chaves.add(chave);
            } else {
                chave.setPos(MAX_CHAVES);
                ultimaChave = chave;
            }
        }
    }

    void add(int valor) {
        int pos = busca(valor);
        chaves.add(pos, new Chave(valor));
    }

    int busca(int valor) {
        int pos = 0;
        for (int i = 0; i < chaves.size(); i++) {
            if (chaves.get(i).getValor() > valor) {
                break;
            }
            pos++;
        }
        return pos;
    }

    public ArrayList<Chave> getChaves() {
        return chaves;
    }

    public int size() {
        if (chaves != null) {
            return chaves.size();
        }
        return 0;
    }

    @Override
    public String toString() {
        String s = "", s2 = "[ ";
        if (chaves == null) {
            return "";
        }
        for (int i = 0; i < chaves.size(); i++) {
            s2 += chaves.get(i).getValor() + " ";
            s2 = s2;
        }
        if (!s2.equals("[")) {
            s = s2 + "]";
        }
        return s;
    }

    public Chave getUltimaChave() {
        return ultimaChave;
    }

    public void setUltimaChave(Chave ultimaChave) {
        this.ultimaChave = ultimaChave;
    }

    public Chave getIrmao(int valor) {
//        System.out.println("getirmao " + chaves);
        Chave irmao = null;
        for (int i = 0; i < chaves.size(); i++) {
//            System.out.println("chave " + i + " " + chaves.get(i));
            if (valor < chaves.get(i).getValor()) {
                irmao = chaves.get(i);
            } else {
                break;
            }
        }
        if (irmao == null) {
            return ultimaChave;
        }
        return irmao;
    }

    void setChave(int pos, Chave chave) {
        Chave removida = chaves.remove(pos);
        chaves.add(pos, chave);
    }

}
