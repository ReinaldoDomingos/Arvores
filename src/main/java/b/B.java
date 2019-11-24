package b;

import java.util.ArrayList;
import jdk.nashorn.api.tree.ForInLoopTree;

public class B<T extends Comparable<T>> {

    Pagina<T> raiz;
    static Pagina sentinela = new Pagina();
    int MIN_CHAVES, MAX_CHAVES, MAX_FILHOS;

    public B(int ordem) {
        raiz = new Pagina<>(ordem);
        MIN_CHAVES = ordem - 1;
        MAX_CHAVES = (2 * ordem) - 1;
        MAX_FILHOS = (2 * ordem);
        System.out.println("ordem " + ordem);
        System.out.println("Min Chaves = " + MIN_CHAVES);
        System.out.println("Max Chaves = " + MAX_CHAVES);
        System.out.println("Max filhos = " + MAX_FILHOS);
        imprimePorNivel();
    }

    void imprimePorNivel() {
        if (raiz.size() > 0) {
            imprimePagina(raiz);
        }
        System.out.println("");
    }

    void inserir(int valor) {
        if (raiz.size() <= MAX_CHAVES) {
            if (raiz.equals(sentinela)) {
                raiz = new Pagina<>(MIN_CHAVES + 1);
            }
            raiz.size();
            inserir(null, raiz, valor);
            imprimePorNivel();
        }
    }

    void remove(int valor) {
    }
//
//    void buscaB(T x, Pagina ptr, int invalida, int pos) {
//        int m = 0;
//        Pagina<T> p = raiz;
//        ptr = sentinela;
//        invalida = 0;
//        while (!p.equals(sentinela)) {
//            int i = pos = 1;
//            ptr = p;
//            while (i <= m) {
//                switch (x.compareTo(p.getChave(i).getValor())) {
//                    case 1:
//                        i = pos = i + 1;
//                        break;
//                    case 0:
//                        p = sentinela;//chave encontrada
//                        invalida = 1;
//                        break;
//                    default:
//                        p = p.getChave(i - 1).getEsq();//mudança de página
//                        i = m + 2;
//                        break;
//                }
//            }
//            if (i == m + 1) {
//                p = p.getChave(m).getEsq();
//            }
//        }
//    }

