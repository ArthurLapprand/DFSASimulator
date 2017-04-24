package Estimador;

import java.util.HashMap;
import java.util.Random;

/**
 * Created by danil on 24/04/2017.
 */
public class Estimador implements Runnable {
    public int[] frame;
    public Random rng;
    public int qtd_etiquetas;
    public int qtd_etiquetas_i;
    public String tipo;

    public Estimador(int frameSize,int qtd_etiquetas,String tipo){
        this.frame = new int[frameSize];
        this.qtd_etiquetas = qtd_etiquetas;
        this.qtd_etiquetas_i = 0;
        this.rng = new Random();
        this.tipo = tipo;
    }

    @Override
    public void run() {

    }
}
