import java.io.BufferedWriter;
import java.io.File;
import java.io.FileWriter;
import java.io.IOException;
import java.util.*;


//Name: Henry Fowler
//Course Section:  Intro to Computer Concepts 04:547:01
//Semester:  Spring 2019
//Assignment:  Individual Assignment #6 - Multiplayer Random Number Game With File Output
//Summary: Create a game that generates a random number based on user specifications. 
//        The user then needs to guess the number. The game will tell the user if they 
//        guess too high or low, and tell them their attempts and how many there were 
//        once they win the game. The user will be then prompted to generate another 
//        number to guess, or to end the program. 
//        This version of the program accepts multiple players - at the start of each game, 
//        The user(s) will be prompted for the number of players. The above process will apply 
//        for each player registered. 
//		  After all this is finished, an output log file will be generated for each game played 
//	      with the place of each player and all of their guesses, including correct ones. 

public class MRNGwithOutFile {

	public static void main(String[] args) {
		//List<List<Integer>> totalguesses = new ArrayList<List<Integer>>(); 
		ArrayList<Integer> answers = new ArrayList<Integer>(); 
		int repeat = 1; 
		int lower = 1; 
		int upper = 0; 
		int modify = 0; 
		int j = 0; 
		int k = 0; 
		int allwin = 0; 
		int numplayers = 0; 
		int gamenumber = 0; 
		int dummy = 0; 
		ArrayList<Integer> win = new ArrayList<Integer>(); 
		ArrayList<Integer> playerguesses = new ArrayList<Integer>(); 
		ArrayList<Integer> numfail = new ArrayList<Integer>(); 
		ArrayList<String> names = new ArrayList<String>(); 
		Scanner Keyboard = new Scanner(System.in);
		ArrayList<String> places = new ArrayList<String>(); 
		ArrayList<Integer> placeguess = new ArrayList<Integer>(); 
		ArrayList<Integer> winorder = new ArrayList<Integer>(); 
		System.out.println("Welcome!"); 
		
		while(repeat == 1)
		{
			j = 0; 
			k = 0; 
			allwin = 0; 
			lower = 5; 
			upper = 0; 
		//	totalguesses.clear();
			playerguesses.clear(); 
			answers.clear(); 
			win.clear(); 
			numfail.clear(); 
			names.clear(); 
			places.clear();
			placeguess.clear(); 
			winorder.clear(); 
			System.out.print("Please enter number of players: ");
			numplayers = getInput(dummy); 
			for(int i=0;i<numplayers;i++) {
				System.out.print("Please enter the name of player number " + (i+1) + ": "); 
				names.add(Keyboard.next()); 
				System.out.println("Hello, " + names.get(i) + "!"); 
			}
			while(lower>upper)
			{
				
				System.out.print("Please enter a lower bound for the answers: ");
				lower = getInput(repeat);  
				System.out.print("Please enter an upper bound for the answers: ");
				upper = getInput(repeat); 
				if(lower>upper)
				{
					System.out.println("Lower bound cannot be higher than upper bound!"); 
				}
			}
				for(int i=0;i<numplayers;i++) {
				answers.add(randGen(upper, lower));
			}
			while(allwin!=1) { 
				for(int i=0;i<numplayers;i++) {
					win.add(0); 
					numfail.add(0);  
				}
				for(int i=0;i<numplayers;i++) {
					//totalguesses.add(new ArrayList<Integer>());
					//System.out.println("DEBUG: i is " + i + "."); 
					//System.out.println("DEBUG: win.get(i) is " + win.get(i) + "."); 
					//System.out.println("DEBUG: Answer " + i + " is " + answers.get(i)); 
					if(win.get(i)==0) {
						System.out.print("Please enter a guess, " + names.get(i) + ":");
						playerguesses.add(getInput(repeat)); 
						//System.out.println("DEBUG: First player guess is " + playerguesses.get(0)); 
						if(playerguesses.get(j) == answers.get(i))
						{
							System.out.println(""); 
							System.out.println("You win!");
							win.set(i, 1); 
							places.add(names.get(i)); 
							placeguess.add(numfail.get(i)); 
							winorder.add(i);
						}
						else if(playerguesses.get(j)<answers.get(i))
						{
							System.out.println("Too low! Try again!"); 
							modify = numfail.get(i); 
							modify++; 
							numfail.set(i, modify); 
							win.set(i,0); 
						}
						else if(playerguesses.get(j) > answers.get(i))
						{
							System.out.println("Too high! Try again!"); 
							modify = numfail.get(i); 
							modify++; 
							numfail.set(i, modify); 
							win.set(i,0); 
						}
						else
						{
							answers.set(i, 0); 
							playerguesses.set(i, 0);
							win.set(i,1); 
							System.out.println("Something unexpected happened. Please try again.");
						}
						j++; 
					}
					else if(win.get(i) == 1) {
						System.out.println(names.get(i) + " already won. Skipping.."); 
						playerguesses.add(answers.get(i)); 
						j++;
					}
					
					
					}
				
				for(int i=0;i<numplayers;i++)
				{
					
					if(win.get(i) == 1) {
						allwin = 1; 
					}
					else
					{
						allwin = 0; 
						i = numplayers; 
					}
				}
					
				}
			for(int i=0;i<numplayers;i++) {
				k = 1; 
				j = i; 
			System.out.println("-----------");
			System.out.println(names.get(i) + " had " + (numfail.get(i)) + " wrong answer(s)."); 
			//System.out.println("DEBUG: j is " + j + "."); 
			//System.out.println("DEBUG: guesses.size is " + playerguesses.size()); 
			while(j<playerguesses.size()){
				if(playerguesses.get(j) == answers.get(i))
				{
					k++; 
					j=j+numplayers; 
				}
				else
				{
				System.out.println("Wrong guess number " + k + ": " + playerguesses.get(j)); 
				j = j+numplayers; 
				k++; 
				}
				
			}
			System.out.println("-----------");
			}
			for(int i=0;i<numplayers;i++) {
				System.out.println(places.get(i) + " got place number " + (i+1) + "."); 
			}
			gamenumber++; 
			//for(int i=0; i<winorder.size(); i++)
			//{
			//	System.out.println("DEBUG: Winorder number " + i + " is " + winorder.get(i)); 
		//	}
			try
			{
				
				File outfile = new File("/Users/Henry/Desktop/MP_RNG_ROUND_" + gamenumber + ".txt");
				FileWriter fw = new FileWriter(outfile.getAbsoluteFile());  //Open the file for writing
				BufferedWriter bw = new BufferedWriter(fw);
				if (!outfile.exists()) {
					outfile.createNewFile();
				}
				bw.write("This is the LOG file for round number" + gamenumber + ".");
				bw.append("\n");
				bw.append("\n");
				for(int i = 0; i<numplayers; i++)
				{
					k = 1; 
					j = winorder.get(i);
					bw.append(places.get(i) + " came in place #" + (i+1) + " with " + (placeguess.get(i)+1) + " guesses.");
					//System.out.println("Debug: writing line 1.."); 
					bw.append("\n");
					//System.out.println("Debug: writing break.."); 
					bw.append(places.get(i) + "'s guesses were: "); 
					//System.out.println("Debug: writing line 2.."); 
					
					while(j<playerguesses.size()){
						if(playerguesses.get(j) == answers.get(winorder.get(i)))
						{
							k++; 
							j=j+numplayers; 
						}
						else
						{
						bw.append(playerguesses.get(j) + ","); 
						j = j+numplayers; 
						k++; 
						}
						 
					}
					bw.append(answers.get(i) + "\n"); 
					bw.append("\n");
				}
				
				bw.flush(); 
				bw.close(); 
			}
			catch (IOException e) {
				e.printStackTrace();
			} 
			repeat = tryAgain(); 
			}
			
		}
		
	

