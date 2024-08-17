package SC02;
import processing.core.PApplet;

public class Tetris extends PApplet {
    Tauler t;
    Colors colorTetris;
    Figura[] figures;
    int numFigures = 0;
    Figura figActual;

    public void settings(){
        size(800, 800);
    }

    public static void main(String[] args){
        PApplet.main("SC02.Tetris");
    }

    public void setup(){
        t = new Tauler(10, 20, 200, 0, 400, 800);
        t.inicialitzaCaselles();

        colorTetris = new Colors(this);

        figures = new Figura[10];

        figActual = Figura.creaFiguraRandom(this, t);
        figures[numFigures] = figActual;
        numFigures++;
    }

    public void draw(){

        dibuixaJoc();
    }

    public void dibuixaJoc(){
        background(200);
        // Escenari de joc
        pushMatrix();
            translate(t.x, t.y);

            // Graella del tauler.
            t.dibuixaGraella(this,colorTetris.colorBUIT);

            // Figures de l'array
            for(int i=0; i<numFigures; i++) {
                t.dibuixaFigura(this, figActual, colorTetris.colors);
            }
        popMatrix();
    }


    public void keyPressed(){
        if(keyCode==LEFT){
            figActual.mouEsquerra(t);
        }
        else if(keyCode==RIGHT){
            figActual.mouDreta(t);
        }
        else if(keyCode==DOWN){
            figActual.mouBaix(t);
        }
        else if(key=='b' || key=='B'){
            figActual.mouTopeBaix(t);
        }
        else if(key=='r' || key=='R'){
            figActual.rota();
        }

    }


}
