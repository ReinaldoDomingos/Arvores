package abb;

import java.util.Scanner;

public class Main {
    public static void main(String[] args) {
        System.out.println("_________________________\n" +
                "-1 - Encerra o teste\n" +
                "0 - Imprime a Árvore em ordem\n" +
                "1 - Insere um dado valor\n" +
                "2 - Remove um dado valor\n" +
                "3 - Imprime a altura de um dado Nó\n" +
                "4 - imprime o nível de um dado Nó\n" +
                "_________________________\n");

        Scanner leia = new Scanner(System.in);
        int opcao = 0, valor = 0;
        ABB abb = new ABB();
        while (opcao != -1) {
            String linha = leia.nextLine();
            String s[] = linha.split(" ");
            opcao = Integer.parseInt(s[0]);
            if (s.length > 1) valor = Integer.parseInt(s[1]);

            switch (opcao) {
                case 0:
                    abb.imprimeEmOrdem();
                    break;
                case 1:
                    abb.insere(valor);
                    break;
                case 2:
                    abb.remove(valor);
                    break;
                case 3:
                    abb.imprimeAlturaNo(valor);
                    break;
                case 4:
                    abb.imprimeNivelNo(valor);
            }
        }
    }
}

/*
*
Teste 1

1 34
1 3
1 50
1 23
1 1
0
2 34
0
1 100
1 90
0
2 100
0
1 100
0
2 90
0
2 23
0
1 30
0
2 3
0
3 30
3 50
3 100
4 100
4 50
4 1
-1


1 3 23 34 50
1 3 23 50
1 3 23 50 90 100
1 3 23 50 90
1 3 23 50 90 100
1 3 23 50 100
1 3 50 100
1 3 30 50 100
1 30 50 100
2
3
1
1
0
2

Teste 2

1 47
1 30
1 20
1 43
1 100
1 200
1 45
1 150
1 130
1 44
1 46
1 156
0
2 20
0
2 200
0
-1

20 30 43 44 45 46 47 100 130 150 156 200
30 43 44 45 46 47 100 130 150 156 200
30 43 44 45 46 47 100 130 150 156
* */