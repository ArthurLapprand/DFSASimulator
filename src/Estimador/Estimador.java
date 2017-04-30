package Estimador;

import java.util.HashMap;
import java.util.Random;

/**
 * Created by danil on 24/04/2017.
 */
public class Estimador{
    public int[] frame;
    public String tipo;

    public Estimador(int[] frame,String tipo){
        this.frame = frame;
        this.tipo = tipo;
    }

    public int LowerBound(){
        return 0;
    }

    public int estimate(){
        return 0;
    }

    public int[] getFrame() {
        return frame;
    }

    public void setFrame(int[] frame) {
        this.frame = frame;
    }

    public String getTipo() {
        return tipo;
    }

    public void setTipo(String tipo) {
        this.tipo = tipo;
    }
}
