package rbn.remocao;

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
                    rbn.inserir(valor);
                    break;
                case 2:
                    rbn.remove(valor);
                    break;
            }
        }
    }
}
//Inserção
/*        
1 41
1 38
1 31
1 12
1 19
1 8
0
-1
 */
 /*
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
-1
 */
 /*
1 4
1 7
1 12
1 15
1 3
1 5
1 14
1 18
0
-1
 */
 /*
1 78
1 50
1 30
1 15
1 40
1 60
1 90
1 55
1 65
1 66
0
-1
 */
//Remoção
/*
22
1 10
1 51
1 40
1 70
1 80
0
2 22
0
-1
 */
 /*
1 22
1 10
1 51
1 40
0
2 22
0
-1
 */
 /*
1 22
1 10
1 51
1 40
1 60
0
2 22
0
-1
 */
 /*
1 50
1 25
1 70
1 30
1 15
1 28
0
2 70
0
-1
 */
