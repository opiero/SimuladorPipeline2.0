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

    public void convert () {

        String [] fragments = command.split(" ");

        switch (fragments[0]) {

            case "add":
                this.instruction = new Instruction(OperandType.ADD, convertRegisterStringToInteger(fragments[2]), convertRegisterStringToInteger(fragments[3]),
                        convertRegisterStringToInteger(fragments[1]), 0);
                break;
            case "sub":
                this.instruction = new Instruction(OperandType.SUB, convertRegisterStringToInteger(fragments[2]), convertRegisterStringToInteger(fragments[3]),
                        convertRegisterStringToInteger(fragments[1]), 0);
                break;
            case "mult":
                this.instruction = new Instruction(OperandType.MULT, convertRegisterStringToInteger(fragments[2]), convertRegisterStringToInteger(fragments[3]),
                        convertRegisterStringToInteger(fragments[1]), 0);
                break;
            case "div":
                this.instruction = new Instruction(OperandType.DIV, convertRegisterStringToInteger(fragments[2]), convertRegisterStringToInteger(fragments[3]),
                        convertRegisterStringToInteger(fragments[1]), 0);
                break;
            case "and":
                this.instruction = new Instruction(OperandType.AND, convertRegisterStringToInteger(fragments[2]), convertRegisterStringToInteger(fragments[3]),
                        convertRegisterStringToInteger(fragments[1]), 0);
                break;
            case "or":
                this.instruction = new Instruction(OperandType.OR, convertRegisterStringToInteger(fragments[2]), convertRegisterStringToInteger(fragments[3]),
                        convertRegisterStringToInteger(fragments[1]), 0);
                break;
            case "xor":
                this.instruction = new Instruction(OperandType.XOR, convertRegisterStringToInteger(fragments[2]), convertRegisterStringToInteger(fragments[3]),
                        convertRegisterStringToInteger(fragments[1]), 0);
                break;
            case "sll":
                this.instruction = new Instruction(OperandType.SLL, convertRegisterStringToInteger(fragments[2]), convertRegisterStringToInteger(fragments[3]),
                        convertRegisterStringToInteger(fragments[1]), 0);
                break;
            case "srl":
                this.instruction = new Instruction(OperandType.SRL, convertRegisterStringToInteger(fragments[2]), convertRegisterStringToInteger(fragments[3]),
                        convertRegisterStringToInteger(fragments[1]), 0);
                break;
            case "addi":
                this.instruction = new Instruction(convertRegisterStringToInteger(fragments[2]), convertRegisterStringToInteger(fragments[1]),
                        convertImmediateValueStringToInteger(fragments[3]), 0, OperandType.ADDI);
                break;
            case "andi":
                this.instruction = new Instruction(convertRegisterStringToInteger(fragments[2]), convertRegisterStringToInteger(fragments[1]),
                        convertImmediateValueStringToInteger(fragments[3]), 0, OperandType.ANDI);
                break;
            case "ori":
                this.instruction = new Instruction(convertRegisterStringToInteger(fragments[2]), convertRegisterStringToInteger(fragments[1]),
                        convertImmediateValueStringToInteger(fragments[3]), 0, OperandType.ORI);
                break;
            case "xori":
                this.instruction = new Instruction(convertRegisterStringToInteger(fragments[2]), convertRegisterStringToInteger(fragments[1]),
                        convertImmediateValueStringToInteger(fragments[3]), 0, OperandType.XORI);
                break;
            case "beq":
                this.instruction = new Instruction(convertRegisterStringToInteger(fragments[2]), convertRegisterStringToInteger(fragments[1]),
                        convertImmediateValueStringToInteger(fragments[3]), 0, OperandType.BEQ);
                break;
            case "bne":
                this.instruction = new Instruction(convertRegisterStringToInteger(fragments[2]), convertRegisterStringToInteger(fragments[1]),
                        convertImmediateValueStringToInteger(fragments[3]), 0, OperandType.BNE);
                break;
            case "lw":
                this.instruction = new Instruction(convertRegisterStringToInteger(fragments[3]), convertRegisterStringToInteger(fragments[1]),
                        convertOffsetToString(fragments[2]), 0, OperandType.LW);
                break;
            case "sw":
                this.instruction = new Instruction(convertRegisterStringToInteger(fragments[3]), convertRegisterStringToInteger(fragments[1]),
                        convertOffsetToString(fragments[2]), 0, OperandType.SW);
                break;
            case "j":
                this.instruction = new Instruction(OperandType.J,0, 0);
                break;
            case "nop":
                this.instruction = new Instruction(OperandType.NOP);


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