	public static int getInput(int acceptNegative)
	
	{
		Scanner Keyboard = new Scanner(System.in);
		int output = 0; 
		boolean errorCheck = false; 
		String input = ""; 
		do
		{
			try 
			{
				input = Keyboard.next(); 
				output = Integer.parseInt(input.trim()); 
				errorCheck = false; 
			} catch(Exception e) { 
				System.out.print("Error! String input is not accepted. " );
				errorCheck = true;
			}
			if(output <= 0 && acceptNegative == 0 && errorCheck == false)
			{
				System.out.print("Error! Negative or zero input is not accepted. " );
				errorCheck = true; 
			}
			if(errorCheck == true)
			{
				System.out.print("Please retry: ");
			}
		} while (errorCheck == true);
		return output; 
	}
	
	
	public static int tryAgain() {
		int YesNoFail = 1; 
		String YesNo = "";
		Scanner Keyboard = new Scanner(System.in);
		int repeat = 1; 
		while(YesNoFail == 1)
		{
			
		System.out.print("Do you want to play again? Answer Yes or No, case sensitive: "); 
		YesNo = Keyboard.next(); 
		//System.out.println("Debugging: YesNo is " + YesNo); 
		if(YesNo.equals("Yes"))
		{
			YesNoFail = 0; 
		}
		else if(YesNo.equals("No"))
		{
			System.out.println("Closing program."); 
			repeat = 0; 
			YesNoFail = 0; 
		}
		else
		{
			System.out.print("Invalid input! "); 
		}
		}
		return repeat; 
	}
	
	public static int randGen(int upper,int lower) {
		int answer = 0; 
		System.out.println("Generating random number..."); 
		Random rand = new Random(); 
		if(upper == lower || upper < lower)
		{
			answer = lower; 
		}
		else
		answer = rand.nextInt(upper-lower) + lower; 
		
		return answer; 
		
	}
	
	
}

