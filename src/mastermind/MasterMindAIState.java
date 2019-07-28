package mastermind;

class MasterMindAIState implements MasterMindState
{
	private MasterMind mastermind;
	private MasterMindAI[] ais;
	private MasterMindAI curr_ai;
	
	public MasterMindAIState(MasterMind mm)
	{
		mastermind = mm;
		ais = new MasterMindAI[3];
		ais[0] = new MasterMindAIRandom(mm);
		ais[1] = new MasterMindAIConsistent(mm);
		ais[2] = new MasterMindAIMiniMax(mm);
		curr_ai = ais[0];
	}
	
	public void changeAI(int ai_id)
	{
		curr_ai = ais[ai_id - 1];
	}
	
	public void mouseClicked(int x_click, int y_click)
	{
		if (mastermind.isGameOver())
		{
			mastermind.changeState(mastermind.getMasterMindGameOverState());
			return;
		}
		
		int ai = mastermind.changeAI(x_click, y_click);
		if (ai == 0)
			mastermind.changeState(mastermind.getMasterMindPlayerState());
		else if (ai > 0)
			changeAI(ai);
		else
			mastermind.addGuess(curr_ai.nextGuess());
	}
}