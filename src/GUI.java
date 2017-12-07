import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.ItemEvent;
import java.awt.event.ItemListener;
import java.util.ArrayList;
import java.util.logging.Level;

public class GUI {
    // Window
    private JFrame window;

    // Panels for organization of the many components used
    private JPanel panel1;
    private JPanel panel2;

    // Labels that tells the user what is each parameter that he/she is setting on the interface
    private JLabel l1, l2, l3;

    // Java Combo Boxes to show user the options that he/she have for each parameter on the interface
    private JComboBox<Integer> size;
    private JComboBox<String> operandTypes;
    private JComboBox<Integer> r1, r2, r3;

    // Buttons for actions
    private JButton Set;
    private JButton addButton;
    private JButton runButton;

    // Java Text Area for showing user the instructions he/she already selected
    private JTextArea instructions;

    // Integers for knowing how many instructions the user wants to pass for the processor and to count how many he/she already selected
    private int nInstructions, counter;

    // Vectors of Integers for Java Combo Boxes initialization
    private Integer vi[] = {0, 1, 2, 3, 4, 5, 6, 7, 8, 9, 10 ,11, 12, 13, 14, 15}, vi2[] = {0, 4, 8, 12, 16, 20, 24, 28, 32, 36, 40, 44, 48, 52, 56, 60, 64};

    // Tools needed for the proper execution of the pipeline, an array list of instructions, a StringtoInstruction to convert a string into an instruction and the processor
    private StringtoInstruction stiInstruction;
    private ArrayList<Instruction> alIinstructions;
    private Processor processor;

    // Constructor, allocating space for the tools needed and setting the counter with zero
    public GUI(){
        this.counter = 0;
        stiInstruction = new StringtoInstruction();
        alIinstructions = new ArrayList<Instruction>();
        instructionSizeSelection();
    }

    public ArrayList<Instruction> getAlIinstructions() {
        return alIinstructions;
    }

    public void setnInstructions(int nInstructions) {
        this.nInstructions = nInstructions;
    }

    // Function that, using the data selected by the user on the interface, generates the String of the struction selected, that after will be converted to an Instruction
    public String createInstructionString(){
        String instructionI = operandTypes.getSelectedItem().toString();

        if(r1.isVisible()){
            if(l1.getText().equals("Rd: ") || l1.getText().equals("Rt: ") || l1.getText().equals("Rs: "))
                instructionI += " $r" + r1.getItemAt(r1.getSelectedIndex());
            else if(l1.getText().equals("Value: "))
                instructionI += " " + r1.getItemAt(r1.getSelectedIndex());
        }

        if(r2.isVisible()){
            if(l2.getText().equals("Rs: ") || l2.getText().equals("Rt: "))
                instructionI += " $r" + r2.getItemAt(r2.getSelectedIndex());
            else if(l2.getText().equals("Offset: "))
                instructionI += " " + r2.getItemAt(r2.getSelectedIndex());
        }

        if(r3.isVisible()){
            if(l3.getText().equals("Rs: ") || l3.getText().equals("Rt: "))
                instructionI += " $r" + r3.getItemAt(r3.getSelectedIndex());
            else if(l3.getText().equals("Offset: ") || l3.getText().equals("Immediate Value: "))
                instructionI += " " + r3.getItemAt(r3.getSelectedIndex());
        }

        return instructionI;
    }

    // Function to fill or refill the Java Combo Boxes with the proper values for each parameter
    public void fillJComboBox(int box, int fillOption){
        if(box == 1){
            if(fillOption == 1){
                r1.removeAllItems();
                for(int i = 0 ; i < vi.length ; i++)
                    r1.addItem(vi[i]);
            }
            else if(fillOption == 2){
                r1.removeAllItems();
                for(int i = 0 ; i < vi2.length ; i++)
                    r1.addItem(vi2[i]);
            }
        }
        else if(box == 2){
            if(fillOption == 1){
                r2.removeAllItems();
                for(int i = 0 ; i < vi.length ; i++)
                    r2.addItem(vi[i]);
            }
            else if(fillOption == 2){
                r2.removeAllItems();
                for(int i = 0 ; i < vi2.length ; i++)
                    r2.addItem(vi2[i]);
            }
        }
        else if(box == 3){
            if(fillOption == 1){
                r3.removeAllItems();
                for(int i = 0 ; i < vi.length ; i++)
                    r3.addItem(vi[i]);
            }
            else if(fillOption == 2){
                r3.removeAllItems();
                for(int i = 0 ; i < vi2.length ; i++)
                    r3.addItem(vi2[i]);
            }
        }
    }

