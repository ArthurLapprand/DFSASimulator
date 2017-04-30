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
    private int QTD_TAGS_IDENTIFICADAS;
    private int QTD_SIM_PASSO;//Qtd de simulações por passo
    private Random rng;
    private int counterFrames;
    private int[] frame;
    private Estimador estimador;
    private int tamanho_inicial_frame;

    public DFSASimulator(int qtd_i_s, int qtd_i_t, int inc_passos, int passos, int qtd_sim_passo,int tamanho_inicial_frame,String tipo){
        this.QTD_INICIAL_SLOTS = qtd_i_s;
        this.QTD_INICIAL_TAGS = qtd_i_t;
        this.INC_PASSOS = inc_passos;
        this.QTD_PASSOS = passos;
        this.QTD_SIM_PASSO = qtd_sim_passo;
        this.frame = new int[tamanho_inicial_frame];
        this.tamanho_inicial_frame = tamanho_inicial_frame;
        this.counterFrames = 1;
        this.rng = new Random();
        this.estimador = new Estimador(frame, tipo);
        this.QTD_TAGS_IDENTIFICADAS = 0;
    }


    @Override
    public void run() {
        for(int i =0; i< this.QTD_PASSOS; i++) {
            for(int j=0; j<this.QTD_SIM_PASSO;j++) {
                this.QTD_TAGS_IDENTIFICADAS = 0;
                this.frame = new int[tamanho_inicial_frame];
                while (this.QTD_TAGS_IDENTIFICADAS < this.QTD_INICIAL_TAGS) {
                    for(int k=0;k<this.QTD_INICIAL_TAGS - this.QTD_TAGS_IDENTIFICADAS;k++){
                        int randomNum = rng.nextInt(frame.length);
                        frame[randomNum]++;
                    }
                    for(int k=0;k<this.frame.length;k++){
                        if(frame[k]==1){
                            this.QTD_TAGS_IDENTIFICADAS++;
                        }
                    }
                    if(!(this.QTD_TAGS_IDENTIFICADAS == this.QTD_INICIAL_TAGS)){
                        estimador.setFrame(this.frame);
                        int nextFrameSize = estimador.estimate();
                        this.frame = new int[nextFrameSize];
                    }
                }
            }
            this.QTD_INICIAL_TAGS += this.INC_PASSOS;
        }
    }

    public int getQTD_INICIAL_SLOTS() {
        return QTD_INICIAL_SLOTS;
    }

    public void setQTD_INICIAL_SLOTS(int QTD_INICIAL_SLOTS) {
        this.QTD_INICIAL_SLOTS = QTD_INICIAL_SLOTS;
    }

    public int getQTD_INICIAL_TAGS() {
        return QTD_INICIAL_TAGS;
    }

    public void setQTD_INICIAL_TAGS(int QTD_INICIAL_TAGS) {
        this.QTD_INICIAL_TAGS = QTD_INICIAL_TAGS;
    }

    public int getINC_PASSOS() {
        return INC_PASSOS;
    }

    public void setINC_PASSOS(int INC_PASSOS) {
        this.INC_PASSOS = INC_PASSOS;
    }

    public int getQTD_PASSOS() {
        return QTD_PASSOS;
    }

    public void setQTD_PASSOS(int QTD_PASSOS) {
        this.QTD_PASSOS = QTD_PASSOS;
    }

    public int getQTD_SIM_PASSO() {
        return QTD_SIM_PASSO;
    }

    public void setQTD_SIM_PASSO(int QTD_SIM_PASSO) {
        this.QTD_SIM_PASSO = QTD_SIM_PASSO;
    }

    public Random getRng() {
        return rng;
    }

    public void setRng(Random rng) {
        this.rng = rng;
    }

    public int getCounterFrames() {
        return counterFrames;
    }

    public void setCounterFrames(int counterFrames) {
        this.counterFrames = counterFrames;
    }

    public int[] getFrame() {
        return frame;
    }

    public void setFrame(int[] frame) {
        this.frame = frame;
    }

    public Estimador getEstimador() {
        return estimador;
    }

    public void setEstimador(Estimador estimador) {
        this.estimador = estimador;
    }

    public int getQTD_TAGS_IDENTIFICADAS() {
        return QTD_TAGS_IDENTIFICADAS;
    }

    public void setQTD_TAGS_IDENTIFICADAS(int QTD_TAGS_IDENTIFICADAS) {
        this.QTD_TAGS_IDENTIFICADAS = QTD_TAGS_IDENTIFICADAS;
    }
}
