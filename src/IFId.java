public class IFId {

    private Instruction CIr;

    private int iNPC, PC;

    public void setCIr(Instruction CIr) {
        this.CIr = CIr;
    }

    public void setiNPC(int iNPC) {
        this.iNPC = iNPC;
    }

    public void setPC(int PC){
        this.PC = PC;
    }

    public Instruction getCIr() {
        return CIr;
    }

    public int getiNPC() {
        return iNPC;
    }

    public int getPC() {
        return PC;
    }

}
