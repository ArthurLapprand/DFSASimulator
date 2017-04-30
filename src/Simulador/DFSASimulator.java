package Simulador;
import Estimador.*;
import Plot.FileName;
import Plot.Result;
import Plot.ResultGenerator;

import java.util.Random;
import java.util.concurrent.LinkedBlockingQueue;

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
    private int[] scCounter;
    private int[] svCounter;
    private int[] ssCounter;
    private int[] counterFrames;
    private int[] counterSlots;
    private int[] frame;
    private long[] counterTime;
    private int tamanho_frame;
    private Result[] results;
    private Estimador estimador;
    private int tamanho_inicial_frame;
    private LinkedBlockingQueue<FileName> filenames;

    public DFSASimulator(int qtd_i_s, int qtd_i_t, 
    		int inc_passos, int passos, int qtd_sim_passo,
    		String tipo, LinkedBlockingQueue<FileName> filenames){
        this.QTD_INICIAL_SLOTS = qtd_i_s;
        this.QTD_INICIAL_TAGS = qtd_i_t;
        this.INC_PASSOS = inc_passos;
        this.QTD_PASSOS = passos;
        this.QTD_SIM_PASSO = qtd_sim_passo;
        this.frame = new int[10000];
        this.results = new Result[this.QTD_PASSOS];
        this.tamanho_inicial_frame = QTD_INICIAL_SLOTS;
        this.tamanho_frame = tamanho_inicial_frame;
        this.scCounter = new int[this.QTD_SIM_PASSO];
        this.svCounter = new int[this.QTD_SIM_PASSO];
        this.ssCounter = new int[this.QTD_SIM_PASSO];
        this.counterFrames = new int[this.QTD_SIM_PASSO];
        this.counterSlots = new int[this.QTD_SIM_PASSO];
        this.counterTime = new long[this.QTD_SIM_PASSO];
        this.rng = new Random();
        this.estimador = new Estimador(frame, tipo);
        this.QTD_TAGS_IDENTIFICADAS = 0;
        this.filenames = filenames;
    }


    @Override
    public void run() {
        for(int i =0; i< this.QTD_PASSOS; i++) {
            for(int j=0; j<this.QTD_SIM_PASSO;j++) {
                this.counterTime[j] = System.currentTimeMillis();
                this.QTD_TAGS_IDENTIFICADAS = 0;
                this.frame = new int[tamanho_inicial_frame];
                this.counterFrames[j] = 1;
                this.counterSlots[j] = this.tamanho_frame;
                while (this.QTD_TAGS_IDENTIFICADAS < this.QTD_INICIAL_TAGS) {
                    for(int k=0;k<this.QTD_INICIAL_TAGS - this.QTD_TAGS_IDENTIFICADAS;k++){
                        int randomNum = rng.nextInt(tamanho_frame);
                        frame[randomNum]++;
                    }
                    int sc = 0,sv =0,ss = 0;
                    for(int k=0;k<this.tamanho_frame;k++){
                        if(frame[k]==1){
                            this.QTD_TAGS_IDENTIFICADAS++;
                            ss++;
                            this.ssCounter[j] ++;
                        }else if(frame[k]==0){
                            sv++;
                            this.svCounter[j] ++;
                        }else{
                            sc++;
                            this.scCounter[j]++;
                        }
                        frame[k] = 0;
                    }
                    if(!(this.QTD_TAGS_IDENTIFICADAS == this.QTD_INICIAL_TAGS)){
                        double frameSize = Math.ceil(estimador.estimate(this.tamanho_frame,sc,sv,ss));
                        this.tamanho_frame = (int) frameSize;
                        this.counterFrames[j]++;
                        this.counterSlots[j]+=this.tamanho_frame;
                    }
                }
                this.counterTime[j] -= System.currentTimeMillis();
            }
            //AQUI CALCULO A MEDIA DAS SIMULAÇOES, COLOCA EM ALGUM LUGAR , E PLOTT
            double tempoMedio, svMedio,ssMedio,scMedio,framesMedio,slotsMedio;
             long tempoM = 0,svM = 0 , ssM = 0, scM = 0, fM = 0, sM = 0;
            for(int l =0; l<QTD_SIM_PASSO;l++){
                tempoM += this.counterFrames[l];
                this.counterFrames[l] = 0;
                svM += this.svCounter[l];
                this.svCounter[l] = 0;
                ssM += this.ssCounter[l];
                this.ssCounter[l] = 0;
                scM += this.scCounter[l];
                this.scCounter[l] = 0;
                fM += this.counterFrames[l];
                this.counterFrames[l] = 0;
                sM += this.counterSlots[l];
                this.counterSlots[l] = 0;
            }
            tempoMedio = (double)tempoM / (double)this.QTD_SIM_PASSO;
            svMedio = (double) svM/ (double)this.QTD_SIM_PASSO;
            ssMedio = (double) ssM/ (double)this.QTD_SIM_PASSO;
            scMedio = (double) scM/ (double)this.QTD_SIM_PASSO;
            framesMedio = (double) fM / (double)this.QTD_SIM_PASSO;
            slotsMedio = (double) sM / (double)this.QTD_SIM_PASSO;
            Result r = new Result(tempoMedio,svMedio,ssMedio,scMedio,framesMedio,slotsMedio,this.QTD_INICIAL_TAGS, this.estimador.tipo);
            results[i] = r;
            //AQUI CALCULA A MEDIA DAS SIMULACOES
            this.QTD_INICIAL_TAGS += this.INC_PASSOS;
        }
        
        ResultGenerator rg = new ResultGenerator(estimador.getTipo(), results,
        		QTD_INICIAL_SLOTS, 
        		QTD_INICIAL_TAGS,
        		QTD_PASSOS,
        		QTD_SIM_PASSO);
        rg.outputResults(filenames);
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

    public int[] getCounterFrames() {
        return counterFrames;
    }

    public void setCounterFrames(int[] counterFrames) {
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
