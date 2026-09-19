



import java.awt.*;


public class Barra {
    private double x, y;
    private double largura, altura;


    public Barra(double x, double y, double largura, double altura) {
        this.x = x;
        this.y = y;
        this.largura = largura;
        this.altura = altura;
    }


    public void desenhar(Graphics2D g) {
        g.setColor(Color.GRAY);
        g.fillRect((int) x, (int) y, (int) largura, (int) altura);


        g.setColor(Color.BLACK);
        g.drawRect((int) x, (int) y, (int) largura, (int) altura);
    }


    public double getX() {
        return x;
    }


    public void setX(double x) {
        this.x = x;
    }


    public double getY() {
        return y;
    }


    public void setY(double y) {
        this.y = y;
    }


    public double getLargura() {
        return largura;
    }


    public void setLargura(double largura) {
        this.largura = largura;
    }


    public double getAltura() {
        return altura;
    }


    public void setAltura(double altura) {
        this.altura = altura;
    }
}



