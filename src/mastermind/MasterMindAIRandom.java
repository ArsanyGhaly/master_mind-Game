package mastermind;

import util.Random;

class MasterMindAIRandom implements MasterMindAI
{
	private MasterMind mastermind;
	private Random random;

	public MasterMindAIRandom(MasterMind mm)
	{
		random = random.getRandomNumberGenerator();
		mastermind = mm;
	}
	
	public Guess nextGuess()
	{
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