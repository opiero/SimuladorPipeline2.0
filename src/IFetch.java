import java.util.ArrayList;

public class IFetch {

    private int iPC;

    Instruction instruction;


    public Instruction getInstruction() {
        return instruction;
    }

    public void setInstruction(Instruction instruction) {
        this.instruction = instruction;
    }

    public void incrementPC () {
        iPC += 1;
    }

    public int getiPC() {
        return iPC;
    }

    public IFetch() {
        this.iPC = 0;
    }

    public void muxIFetch (boolean bPCSel, int iNPC) {

        if (bPCSel){
            this.iPC = iNPC;
        }
        else{
            incrementPC();
        }

    }


}
