package SC02;
import processing.core.PApplet;
import processing.sound.*;

public class Tetris extends PApplet {
    Tauler t;
    Colors colorTetris;
    Figura figActual;
    int speed = 30;
    int numLinies = 0;

    SoundFile musicaTetris;
    boolean musicaOnOff = true;

    boolean gameOver = false;

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

        figActual = Figura.creaFiguraRandom(this, t);

        musicaTetris = new SoundFile(this, "tetris.mp3");
        musicaTetris.play();
    }

    public void draw(){
        //Lógica
        if(frameCount % speed == 0){
            if (!figActual.mouBaix(t)) {
                print("no mou baix");
                if(figActual.fila == 0){
                    gameOver = true;
                }
                else{
                    t.afegirFigura(figActual);
                    t.aplicaFigura(figActual);
                    figActual = Figura.creaFiguraRandom(this, t);
                    //t.printTauler();
                }
            }
        }
        else {
            // Comprovar línies
            boolean[] plenes = t.comprovaFilesPlenes();
            for (int f = 0; f < plenes.length; f++) {
                if (plenes[f] == true) {
                    t.baixarFiguresAbansDe(f);
                    numLinies++;
                    println("NUM LÍNIES: " + numLinies);
                }
            }
        }

        //Dibuix
        if(gameOver){
            dibuixaPantallaResultat();
        }
        else {
            dibuixaJOC();
        }
    }

    public void dibuixaJOC(){
        background(200);
        pushMatrix();
            translate(t.x, t.y);

            t.dibuixaCaselles(this,colorTetris.colorBUIT,colorTetris.colors);
            t.dibuixaFigura(this, figActual, colorTetris.colors);
        popMatrix();

        // Marcador
        fill(0); textAlign(LEFT); textSize(20);
        text("Figures:" + t.numFigures, 50, 50);
        text("Línies:" + numLinies, 50, 70);
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
            figActual.rota(t);
        }
        else if(key =='s' || key =='S'){
            musicaOnOff = !musicaOnOff;
            if(musicaOnOff){
                musicaTetris.loop();
            }
            else {
                musicaTetris.stop();
            }
        }


    }

    public void dibuixaPantallaResultat(){
        // Dibuixa el fons
        background(255, 100, 100);

        fill(0); textAlign(CENTER); textSize(50);

        // Missatge de final de partida
        textSize(100);
        text("GAME OVER", width / 2, height / 2);

        // Número de figures col·locades en el tauler
        textSize(30);
        text("FIGURES:" + t.numFigures, width / 2, height / 2 + 100);

        // Número de línies resoltes
        textSize(30);
        text("LÍNIES:" + numLinies, width / 2, height / 2 + 200);
    }

}
