package mastermind;

class MasterMindPlayerState implements MasterMindState
{
	private MasterMind mastermind;
	
	public MasterMindPlayerState(MasterMind mm)
	{
		mastermind = mm;
	}
	
	public void mouseClicked(int x_click, int y_click)
	{
		if (mastermind.isGameOver())
		{
			mastermind.changeState(mastermind.getMasterMindGameOverState());
			return;
		}
		
		int ai = mastermind.changeAI(x_click, y_click);
		if (ai > 0)
		{
			mastermind.changeState(mastermind.getMasterMindAIState());
			mastermind.changeAI(ai);
		}
		
		int is_color_selected = mastermind.isColorSelected(x_click, y_click);
		if (is_color_selected > 0)
			mastermind.addGuess(is_color_selected);
	}
}