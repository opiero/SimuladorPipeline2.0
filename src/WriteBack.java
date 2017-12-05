public class WriteBack {

    private int iAluOutput;

    private int iLMD;

    private boolean bControl;

    private int iRd;

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

    public int getiRd() {
        return iRd;
    }

    public void setiRd(int iRd) {
        this.iRd = iRd;
    }

    public WriteBack () {
        this.bControl = false;
    }

    public int aluOutputOrLMD() {
        if (this.bControl)
            return iLMD;
        else
            return iAluOutput;
    }
}
