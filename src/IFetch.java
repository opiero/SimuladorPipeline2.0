import java.util.ArrayList;

/**
 * This class represents the IFetch state
 */
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

    /**
     * If bPCSel is true, the pc is set to the branch
     * @param bPCSel true if branch
     * @param iNPC New program counter value
     */
    public void muxIFetch (boolean bPCSel, int iNPC) {

        if (bPCSel){
            this.iPC = iNPC;
        }
        else{
            incrementPC();
        }

    }


}
