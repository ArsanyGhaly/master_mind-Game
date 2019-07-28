package mastermind;

public class MasterMindDriver
{
   public static void main(String[] args)
   {
	   MasterMind mm = new MasterMind();
	   
	   try 
	   {
		   if (Integer.parseInt(args[0]) > 0)
			   mm.changeState(mm.getMasterMindAIState());
	   } 
	   catch (Exception e) 
	   {
	   }
   }
}
   