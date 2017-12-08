/**
 * Converts an instruction string to a Instruction class
 */
public class StringtoInstruction {

    private String command;

    private Instruction instruction;

    private int convertRegisterStringToInteger (String str) {

        int id = 0;

        String number = "";

        if (str.length() == 3)
            number = "" + str.charAt(2);
        else
            number = "" + str.charAt(2) + str.charAt(3);

        return Integer.parseInt(number);


    }

    private int convertImmediateValueStringToInteger (String str) {

        return Integer.parseInt(str);

    }

    private int convertOffsetToString (String str) {

        return Integer.parseInt(str)/4;

    }


    /**
     * Converts the string to a Instruction
     */
    public void convert () {

        String [] fragments = command.split(" ");

        switch (fragments[0]) {

            case "add":
                this.instruction = new Instruction(OperandType.ADD, convertRegisterStringToInteger(fragments[2]), convertRegisterStringToInteger(fragments[3]),
                        convertRegisterStringToInteger(fragments[1]), 0, command);
                break;
            case "sub":
                this.instruction = new Instruction(OperandType.SUB, convertRegisterStringToInteger(fragments[2]), convertRegisterStringToInteger(fragments[3]),
                        convertRegisterStringToInteger(fragments[1]), 0, command);
                break;
            case "mult":
                this.instruction = new Instruction(OperandType.MULT, convertRegisterStringToInteger(fragments[2]), convertRegisterStringToInteger(fragments[3]),
                        convertRegisterStringToInteger(fragments[1]), 0, command);
                break;
            case "div":
                this.instruction = new Instruction(OperandType.DIV, convertRegisterStringToInteger(fragments[2]), convertRegisterStringToInteger(fragments[3]),
                        convertRegisterStringToInteger(fragments[1]), 0, command);
                break;
            case "and":
                this.instruction = new Instruction(OperandType.AND, convertRegisterStringToInteger(fragments[2]), convertRegisterStringToInteger(fragments[3]),
                        convertRegisterStringToInteger(fragments[1]), 0, command);
                break;
            case "or":
                this.instruction = new Instruction(OperandType.OR, convertRegisterStringToInteger(fragments[2]), convertRegisterStringToInteger(fragments[3]),
                        convertRegisterStringToInteger(fragments[1]), 0, command);
                break;
            case "xor":
                this.instruction = new Instruction(OperandType.XOR, convertRegisterStringToInteger(fragments[2]), convertRegisterStringToInteger(fragments[3]),
                        convertRegisterStringToInteger(fragments[1]), 0, command);
                break;
            case "sll":
                this.instruction = new Instruction(OperandType.SLL, convertRegisterStringToInteger(fragments[2]), convertRegisterStringToInteger(fragments[3]),
                        convertRegisterStringToInteger(fragments[1]), 0, command);
                break;
            case "srl":
                this.instruction = new Instruction(OperandType.SRL, convertRegisterStringToInteger(fragments[2]), convertRegisterStringToInteger(fragments[3]),
                        convertRegisterStringToInteger(fragments[1]), 0, command);
                break;
            case "addi":
                this.instruction = new Instruction(convertRegisterStringToInteger(fragments[2]), convertRegisterStringToInteger(fragments[1]),
                        convertImmediateValueStringToInteger(fragments[3]), 0, OperandType.ADDI, command);
                break;
            case "andi":
                this.instruction = new Instruction(convertRegisterStringToInteger(fragments[2]), convertRegisterStringToInteger(fragments[1]),
                        convertImmediateValueStringToInteger(fragments[3]), 0, OperandType.ANDI, command);
                break;
            case "ori":
                this.instruction = new Instruction(convertRegisterStringToInteger(fragments[2]), convertRegisterStringToInteger(fragments[1]),
                        convertImmediateValueStringToInteger(fragments[3]), 0, OperandType.ORI, command);
                break;
            case "xori":
                this.instruction = new Instruction(convertRegisterStringToInteger(fragments[2]), convertRegisterStringToInteger(fragments[1]),
                        convertImmediateValueStringToInteger(fragments[3]), 0, OperandType.XORI, command);
                break;
            case "beq":
                this.instruction = new Instruction(convertRegisterStringToInteger(fragments[2]), convertRegisterStringToInteger(fragments[1]),
                        convertImmediateValueStringToInteger(fragments[3]), 0, OperandType.BEQ, command);
                break;
            case "bne":
                this.instruction = new Instruction(convertRegisterStringToInteger(fragments[2]), convertRegisterStringToInteger(fragments[1]),
                        convertImmediateValueStringToInteger(fragments[3]), 0, OperandType.BNE, command);
                break;
            case "lw":
                this.instruction = new Instruction(convertRegisterStringToInteger(fragments[3]), convertRegisterStringToInteger(fragments[1]),
                        convertOffsetToString(fragments[2]), 0, OperandType.LW, command);
                break;
            case "sw":
                this.instruction = new Instruction(convertRegisterStringToInteger(fragments[3]), convertRegisterStringToInteger(fragments[1]),
                        convertOffsetToString(fragments[2]), 0, OperandType.SW, command);
                break;
            case "j":
                this.instruction = new Instruction(OperandType.J,0, 0, command);
                break;
            case "nop":
                this.instruction = new Instruction(OperandType.NOP, command);


        }
    }

    public String getCommand() {
        return command;
    }

    public void setCommand(String command) {
        this.command = command;
    }

    public Instruction getInstruction() {
        return instruction;
    }


}
