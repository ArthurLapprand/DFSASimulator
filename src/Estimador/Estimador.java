package Estimador;

import java.util.HashMap;
import java.util.Random;

/**
 * Created by danil on 24/04/2017.
 */
public class Estimador implements Runnable {
    public int[] frame;
    public String tipo;

    public Estimador(int[] frame,String tipo){
        this.frame = frame;
        this.tipo = tipo;
    }

    public void LowerBound(){

    }

    @Override
    public void run() {

    }
}
