import javax.swing.*;
import java.awt.*;
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
        processadorImagem.floodFillPilha(0,0, new Color(255, 0, 0).getRGB());
        processadorImagem.floodFillFila(0,0, new Color(0, 255, 0).getRGB());
        JanelaPintura janelaPintura = new JanelaPintura();
    }
}