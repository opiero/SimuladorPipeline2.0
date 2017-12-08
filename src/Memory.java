import java.util.ArrayList;

public class Memory {

    private ArrayList<Integer> AlIMemoryData;

    private boolean bControl;

    private int iAluOutput;

    private boolean bStore;

    private int iBContent;

    private boolean bLoad;

    private int iContent;

    public boolean isbControl() {
        return bControl;
    }

    public void setbControl(boolean bControl) {
        this.bControl = bControl;
    }

    public int getiAluOutput() {
        return iAluOutput;
    }

    public void setiAluOutput(int iAluOutput) {
        this.iAluOutput = iAluOutput;
    }

    public boolean isbStore() {
        return bStore;
    }

    public void setbStore(boolean bStore) {
        this.bStore = bStore;
    }

    public int getiBContent() {
        return iBContent;
    }

    public void setiBContent(int iBContent) {
        this.iBContent = iBContent;
    }

    public boolean isbLoad() {
        return bLoad;
    }

    public void setbLoad(boolean bLoad) {
        this.bLoad = bLoad;
    }

    public int getiContent() {
        return iContent;
    }

    public void setiContent(int iContent) {
        this.iContent = iContent;
    }

    public int getAlIMemoryDataAtIndex(int index) {
        return AlIMemoryData.get(index);
    }

    private void manageMemoryFlags (OperandType eOperand, InstructionType eType) {

        if (eType == InstructionType.I) {

            if (eOperand == OperandType.LW ) {
                setbLoad(true);
                setbStore(false);
            }
            else if (eOperand == OperandType.SW) {
                setbStore(true);
                setbLoad(false);
            }

        }

        else {
            setbStore(false);
            setbLoad(false);
        }

    }

    public void manageMemory (OperandType eOperand, InstructionType eType) {

        manageMemoryFlags(eOperand, eType);

        if (this.bStore)
            AlIMemoryData.add(this.iAluOutput, this.iBContent);

        else if (this.bLoad)
            this.iContent = this.AlIMemoryData.get(this.iAluOutput);



    }

    public Memory() {

        final int memorySize = 32;

        AlIMemoryData = new ArrayList<Integer>();

        for (int i = 0; i < memorySize; i++)
            this.AlIMemoryData.add(0);

        this.bLoad = this.bStore = false;


    }
}
