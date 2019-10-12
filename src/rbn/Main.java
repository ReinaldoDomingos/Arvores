package rbn;


import java.util.Scanner;

public class Main {

    public static void main(String[] args) {
        System.out.println("_________________________\n"
                + "-1 - Encerra o teste\n"
                + "0 - Imprime a Árvore em ordem\n"
                + "1 - Insere um dado valor\n"
                + "2 - Remove um dado valor\n"
                + "_________________________\n");

        Scanner leia = new Scanner(System.in);
        int opcao = 0, valor = 0;
        RN avl = new RN();
        while (opcao != -1) {
            String linha = leia.nextLine();
            String s[] = linha.split(" ");
            opcao = Integer.parseInt(s[0]);
            if (s.length > 1) {
                valor = Integer.parseInt(s[1]);
            }

            switch (opcao) {
                case 0:
                    avl.imprimeEmOrdem();
                    break;
                case 1:
                    avl.insere(valor);
                    break;
                case 2:
                    avl.remove(valor);
                    break;
            }
        }
    }
}
