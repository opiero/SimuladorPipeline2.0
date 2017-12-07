public class Instruction {


    private InstructionType EType;
    private OperandType EOperand;

    private int iRt;
    private int iRs;
    private int iRd;

    private int iShamt;

    private int iITypeRs;
    private int iITypeRt;

    private int iImmediateValue;

    private int iData;

    private int iTarget;

    private String instruction;

    public InstructionType getEType() {
        return EType;
    }

    public OperandType getEOperand() {
        return EOperand;
    }

    public int getiRt() {
        return iRt;
    }

    public int getiRs() {
        return iRs;
    }

    public int getiRd() {
        return iRd;
    }

    public int getiShamt() {
        return iShamt;
    }

    public int getiITypeRs() {
        return iITypeRs;
    }

    public int getiITypeRt() {
        return iITypeRt;
    }

    public int getiImmediateValue() {
        return iImmediateValue;
    }

    public int getiData() {
        return iData;
    }

    public int getiTarget() {
        return iTarget;
    }

    public String getInstruction(){
        return instruction;
    }

    public Instruction(OperandType EOperand, int iRt, int iRs, int iRd, int iShamt, String instruction) {
        this.EOperand = EOperand;
        this.iRt = iRt;
        this.iRs = iRs;
        this.iRd = iRd;
        this.iShamt = iShamt;
        this.EType = InstructionType.R;
        this.instruction = instruction;

    }

    public Instruction(int iITypeRs, int iITypeRt, int iImmediateValue, int iTarget, OperandType EOperand, String instruction) {
        this.iITypeRs = iITypeRs;
        this.iITypeRt = iITypeRt;
        this.iImmediateValue = iImmediateValue;
        this.iTarget = iTarget;
        this.EOperand = EOperand;
        this.EType = InstructionType.I;
        this.instruction = instruction;
    }

    public Instruction(OperandType EOperand, int iData, int iTarget, String instruction) {
        this.EOperand = EOperand;
        this.iData = iData;
        this.iTarget = iTarget;
        this.EType = InstructionType.J;
        this.instruction = instruction;

    }

    public Instruction(OperandType EOperand, String instruction) {

        if (EOperand == OperandType.NOP) {

            this.EOperand = EOperand;
            this.EType = InstructionType.NOP;
            this.instruction = instruction;

        }

        else
            System.out.println("Not NOP ERROR.");

    }
}
