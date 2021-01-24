import java.util.*; //for Scanner (getting user input)
import java.lang.System; //for System.out.println (printing to console)
public class main {
    public static void main(String[] args){
        Calculator myCalc=new Calculator(); //Creates new calculator object myCalc
        myCalc.setname("Group 9"); //sets myCalc's name to Group 9
        Scanner sc=new Scanner(System.in); //Gets user's choice of A for Add, S for Subtract, M for mult and Q for quit
        String reply=""; //Defined for later use
        float arg1=0;
        float arg2=0;
        float answer=0;
        while (true) { //keep going forever...
            System.out.println("Welcome to the Calculator designed by "+ myCalc.getname());
            System.out.println("Enter A to Add, S to Subtract, M to Multiply, and Q to quit."); //and ask the user what they want.
            reply=sc.nextLine(); //Get the input...
            if(reply.equals("Q")){ //and if they ask to quit, end the loop, which will end the program.
                break;
            }
            try {
            System.out.println("Enter argument 1. "); //Else, get arguments from the user...
            arg1 = Float.parseFloat(sc.nextLine());
            System.out.println("Enter argument 2. ");
            arg2 = Float.parseFloat(sc.nextLine());
            }
            catch(NumberFormatException e){
                System.out.println("Entered number is not a float.");
            }
            switch (reply) { //Based on their reply...
                case "A" -> { //If they ask to add, add the numbers and print.
                    answer = myCalc.addition(arg1, arg2);
                    System.out.println("The sum of argument 1 and argument 2 is " + answer);
                }
                case "S" -> { //If they ask to subtract, subtract the numbers and print the result.
                    answer = myCalc.subtraction(arg1, arg2);
                    System.out.println("The difference of argument 1 and argument 2 is " + answer);
                }
                case "M" -> { //Likewise for multiplication.
                    answer = myCalc.multiplication(arg1, arg2);
                    System.out.println("The product of argument 1 and argument 2 is " + answer);
                }
            }
            //If they don't ask for something defined here, return to the beginning of the loop (i.e. show them the welcome screen again)
        }
    }

}
