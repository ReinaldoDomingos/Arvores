package b;

public class Main {

    public static void main(String[] args) {
        B b = new B();
        // Teste1
        // int[] v = new int[]{50, 20, 30, 37, 42, 47, 41, 60, 31, 32, 43, 44, 61, 62,
        // 33, 45, 63};;
        // Teste 2
        // int[] v = new int[]{9, 10, 12, 13, 14, 15, 18, 19, 23, 24, 25, 20, 6, 4, 3,
        // 2, 0, 17, 21, 1, 16, 11, 5};
        //
        int[] v = new int[]{ 56, 45, 89, 34, 23, 58, 24, 57, 30, 12, 3, 47, 9, 4, 60, 74, 65, 71, 15, 10, 11, 14, 68, 82 };
        for (int i = 0; i < v.length; i++) {
            b.inserir(v[i]);
        }

    }
}
