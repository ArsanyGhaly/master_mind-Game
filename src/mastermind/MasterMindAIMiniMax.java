package mastermind;

import java.util.ArrayList;
import java.util.List;

class MasterMindAIMiniMax implements MasterMindAI
{
	private MasterMind mastermind;
	private ArrayList<Guess> all_guesses;
	private ArrayList<Guess> possible_guesses;
	private ArrayList<int[]> possible_combinations;
	
	public MasterMindAIMiniMax(MasterMind mm)
	{
		mastermind = mm;
		all_guesses = new ArrayList<Guess>();
		possible_guesses = new ArrayList<Guess>();
		generateAllPossibleGuesses();
		
		possible_combinations = new ArrayList<int[]>();
		int[] curr_combination = new int[2];
		
		// w
		curr_combination[0] = 0;
		curr_combination[1] = 1;
		possible_combinations.add(curr_combination);
		
		// ww
		curr_combination[0] = 0;
		curr_combination[1] = 2;
		possible_combinations.add(curr_combination);
		
		// www
		curr_combination[0] = 0;
		curr_combination[1] = 3;
		possible_combinations.add(curr_combination);
		
		// wwww
		curr_combination[0] = 0;
		curr_combination[1] = 4;
		possible_combinations.add(curr_combination);
		
		// b
		curr_combination[0] = 1;
		curr_combination[1] = 0;
		possible_combinations.add(curr_combination);
		
		// bb
		curr_combination[0] = 2;
		curr_combination[1] = 0;
		possible_combinations.add(curr_combination);
		
		// bbb
		curr_combination[0] = 3;
		curr_combination[1] = 0;
		possible_combinations.add(curr_combination);
		
		// bbbb
		curr_combination[0] = 4;
		curr_combination[1] = 0;
		possible_combinations.add(curr_combination);
		
		// bw
		curr_combination[0] = 1;
		curr_combination[1] = 1;
		possible_combinations.add(curr_combination);
		
		// bww
		curr_combination[0] = 1;
		curr_combination[1] = 2;
		possible_combinations.add(curr_combination);
		
		// bwww
		curr_combination[0] = 1;
		curr_combination[1] = 3;
		possible_combinations.add(curr_combination);
		
		// bbw
		curr_combination[0] = 2;
		curr_combination[1] = 1;
		possible_combinations.add(curr_combination);
		
		// bbbw
		curr_combination[0] = 3;
		curr_combination[1] = 1;
		possible_combinations.add(curr_combination);
		
		// bbww
		curr_combination[0] = 2;
		curr_combination[1] = 2;
		possible_combinations.add(curr_combination);
		
		// no feedback
		curr_combination[0] = 0;
		curr_combination[1] = 0;
		possible_combinations.add(curr_combination);
	}
	
	// 7^4 = 2401
	// 1 = red
	// 2 = green
	// 3 = blue
	// 4 = purple
	// 5 = yellow
	// 6 = black
	// 7 = white
	private void generateAllPossibleGuesses()
	{
		int guess_id = mastermind.getNumGuesses() + 1;
		for (int one = 1; one <= 7; one++)
		{
			for (int two = 1; two <= 7; two++)
			{
				for (int three = 1; three <= 7; three++)
				{
					for (int four = 1; four <= 7; four++)
					{
						// Removing the first guess (1324)
						if (one == 1 && two == 3 && three == 2 && four == 4) continue;
						
						Guess guess = new Guess(guess_id);
						Integer color = new Integer(one);
						guess.addColor(color);
						
						color = new Integer(two);
						guess.addColor(color);
						
						color = new Integer(three);
						guess.addColor(color);
						
						color = new Integer(four);
						guess.addColor(color);
						
						all_guesses.add(guess);
						possible_guesses.add(guess);
					}
				}
			}
		}
	}
	
	public Guess nextGuess()
	{
		Guess guess = null;
		
		if (mastermind.getNumGuesses() == 0)
		{
			guess = new Guess(mastermind.getNumGuesses() + 1);
			Integer color = new Integer(1);
			guess.addColor(color);
			
			color = new Integer(3);
			guess.addColor(color);
			
			color = new Integer(2);
			guess.addColor(color);
			
			color = new Integer(4);
			guess.addColor(color);
		}
		else
		{
			Guess last_guess = mastermind.getGuess(mastermind.getNumGuesses());
			int last_black = last_guess.getNumBlack();
			int last_white = last_guess.getNumWhite();
			
			for (int i = (possible_guesses.size() - 1); i >= 0; i--)
			{
				Guess currGuess = possible_guesses.get(i);
				int[] results = currGuess.reportResult(last_guess);
				
				if (last_black != results[0] || last_white != results[1]) {
					possible_guesses.remove(i);
					
				}
			}
			
			int count = 0;
			int smallest = 5000;
			int largest = 0;
			int pos = 0;
			
			for (int i = 0; i < possible_guesses.size(); i++)
			{
				Guess all_guess = possible_guesses.get(i);
				
				for (int k = 0; k < possible_combinations.size(); k++)
				{
					int[] curr_combination = possible_combinations.get(k);
					
					for (int j = 0; j < all_guesses.size(); j++)
					{
						Guess possible_guess = all_guesses.get(j);
						int[] results = possible_guess.reportResult(all_guess);
						
						if (curr_combination[0] != results[0] || curr_combination[1] != results[1])
						{
							count++;
						}
					}
					
					if (count < smallest)
					{
						smallest = count;
						count = 0;
					}
				}
				
				if (smallest > largest)
				{
					largest = smallest;
					guess = all_guess;
					pos = i;
					smallest = 5000;
				}
			}
			
			List<Integer> colors = guess.getGuessColorIDs();
			
			guess = new Guess(mastermind.getNumGuesses() + 1);
			guess.addColor(colors.get(0));
			guess.addColor(colors.get(1));
			guess.addColor(colors.get(2));
			guess.addColor(colors.get(3));
			
			possible_guesses.remove(pos);
		}
		
		return guess;
	}
}