package grafos.arvores.outros;

import java.io.BufferedReader;
import java.io.File;
import java.io.FileInputStream;
import java.io.FileOutputStream;
import java.io.FileReader;
import java.io.IOException;
import java.io.InputStream;
import java.io.OutputStream;

public class Arquivo {

    public static void main(String[] args) {
        File arquivo = new File("teste.bin");
        try (OutputStream os = new FileOutputStream(arquivo)) {
            // byte[] b = { 50, 51, 52, 53 };
            String string = ler("testebin.txt");
            os.write(string.getBytes());
            os.flush();
        } catch (IOException ex) {
            ex.printStackTrace();
        }

        try (InputStream is = new FileInputStream(arquivo)) {
            int content;
            while ((content = is.read()) != -1) {
                System.out.print(((char) content));
            }
        } catch (IOException ex) {
            ex.printStackTrace();
        }
    }

    public static String ler(String nomeAquivo) {
        FileReader arq;
        String texto = "";
        try {
            arq = new FileReader(nomeAquivo);
            BufferedReader lerArq = new BufferedReader(arq);
            String linha = lerArq.readLine();
            // texto += linha;
            while (linha != null) {
                // System.out.printf("%s\n", linha);
                texto += linha + "\n";
                linha = lerArq.readLine();
            }
            arq.close();
        } catch (Exception e1) {
            e1.printStackTrace();
        }
        return texto;
    }
}