import javax.imageio.ImageIO;
import java.awt.image.BufferedImage;
import java.io.File;
import java.io.IOException;

public class ProcessadorImagem {
    private BufferedImage bufferedImage;
    private int[][] matrizCores;

    private int xSelecionado;
    private int ySelecionado;
    private int corNova;
    private int corVelha;
    ProcessadorImagem(String caminho) throws IOException {
        bufferedImage = ImageIO.read(new File(caminho));
        matrizCores = obterMatrizCores();
    }

    public int[][] obterMatrizCores() {
        int width = bufferedImage.getWidth();
        int height = bufferedImage.getHeight();
        int[][] result = new int[height][width];

        for (int row = 0; row < height; row++) {
            for (int col = 0; col < width; col++) {
                result[row][col] = bufferedImage.getRGB(col, row);
            }
        }
        return result;
    }
}