    private void inserir(Pagina<T> pai, Pagina<T> pagina, int valor) {
        if (pagina.size() == MAX_CHAVES && pagina.getUltimaChave().getEsq().size() > 0) {
            if (pagina.getPai() == null) {
                Pagina<T> novaRaiz = new Pagina<>();
                Pagina filho1 = new Pagina(MAX_FILHOS / 2);
                Pagina filho2 = new Pagina(MAX_FILHOS / 2);
                ArrayList<Chave> filhos = new ArrayList();
                Chave promovido = raiz.getChave(MAX_CHAVES / 2);
                System.out.println("promovido " + promovido);

                int valorPromovido = promovido.getValor();
                for (int i = 0; i < MAX_FILHOS / 2; i++) {
                    if (pagina.getChave(i).getValor() != valorPromovido && pagina.getChave(i).getValor() != -1) {
                        filho1.add(pagina.getChave(i));
                    }
                    for (int j = 0; j < pagina.getChave(i).getEsq().size() + 1; j++) {
                        filhos.add(pagina.getChave(i).getEsq().getChave(j));
                    }
                }
                for (int i = MAX_FILHOS / 2; i < MAX_FILHOS; i++) {
                    if (pagina.getChave(i).getValor() != valorPromovido && pagina.getChave(i).getValor() != -1) {
                        filho2.add(pagina.getChave(i));
                    }
                    for (int j = 0; j < pagina.getChave(i).getEsq().size() + 1; j++) {
                        filhos.add(pagina.getChave(i).getEsq().getChave(j));
                    }
                }

                Pagina esq;

                int i = -1;
                int j = 0, k = 0;
                esq = new Pagina(MAX_FILHOS / 2);
                while (++i < filhos.size()) {
                    if (filhos.get(i).getValor() == -1 && j <= filho1.size()) {
                        filho1.getChave(j++).setEsq(esq);
                        esq.setPai(filho1.getChave(j - 1));
                        if (j == filho1.size()) {
                            filho1.setUltimaChave(filhos.get(i));
                        } else if (j == filho1.size() + 1) {
                            break;
                        }
                        esq = new Pagina(MAX_FILHOS / 2);
                    } else {
                        esq.add(filhos.get(i));
                        filhos.get(i).setPai(esq);
                    }
                }
                esq = new Pagina(MAX_FILHOS / 2);
                while (++i < filhos.size()) {
                    if (filhos.get(i).getValor() == -1 && k <= filho2.size()) {
                        filho2.getChave(k++).setEsq(esq);
                        esq.setPai(filho2.getChave(k - 1));
                        if (k == filho2.size() && i < filhos.size()) {
                            filho2.setUltimaChave(filhos.get(i));
                        } else if (k == filho2.size()) {
                            break;
                        }
                        esq = new Pagina(MAX_FILHOS / 2);
                    } else {
                        esq.add(filhos.get(i));
                        filhos.get(i).setPai(esq);
                    }
                }
                for (int l = 0; l < filho1.size(); l++) {
                    filho1.getChave(l).setPai(filho1);
                }
                System.out.println("filho1 " + filho1);
                System.out.println("filho1 " + filho1.getChave(0).getPai());
                System.out.println("filho1 " + filho1.getChave(1).getPai());
                System.out.println("filho1 " + filho1.getChave(2).getPai());
                System.out.println("filho2 " + filho2.getChaves());
                promovido.setEsq(filho1);
                System.out.println("promovido " + promovido);
                novaRaiz.add(promovido);
                promovido.setPage(novaRaiz);
                novaRaiz.getChave(0).setEsq(filho1);
                System.out.println("aqui " + novaRaiz.getChave(0).getPage());
                filho1.setPai(novaRaiz.getChave(0));
                novaRaiz.getUltimaChave().setEsq(filho2);
                filho2.setPai(novaRaiz.getUltimaChave());
                raiz = novaRaiz;
            }
        }

        System.out.println("--- CHAVE '" + valor + "' INSERIDA ---");
        int pos = 0;

        if (pagina.size() <= MAX_CHAVES) {
            if (isFolha(pagina)) {
                pagina.add(valor);
            } else {
                boolean inseriu = false;
                int i = 0;
                for (; i < pagina.size(); i++) {
                    if (valor == pagina.getChave(i).getValor()) {
                        System.out.println("igual");
                        inseriu = true;
                    } else if (valor < pagina.getChave(i).getValor()) {
                        inserir(pagina, pagina.getChave(i).getEsq(), valor);
                        inseriu = true;
                        return;
                    }
                }
                if (!inseriu) {
                    inserir(pagina, pagina.getUltimaChave().getEsq(), valor);
                    pos = i;
                }
            }
        }

        if (pagina.size()
                == MAX_FILHOS) {
            split(pagina, valor);
        }
    }

