package SC02;

import processing.core.PApplet;

class Tauler {
    int x, y;
    int numCols, numFiles;
    float ampleCella, altCella;
    Figura.TIPUS_FIGURA[][] caselles;

    /**
     * @param nc nombre columnes
     * @param nf nombre files
     * @param x posició tauler
     * @param y posició tauler
     * @param w ample tauler
     * @param h alt tauler
     */
    Tauler(int nc, int nf, int x, int y, int w, int h){
        this.numCols = nc;
        this.numFiles = nf;
        this.x = x;
        this.y = y;

        //Calc caselles
        this.ampleCella = w / numCols;
        this.altCella = h / numFiles;
    }

    public void inicialitzaCaselles(){
        caselles = new Figura.TIPUS_FIGURA [numFiles][numCols];
        for(int c = 0; c<numCols; c++){
            for (int f = 0; f<numFiles; f++){
                caselles[f][c] = Figura.TIPUS_FIGURA.BUIDA;
            }
        }
    }

    public void dibuixaGraella(PApplet p5, int colorBUIT){
        for(int c = 0; c<numCols; c++){
            for (int f = 0; f<numFiles; f++){
                p5.fill(colorBUIT);
                p5.stroke(0);
                p5.rect(c*ampleCella, f*altCella, ampleCella, altCella);
            }
        }
    }

    public void dibuixaFigura(PApplet p5, Figura fig, int[] colors){
        for(int f = 0; f<fig.matriu.length; f++){
            for (int c = 0; c<fig.matriu[0].length;c++){

                //Posició al tauler dels quadrats de les figures
                int ft = fig.fila + f;
                int ct = fig.col + c;

                if(fig.matriu[f][c]==1){
                    p5.fill(colors[fig.tipusFigura.ordinal()]);
                    p5.stroke(0);
                    p5.rect(ct*ampleCella,ft*altCella,ampleCella,altCella);
                }
            }
        }
    }

    void aplicaFigura(Figura fig){
        for(int f = 0; f<fig.matriu.length; f++){
            for(int c = 0; c<fig.matriu[0].length; c++){
                if(fig.matriu[f][c]==1){
                    int ct = c + fig.col;
                    int ft = f + fig.fila;
                    this.caselles[ft][ct] = fig.tipusFigura;
                }
            }
        }
    }
}