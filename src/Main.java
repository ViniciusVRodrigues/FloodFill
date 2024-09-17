import javax.swing.*;
import java.io.IOException;

public class Main {
    public static void main(String[] args) {
        System.out.println("--- -- - -- --- Flood Fill --- -- - -- ---");
        ProcessadorImagem processadorImagem;
        try{
            processadorImagem = new ProcessadorImagem("imagem.png");
        }catch (IOException ioException){
            System.out.println("Erro ao pegar imagem! Encerrando...");
            return;
        }
        processadorImagem.mostrarMatrizCores();
        processadorImagem.floodFill(0,0,0);
        processadorImagem.verFila();

        JFrame frame = new JFrame();// Create the panel with image

        frame.setSize(400, 400);
        frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        frame.setVisible(true);
    }
}