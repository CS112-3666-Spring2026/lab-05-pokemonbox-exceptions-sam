import java.util.InputMismatchException;
import java.util.Scanner;

class Main {
	public static void main(String[] args) {
		// DECLARATION + INITIALIZATION
		int choice = -1;
		boolean tryAgain = true;
		Scanner keyboard = new Scanner(System.in);
		Pokemon[] caught = {
			new Pokemon("Pikachu", "Electric"),
			new Pokemon("Bulbasaur", "Grass", "Poison"),
			new Pokemon("Charmeleon", "Fire"),
			new Pokemon("Squirtle", "Water"),
			new Pokemon("Butterfree", "Bug", "Flying"),
			new Pokemon("Pidgeotto", "Normal", "Flying")
		};

		// WELCOME
		System.out.println("Preloading Pokemon Box...");
		PokemonBox myBox = new PokemonBox(caught);
		System.out.println("...Done!\n");

		System.out.println("---------------------------");
		System.out.println("| Welcome to Pokemon Box! |");
		System.out.println("---------------------------\n");
		System.out.println(myBox);
	// Upgrade the driver (menu program) to handle the following exceptions:

    // InputMismatchException: Allow the user to reenter a valid option (as an integer)
    // IllegalArgumentException: Allow the user to reenter valid data for Pokemon data
    // PokemonAlreadyExistsException: Allow the user to try again,reminding them that our regions sustainability efforts in reducing habitat loss and environmental impacts requires a max of 1 of the same type of Pokémon in the Box.

		//INPUT + PROCESSING + OUTPUT
		do {
			System.out.println("\nMAIN MENU\nWhat would you like to do?");
			System.out.println("\t1) Add a New Pokemon \n\t2) List All Pokemon \n\t3) Exit Program \n");
			System.out.print("Enter choice number> ");
			//MISMATCH EXCEPTION -------------
			boolean mismatch = false;

			while(!mismatch){
				try{
					choice = keyboard.nextInt();
					mismatch = true;
				} catch (InputMismatchException e) {
					System.out.println("ERROR: enter a number: " + e.getMessage());
					keyboard.nextLine();
				}
		    }
			//---------------------------------
			keyboard.nextLine();
			System.out.println();

			if (choice == 1) {
				System.out.println("Enter Pokemon Info to be added:");
				System.out.print("Enter Pokemon Name> ");
				String name = keyboard.nextLine();
				System.out.print("Enter Pokemon Type #1> ");
				String type1 = keyboard.nextLine();
				System.out.print("Enter Pokemon Type #2 (none if no second type)> ");
				String type2 = keyboard.nextLine();
				type2 = (type2.equalsIgnoreCase("none")) ? null : type2;
				Pokemon p = new Pokemon();
				try {
					p = new Pokemon(name, type1, type2);
					tryAgain = false;
				} catch (IllegalArgumentException e) {
					System.out.println(
							"Invalid data for pokemon. Make sure type is a valid pokemon type or second type is none\n"
									+ e.getMessage());
					continue;
				}
			//POKEMON ALREADY EXISTS EXCEPTION ------
				
				
				try {
					myBox.add(p);
					tryAgain = false;
				} catch (PokemonAlreadyExistsException e) {
					System.out.println("You already have a pokemon of that type. Reminder that our regions sustainability efforts in reducing habitat loss and environmental impacts requires a max of 1 of the same type of Pokémon in the Box.\n" + e.getMessage());
					
					continue;
				}
				
			//--------------------------------------- 

				System.out.println("\n" + name + " added!");
			} else if (choice == 2) {
				System.out.println(myBox);
			} else if (choice == 3) {
				keyboard.close();
				tryAgain = false;
			} else {
				System.out.println("Invalid choice, please pick a valid option from the menu.\n");
			}
		} while (tryAgain);

		System.out.println("Thank you for using the Pokemon Box program :D see you later!");
	}
}