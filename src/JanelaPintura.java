import javax.imageio.ImageIO;
import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.image.BufferedImage;
import java.io.File;
import java.io.IOException;

public class JanelaPintura extends JFrame {
    private JLabel labelImagem;
    private Timer timer;
    private String caminhoImagem;
    private int contadorImagem = 1;
    private int totalImagens = 100;
    private String modo = "";

    public JanelaPintura() {
        // Configurações da janela
        setTitle("Pintura por Pilha e Fila");
        setSize(800, 600);
        setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        setLayout(new BorderLayout());

        // Label para exibir as imagens
        labelImagem = new JLabel();
        labelImagem.setHorizontalAlignment(JLabel.CENTER);
        add(labelImagem, BorderLayout.CENTER);

        // Painel para os botões
        JPanel painelBotoes = new JPanel();
        JButton botaoPilha = new JButton("Pintar por Pilha");
        JButton botaoFila = new JButton("Pintar por Fila");

        painelBotoes.add(botaoPilha);
        painelBotoes.add(botaoFila);
        add(painelBotoes, BorderLayout.SOUTH);

        // Ações dos botões
        botaoPilha.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                modo = "Pilha";
                contadorImagem = 1;
                exibirImagensSequencia();
            }
        });

        botaoFila.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                modo = "Fila";
                contadorImagem = 1;
                exibirImagensSequencia();
            }
        });

        setVisible(true);
    }

    // Função para exibir as imagens em sequência
    private void exibirImagensSequencia() {
        if (timer != null && timer.isRunning()) {
            timer.stop();
        }

        timer = new Timer(100, new ActionListener() { // 100ms de intervalo para exibir cada imagem
            @Override
            public void actionPerformed(ActionEvent e) {
                if (contadorImagem <= totalImagens) {
                    caminhoImagem = "img/imagem" + modo + contadorImagem + ".png";
                    exibirImagem(caminhoImagem);
                    contadorImagem++;
                } else {
                    timer.stop();
                }
            }
        });
        timer.start();
    }

    // Função para exibir a imagem
    private void exibirImagem(String caminho) {
        try {
            BufferedImage img = ImageIO.read(new File(caminho));
            ImageIcon icon = new ImageIcon(img);
            labelImagem.setIcon(icon);
            labelImagem.repaint();
        } catch (IOException e) {
            System.out.println("Erro ao carregar a imagem: " + caminho);
            e.printStackTrace();
        }
    }

    public static void main(String[] args) {
        // Inicializa o JFrame
        new JanelaPintura();
    }
}
