public class MemWb {

    private Instruction CIr;

    private int iAluOutput;

    private int iLMD;

    private boolean bControl;

    private int iB;

    private int iDestiny;

    public Instruction getCIr() {
        return CIr;
    }

    public void setCIr(Instruction CIr) {
        this.CIr = CIr;
    }

    public int getiAluOutput() {
        return iAluOutput;
    }

    public void setiAluOutput(int iAluOutput) {
        this.iAluOutput = iAluOutput;
    }

    public int getiLMD() {
        return iLMD;
    }

    public void setiLMD(int iLMD) {
        this.iLMD = iLMD;
    }

    public boolean isbControl() {
        return bControl;
    }

    public void setbControl(boolean bControl) {
        this.bControl = bControl;
    }

    public int getiB() {
        return iB;
    }

    public void setiB(int iB) {
        this.iB = iB;
    }

    public int getiDestiny() {
        return iDestiny;
    }

    public void setiDestiny(int iDestiny) {
        this.iDestiny = iDestiny;
    }

    public MemWb () {
        this.bControl = false;
    }
}
