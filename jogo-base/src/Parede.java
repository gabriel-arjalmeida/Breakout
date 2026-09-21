import java.awt.*;


public class Parede {
    private double x;
    private double y;
    private double altura;
    private double largura;

    private Color cor;
    private int resistencia;




    public Parede(double x,  double y, double largura, double altura,Color cor) {
        this.x = x;
        this.y = y;
        this.altura = altura;
        this.largura = largura;
        this.cor = cor;



    }

    public boolean tomarDano(){
        resistencia--;

        return resistencia <= 0;
    }




    public void desenhar(Graphics2D g) {
        g.setColor(cor);
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

    public int getResistencia(){
        return resistencia;
    }

    public void setResistencia(int resistencia){
        if (resistencia > 0) {
            this.resistencia = resistencia;
        } else {
            return;
        }
    }

    public Color getCor(){
        return cor;
    }
}

