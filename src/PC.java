import java.util.ArrayList;
import java.util.Scanner;

public class PC {

    public static void main (String [] args) {

        StringtoInstruction stiInstruction = new StringtoInstruction();
        ArrayList<Instruction> instructions = new ArrayList<Instruction>();
        Scanner scanner = new Scanner(System.in);
        String line;
        int nInstructions, i;

        System.out.println("How many instructions do you want to write? (max: 16)");

        nInstructions = scanner.nextInt();
        line = scanner.nextLine();

        for(i = 0 ; i < nInstructions ; i++){
            line = scanner.nextLine();
            stiInstruction.setCommand(line);
            stiInstruction.convert();
            instructions.add(stiInstruction.getInstruction());
        }
        for(; i < 16 ; i++){
            instructions.add(new Instruction(OperandType.NOP));
        }


/*
        instructions.add(new Instruction(1, 3, 4, 0, OperandType.ADDI));
        instructions.add(new Instruction(1, 4, 5, 0, OperandType.ADDI));
        instructions.add(new Instruction(1, 5, 6, 0, OperandType.ADDI));
        instructions.add(new Instruction(1, 6, 7, 0, OperandType.ADDI));
        instructions.add(new Instruction(1, 7, 8, 0, OperandType.ADDI));

        instructions.add(new Instruction(OperandType.NOP));
        instructions.add(new Instruction(OperandType.NOP));
        instructions.add(new Instruction(OperandType.NOP));
        instructions.add(new Instruction(OperandType.NOP));
        instructions.add(new Instruction(OperandType.NOP));


        // instructions.add(new Instruction( 1, 1,1,1,  OperandType.LW));

        /*instructions.add(new Instruction(OperandType.ADD, 3, 4, 9, 0));
        instructions.add(new Instruction(OperandType.MULT, 4, 5, 10, 0));
        instructions.add(new Instruction(OperandType.SUB, 6, 7, 11, 0));
        instructions.add(new Instruction(OperandType.XOR, 8, 9, 12, 0));
        instructions.add(new Instruction(OperandType.DIV, 10, 11, 13, 0));*/

        Processor processor = new Processor(instructions);

        processor.run();

    }

}
