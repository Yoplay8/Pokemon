package Bots.Pokemon;

import java.awt.Rectangle;
import java.awt.Robot;
import java.awt.image.BufferedImage;
import java.io.IOException;
import java.util.HashMap;
import java.util.LinkedList;
import java.util.List;
import java.util.Map;
import java.util.Queue;

import net.dv8tion.jda.core.entities.Message;
import net.dv8tion.jda.core.entities.User;
import net.dv8tion.jda.core.entities.MessageEmbed.Field;


public class Catching
{

	private Auto Auto_Features = null;
	
	private String File_Path = "Pokemon/Pokemon.txt";
	
	private Map<String, String> Pokemon_List = new HashMap<String, String>();
	
    private Queue<String> Updating_Pokemon = new LinkedList<String>();
    private int Page_Count = 1, Correct_Page = 1;
    private String Getting_Pokemon = "";
    //private boolean Delete_Messages = false;
    private String Found_Pokemon = "";
    //private int Click_Count = 0;
	protected int Pokemon_Count = 0;
	private int Catch_Time = 3 * 1000;
	private double Up_Catch = 0.3, Down_Catch = 0.1;
    
    
	
    ////////////////////////////////////////////////////////////////////////////////////////
	////////////////////////////////////////////////////////////////////////////////////////
	////////////////////////////////////////////////////////////////////////////////////////
    protected void Update_Pokemon(User The_User, Message The_Message)
    {

    	String C_Getting = "p!pokedex", C_Updating = "p!info";
    	String P_Getting = "Showing ", P_Updating = "Base stats for ";

	
		if(The_Message.getEmbeds().get(0).getTitle().equals(P_Updating + Getting_Pokemon + "."))
			Update_File(Getting_Pokemon); 
		else if(The_Message.getEmbeds().get(0).getFooter().getText().startsWith(P_Getting + Integer.toString(Correct_Page)))
		{
			
			String[] Poke_Page = The_Message.getEmbeds().get(0).getFooter().getText().split(" ");
			
			Correct_Page += 20;
			Page_Count++;
			
			The_Message.delete().complete();
			
			Get_Pokemon(The_Message.getEmbeds().get(0).getFields());
			
			
			if(Poke_Page[1].substring(Poke_Page[1].length() - Poke_Page[3].length()).equals(Poke_Page[3]))
				Auto_Features.Auto_Talk((C_Getting + " " + Integer.toString(Page_Count)), 0, true);
			else
			{
			
				Getting_Pokemon = Updating_Pokemon.remove();
				
				Auto_Features.The_File(File_Path, Auto_Features.SNew);
				Auto_Features.The_File(File_Path, Auto_Features.SWrite);
				
				Auto_Features.Auto_Talk((C_Updating + " " + Getting_Pokemon), 0, true);
			
			}
			
		}			
    	
    }
    
	////////////////////////////////////////////////////////////////////////////////////////
	////////////////////////////////////////////////////////////////////////////////////////
	////////////////////////////////////////////////////////////////////////////////////////
	private void Get_Pokemon(List<Field> Pokemons)
	{
		
		for(Field Pokemon: Pokemons)
			Updating_Pokemon.add(Pokemon.getName());
		
	}
    
	////////////////////////////////////////////////////////////////////////////////////////
	////////////////////////////////////////////////////////////////////////////////////////
	////////////////////////////////////////////////////////////////////////////////////////
	private void Update_File(String Pokemon_Name)
	{
		
		try
		{
			
			Robot Mouse = new Robot();
			String Key = "";
			
			Mouse.delay(3 * 1000);
			
			BufferedImage Pokemon = Mouse.createScreenCapture(new Rectangle(400, 290, 300, 300));
			int Skip_Color = Pokemon.getRGB(0, 0);
			int Save_Data = Pokemon.getWidth() / 2;
			
			
			for (int Y = 0, Flag = 0; Y < Pokemon.getHeight(); Y++)
	        {
	        	
	            for (int X = 0; X < Save_Data; X++)
	            {
	            	
	            	if((Flag == 0) && (Pokemon.getRGB(X, Y) != Skip_Color))
	            	{
	            		
	            		Key += Integer.toString(X);
	            	
	            		Flag = 1;
	            		
	            	}
	            	else if((Flag == 1) && (Pokemon.getRGB(X, Y) == Skip_Color))
	            	{
	            		
	            		Key += Integer.toString(X);
	            	
	            		Flag = 0;
	            		
	            	}
	            	
	            }

	        }

			
			Auto_Features.WriteF.write(Key + "@" + Pokemon_Name + "\n");
			
		}
		catch (Exception e)
		{
			
			e.printStackTrace();
			
		}

		
		if(!Updating_Pokemon.isEmpty())
		{
		
			Getting_Pokemon = Updating_Pokemon.remove();
			
			Auto_Features.Auto_Talk(("p!info" + " " + Getting_Pokemon), 0, true);
		
		}
		else
		{
			
			Auto_Features.The_File(File_Path, Auto_Features.SClose);
			
			Auto_Features.Auto_Talk("poke 2 reset", 0, true);

		}
		
	}
	
