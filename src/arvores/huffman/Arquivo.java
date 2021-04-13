package arvores.huffman;

import java.io.*;

/**
 *
 * @author reinaldo
 */
public class Arquivo {

    public static String ler(String nomeArquivo) {
        String texto = null;
        try {
            InputStream inputstream = new FileInputStream(nomeArquivo);
            int data = inputstream.read();
            texto = (char) data + "";
            while (data != -1) {
                texto += (char) data + "";
                data = inputstream.read();
            }
            inputstream.close();
        } catch (Exception e1) {
            System.out.println("Erro na leitura do arquivo " + nomeArquivo);
        }
        return texto;
    }

    public static void escreverEmBinario(String texto, String nomeArquivo) {
        try {
            FileOutputStream fos = new FileOutputStream(nomeArquivo);
            DataOutputStream outputStream = new DataOutputStream(fos);
            for (int i = 0; i < texto.length(); i++) {
                int j = 0;
                byte x = 0;
                while (j < 8 && i < texto.length()) {
                    x <<= 1;
                    x += texto.charAt(i) - '0';
                    j++;
                    i++;
                }
                if (j < 8) {
                    x <<= 8 - j;
                }
                outputStream.writeByte(x);
            }
        } catch (IOException ex) {
            System.out.println("Erro na escrita");
        }
    }

    public static void escrever(String texto, String nomeArquivo) {
        try {
            FileOutputStream fos = new FileOutputStream(nomeArquivo);
            DataOutputStream outputStream = new DataOutputStream(fos);
            outputStream.write(texto.getBytes());
        } catch (IOException ex) {
            System.out.println("Erro na escrita");
        }
    }

}
