import java.util.ArrayList;

public class PC {

    public static void main (String [] args) {

        //GUI gui = new GUI();

        ArrayList<Instruction> instructions = new ArrayList<Instruction>();

        instructions.add(new Instruction(0, 1, 100, 0, OperandType.ADDI, ""));
        instructions.add(new Instruction(0, 1, 100, 0, OperandType.ADDI, ""));
        instructions.add(new Instruction(0, 1, 100, 0, OperandType.ADDI, ""));

        instructions.add(new Instruction(0, 1, 100, 0, OperandType.ADDI, ""));
        instructions.add(new Instruction(1, 2, 100, 0, OperandType.ADDI, ""));
        instructions.add(new Instruction(2, 3, 100, 0, OperandType.ADDI, ""));
        instructions.add(new Instruction(OperandType.NOP, ""));
        instructions.add(new Instruction(OperandType.NOP, ""));
        instructions.add(new Instruction(OperandType.NOP, ""));
        instructions.add(new Instruction(OperandType.NOP, ""));
        instructions.add(new Instruction(OperandType.NOP, ""));


        Processor PC = new Processor(instructions);

        for (int i = 0; i < instructions.size(); i++)
            PC.runNextClock(i);


    }

}
