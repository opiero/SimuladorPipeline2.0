import java.util.ArrayList;
import java.util.Collections;
import java.util.Queue;
import java.util.concurrent.LinkedBlockingQueue;

// todo mastigar as instruçoes

//todo interface usuario

//todo

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

    public Processor (ArrayList<Instruction>AlInstructionMemory) {

        clock = new LinkedBlockingQueue<Integer>();
        states = new LinkedBlockingQueue<PipelineStates>();

        AlIRegistersBank = new ArrayList<Integer>();
       for (int i = 0; i < 16; i++)
           AlIRegistersBank.add(0);

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

    private void writeRegisters(){
        this.AlIRegistersBank.set(decode.getiOldRd(), decode.getiOldData());
        decode.setbWriteRegister(false);
    }

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

    private int executeCommand (InstructionType eType, OperandType eOperand) {

        int exec_return = 0;

        if (eType == InstructionType.R)
            exec_return = executeRCommand(eOperand);
        else if (eType == InstructionType.I)
            exec_return = executeICommand(eOperand);

        /*else if (eType == InstructionType.J) {

           //shits will be made

        }*/

        return exec_return;

    }

    private void iFetchIfId(boolean bPCSel, int iNPC){

        ifId.setCIr(iFetch.getInstruction());

        ifId.setiNPC(iFetch.getiPC());

        iFetch.muxIFetch(bPCSel, iNPC);

    }


    private void ifIdDecode () {

        decode.setCIr(ifId.getCIr());

        if (decode.isbWriteRegister())
            writeRegisters();

    }

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

    }

    private void idExExecute () {

        if (idEx.getCir().getEType() == InstructionType.R) {

            execute.setiA(idEx.getiA());
            execute.setiB(idEx.getiB());

        }

        else if (idEx.getCir().getEType() == InstructionType.I) {

            execute.setiA(idEx.getiA());
            execute.setiB(idEx.getiB());

            execute.setiImmediateValue(idEx.getiImmediateValue());


        }

    }


    private void executeExMem () {

        exMem.setCIr(idEx.getCir());

        exMem.setiAluOutput(executeCommand(exMem.getCIr().getEType(), exMem.getCIr().getEOperand()));

        exMem.setiB(execute.getiB());

        exMem.setiDestiny(idEx.getiDestiny());

        exMem.setbControl(idEx.isbControl());

        exMem.setiTarget(exMem.getCIr().getiTarget());

    }

    private void exMemMemory () {

        memory.setbControl(exMem.isbControl());

        memory.setiAluOutput(exMem.getiAluOutput());

        memory.setiBContent(this.AlIRegistersBank.get(exMem.getiB())); //OLHAR ESSA JOSSA DEPOIS


    }

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


   public void run () {

       Queue<PipelineStates> states = new LinkedBlockingQueue<PipelineStates>();
       Instruction instruction;

       states.add(PipelineStates.IFetch);

       int i = 0;

       while (!states.isEmpty()) {

           PipelineStates actual = states.poll();


           switch (actual){

               case IFetch:
                   states.add(PipelineStates.Decode);
                   iFetch.setInstruction(AlInstructionMemory.get(i));
                   iFetchIfId(false, i);
                   if (i < this.AlInstructionMemory.size() - 1) {

                       states.add(PipelineStates.IFetch);
                       i++;

                   }
                   break;

               case Decode:
                   states.add(PipelineStates.Execute);
                   ifIdDecode();
                   decodeIdEx();
                   break;

               case Execute:
                   states.add(PipelineStates.Memory);
                   idExExecute();
                   executeExMem();
                   break;

               case Memory:
                   states.add(PipelineStates.WriteBack);
                   exMemMemory();
                   memoryMemWb();
                   break;

               case WriteBack:
                   memWbWriteBack();
                   System.out.println("Registradores: " + this.AlIRegistersBank);
                   System.out.println();
                   System.out.println();
                   break;


           }




       }



   }

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
                   System.out.println("Registradores: " + this.AlIRegistersBank);
                   System.out.println();
                   System.out.println();
                   break;


           }


       }
   }



}
