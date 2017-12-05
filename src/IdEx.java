public class IdEx {

    private Instruction Cir;

    private int iA, iB, iDestiny;

    private int iNPC;

    private int iImmediateValue;

    private boolean bControl;

    public Instruction getCir() {
        return Cir;
    }

    public int getiA() {
        return iA;
    }

    public int getiB() {
        return iB;
    }

    public int getiDestiny(){
        return iDestiny;
    }

    public int getiNPC() {
        return iNPC;
    }

    public int getiImmediateValue() {
        return iImmediateValue;
    }

    public boolean isbControl() {
        return bControl;
    }

    public void setCir(Instruction cir) {
        Cir = cir;
    }

    public void setiA(int iA) {
        this.iA = iA;
    }

    public void setiB(int iB) {
        this.iB = iB;
    }

    public void setiDestiny(int iDestiny){
        this.iDestiny = iDestiny;
    }

    public void setiNPC(int iNPC) {
        this.iNPC = iNPC;
    }

    public void setiImmediateValue(int iImmediateValue) {
        this.iImmediateValue = iImmediateValue;
    }

    public void setbControl(boolean bControl) {
        this.bControl = bControl;
    }

    public IdEx () {
        this.bControl = false;
    }

}