    // Function that sets the first interface, the one that the user chooses how many instructions he/she wants to pass
    public void instructionSizeSelection(){
        window = new JFrame("How many instructions will you add ?");

        window.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        window.setSize(400, 100);
        window.setLocationRelativeTo(null); // Centralize the window

        panel1 = (JPanel)window.getContentPane();
        panel1.setLayout(new BorderLayout());

        Integer vi3[] = {1, 2, 3, 4, 5, 6, 7, 8, 9, 10 ,11, 12, 13, 14, 15, 16}; // Another vector for Java Combo Box initialization
        size = new JComboBox<>(vi3);

        panel1.add(size, BorderLayout.CENTER);

        Set = new JButton("Set");
        panel1.add(Set, BorderLayout.AFTER_LAST_LINE);

        // Action Listener for the button, so that when the user makes the click, the number of instructions he/she chose will be setted and the next interface will be shown
        Set.addActionListener(new ActionListener(){
            @Override
            public void actionPerformed(ActionEvent e){
                setnInstructions(size.getItemAt(size.getSelectedIndex()));
                window.setVisible(false);
                instructionsSelection();
            }
        });

        window.setVisible(true); // properly shows the window
    }


    public void instructionsSelection(){
        window = new JFrame("Instruction Selection");

        window.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        window.setSize(800, 400);
        window.setLocationRelativeTo(null);

        panel2 = (JPanel)window.getContentPane();
        GroupLayout layout = new GroupLayout(panel2);
        panel2.setLayout(layout);
        layout.setAutoCreateGaps(true);
        layout.setAutoCreateContainerGaps(true);

        String vs[] = {"add", "sub", "mult", "div", "and", "or", "xor", "sll", "srl", "addi", "andi", "ori", "xori", "beq", "bne", "lw", "sw", "j", "nop"};
        operandTypes = new JComboBox<>(vs);

        l1 = new JLabel("Rd: ");
        l2 = new JLabel("Rs: ");
        l3 = new JLabel("Rt: ");

        r1 = new JComboBox<>(vi);
        r2 = new JComboBox<>(vi);
        r3 = new JComboBox<>(vi);

        addButton = new JButton("Add Instruction");
        runButton = new JButton("Run!");
        runButton.setVisible(false);

        instructions = new JTextArea();
        instructions.setSize(400, 500);
        instructions.setEditable(false);
        instructions.setBackground(Color.WHITE);

        // Action Listener for the button add, so that when the user makes the click, an instruction with the parameter chosen by him/her will be created and passed for the array list of instructions, and to limitate te user with the number of instructions he/she previously chose
        addButton.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                if(counter < nInstructions) {
                    String instructionI = createInstructionString();
                    stiInstruction.setCommand(instructionI);
                    stiInstruction.convert();
                    alIinstructions.add(stiInstruction.getInstruction());
                    instructions.append(instructionI + System.lineSeparator());
                    counter++;
                }
                if(counter == nInstructions) {
                    for (; counter < 16; counter++) {
                        alIinstructions.add(new Instruction(OperandType.NOP));
                    }
                    runButton.setVisible(true);
                }
            }
        });

        // Action Listener for the button run, so that when the user makes the click the processor runs with the instructions he/she chose
        runButton.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                window.setVisible(false);
                processor = new Processor(getAlIinstructions());
                processor.run();
            }
        });

        // Item Listener for the Java Combo Box, so that when the user select what kind of instruction he/she wants to set the parameters shown change for the parameters necessary for the instruction selected
        operandTypes.addItemListener(new ItemListener() {
            @Override
            public void itemStateChanged(ItemEvent e) {
                Object i = operandTypes.getSelectedItem();
                if("add".equals(i) || "sub".equals(i) || "div".equals(i) || "mult".equals(i) || "and".equals(i) || "or".equals(i) || "xor".equals(i) || "sll".equals(i) || "srl".equals(i)){
                    l1.setText("Rd: ");
                    l2.setText("Rs: ");
                    l3.setText("Rt: ");
                    fillJComboBox(1, 1);
                    fillJComboBox(2, 1);
                    fillJComboBox(3, 1);
                    r1.setVisible(true);
                    r2.setVisible(true);
                    r3.setVisible(true);
                }
                else if("addi".equals(i) || "andi".equals(i) || "ori".equals(i) || "xori".equals(i)){
                    l1.setText("Rt: ");
                    l2.setText("Rs: ");
                    l3.setText("Immediate Value: ");
                    fillJComboBox(1, 1);
                    fillJComboBox(2, 1);
                    fillJComboBox(3, 1);
                    r1.setVisible(true);
                    r2.setVisible(true);
                    r3.setVisible(true);
                }
                else if("lw".equals(i) || "sw".equals(i)){
                    l1.setText("Rt: ");
                    l2.setText("Offset: ");
                    l3.setText("Rs: ");
                    fillJComboBox(1, 1);
                    fillJComboBox(2, 2);
                    fillJComboBox(3, 1);
                    r1.setVisible(true);
                    r2.setVisible(true);
                    r3.setVisible(true);
                }
                else if("beq".equals(i) || "bne".equals(i)){
                    l1.setText("Rs: ");
                    l2.setText("Rt: ");
                    l3.setText("Offset: ");
                    fillJComboBox(1, 1);
                    fillJComboBox(2, 1);
                    fillJComboBox(3, 2);
                    r1.setVisible(true);
                    r2.setVisible(true);
                    r3.setVisible(true);
                }
                else if("j".equals(i)){
                    l1.setText("Value: ");
                    l2.setText(null);
                    l3.setText(null);
                    fillJComboBox(1, 1);
                    r1.setVisible(true);
                    r2.setVisible(false);
                    r3.setVisible(false);

                }
                else if("nop".equals(i)){
                    l1.setText(null);
                    l2.setText(null);
                    l3.setText(null);
                    r1.setVisible(false);
                    r2.setVisible(false);
                    r3.setVisible(false);
                }

            }
        });

        // Layout setup
        layout.setVerticalGroup(
                layout.createSequentialGroup()
                .addGroup(layout.createParallelGroup(GroupLayout.Alignment.BASELINE)
                        .addComponent(operandTypes)
                        .addComponent(instructions, 50, GroupLayout.DEFAULT_SIZE, 500)
                )
                .addGroup(layout.createParallelGroup(GroupLayout.Alignment.BASELINE)
                        .addComponent(l1)
                        .addComponent(r1)
                )
                .addGroup(layout.createParallelGroup(GroupLayout.Alignment.BASELINE)
                        .addComponent(l2)
                        .addComponent(r2)
                )
                .addGroup(layout.createParallelGroup(GroupLayout.Alignment.BASELINE)
                        .addComponent(l3)
                        .addComponent(r3)
                )
                .addComponent(addButton)
                .addComponent(runButton)
        );
        layout.setHorizontalGroup(
                layout.createSequentialGroup()
                .addGroup(layout.createParallelGroup(GroupLayout.Alignment.LEADING)
                        .addComponent(operandTypes)
                        .addGroup(layout.createSequentialGroup()
                                .addComponent(l1)
                                .addComponent(r1)
                        )
                        .addGroup(layout.createSequentialGroup()
                                .addComponent(l2)
                                .addComponent(r2)
                        )
                        .addGroup(layout.createSequentialGroup()
                                .addComponent(l3)
                                .addComponent(r3)
                        )
                        .addComponent(addButton)
                        .addComponent(runButton))

                .addComponent(instructions, 400, GroupLayout.DEFAULT_SIZE, 400)
        );

        window.setVisible(true);

    }

}