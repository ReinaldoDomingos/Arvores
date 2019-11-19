package rbn;

import java.util.Scanner;

public class Main {

    public static void main(String[] args) {
        System.out.println("_________________________\n" + "-1 - Encerra o teste\n" + "0 - Imprime a Árvore em ordem\n"
                + "1 - Insere um novo valor\n" + "2 - Remove um valor\n" + "_________________________\n");

        Scanner leia = new Scanner(System.in);
        int opcao = 0, valor = 0;
        RN rbn = new RN();
        while (opcao != -1) {
            String linha = leia.nextLine();
            String s[] = linha.split(" ");
            opcao = Integer.parseInt(s[0]);
            if (s.length > 1) {
                valor = Integer.parseInt(s[1]);
            }

            switch (opcao) {
            case 0:
                rbn.imprimeEmOrdem();
                break;
            case 1:
                // avl.insere(valor);
                rbn.inserir(valor);
                break;
            case 2:
                rbn.remove(valor);
                break;
            }
        }
    }
}

/**
1 11
1 2
1 14
1 1
1 7
1 15
1 5
1 8
1 4
0
-1 */
 