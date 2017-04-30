package Simulador;
import Estimador.*;

import java.util.Random;

/**
 * Created by danil on 24/04/2017.
 */
public class DFSASimulator implements Runnable{
    private int QTD_INICIAL_SLOTS;//Qtd inicial de slots
    private int QTD_INICIAL_TAGS;//Qtd inicial das tags
    private int INC_PASSOS;//Quantidade à ser incrementada por passo
    private int QTD_PASSOS;
    private int QTD_SIM_PASSO;//Qtd de simulações por passo
    private Random rng;
    private int counterFrames;
    private int[] frame;
    private Estimador estimador;
    public DFSASimulator(int qtd_i_s, int qtd_i_t, int inc_passos, int passos, int qtd_sim_passo,int tamanho_inicial_frame,String tipo){
        this.QTD_INICIAL_SLOTS = qtd_i_s;
        this.QTD_INICIAL_TAGS = qtd_i_t;
        this.INC_PASSOS = inc_passos;
        this.QTD_PASSOS = passos;
        this.QTD_SIM_PASSO = qtd_sim_passo;
        this.frame = new int[tamanho_inicial_frame];
        this.counterFrames = 1;
        this.rng = new Random();
        this.estimador = new Estimador(frame, tipo);
    }


    @Override
    public void run() {

    }
}
