package Estimador;

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

    public int LowerBound(int tamanho_frame,int sc,int sv,int ss){
      
        return sc * 2;
    }
    public double EomLee(int tamanho_frame,int sc,int sv,int ss){
            double dTamanho_frame = tamanho_frame;
            double beta, oldGama, num, den, frac;
            double newGama = 2.0;
            do {
                oldGama = newGama;
                beta = dTamanho_frame / ((oldGama * sc) + ss);
                frac = Math.exp(-(1.0 / beta));
                num = (1.0 - frac);
                den = (beta * (1.0 - (1.0 + (1.0 / beta)) * frac));
                newGama = num / den;
            } while (Math.abs(oldGama - newGama) >= 0.001);
            //mult++;
            //if (Double.isNaN(newGamma * sc)) System.out.println("newGamma = " + newGamma + "sc = " + sc);
            return newGama * sc;
    }

    public double estimate(int tamanho_frame, int sc, int sv, int ss){
        if(tipo.equals("LowerBound")){
            return LowerBound(tamanho_frame,sc,sv,ss);
        }else if(tipo.equals("Eom-Lee")){
            return EomLee(tamanho_frame,sc,sv,ss);
        }
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
