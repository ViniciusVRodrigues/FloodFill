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
    private int xSelecionado;
    private int ySelecionado;
    private int corNova;
    private int corVelha;
    private int imageWidth;
    private int imageHeight;
    ProcessadorImagem(String caminho) throws IOException {
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

    public void floodFill(int x,int y){
        this.xSelecionado = x;
        this.ySelecionado = y;
        this.corVelha = matrizCores[y][x];
        queue = new DynamicQueue<Pixel>();
        pile = new DynamicPile<Pixel>();
        Pixel pixel = new Pixel(x, y);
        pegarPixelsComCorIgual(pixel);
    }

    public void pintarPorPilha(){
        //Gerando imagens a cada 1% de progresso
        int totalPixels = pile.top+1;
        int pixelsPintados = 0;
        while(!pile.isEmpty()){
            Pixel pixel = pile.pop();
            int x = pixel.getX();
            int y = pixel.getY();
            bufferedImage.setRGB(x, y, corNova);
            pixelsPintados++;
            if(pixelsPintados % (totalPixels/100) == 0){
                System.out.println(pixelsPintados/(totalPixels/100) + "%");
                try {
                    File outputFile = new File("img/imagemPilha"+(pixelsPintados/(totalPixels/100))+".png"); // Save as PNG
                    ImageIO.write(bufferedImage, "png", outputFile);
                } catch (IOException e) {
                    e.printStackTrace();
                }
            }
        }
    }

    public void pintarPorFila(){
        //Gerando imagens a cada 1% de progresso
        int totalPixels = queue.top+1;
        int pixelsPintados = 0;
        while(!queue.isEmpty()){
            Pixel pixel = queue.pop();
            int x = pixel.getX();
            int y = pixel.getY();
            bufferedImage.setRGB(x, y, corNova);
            pixelsPintados++;
            if(pixelsPintados % (totalPixels/100) == 0){
                System.out.println(pixelsPintados/(totalPixels/100) + "%");
                try {
                    File outputFile = new File("img/imagemFila"+(pixelsPintados/(totalPixels/100))+".png"); // Save as PNG
                    ImageIO.write(bufferedImage, "png", outputFile);
                } catch (IOException e) {
                    e.printStackTrace();
                }
            }
        }
    }

    public void verPilha(){
        System.out.println("Pilha");
        System.out.println();
        while(!pile.isEmpty()){
            Pixel pixel = pile.pop();
            System.out.println(pixel.toString());
        }
    }

    public void verFila(){
        System.out.println("Fila");
        System.out.println();
        while(!queue.isEmpty()){
            Pixel pixel = queue.pop();
            System.out.println(pixel.toString());
        }
    }

    private void pegarPixelsComCorIgual(Pixel pixelAtual){
        int x = pixelAtual.getX();
        int y = pixelAtual.getY();
        if(matrizCores[y][x] != corVelha)
            return;
        if(pile.contains(pixelAtual) || queue.contains(pixelAtual))
            return;
        pile.push(pixelAtual);
        queue.push(pixelAtual);
        //Pegando pixels vizinhos e testando se eles já foram pegos
        //Pixel da esquerda
        if(x-1 >= 0){
            Pixel pixel = new Pixel(x-1, y);
            pegarPixelsComCorIgual(pixel);
        }
        //Pixel da direita
        if(x+1 < imageWidth){
            Pixel pixel = new Pixel(x+1, y);
            pegarPixelsComCorIgual(pixel);
        }
        //Pixel de cima
        if(y-1 >= 0){
            Pixel pixel = new Pixel(x, y-1);
            pegarPixelsComCorIgual(pixel);
        }
        //Pixel de baixo
        if(y+1 < imageHeight){
            Pixel pixel = new Pixel(x, y+1);
            pegarPixelsComCorIgual(pixel);
        }
    }
}
