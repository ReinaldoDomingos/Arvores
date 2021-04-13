package grafos.arvores.abb.exercicios.slides;

public class Teste {
    public static void main(String[] args) {
        ABB abb = new ABB();

        abb.insereRecursivo(34);
        abb.insereRecursivo(3);
        abb.insereRecursivo(50);
        abb.insereRecursivo(23);
        abb.insereRecursivo(1);
        abb.isABB();
        abb.getVetorCrescente();

        abb.remove(34);
        abb.isABB();

        abb.insere(90);
        abb.insere(100);
        abb.isABB();

        abb.remove(100);
        abb.isABB();

        abb.insere(100);
        abb.isABB();

        abb.remove(90);
        abb.isABB();

        abb.remove(23);
        abb.isABB();

        abb.insere(30);
        abb.isABB();
        abb.getVetorCrescente();

        abb.remove(3);
        abb.isABB();

        abb.getVetorCrescente();
    }
}