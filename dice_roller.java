import java.util.Scanner;
import java.util.regex.Matcher;
import java.util.regex.Pattern;

class dice_roller {
    String intro =
            "--Dice Roller-- \n" +
            "To Start: \n";

    String opening_line =
            "\t- input the number of rolls of the dice \n" +
            "\t- insert a 'd'\n" +
            "\t- insert the sides of the die (e.g. 6, 8, 20) \n" +
            "\t- type 'quit' to end program \n" +
            "\t- type ? to repeat instructions \n";

    String ending =
            "Thank you for using Dice Roller...";

    void CLI_interpreter(String script) {
        switch (script) {
            case "begin":
                System.out.print(intro + opening_line);
                break;
            case "?":
                System.out.print(opening_line);
                break;
            case "quit":
                System.out.print(ending);
                break;
            default:
                System.out.println("Invalid Line");
                break;
        }
    }

    void begin_program() {
        Scanner sc = new Scanner(System.in);
        CLI_interpreter("begin");
        String pattern = "(\\d*)[d](\\d*)";
        Boolean quit_program = true;

        while (quit_program) {
            int numRolls = 0;
            int numSides = 0;
            String cont_program = sc.next();

            Pattern r  = Pattern.compile(pattern);
            Matcher m = r.matcher(cont_program);

            if (cont_program.equals("quit")) {
                quit_program = false;
                CLI_interpreter(cont_program);
            }
            else if(cont_program.matches(pattern)) {
                if(m.find()) {
                    numRolls = Integer.parseInt(m.group(1));
                    numSides = Integer.parseInt(m.group(2));
                }
                roll_dice(numRolls, numSides);
            }
            else {
                CLI_interpreter(cont_program);
            }
        }
    }

    void roll_dice(int numRolls, int numSides){
        int sumOfDice = 0;
        int[] partsOfSum = new int[numRolls];

        for(int i = 0; i < numRolls; i++){
            int rand = (int) (Math.random() * numSides) + 1;
            partsOfSum[i] = rand;
            sumOfDice = sumOfDice + rand;
        }

        System.out.print(sumOfDice + ": ");

        for(int j = 0; j < numRolls; j++){
            System.out.print(partsOfSum[j] + " ");
        }

        System.out.print("\n");
    }
}
