package com.ar.visualizzatore.gui;

public class Colore {
    private int r = 255;
    private int g = 0;
    private int b = 0;

    public Colore() {}

    public void cambiaColore(){
        if(r>0 && b<=254 && g==0)
        {
            r--;
            b++;
        }
        else
        {
            if(b>0 && g<=254 && r==0)
            {
                b--;
                g++;
            }
            else
            {
                g--;
                r++;
            }
        }
    }

    public int getR() {
        return r;
    }

    public void setR(int r) {
        this.r = r;
    }

    public int getG() {
        return g;
    }

    public void setG(int g) {
        this.g = g;
    }

    public int getB() {
        return b;
    }

    public void setB(int b) {
        this.b = b;
    }
}
