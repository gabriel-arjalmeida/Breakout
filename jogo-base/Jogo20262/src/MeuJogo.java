import java.awt.*;
import java.awt.event.KeyEvent;
import java.util.Random;


public class MeuJogo extends Jogo {
    private Bola bola;

    private Parede[][] paredes = new Parede[6][10];

    private Barra barra;
    private boolean esquerdaPressionada, direitaPressionada;

    private Estado estado;
    private int pontos = 0;



    @Override
    public void inicializar(int largura, int altura) {
        estado = Estado.JOGANDO;

        int count = 0;

        super.inicializar(largura, altura);

        Random random = new Random();

        bola = new Bola(largura / 2, altura / 2,
                30/1.5, 2,
                2.5);

        for(int linha = 0;linha < 6;linha++) {
            for (int coluna = 0; coluna < 10; coluna++) {
                var num = random.nextDouble();
                Color c;
                if (num < 0.1){
                    c = Color.ORANGE;


                }
                else{
                    c = Color.YELLOW;

                }
                paredes[linha][coluna] = new Parede(50+coluna*70,64+linha*25,70,25,c);
                if (paredes[linha][coluna].getCor().equals(Color.ORANGE)) {
                    paredes[linha][coluna].setResistencia(2);

                }
                else{
                    paredes[linha][coluna].setResistencia(1);

                }

                //no x usa-se coluna, pq quando a gente quer andar entre colunas, a gente movimenta o x.
            }

        }
        barra = new Barra(largura / 2 - 40, 560, 100,30);

        //parede = new Parede(100,64,100,50);
        //count += 51;


    }

    @Override
    public void atualizar() {
        if(estado == Estado.JOGANDO) {
            bola.mover();
            bola.mover();
            if (bola.tratarBordas(getLargura(), getAltura())) {
                estado = Estado.GAMEOVER;
            }
            int colisao = bola.colide(barra);

            double velocidadeBase = 5.0; // ou a velocidade "base" do seu jogo

            if (colisao == Bola.PRIMEIRO_TERCO) {
                bola.setVelX(-velocidadeBase);
                bola.setVelY(-Math.abs(bola.getVelY()));
            }
            else if (colisao == Bola.SEGUNDO_TERCO) {
                bola.setVelX(0);
                bola.setVelY(-Math.abs(bola.getVelY()));
            }
            else if (colisao == Bola.TERCEIRO_TERCO) {
                bola.setVelX(velocidadeBase);
                bola.setVelY(-Math.abs(bola.getVelY()));
            }

            for (Parede[] linha : paredes) {
                for (int i = 0; i < linha.length; i++) {
                    Parede pp = linha[i];
                    if (pp != null && bola.colide(pp)) {
                        pp.tomarDano();
                        bola.setVelY(-bola.getVelY());
                        if(pp.getResistencia() == 0) {
                            linha[i] = null;
                            pontos += 1;
                            break;
                        }
                        // remove a parede
                    }
                }
            }
            if (pontos == 60) {
                estado = Estado.VITORIA;
            }

            final int INCREMENTO = 15;

            if (direitaPressionada) {
                barra.setX(barra.getX() + INCREMENTO);
            }

            if (esquerdaPressionada) {
                barra.setX(barra.getX() - INCREMENTO);
            }
        }


    }


    @Override
    public void desenhar(Graphics2D g) {
        if(estado == Estado.JOGANDO) {
            desenharPlanoDeFundo(g);
            bola.desenhar(g);
            barra.desenhar(g);
            desenharPontuacao(g);

            for (Parede[] linha : paredes) {
                for (Parede p : linha) {
                    if (p != null) {
                        p.desenhar(g);
                    }
                }
            }
        }
        else if(estado == Estado.GAMEOVER){
            desenharGameOver(g);

        }
        else{
            desenharVitoria(g);
        }


    }
    private void desenharPlanoDeFundo(Graphics2D g) {
        g.setColor(new Color(173, 216, 230));
        g.fillRect(0, 0, getLargura(), getAltura());
    }
    private void desenharPontuacao(Graphics2D g) {
        g.setColor(Color.BLACK);
        g.drawString("Pontos: " + pontos, 20, 20);
    }

    private void desenharGameOver(Graphics2D g) {
        g.setColor(Color.BLACK);
        g.fillRect(0, 0, getLargura(), getAltura());

        g.setColor(Color.WHITE);

        Font font = new Font("Monospaced", Font.PLAIN, 30);
        desenharStringCentralizada(g, "Game Over!", font, 0);
        desenharStringCentralizada(g, "Pontos: " + pontos, font, 40);
    }
    private void desenharVitoria(Graphics2D g) {
        g.setColor(Color.YELLOW);
        g.fillRect(0, 0, getLargura(), getAltura());

        g.setColor(Color.BLACK);

        Font font = new Font("Monospaced", Font.PLAIN, 30);
        desenharStringCentralizada(g, "VOCÊ GANHOU!", font, 0);
        desenharStringCentralizada(g, "Pontos: " + pontos, font, 40);
    }

    public void desenharStringCentralizada(Graphics2D g, String text, Font font, int verticalSpacing) {
        Rectangle rect = new Rectangle(getLargura(), getAltura());
        FontMetrics metrics = g.getFontMetrics(font);

        int x = rect.x + (rect.width - metrics.stringWidth(text)) / 2;
        int y = rect.y + ((rect.height - metrics.getHeight()) / 2) + metrics.getAscent();
        g.setFont(font);
        g.drawString(text, x, y + verticalSpacing);
    }

    @Override
    public void teclaPressionada(KeyEvent e) {
        super.teclaPressionada(e);

        if (e.getKeyCode() == KeyEvent.VK_LEFT) {
            esquerdaPressionada = true;
        } else if (e.getKeyCode() == KeyEvent.VK_RIGHT) {
            direitaPressionada = true;
        }
    }

    @Override
    public void teclaLiberada(KeyEvent e) {
        super.teclaLiberada(e);

        if (e.getKeyCode() == KeyEvent.VK_LEFT) {
            esquerdaPressionada = false;
        } else if (e.getKeyCode() == KeyEvent.VK_RIGHT) {
            direitaPressionada = false;
        }
    }


}