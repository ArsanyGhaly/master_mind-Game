package mastermind;

import util.Random;
import java.util.ArrayList;

class MasterMindAIConsistent implements MasterMindAI
{
	private MasterMind mastermind;

	public MasterMindAIConsistent(MasterMind mm)
	{
		mastermind = mm;
	}
	
	public Guess nextGuess()
	{
		Guess temp = null;
		
		if (mastermind.getNumGuesses() == 0)
			temp = getRandomGuess();
		else
		{
			int num_guesses = mastermind.getNumGuesses();
			
			boolean acceptable = false;
			while(!acceptable) 
			{
				Guess guess = mastermind.getGuess(1);
				int guess_black = guess.getNumBlack();
				int	guess_white = guess.getNumWhite();
				
				temp = getRandomGuess();
				int[] temp_results = temp.reportResult(guess);

				for(int i = 1; i <= num_guesses; i++) 
				{
					while(guess_black != temp_results[0] || guess_white != temp_results[1]) 
					{
						temp = getRandomGuess();
						temp_results = temp.reportResult(guess);
					} 

					guess = mastermind.getGuess(i + 1);
				}
				
				boolean done = true;
				
				for (int j = 1; j <= num_guesses; j++) 
				{
					guess = mastermind.getGuess(j);
					guess_black = guess.getNumBlack();
					guess_white = guess.getNumWhite();
					
					temp_results = temp.reportResult(guess);
					
					if (guess_black != temp_results[0] || guess_white != temp_results[1]) 
						done = false;
				}
				
				if (done)
					acceptable = true;
			}
		}
		return temp;
	}
	
	private Guess getRandomGuess()
	{
		Random random = Random.getRandomNumberGenerator();
		Guess guess = new Guess(mastermind.getNumGuesses() + 1);
		Integer color = new Integer(random.randomInt(1, 7));
		guess.addColor(color);
		
		color = new Integer(random.randomInt(1, 7));
		guess.addColor(color);
		
		color = new Integer(random.randomInt(1, 7));
		guess.addColor(color);
		
		color = new Integer(random.randomInt(1, 7));
		guess.addColor(color);
		
		return guess;
	}
}