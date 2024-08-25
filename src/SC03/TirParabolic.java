package SC03;

import processing.core.PApplet;
import processing.core.PFont;
import processing.sound.SoundFile;


public class TirParabolic extends PApplet {
    Projectil p;
    float f = 100;
    float h = 400;
    boolean disparat = false;
    float t =0;
    float g = 9.8f;
    Target[] targets;
    //Estadístiques
    int numShots = 0, numPoints = 0, numTargets = 0;
    //Estètica
    PFont font1, font2;
    SoundFile soCano, soImpacte;

    public static void main(String[] args){
        PApplet.main("SC03.TirParabolic");
    }

    public void settings(){
        size(1600, 800);
    }

    public void setup(){
        p = new Projectil(100, height/2, 50);
        p.setImatgeCano(this);

        font1 = createFont("EvilEmpire.otf", 34);
        font2 = createFont("Sono.ttf", 14);
        soCano = new SoundFile(this, "explosio.wav");
        soImpacte = new SoundFile(this, "impacte.wav");

        setTargets(3, 9);
    }

    public void draw(){
        background(220);

        // Dibuixa els targets
        for(int i=0; i<targets.length; i++) {
            targets[i].display(this);
        }

        // Si no s'ha disparat, configura posició H, força F i direcció A del canó
        if (!disparat) {
            float a = map(mouseY, this.h-100, this.h+100, 0, -PI);
            p.setProperties(a, f, h);
        }
        //Dins camp joc?
        else if (disparat && p.x <= width && p.y <= height){
            // Posició del projectil
            p.update(t, g);
            // Temps
            t += 0.1;

            // Col·lisions?
            for(int i=0; i<targets.length; i++){
                if(targets[i].estat != Target.ESTAT.EXPLOTAT && targets[i].esImpactatPer(this, p)){
                    targets[i].setEstat(Target.ESTAT.EXPLOTAT);
                    numPoints++;
                    soImpacte.play();
                }
            }
        }
        else if (disparat && (p.x > width || p.y > height)) {

            // Posam la resta d'objectius a fallats (failed)
            for(int i=0; i<targets.length; i++){
                if(targets[i].estat == Target.ESTAT.PENDENT){
                    targets[i].setEstat(Target.ESTAT.FALLAT);
                }
            }
            // Missatge reset
            textAlign(CENTER); textSize(36); fill(255, 0, 0);
            text("Press R key to set up the next scenario", width/2, height/2);
        }

        // Dibuixa el projectil
        p.display(this);

        //Stats
        displayInfo();
    }

    void setTargets(int n1, int n2){
        int nt = (int) random(n1, n2);
        targets = new Target[nt];

        // Posicions randoms
        for(int i=0; i<nt; i++){
            float x = random(width/2, width);
            float y = random(height/5, 4*height/5);
            float r = random(20, 60);
            targets[i] = new Target(x, y, r);
        }
        numTargets += nt;
    }

    public void keyPressed() {
        if (key == 's' || key == 'S') {
            disparat = true;
            numShots++;
            soCano.play();
        }
        else if (key == 'r') {
            disparat = false;
            t = 0;
            setTargets(3, 12);
        }
        // +força
        else if (keyCode == RIGHT) {
            f += 10;
            f = constrain(f, 10, 300);
        }
        // -força
        else if (keyCode == LEFT) {
            f -= 10;
            f = constrain(f, 10, 300);
        }
        // +altura H del canó
        else if (keyCode == UP) {
            h -= 10;
        }
        // -altura H del canó
        else if (keyCode == DOWN) {
            h += 10;
        }
    }

    void displayInfo(){

        // Títol del joc
        fill(0); textAlign(LEFT); textSize(34);
        textFont(font1);
        text("Tir Parabòlic", 50, 50);

        // Marcador
        fill(0); textAlign(RIGHT);
        text("Score", width - 50, 50);
        textSize(14);
        String percentatge = nf(100*(numPoints/(float)numTargets), 2, 2);
        text("Rate: "+ percentatge+"%", width - 50, 80);
        text("Hits: "+ numPoints + " / " + numTargets, width - 50, 100);
        text("Shots: "+ numShots, width - 50, 120);


        // Instruccions
        fill(0); textSize(14); textAlign(LEFT);
        textFont(font2);
        text("Press S key to shot your cannon.", 50, height-90);

    }
}
