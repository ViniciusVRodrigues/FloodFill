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
        // Definir o título da janela
        setTitle("FLOOD FILL");
        setSize(800, 600);
        setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        setLayout(new BorderLayout());

        // Rótulo para exibir a imagem
        labelImagem = new JLabel();
        labelImagem.setHorizontalAlignment(JLabel.CENTER);
        add(labelImagem, BorderLayout.CENTER);

        JPanel painelBotoes = new JPanel();
        painelBotoes.setLayout(new FlowLayout(FlowLayout.CENTER, 20, 10));  // Personalizar layout e espaçamento

        JButton botaoPilha = new JButton("Pintar por Pilha");
        JButton botaoFila = new JButton("Pintar por Fila");

        estilizarBotao(botaoPilha, new Color(56, 116, 120), Color.BLACK);
        estilizarBotao(botaoFila, new Color(56, 116, 120), Color.BLACK);

        painelBotoes.add(botaoPilha);
        painelBotoes.add(botaoFila);
        add(painelBotoes, BorderLayout.SOUTH);

        JPanel painelTexto = new JPanel() {
            @Override
            protected void paintComponent(Graphics g) {
                super.paintComponent(g);
                g.setFont(new Font("Arial", Font.BOLD, 24));
                g.setColor(Color.RED);
                g.drawString("Escolha o método de pintura", 250, 30); // Posição do texto
            }
        };
        painelTexto.setPreferredSize(new Dimension(800, 50));

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


    private void estilizarBotao(JButton botao, Color corFundo, Color corTexto) {
        botao.setBackground(corFundo);
        botao.setForeground(corTexto);
        botao.setFont(new Font("Arial", Font.BOLD, 14));
        botao.setFocusPainted(false);
        botao.setBorder(BorderFactory.createLineBorder(Color.DARK_GRAY, 1));  // Borda com espessura
    }

    private void exibirImagensSequencia() {
        if (timer != null && timer.isRunning()) {
            timer.stop();
        }

        timer = new Timer(100, new ActionListener() {
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
        new JanelaPintura();
    }
}

