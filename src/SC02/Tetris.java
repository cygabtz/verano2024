package SC02;
import processing.core.PApplet;

public class Tetris extends PApplet {
    Tauler t;
    Colors colorTetris;
    Figura f1, f2, f3;

    public void settings (){
        size(800, 800);
    }

    public static void main(String[] args){
        PApplet.main("SC02.Tetris");
    }

    public void setup(){
        t = new Tauler(10, 20, 200, 0, 400, 800);
        colorTetris = new Colors(this);
        f1 = new Figura(Matrius.matrizT, Figura.TIPUS_FIGURA.T);
        f1.setPosicio(0, 4);
        f2 = new Figura(Matrius.matrizS, Figura.TIPUS_FIGURA.S);
        f1.setPosicio(4, 6);
        f3 = new Figura(Matrius.matrizO, Figura.TIPUS_FIGURA.O);
        f3.setPosicio(10, 2);

    }

    public void draw(){
        //Fons
        background(255);

        pushMatrix();
            //Escenari de joc
            translate(t.x, t.y);
            t.dibuixaGraella(this, colorTetris.colorBUIT);
            //Figures
            t.dibuixaFigura(this, f1, colorTetris.colors);
            t.dibuixaFigura(this, f2, colorTetris.colors);
            t.dibuixaFigura(this, f3, colorTetris.colors);
        popMatrix();
    }
}
