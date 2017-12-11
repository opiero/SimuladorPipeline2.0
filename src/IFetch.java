import java.util.ArrayList;

/**
 * This class represents the IFetch state
 */
public class IFetch {

    private int iPC;

    private Instruction instruction;

    private Boolean bPCSel;

    private int iNPC;


    public Instruction getInstruction() {
        return instruction;
    }

    public void setInstruction(Instruction instruction) {
        this.instruction = instruction;
    }

    public void setiNPC(int iNPC) {
        this.iNPC = iNPC;
    }

    public void setbPCSel(Boolean bPCSel) {
        this.bPCSel = bPCSel;
    }

    public int getiNPC() {
        return this.iNPC;
    }

    public Boolean isBPCSel(){
        return this.bPCSel;
    }

    public void incrementPC () {
        iPC += 1;
    }

    public int getiPC() {
        return iPC;
    }

    public IFetch() {
        this.iPC = 0;
        this.bPCSel = false;
    }

    /**
     * If bPCSel is true, the pc is set to the branch
     */
    public void muxIFetch () {

        if (this.bPCSel){
            this.iPC = this.iNPC;
            setbPCSel(false);
        }
        else{
            if(getiPC() < 15) incrementPC();
        }

    }


}