    private void split(Pagina<T> pagina, int valor) {
        System.out.println("split " + valor);
        Pagina filho1 = new Pagina(MAX_FILHOS / 2);
        Pagina filho2 = new Pagina(MAX_FILHOS / 2);
        int pos = 0;
        for (int i = 0; i < MAX_FILHOS / 2; i++) {
            if (pagina.getChave(i).getValor() != valor) {
                filho1.add(pagina.getChave(i));
            } else {
                pos = i;
            }
        }
        for (int i = MAX_FILHOS / 2; i < MAX_FILHOS; i++) {
            if (pagina.getChave(i).getValor() != valor) {
                filho2.add(pagina.getChave(i));
            } else {
                pos = i;
            }
        }

        Chave promovido = null;
        if (filho1.size() != MAX_FILHOS / 2) {
            System.out.println("chave está no filho1");
            filho1.add(pagina.getChave(pos));
            pagina.getChave(pos).setPage(filho1);
            promovido = (Chave) filho2.getChaves().remove(0);
            promovido.setEsq(filho1);;
            filho1.setPai(promovido);
            Chave pai = pagina.getPai();
            Pagina paginaDoPai = pai.getPage();
            pai.setEsq(filho2);
            filho2.setPai(pai);
            paginaDoPai.add(promovido);
            promovido.setPage(paginaDoPai);
        } else if (filho2.size() != MAX_FILHOS / 2) {
            filho2.add(pagina.getChave(pos));
            promovido = (Chave) filho1.getChaves().remove((MAX_FILHOS / 2) - 1);
            promovido.setEsq(filho1);
            if (pagina.getPai() == null) {
                System.out.println("paginar" + pagina);
                System.out.println("paginar" + pagina.getPai());
                Pagina<T> novaRaiz = new Pagina<>(MAX_FILHOS / 2);
                novaRaiz.add(promovido);
                promovido.setPage(novaRaiz);
                novaRaiz.getChave(0).setEsq(filho1);
                filho1.setPai(novaRaiz.getChave(0));
                novaRaiz.getUltimaChave().setEsq(filho2);
                filho2.setPai(novaRaiz.getUltimaChave());
                raiz = novaRaiz;
            } else {
                System.out.println("chave está no filho2");
                Chave pai = pagina.getPai();
                System.out.println("promovido " + promovido);
                System.out.println("promovido esq" + promovido.getEsq());
                System.out.println("pagina " + pagina);
                System.out.println("pai " + pai);
                System.out.println("page pai ultimaChave" + pai.getPage().getUltimaChave().getEsq());
                System.out.println("filho1 " + filho1.getChaves());
                System.out.println("filho2 " + filho2.getChaves());
                Pagina paginaDoPai = pai.getPage();
                System.out.println("paginaDoPai " + paginaDoPai);
                System.out.println("pai esq" + pai.getEsq());
                System.out.println("page pai ultimaChave" + pai.getPage().getUltimaChave().getEsq());
                Chave ultima = pai.getPage().getUltimaChave();
                pai.setEsq(filho2);
                filho2.setPai(pai);
                paginaDoPai.add(promovido);
                System.out.println("paginaDoPai " + paginaDoPai);
                System.out.println("paginaDoPai " + paginaDoPai.getChave(0) + paginaDoPai.getChave(0).getEsq());
                System.out.println("paginaDoPai " + paginaDoPai.getChave(1) + paginaDoPai.getChave(1).getEsq());
                System.out.println("paginaDoPai " + paginaDoPai.getChave(2) + paginaDoPai.getChave(2).getEsq());
                System.out.println("paginaDoPai " + paginaDoPai.getChave(3) + paginaDoPai.getChave(3).getEsq());
                promovido.setPage(paginaDoPai);
            }
        } else {
            System.out.println("aqui3");
        }
        System.out.println(filho1 + " " + promovido + " " + filho2);
    }

    private void imprimePagina(Pagina<T> page) {
        String linha1, linha2 = "", linha3 = "";
        linha1 = page.getChaves().toString();
        if (page.getChave(0).getEsq() != null) {
            for (int i = 0; i < page.size(); i++) {
                linha2 += page.getChave(i).getEsq().getChaves() + " ";
                if (page.getChave(i).getEsq().getChave(0).getEsq() != null) {
                    linha3 += page.getChave(i).getEsq().getChave(0).getEsq().getChaves() + " ";
                    linha3 += page.getChave(i).getEsq().getChave(1).getEsq().getChaves() + " ";
                    linha3 += page.getChave(i).getEsq().getChave(2).getEsq().getChaves() + " ";
                    if (page.getChave(i).getEsq().size() == 3 && page.getChave(i).getEsq().getChave(3).getEsq() != null) {
                        linha3 += page.getChave(i).getEsq().getChave(3).getEsq().getChaves() + " ";
                    }
                }
            }

            linha2 += page.getUltimaChave().getEsq().getChaves() + " ";
            if (page.getUltimaChave().getEsq().getChave(0).getEsq() != null) {
                for (int i = 0; i <= page.getUltimaChave().getEsq().size(); i++) {
//                    if (page.getUltimaChave().getEsq().getChave(0).getEsq().getChaves() + "" != null) {
                    linha3 += page.getUltimaChave().getEsq().getChave(i).getEsq().getChaves() + " ";
//                    }
                }
//                linha3 += page.getUltimaChave().getEsq().getUltimaChave().getEsq().getChaves() + " ";
            }

        }
        System.out.println(linha1.replaceAll(",", ""));
        System.out.println(linha2.replaceAll(",", ""));
        System.out.println(linha3);
//        System.out.println(linha3.replaceAll("null", "").replaceAll(",", ""));
    }

    private boolean isFolha(Pagina<T> pagina) {
        return pagina.size() == 0 || pagina.getChave(0).getEsq() == null;
    }

}
