import java.util.ArrayList;
import java.util.Collections;
import java.util.Queue;
import java.util.concurrent.LinkedBlockingQueue;

/** This class simulates a processor
 * */
public class Processor {

    private ArrayList<Integer> AlIRegistersBank;
    private ArrayList<Instruction> AlInstructionMemory;
    Queue<PipelineStates> states;
    private Queue<Integer> clock;


    private IFetch iFetch;
    private IFId ifId;
    private Decode decode;
    private IdEx idEx;
    private Execute execute;
    private ExMem exMem;
    private Memory memory;
    private MemWb memWb;
    private WriteBack writeBack;

    public IFId getIfId() {
        return ifId;
    }

    public IdEx getIdEx() {
        return idEx;
    }

    public ExMem getExMem() {
        return exMem;
    }

    public MemWb getMemWb() {
        return memWb;
    }

    public Memory getMemory() {
        return memory;
    }

    public int getRegisterBankAtIndex(int index){
        return this.AlIRegistersBank.get(index);
    }


    /**
     * This is the processor constructor.
     * @param AlInstructionMemory ArrayList with all the instructions
     */
    public Processor (ArrayList<Instruction>AlInstructionMemory) {

        //initializing queues. Will be needed at the run method
        clock = new LinkedBlockingQueue<Integer>();
        states = new LinkedBlockingQueue<PipelineStates>();

       //creating the register bank
       AlIRegistersBank = new ArrayList<Integer>();
       for (int i = 0; i < 16; i++)
           AlIRegistersBank.add(0);

        //initializing attributes
        this.AlInstructionMemory =  AlInstructionMemory;
        iFetch = new IFetch();
        ifId = new IFId();
        decode = new Decode();
        idEx = new IdEx();
        execute = new Execute();
        exMem = new ExMem();
        memory = new Memory();
        memWb = new MemWb();
        writeBack = new WriteBack();


    }

    /**
     * This method writes on the given register
     */
    private void writeRegisters(){
        this.AlIRegistersBank.set(decode.getiOldRd(), decode.getiOldData());
        decode.setbWriteRegister(false);
    }

    /**
     * Executes an R command
     * @param eOperand The type of the operand
     * @return returns the result of the operation
     */
    private int executeRCommand (OperandType eOperand) {

        switch (eOperand) {
            case ADD:
                return this.AlIRegistersBank.get(execute.getiA()) + this.AlIRegistersBank.get(execute.getiB());
            case SUB:
                return this.AlIRegistersBank.get(execute.getiA()) - this.AlIRegistersBank.get(execute.getiB());
            case MULT:
                return this.AlIRegistersBank.get(execute.getiA()) * this.AlIRegistersBank.get(execute.getiB());
            case DIV:
                return this.AlIRegistersBank.get(execute.getiA()) * this.AlIRegistersBank.get(execute.getiB());
            case AND:
                return this.AlIRegistersBank.get(execute.getiA()) & this.AlIRegistersBank.get(execute.getiB());
            case OR:
                return this.AlIRegistersBank.get(execute.getiA()) | this.AlIRegistersBank.get(execute.getiB());
            case XOR:
                return this.AlIRegistersBank.get(execute.getiA()) ^ this.AlIRegistersBank.get(execute.getiB());
            case SLL:
                return this.AlIRegistersBank.get(execute.getiA()) << this.AlIRegistersBank.get(execute.getiB());
            case SRL:
                return this.AlIRegistersBank.get(execute.getiA()) >> this.AlIRegistersBank.get(execute.getiB());

        }

        return -1;

    }

    /**
     * Executes an I command
     * @param eOperand The type of the operand
     * @return returns the result of the operation
     */
    private int executeICommand (OperandType eOperand) {

        switch (eOperand) {

            case ADDI:
                return this.AlIRegistersBank.get(execute.getiA()) + execute.getiImmediateValue();

            case XORI:
                return this.AlIRegistersBank.get(execute.getiA()) ^ execute.getiImmediateValue();

            case ANDI:
                return this.AlIRegistersBank.get(execute.getiA()) & execute.getiImmediateValue();

            case ORI:
                return this.AlIRegistersBank.get(execute.getiA()) | execute.getiImmediateValue();

            case BEQ:
                if (this.AlIRegistersBank.get(execute.getiA()) == this.AlIRegistersBank.get(execute.getiB()))
                    return 1;
                else
                    return 0;

            case BNE:
                if (this.AlIRegistersBank.get(execute.getiA()) != this.AlIRegistersBank.get(execute.getiB()))
                    return 1;
                else
                    return 0;

            case LW:
                return this.AlIRegistersBank.get(execute.getiA()) + execute.getiImmediateValue();

            case SW:
                return this.AlIRegistersBank.get(execute.getiA()) + execute.getiImmediateValue();

        }

        return -1;

    }

    /**
     * Executes a command
     * @param eType Type of the instruction
     * @param eOperand Type of the operand
     * @return the result of the given command
     */
    private int executeCommand (InstructionType eType, OperandType eOperand) {

        int exec_return = 0;

        if (eType == InstructionType.R)
            exec_return = executeRCommand(eOperand);
        else if (eType == InstructionType.I)
            exec_return = executeICommand(eOperand);

        return exec_return;

    }

    /**
     * Represents the transition of the data from the Fetch state to the IFID register
     * @param bPCSel true if a branch is activated
     * @param iNPC Program counter value after the branch
     */
    private void iFetchIfId(boolean bPCSel, int iNPC){

        ifId.setCIr(iFetch.getInstruction());

        ifId.setPC(iFetch.getiPC());

        ifId.setiNPC(iFetch.getiPC());

        iFetch.muxIFetch(bPCSel, iNPC);

    }


