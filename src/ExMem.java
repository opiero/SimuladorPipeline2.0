/**
 * This class represents the EXMEM register
 */
public class ExMem {

    private Instruction CIr;

    private int iAluOutput;

    private int iB;
    private int iDestiny;
    private int iTarget;

    private boolean bControl;

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

    public int getiTarget() {
        return iTarget;
    }

    public void setiTarget(int iTarget) {
        this.iTarget = iTarget;
    }

    public boolean isbControl() {
        return bControl;
    }

    public void setbControl(boolean bControl) {
        this.bControl = bControl;
    }

    /**
     * Constructor. Sets the control signal as false.
     */
    public ExMem () {
        this.bControl = false;
    }
}