	////////////////////////////////////////////////////////////////////////////////////////
	////////////////////////////////////////////////////////////////////////////////////////
	////////////////////////////////////////////////////////////////////////////////////////
	protected void Populate_Pokemon_Hash()
	{
		
		if(!Knows_Pokemon())
		{
			
			Auto_Features.The_File(File_Path, Auto_Features.SCheck);
			Auto_Features.The_File(File_Path, Auto_Features.SRead);
			
			String Line = "";
			String[] Split = null;
			
			
			try
			{
				
				while((Line = Auto_Features.ReadF.readLine()) != null)
				{
					
					Split = Line.split("@");
					
					if(Pokemon_List.containsKey(Split[0]))
						Auto_Features.Auto_Talk((Split[0] + " collided with " + Pokemon_List.get(Split[0])), 0, false);
					
					Pokemon_List.put(Split[0], Split[1].toLowerCase());
					
				}
				
			}
			catch (IOException e)
			{
				
				e.printStackTrace();
				
			}
			
			Auto_Features.The_File(File_Path, Auto_Features.SClose);
		
		}

		
		Auto_Features.Auto_Talk(("I know " + Integer.toString(Pokemon_List.size()) + "pokemon."), 0, false);
		
	}
	
	////////////////////////////////////////////////////////////////////////////////////////
	////////////////////////////////////////////////////////////////////////////////////////
	////////////////////////////////////////////////////////////////////////////////////////
	private boolean Knows_Pokemon()
	{
		return (Pokemon_List.size() > 0) ? true : false;
	}

	////////////////////////////////////////////////////////////////////////////////////////
	////////////////////////////////////////////////////////////////////////////////////////
	////////////////////////////////////////////////////////////////////////////////////////
	protected void Poke_Em_All(int Y_Coord)
	{
		
		String Poke_Command = "p!catch ";
		
		
		try
		{
			
			Robot Mouse = new Robot();
			String Key = "";
			

			BufferedImage Pokemon = Mouse.createScreenCapture(new Rectangle(400, Y_Coord + 120, 300, 300));
			int Skip_Color = Pokemon.getRGB(0, 0);
			int Save_Data = Pokemon.getWidth() / 2;
			
			
			for (int Y = 0, Flag = 0; Y < Pokemon.getHeight(); Y++)
	        {
	        	
	            for (int X = 0; X < Save_Data; X++)
	            {
	            	
	            	if((Flag == 0) && (Pokemon.getRGB(X, Y) != Skip_Color))
	            	{
	            		
	            		Key += Integer.toString(X);
	            	
	            		Flag = 1;
	            		
	            	}
	            	else if((Flag == 1) && (Pokemon.getRGB(X, Y) == Skip_Color))
	            	{
	            		
	            		Key += Integer.toString(X);
	            	
	            		Flag = 0;
	            		
	            	}
	            	
	            }
	            
	        }			

			String Temp_Pokemon = Found_Pokemon;
			
			
			if(Pokemon_List.containsKey(Key))
				Found_Pokemon = Pokemon_List.get(Key);

			
			
			if(Temp_Pokemon != Found_Pokemon)
			{
				
				Pokemon_Count--;
				
				Auto_Features.Auto_Talk((Poke_Command + Pokemon_List.get(Key)), Catch_Time, true);
			
			}
				
		}
		catch (Exception e)
		{
			e.printStackTrace();
		}
		
	}
	 
	////////////////////////////////////////////////////////////////////////////////////////
	////////////////////////////////////////////////////////////////////////////////////////
	////////////////////////////////////////////////////////////////////////////////////////
	protected void Screen_Em_All()
	{

		try
		{
			
			Robot Rob = new Robot();
			

			while(Pokemon_Count > 0)
			{
				
				Rob.delay(1 * 300);
	    		
	    		int Total = 0;
	    		
	    		BufferedImage Pokemon1 = Rob.createScreenCapture(new Rectangle(400, 120, 340, 500));
				int The_Color = -13421252;
				int The_Value = -1149228506;
				
				
				for (int Y = 0, Flag = 1; Y < Pokemon1.getHeight(); Y++)
		        {
		        	
					if((Flag == 1) && (Pokemon1.getRGB(0, Y) == The_Color) && (Y + 30 < Pokemon1.getHeight()))
					{
						
						Flag = 0;
						
						for(int Hold_Y = Y + 30; Y < Hold_Y; Y++)
						{
							
				            for (int X = 0; X < Pokemon1.getWidth(); X++)
				            	Total += Pokemon1.getRGB(X, Y);
			            
						}
						
						
						if(Total == The_Value)
						{
							
							Poke_Em_All(Y + 30);
							
							break;
							
						}
						else
							Total = 0;
		            
					}
					else if((Flag == 0) && (Pokemon1.getRGB(0, Y) != The_Color))
						Flag = 1;
	
		        }
			
			}

		}
		catch(Exception E)
		{
			E.printStackTrace();
		}

	}
	
	////////////////////////////////////////////////////////////////////////////////////////
	////////////////////////////////////////////////////////////////////////////////////////
	////////////////////////////////////////////////////////////////////////////////////////
	Catching(Auto Auto_Features)
	{
		this.Auto_Features = Auto_Features;
	}
	
}
