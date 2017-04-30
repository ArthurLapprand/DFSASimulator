package Plot;

/**
 * Created by danil on 30/04/2017.
 */
public class Result {

    private double tempoMedio, svMedio,ssMedio,scMedio,framesMedio,slotsMedio;
    private int QTD_TAGS;
    public Result(double tempoMedio, double svMedio,double ssMedio,double scMedio,double framesMedio,double slotsMedio, int QTD_TAGS, String estimador){
        this.tempoMedio = tempoMedio;
        this.svMedio = svMedio;
        this.ssMedio = ssMedio;
        this.scMedio = scMedio;
        this.framesMedio = framesMedio;
        this.slotsMedio = slotsMedio;
        this.QTD_TAGS = QTD_TAGS;
    }
    
    public double getTotalSlots() {
    	return (this.ssMedio + this.svMedio + this.scMedio);
    }

    public double getTempoMedio() {
        return tempoMedio;
    }

    public void setTempoMedio(double tempoMedio) {
        this.tempoMedio = tempoMedio;
    }

    public double getSvMedio() {
        return svMedio;
    }

    public void setSvMedio(double svMedio) {
        this.svMedio = svMedio;
    }

    public double getSsMedio() {
        return ssMedio;
    }

    public void setSsMedio(double ssMedio) {
        this.ssMedio = ssMedio;
    }

    public double getScMedio() {
        return scMedio;
    }

    public void setScMedio(double scMedio) {
        this.scMedio = scMedio;
    }

    public double getFramesMedio() {
        return framesMedio;
    }

    public void setFramesMedio(double framesMedio) {
        this.framesMedio = framesMedio;
    }

    public double getSlotsMedio() {
        return slotsMedio;
    }

    public void setSlotsMedio(double slotsMedio) {
        this.slotsMedio = slotsMedio;
    }

    public int getQTD_TAGS() {
        return QTD_TAGS;
    }

    public void setQTD_TAGS(int QTD_TAGS) {
        this.QTD_TAGS = QTD_TAGS;
    }
}
