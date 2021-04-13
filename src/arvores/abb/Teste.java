package arvores.abb;

public class Teste {
    public static void main(String[] args) {
        ABB abb = new ABB();

        abb.insere(34);
        abb.insere(3);
        abb.insere(50);
        abb.insere(23);
        abb.insere(1);
        abb.imprimeEmOrdem();

        abb.remove(34);
        abb.imprimeEmOrdem();

        abb.insere(90);
        abb.insere(100);
        abb.imprimeEmOrdem();

        abb.remove(100);
        abb.imprimeEmOrdem();

        abb.insere(100);
        abb.imprimeEmOrdem();

        abb.remove(90);
        abb.imprimeEmOrdem();

        abb.remove(23);
        abb.imprimeEmOrdem();

        abb.insere(30);
        abb.imprimeEmOrdem();

        abb.remove(3);
        abb.imprimeEmOrdem();
    }
}