package arvores.b;

import java.util.Scanner;

public class Main {

    public static void main(String[] args) {
        System.out.println("_________________________\n" + "-1 - Encerra o teste\n" + "0 - Imprime a Árvore por nível\n"
                + "1 - Insere um novo valor\n" + "2 - Remove um valor\n" + "_________________________\n");

        Scanner leia = new Scanner(System.in);
        int opcao = 0, valor = 0;
        int ordem;
        try {
            ordem = Integer.parseInt(leia.nextLine());
        } catch (Exception e) {
            System.out.println("Erro: Na primeira linha deve ser digitado a ordem da arvore");
            return;
        }
        B arvoreB = new B(ordem);
        while (opcao != -1) {
            String linha = leia.nextLine();
            String s[] = linha.split(" ");
            opcao = Integer.parseInt(s[0]);
            if (s.length > 1) {
                valor = Integer.parseInt(s[1]);
            }

            switch (opcao) {
                case 0:
                    arvoreB.imprimePorNivel();
                    break;
                case 1:
                    arvoreB.inserir(valor);
                    break;
                case 2:
                    arvoreB.remove(valor);
                    break;
            }
        }
    }
}
//--- ÁRVORE POR NÍVEL ---
/*
3
1 56
1 45
1 89
1 34
1 23
1 58
1 24
1 57
1 30
1 12
1 3
1 47
1 9
1 4
1 60
1 74
1 65
1 71
1 15
1 10
1 11
1 14
1 68
1 82
0
2 30
2 60
2 10
2 47
0
-1
 */

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
1 22
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
