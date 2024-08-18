package SC02;

import processing.core.PApplet;

class Colors {
    int colorBUIT;
    int colorI, colorS, colorSI, colorT, colorL, colorLI, colorO;
    int[] colors;

    Colors(PApplet p5){
        colorBUIT = p5.color(255);
        colorI = p5.color(81, 225, 252);
        colorS = p5.color(233, 61, 30);
        colorSI = p5.color(121, 174, 61);
        colorT = p5.color(148, 54, 146);
        colorL = p5.color(246, 146, 48);
        colorLI = p5.color(241, 110, 185);
        colorO = p5.color(254, 248, 76);

        colors = new int[8];

        colors[0] = colorBUIT;
        colors[1] = colorI;
        colors[2] = colorS;
        colors[3] = colorSI;
        colors[4] = colorT;
        colors[5] = colorL;
        colors[6] = colorLI;
        colors[7] = colorO;
    }
}
