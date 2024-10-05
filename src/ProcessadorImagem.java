import org.w3c.dom.css.RGBColor;

import javax.imageio.ImageIO;
import java.awt.*;
import java.awt.image.BufferedImage;
import java.io.File;
import javax.swing.JPanel;
import java.io.IOException;

public class ProcessadorImagem{
    private BufferedImage bufferedImage;
    private int[][] matrizCores;
    private DynamicQueue<Pixel> queue;
    private DynamicPile<Pixel> pile;
    private int corNova;
    private int corVelha;
    private int imageWidth;
    private int imageHeight;
    private final String caminho;

    ProcessadorImagem(String caminho) throws IOException {
        this.caminho = caminho;
        setBufferedImage();
    }

    public void setBufferedImage() throws IOException{
        bufferedImage = ImageIO.read(new File(caminho));
        matrizCores = obterMatrizCores();
    }

    private int[][] obterMatrizCores() {
        imageWidth = bufferedImage.getWidth();
        imageHeight = bufferedImage.getHeight();
        int[][] result = new int[imageHeight][imageWidth];

        for (int row = 0; row < imageHeight; row++) {
            for (int col = 0; col < imageWidth; col++) {
                result[row][col] = bufferedImage.getRGB(col, row);
            }
        }
        return result;
    }

    public void mostrarMatrizCores(){
        System.out.println("Matriz Cores");
        System.out.println();
        for (int row = 0; row < imageHeight; row++) {
            for (int col = 0; col < imageWidth; col++) {
                System.out.print(" "+matrizCores[row][col]+" ");
            }
            System.out.println();
        }
    }

    public void floodFillFila(int xSelecionado,int ySelecionado, int cor){
        try {
            setBufferedImage();
        } catch (IOException e) {
            throw new RuntimeException(e);
        }
        this.corVelha = matrizCores[ySelecionado][xSelecionado];
        queue = new DynamicQueue<Pixel>();
        DynamicQueue<Pixel> pixelsParaVerificarQueue = new DynamicQueue<Pixel>();
        corNova = cor;
        Pixel pixel = new Pixel(xSelecionado, ySelecionado);
        pixelsParaVerificarQueue.push(pixel);
        System.out.println("Começando a verificar pixels por fila");
        while (!pixelsParaVerificarQueue.isEmpty()){
            Pixel pixelAtual = pixelsParaVerificarQueue.pop();
            int x = pixelAtual.getX();
            int y = pixelAtual.getY();
            if(queue.contains(pixelAtual))
                continue;
            queue.push(pixelAtual);
            matrizCores[y][x] = corNova;
            if(x-1 >= 0)
                if(matrizCores[y][x-1] == corVelha)
                    pixelsParaVerificarQueue.push(new Pixel(x-1, y));
            if(x+1 < imageWidth)
                if(matrizCores[y][x+1] == corVelha)
                    pixelsParaVerificarQueue.push(new Pixel(x+1, y));
            if(y-1 >= 0)
                if(matrizCores[y-1][x] == corVelha)
                    pixelsParaVerificarQueue.push(new Pixel(x, y-1));
            if(y+1 < imageHeight)
                if(matrizCores[y+1][x] == corVelha)
                    pixelsParaVerificarQueue.push(new Pixel(x, y+1));
        }
        System.out.println("Pixels verificados por fila");
        pintarPorFila();
    }

    public void pintarPorFila(){
        int totalPixels = queue.lenght();
        int pixelsPintados = 0;
        System.out.println("Começando a pintar por fila");
        while(!queue.isEmpty()){
            Pixel pixel = queue.pop();
            int x = pixel.getX();
            int y = pixel.getY();
            bufferedImage.setRGB(x, y, corNova);
            pixelsPintados++;
            if(pixelsPintados % (totalPixels/100) == 0){
                try {
                    File outputFile = new File("img/imagemFila"+(pixelsPintados/(totalPixels/100))+".png"); // Save as PNG
                    ImageIO.write(bufferedImage, "png", outputFile);
                } catch (IOException e) {
                    e.printStackTrace();
                }
            }
        }
        try {
            File outputFile = new File("img/imagemFila100.png"); // Save as PNG
            ImageIO.write(bufferedImage, "png", outputFile);
        } catch (IOException e) {
            e.printStackTrace();
        }
        System.out.println("Pixels pintados por fila");
    }

    public void floodFillPilha(int xSelecionado,int ySelecionado, int cor){
        try {
            setBufferedImage();
        } catch (IOException e) {
            throw new RuntimeException(e);
        }
        this.corVelha = matrizCores[ySelecionado][xSelecionado];
        corNova = cor;
        pile = new DynamicPile<Pixel>();
        DynamicPile<Pixel> pixelsParaVerificarPile = new DynamicPile<Pixel>();
        Pixel pixel = new Pixel(xSelecionado, ySelecionado);
        pixelsParaVerificarPile.push(pixel);
        System.out.println("Começando a verificar pixels por pilha");
        while (!pixelsParaVerificarPile.isEmpty()){
            Pixel pixelAtual = pixelsParaVerificarPile.pop();
            int x = pixelAtual.getX();
            int y = pixelAtual.getY();
            if(pile.contains(pixelAtual))
                continue;
            pile.push(pixelAtual);
            matrizCores[y][x] = corNova;
            if(x-1 >= 0)
                if(matrizCores[y][x-1] == corVelha)
                    pixelsParaVerificarPile.push(new Pixel(x-1, y));
            if(x+1 < imageWidth)
                if(matrizCores[y][x+1] == corVelha)
                    pixelsParaVerificarPile.push(new Pixel(x+1, y));
            if(y-1 >= 0)
                if(matrizCores[y-1][x] == corVelha)
                    pixelsParaVerificarPile.push(new Pixel(x, y-1));
            if(y+1 < imageHeight)
                if(matrizCores[y+1][x] == corVelha)
                    pixelsParaVerificarPile.push(new Pixel(x, y+1));
        }
        System.out.println("Pixels verificados por pilha");
        pintarPorPilha();
    }

    public void pintarPorPilha(){
        int totalPixels = pile.lenght();
        int pixelsPintados = 0;
        System.out.println("Começando a pintar por pilha");
        while(!pile.isEmpty()){
            Pixel pixel = pile.pop();
            int x = pixel.getX();
            int y = pixel.getY();
            bufferedImage.setRGB(x, y, corNova);
            pixelsPintados++;
            if(pixelsPintados % (totalPixels/100) == 0){
                try {
                    File outputFile = new File("img/imagemPilha"+(pixelsPintados/(totalPixels/100))+".png"); // Save as PNG
                    ImageIO.write(bufferedImage, "png", outputFile);
                } catch (IOException e) {
                    e.printStackTrace();
                }
            }
        }
        try {
            File outputFile = new File("img/imagemPilha100.png"); // Save as PNG
            ImageIO.write(bufferedImage, "png", outputFile);
        } catch (IOException e) {
            e.printStackTrace();
        }
        System.out.println("Pixels pintados por pilha");
    }
}
