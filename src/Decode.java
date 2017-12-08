/**
 * This class represents the decode state
 */
public class Decode {

    private Instruction CIr;

    int iOldRd;
    int iOldData;
    boolean bWriteRegister;

    public Instruction getCIr() {
        return CIr;
    }

    public int getiOldRd() {
        return iOldRd;
    }

    public int getiOldData() {
        return iOldData;
    }

    public boolean isbWriteRegister() {
        return bWriteRegister;
    }

    public void setCIr(Instruction CIr) {
        this.CIr = CIr;
    }

    public void setiOldRd(int iOldRd) {
        this.iOldRd = iOldRd;
    }

    public void setiOldData(int iOldData) {
        this.iOldData = iOldData;
    }

    public void setbWriteRegister(boolean bWriteRegister) {
        this.bWriteRegister = bWriteRegister;
    }

    /**
     * Generates the control signal
     * @return control signal
     */
    public boolean getControl () {

        boolean bControl = false;

        if (this.CIr.getEType() == InstructionType.J)
            bControl = true;

        else if (this.CIr.getEType() == InstructionType.I) {

            if (this.CIr.getEOperand() == OperandType.BEQ || this.CIr.getEOperand() == OperandType.BNE || this.CIr.getEOperand() == OperandType.LW)
                bControl = true;
        }

        return bControl;

    }

    /**
     * Constructor. Sets the WriteRegister flag false.
     */
    public Decode () {
        this.bWriteRegister = false;
    }
}
