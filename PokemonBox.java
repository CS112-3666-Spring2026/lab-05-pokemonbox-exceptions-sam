public class PokemonBox {

	// CONSTANT VARIABLES
	public static final int DEFAULT_CAPACITY = 10;

	// INSTANCE VARIABLES
	private Pokemon[] caught;
	private int numCaught;

	// CONSTRUCTORS
	public PokemonBox(Pokemon[] caught) {
		if(caught == null || caught.length == 0) {
			throw new IllegalArgumentException("ERROR: Invalid Pokemon array provided to PokemonBox.");
		}
		this.numCaught = caught.length;
		this.caught = this.deepCopyArray(caught, this.numCaught*2);
	}

	public PokemonBox() {
		this.caught = new Pokemon[DEFAULT_CAPACITY];
		this.numCaught = 0;
	}

	// ACCESSOR/GETTER METHODS
	//returns -1 if not found
	public int getLocation(String pokemonName) {
		int location = -1, count = 0;
		//loop until we reach end of array or we find item
		while(count < this.numCaught && location == -1) {
			if(this.caught[count].getName().equalsIgnoreCase(pokemonName)) {
				//found pokemon!
				location = count;
			} else {
				//didnt find pokemon, setup for next loop
				count++;
			}
		}
		return location;
	}
// Upgrade getPokemon method to throw IndexOutOfBoundsException for illegal location value
public Pokemon getPokemon(int location) {
	if (location < 0) {
		throw new IndexOutOfBoundsException("ERROR: location value is out of bounds");
		}
		return this.caught[location];
	}

	public int getNumCaught() {
		return this.numCaught;
	}

	public boolean isEmpty() {
		return this.numCaught == 0;
	}

	public boolean hasPokemon(String pokemonName) {
		return this.getLocation(pokemonName) != -1;
	}

// Upgrade add method to throw PokemonAlreadyExistsException when the name of the provided Pokemon already exists in the array
	// MUTATOR/SETTER METHODS
	public void add (Pokemon newPoke) throws PokemonAlreadyExistsException {
		//new pokemon,  add to partially filled array
		//but first check if box is full
		if(this.numCaught == this.caught.length) {
			//if full, then grow array *2 and copy contents over
			this.caught = this.deepCopyArray(this.caught, this.numCaught*2);
		}

		for (int i = 0; i < this.numCaught; i++) {
			if (this.caught[i].getName().equals(newPoke.getName())) {
				System.out.println("caught:  " + this.caught[i].getName() + "\nNew Pokemon:  " + newPoke.getName());
				throw new PokemonAlreadyExistsException("ERROR: Already a pokemon of the same name in box.");
			}
		} //then add new caught pokemon
		this.caught[this.numCaught] = new Pokemon(newPoke);
		this.numCaught++;
		
	}
	
	// OTHER REQUIRED METHODS
	public String toString() {
		if(this.isEmpty()) {
			return "This box is empty";
		} else {
			String all = "\t01. " + this.caught[0].toRow();
			for(int i = 1; i < this.numCaught; i++) {
				all += String.format("%n\t%02d. %s", i+1, this.caught[i].toRow());
			}

			return String.format("This box has %d Pokemon, which are:%n%s",
				this.numCaught, all);
		}
	}

	private Pokemon[] deepCopyArray(Pokemon[] p, int newLength) {
		Pokemon[] deepCopy = new Pokemon[newLength];
		
		for(int i = 0; i < p.length; i++) {
			deepCopy[i] = new Pokemon(p[i]);
		}

		return deepCopy;
	}
}