    /**
     * Represents the transition from the IFID register to the Decode state
     * */
    private void ifIdDecode () {

        decode.setCIr(ifId.getCIr());

        if (decode.isbWriteRegister())
            writeRegisters();

    }

    /**
     * Represents the transition from the decode state to the IDEX state
     */
    private void decodeIdEx() {

        idEx.setCir(decode.getCIr());

        idEx.setiNPC(ifId.getiNPC());

        idEx.setbControl(decode.getControl());

        if (decode.getCIr().getEType() == InstructionType.R) {
            idEx.setiA(decode.getCIr().getiRs());
            idEx.setiB(decode.getCIr().getiRt());
            idEx.setiDestiny(decode.getCIr().getiRd());

        }

        else if (decode.getCIr().getEType() == InstructionType.I) {

            idEx.setiA(decode.getCIr().getiITypeRs());
            idEx.setiB(decode.getCIr().getiITypeRt());
            idEx.setiDestiny(decode.getCIr().getiITypeRt());

            idEx.setiImmediateValue(decode.getCIr().getiImmediateValue());


        }

        else if(decode.getCIr().getEType() == InstructionType.NOP){
            idEx.setiA(0);
            idEx.setiB(0);
            idEx.setiImmediateValue(0);
        }

    }

    /**
     * Represents the transition from the IDEX register to the execute state
     */
    private void idExExecute () {

        if (idEx.getCir().getEType() == InstructionType.R) {

            execute.setiA(idEx.getiA());
            execute.setiB(idEx.getiB());

        }

        else if (idEx.getCir().getEType() == InstructionType.I || idEx.getCir().getEType() == InstructionType.NOP) {

            execute.setiA(idEx.getiA());
            execute.setiB(idEx.getiB());

            execute.setiImmediateValue(idEx.getiImmediateValue());


        }
    }


    /**
     * Represents the transition from the execute state to the EXMEM register
     */
    private void executeExMem () {

        exMem.setCIr(idEx.getCir());

        exMem.setiAluOutput(executeCommand(exMem.getCIr().getEType(), exMem.getCIr().getEOperand()));

        exMem.setiB(execute.getiB());

        exMem.setiDestiny(idEx.getiDestiny());

        exMem.setbControl(idEx.isbControl());

        exMem.setiTarget(exMem.getCIr().getiTarget());

    }

    /**
     * Represents the transition from the EXMEM register to the Memory state
     */
    private void exMemMemory () {

        memory.setbControl(exMem.isbControl());

        memory.setiAluOutput(exMem.getiAluOutput());

        memory.setiBContent(this.AlIRegistersBank.get(exMem.getiB())); //OLHAR ESSA JOSSA DEPOIS


    }

    /**
     * Represents the transition from the memory state to the MEMWB register
     */
    private void memoryMemWb () {

        memory.manageMemory(exMem.getCIr().getEOperand(), exMem.getCIr().getEType());

        memWb.setCIr(exMem.getCIr());

        memWb.setiAluOutput(memory.getiAluOutput());

        memWb.setbControl(exMem.isbControl());

        memWb.setiB(exMem.getiB());

        memWb.setiDestiny(exMem.getiDestiny());

        if (memory.isbLoad())
            memWb.setiLMD(memory.getiContent());


    }

    /**
     * Represents to the MEMWB register to the WriteBack state
     */
    private void memWbWriteBack () {

        writeBack.setiAluOutput(memWb.getiAluOutput());

        writeBack.setiLMD(memWb.getiLMD());

        writeBack.setbControl(memWb.isbControl());

        int iResult = writeBack.aluOutputOrLMD();

        decode.setiOldData(iResult);

        decode.setiOldRd(memWb.getiDestiny());

        if (memWb.getCIr().getEType() == InstructionType.R) {
            decode.setbWriteRegister(true);
        }

        else if (memWb.getCIr().getEType() == InstructionType.I) {

            if (!(memWb.getCIr().getEOperand() == OperandType.BEQ || memWb.getCIr().getEOperand() == OperandType.BNE ||
                    memWb.getCIr().getEOperand() == OperandType.SW))
                decode.setbWriteRegister(true);

        }

    }


    /**
     * Runs the given clock
     * @param actualClock actual clock, starts from 0
     */
    public void runNextClock (int actualClock) {


       if (actualClock == 0) {
           states.add(PipelineStates.IFetch);
           clock.add(0);
       }

       while (!states.isEmpty()) {



           if (clock.peek() > actualClock)
               return;

           PipelineStates actual = states.poll();

           switch (actual) {

               case IFetch:
                   states.add(PipelineStates.Decode);
                   clock.add(clock.poll() + 1);
                   iFetch.setInstruction(AlInstructionMemory.get(actualClock));
                   iFetchIfId(false, actualClock);
                   if (actualClock < this.AlInstructionMemory.size() - 1) {
                       states.add(PipelineStates.IFetch);
                       clock.add(actualClock+1);

                   }
                   break;

               case Decode:
                   states.add(PipelineStates.Execute);
                   clock.add(clock.poll() + 1);
                   ifIdDecode();
                   decodeIdEx();
                   break;

               case Execute:
                   states.add(PipelineStates.Memory);
                   clock.add(clock.poll() + 1);
                   idExExecute();
                   executeExMem();
                   break;

               case Memory:
                   states.add(PipelineStates.WriteBack);
                   clock.add(clock.poll() + 1);
                   exMemMemory();
                   memoryMemWb();
                   break;

               case WriteBack:
                   memWbWriteBack();
                   break;


           }


       }
   }



}
