



import java.awt.*;


public class Bola {
    private double x, y;
    private double raio;
    private double velX, velY;

    public static final int NAO_COLIDE = 0;
    public static final int PRIMEIRO_TERCO = 1;
    public static final int SEGUNDO_TERCO = 2;
    public static final int TERCEIRO_TERCO = 3;


    public Bola(double x, double y, double raio, double velX, double velY) {
        this.x = x;
        this.y = y;
        this.raio = raio;
        this.velX = velX;
        this.velY = velY;
    }


    public void mover() {
        x += velX;
        y += velY;
    }


    public boolean tratarBordas(int largura, int altura) {
        if (x < 0 || x > largura - 2 * raio) {
            velX = -velX;
        }


        if (y < 0){
            velY = -velY;
        }


        if  ( y > altura - 2 * raio){


            velY = -velY;
            return true;
        }
        return false;
    }


    public void desenhar(Graphics2D g) {
        g.setColor(Color.WHITE);
        g.fillOval((int) x, (int) y, (int) (raio * 2), (int) (raio * 2));
    }


    public int colide(Barra obj) {
        boolean colide = this.y + this.raio * 2 > obj.getY() &&
                this.y < obj.getY() + obj.getAltura() &&
                this.x < obj.getX() + obj.getLargura() &&
                this.x + this.raio * 2 > obj.getX();

        if (colide) {
            if (this.x < obj.getX() + obj.getLargura() / 3) {
                return PRIMEIRO_TERCO;
            } else if (this.x < obj.getX() + 2 * (obj.getLargura() / 3)) {
                return SEGUNDO_TERCO;
            } else {
                return TERCEIRO_TERCO;
            }
        } else {
            return NAO_COLIDE;
        }
    }






    public double getVelX() {
        return velX;
    }


    public void setVelX(double velX) {
        this.velX = velX;
    }


    public double getVelY() {
        return velY;
    }


    public void setVelY(double velY) {
        this.velY = velY;
    }


    public boolean colide(Parede obj) {
        return obj != null &&
                this.y + this.raio * 2 > obj.getY() &&
                this.y < obj.getY() + obj.getAltura() &&
                this.x < obj.getX() + obj.getLargura() &&
                this.x + this.raio * 2 > obj.getX();
    }




}

