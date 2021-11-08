import java.awt.*;
import javax.swing.*;
import java.awt.event.*;

/**
 * This class attempts to recreate the Windows calculator with a GUI
 * @author Elstan Bultonsheen
 */
 public class Calculator implements ActionListener {
     
    private JTextField text;
    
    private JTextArea calculations;
    
    private String output = "";
    
    private String temp = "";
    
    private String intemp = "";
    
    private String operator = "";
    
    private double calculate = 0.0;
    
    private int line = 1;
    
    /**
     * This is a constructor method for the Calculator application 
     * where the physical aspects of the calculator is created
     */
    public Calculator (){
        
        //Frame of GUI
        JFrame frame = new JFrame();
        frame.setForeground(Color.WHITE);
        frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        frame.setTitle("Calculator");
        frame.setSize(400,400);
        frame.setLocation(500,250);
        
        
        //Layout of GUI
        frame.setLayout(new BorderLayout());
        
        //Panel to output the users inputs
        JPanel work = new JPanel();
        text = new JTextField(10);
        Font font1 = new Font("SansSerif", Font.BOLD, 40);
        text.setFont(font1);
        text.setEditable(false);
        work.add(text);
        
        //Panel for buttons containing numbers 0-9 and operations
        JPanel numbers = new JPanel(new GridLayout(4,4));
        
        for(int i = 1; i < 10; i++) {
            JButton numButton = new JButton("" + i);
            numButton.addActionListener(this);
            numbers.add(numButton);
            if (i == 3) {
                JButton add = new JButton(" + ");
                add.addActionListener(this);
                //keStroke = KeyStroke.getKeyStroke("shift released =");
                //add.getInputMap().put(keStroke, " + ");
                numbers.add(add);
            }
            if (i == 6) {
                JButton subtract = new JButton(" - ");
                subtract.addActionListener(this);
                numbers.add(subtract);
            }
            if (i == 9) {
                JButton multiply = new JButton(" x ");
                multiply.addActionListener(this);
                numbers.add(multiply);
            }
                
        }
        
        //Other operation buttons
        JButton negate = new JButton("+/-");
        negate.addActionListener(this);
        numbers.add(negate);
        JButton zero = new JButton("0");
        zero.addActionListener(this);
        numbers.add(zero);
        JButton decimal = new JButton(".");
        decimal.addActionListener(this);
        numbers.add(decimal);
        JButton divide = new JButton(" / ");
        divide.addActionListener(this);
        numbers.add(divide);
        
        //Text area to show the calculations
        calculations = new JTextArea("             Calculations:             \n");
        calculations.setEditable(false);
        calculations.setColumns(15);
        
        //Panel containing the equals and clear button
        JPanel eval = new JPanel(new GridLayout(1,1));
        JButton equalButton = new JButton(" = ");
        equalButton.addActionListener(this);
        eval.add(equalButton);
        JButton clearButton = new JButton("Clear");
        clearButton.addActionListener(this);
        eval.add(clearButton);
        
        //Adding panels to frame
        frame.add(work,BorderLayout.NORTH);
        frame.add(numbers,BorderLayout.CENTER);
        frame.add(calculations,BorderLayout.EAST);
        frame.add(eval,BorderLayout.SOUTH);
        
        //Make frame visible
        frame.setVisible(true);
    }
    
    /**
     * This method is used to perform an operation when the button is pressed
     * @param event the event of clicking the button
     */
    public void actionPerformed(ActionEvent event) {
        
        //Recreate button that was pressed 
        JButton button = (JButton) event.getSource();
        
        //Get text on button 
        String input = "";
        input = button.getText();
        
        //Boolean to tell whether button is numeric
        boolean numeric = (!(input.equals(" + ") || input.equals(" - ") || input.equals(" x ") || input.equals(" / ") || input.equals("+/-") || input.equals(" = ") || input.equals("Clear")));
        
        //Add up string of numerical values or set operator equal to the operator pressed
        if (numeric) {
            output += input;
        }
        else if(input.equals("+/-")) {
            output = "-" + intemp;
        }
        else {
            operator = input;
        }
        
        

        
        //Convert string to double if numeric
        if(input.equals(" + ") || input.equals(" - ") || input.equals(" x ") || input.equals(" / ")){
            calculate += Double.parseDouble(output);
            output = "";
        }
        else if(input.equals(" = ") && temp.equals(" + ")) {  //Calculations for addition
            calculate += Double.parseDouble(output);
            output = String.format("%.3f", calculate);
            output = format(output);
        }
        else if(input.equals(" = ") && temp.equals(" - ")) {  //Calculations for subtraction
            calculate -= Double.parseDouble(output);
            output = String.format("%.3f", calculate);
            output = format(output);
        }
        else if(input.equals(" = ") && temp.equals(" x ")) {  //Calculations for muliplication
            calculate *= Double.parseDouble(output);
            output = String.format("%.3f", calculate);
            output = format(output);
        }
        else if(input.equals(" = ") && temp.equals(" / ")) {  //Calculations for division
            calculate /= Double.parseDouble(output);
            output = String.format("%.3f", calculate);
            output = format(output);
        }
        
        //Operations if Clear button is pressed
        if(input.equals("Clear")) {
            output = "";
            calculate = 0.0;
        }
        
        
        //Display output to text field
        text.setText(output);
        
        //Test variable for if the operation prior is the same
        //int x = 0;
        
        //Test whether user enter two operators that are not the same operator
        //if(((operator.equals(temp)) || ((input.equals(" + ") || input.equals(" - ") || input.equals(" x ") || input.equals(" / ")|| input.equals("Clear")) 
        //                         && (temp.equals(" + ") || temp.equals(" - ") || temp.equals(" x ") || temp.equals(" / ") || temp.equals("Clear")))) && !(numeric)) {
            // if clear has nor been pressed then x can be set to one
        //    x = 1;
        //    }
            
        calculations.setLineWrap(true);
        
        //Display all that the user has entered in text area 
        //if(x == 0) {
            if(!(input.equals("Clear") || input.equals("+/-"))) {
                calculations.append(input);
            }
            else if (input.equals("+/-")) {
                try {
                    calculations.replaceRange(output + " ", calculations.getLineEndOffset(line)-(output.length() - 1),calculations.getLineEndOffset(line));
                }
                catch (Exception e) {
                    calculations.append("");
                }
            }
            else {
                calculations.setText("             Calculations:             \n");
            }
        //}
        //else {
        //    if (input.equals("Clear")) {
        //        calculations.setText("             Calculations:             \n");
        //    }
        //    else {
        //        try {
        //            calculations.replaceRange(output, calculations.getLineEndOffset(line)-(output.length() - 1),calculations.getLineEndOffset(line));
        //        }
        //        catch (Exception e) {
        //            calculations.append("");
        //        }
        //    }
        //}
        
        //Format after the equal sign
        if (input.equals(" = ")) {
            String print = "";
            print = String.format("%.3f", calculate);
            calculations.append(format(print) + "\n");
            output = "";
            temp = "";
            calculate = 0.0;
            line++;
        }
        
        //Lets the program know which operator was used before 
        temp = operator;
        
        if(numeric) {
            intemp += input;
        }
        else {
            intemp = "";
        }
        
    }
    /** 
     * This method is used to format the output of the calculator
     * @param s string to format
     * @return formatted string that is formatted
     */
    public String format (String s) {
        //Formatted string
        String formatted = "";
        
        if(s.charAt(s.length() - 3) == '0' && s.charAt(s.length() - 2) == '0' && s.charAt(s.length() - 1) == '0') {
            formatted = String.format("%.0f",calculate);
        }
        else if(s.charAt(s.length() - 2) == '0' && s.charAt(s.length() - 1) == '0') {
            formatted = String.format("%.1f",calculate);
        }
        else if(s.charAt(s.length() - 1) == '0') {
            formatted = String.format("%.2f",calculate);
        }
        else {
            formatted = s;
        }
        
        return formatted;
    }
    
    /**
     * This mehtod is used to run the calculator application
     * @param args (not used)
     */
    public static void main (String[] args) {
        new Calculator();
    }
        
 }