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
        processadorImagem.floodFill(0,0);
        processadorImagem.pintarPorPilha();
        processadorImagem.pintarPorFila();
        JanelaPintura janelaPintura = new JanelaPintura();
    }
}