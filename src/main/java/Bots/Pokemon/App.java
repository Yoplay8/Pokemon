package Bots.Pokemon;


import java.awt.Rectangle;
import java.awt.Robot;
import java.awt.event.KeyEvent;
import java.awt.image.BufferedImage;

import javax.security.auth.login.LoginException;

import net.dv8tion.jda.core.AccountType;
import net.dv8tion.jda.core.JDA;
import net.dv8tion.jda.core.JDABuilder;
import net.dv8tion.jda.core.entities.Member;
import net.dv8tion.jda.core.events.guild.member.GuildMemberJoinEvent;
import net.dv8tion.jda.core.events.message.MessageReceivedEvent;
import net.dv8tion.jda.core.hooks.ListenerAdapter;


public class App extends ListenerAdapter
{

	//vvvvvvvvvvvvvvvvvvvvvvvvvvvvvvvvvvvvvvvvvvvvvvvvvvvvvvvvvvvvvvvvvvvvvvvvvvvvvvvvvvvvvvvvvvv
	//                               GLOBAL VARIABLES BELOW
	//vvvvvvvvvvvvvvvvvvvvvvvvvvvvvvvvvvvvvvvvvvvvvvvvvvvvvvvvvvvvvvvvvvvvvvvvvvvvvvvvvvvvvvvvvvv

	
		Bot Discord_Bot = new Bot();
	
	//^^^^^^^^^^^^^^^^^^^^^^^^^^^^^^^^^^^^^^^^^^^^^^^^^^^^^^^^^^^^^^^^^^^^^^^^^^^^^^^^^^^^^^^^^^^^
	//                               GLOBAL VARIABLES ABOVE
	//^^^^^^^^^^^^^^^^^^^^^^^^^^^^^^^^^^^^^^^^^^^^^^^^^^^^^^^^^^^^^^^^^^^^^^^^^^^^^^^^^^^^^^^^^^^^
	
		
	/////////////////////////////////////////////////////////////////////
	//
	// main - Starts the bot in discord. The bot is used as an interface.
    //
	/////////////////////////////////////////////////////////////////////
	public static void main(String[] args) throws LoginException, InterruptedException
    {
		
		final String Token = "NDY3Mzk1Njg3MzAzMjE3MTY1.DjQphQ.xUrEjIRV4AoOPiQ30TUefyO3hxA";
		
		JDA Jda = new JDABuilder(AccountType.BOT).setToken(Token).buildBlocking();
		
		
		
		Jda.addEventListener(new App());
		 
    }

    ////////////////////////////////////////////////////////////////////////////////////////////////
    //
    // onMessageReceived - Passes all the events to a class the will use OOP to deal with all event.
    //
    ////////////////////////////////////////////////////////////////////////////////////////////////
    @Override
    public void onMessageReceived(MessageReceivedEvent E)
    {
    	Discord_Bot.Recieve_Commands(E);
    }
    
	////////////////////////////////////////////////////////////////////////////////////////
    ////////////////////////////////////////////////////////////////////////////////////////
	////////////////////////////////////////////////////////////////////////////////////////
    @Override
    public void onGuildMemberJoin(GuildMemberJoinEvent E)
    {
    	
    	if(E.getGuild().getId().equals(Discord_Bot.Cred.Get_Guild()))
    	{
    	
    		Discord_Bot.Auto = "";
    		Discord_Bot.Cred.Set_Guild("");
    	
    		Discord_Bot.Auto_Features.Auto_Talk("Hey im getting off. Maybe we can meet and play pokemon go soon.", (4 * 1000), true);
    	
    	}
    	
    }
    
    
    
    
    
    
    
    
    
    //vvvvvvvvvvvvvvvvvvvvvvvvvvvvvvvvvvvvvvvvvvvvvvvvvvvvvvvvvvvvvvvvvvvvvvvvvvvvvvvvvvvvvvvvvvvvvvvvvvvvvvvv
    //vvvvvvvvvvvvvvvvvvvvvvvvvvvvvvvvvvvvvvvvvvvvvvvvvvvvvvvvvvvvvvvvvvvvvvvvvvvvvvvvvvvvvvvvvvvvvvvvvvvvvvvv
    //vvvvvvvvvvvvvvvvvvvvvvvvvvvvvvvvvvvvvvvvvvvvvvvvvvvvvvvvvvvvvvvvvvvvvvvvvvvvvvvvvvvvvvvvvvvvvvvvvvvvvvvv
    //
    //                              EVERY THING BELOW MUST BE ORGANIZED
    //
    //vvvvvvvvvvvvvvvvvvvvvvvvvvvvvvvvvvvvvvvvvvvvvvvvvvvvvvvvvvvvvvvvvvvvvvvvvvvvvvvvvvvvvvvvvvvvvvvvvvvvvvvv
    //vvvvvvvvvvvvvvvvvvvvvvvvvvvvvvvvvvvvvvvvvvvvvvvvvvvvvvvvvvvvvvvvvvvvvvvvvvvvvvvvvvvvvvvvvvvvvvvvvvvvvvvv
    //vvvvvvvvvvvvvvvvvvvvvvvvvvvvvvvvvvvvvvvvvvvvvvvvvvvvvvvvvvvvvvvvvvvvvvvvvvvvvvvvvvvvvvvvvvvvvvvvvvvvvvvv
    
    
    //********************************************************************************************
    private final String Update = "update", Fight = "fight", Buy = "buy", Sell = "sell", Remove = "remove", Trade = "trade";
    private String Toggle = "toggle";
    //********************************************************************************************
    
  
    
    
    
    ////////////////////////////////////// ON HOLD METHODS /////////////////////////////////////////////////////////
    ////////////////////////////////////// ON HOLD METHODS /////////////////////////////////////////////////////////
    ////////////////////////////////////// ON HOLD METHODS /////////////////////////////////////////////////////////
    
    
    //********************************************************************************************
    private Member Player_1 = null, Player_2 = null;
    private String Dueler_Name = "";
    //********************************************************************************************
    
    
    ////////////////////////////////////////////////////////////////////////////////////////
	///////////////////////////////////////////////////////// Redo lost Poke_Sytax function
	////////////////////////////////////////////////////////////////////////////////////////
    /*private void Fight(MessageReceivedEvent E)
    {
    	
    	User The_User = E.getAuthor();
    	MessageChannel The_Channel = E.getChannel();
    	Message The_Message = E.getMessage();
    	Guild The_Guild = E.getGuild();
    	Member The_Member = E.getMember();
    	//Presence The_Presence = E.getAuthor();
    	
    	
    	
    	int KeyCode;
		Robot Rob;
    	
    	char Exclamation = '!', At = '@';
    	
    	String Start_Duel = "Someone fight me", Accept_Duel = "Ill fight you";
    	String Dueler_1 = "duel ", Combat = "use 1";
    	String Role = "Speak";
    	

    	if(The_Guild.getName().equalsIgnoreCase("Dsquad"))
    	{
    		
    		try
    		{
    			
    			Rob = new Robot();


    			//if(The_User.getName().compareTo("Pokécord") == 0)
    			if(The_User.getName().compareTo("Ty_Ler") == 0)

    				//System.out.println(The_Message.getEmbeds().get(0).getFields().get(0).getName());
    				//System.out.println("asd");
    				//System.out.println(The_Member.getOnlineStatus());
    				//The_User.getJDA().asClient().getSettings().getJDA().getPresence().setGame(Game.playing("7 Days To Die"));
    			//OnlineStatus stats = "offline";
    			
    			//Rob.createScreenCapture(new Rectangle(20,20));
	    		if(Player_1 == null && The_Message.getContentRaw().equalsIgnoreCase(Start_Duel))
	    		{
	    			
	    			Player_1 = The_Guild.getMember(The_User);
	    			
	    			The_Guild.getController().addSingleRoleToMember(Player_1, The_Guild.getRolesByName(Role, true).get(0)).queue();

					Rob.delay(500);
					
					
	    			for(int Index = 0; Index < Accept_Duel.length(); Index++)
					{
						
						KeyCode = java.awt.event.KeyEvent.getExtendedKeyCodeForChar((int)Accept_Duel.charAt(Index));

						Rob.keyPress(KeyCode);
						Rob.keyRelease(KeyCode);
						
					}
					
	    			The_Guild.getController().removeSingleRoleFromMember(Player_1, The_Guild.getRolesByName(Role, true).get(0)).queue();
	    			
					Rob.keyPress(KeyEvent.VK_ENTER);
					Rob.keyRelease(KeyEvent.VK_ENTER);
	
	    			
	    			
	    		}
	    		else if(Player_2 == null && The_Message.getContentRaw().equalsIgnoreCase(Accept_Duel))
	    		{
	    			
	    			Player_2 = The_Guild.getMember(The_User);
	    			Dueler_Name = The_User.getName();
	    			
	    			The_Guild.getController().addSingleRoleToMember(Player_2, The_Guild.getRolesByName(Role, true).get(0)).queue();

					Rob.delay(500);
					
					
	    			for(int Index = 0; Index < Dueler_1.length(); Index++)
					{
						
						KeyCode = java.awt.event.KeyEvent.getExtendedKeyCodeForChar((int)Dueler_1.charAt(Index));

						Rob.keyPress(KeyCode);
						Rob.keyRelease(KeyCode);
						
					}

	    			
	    			for(int Index = 0; Index < Dueler_Name.length(); Index++)
					{
						
						KeyCode = java.awt.event.KeyEvent.getExtendedKeyCodeForChar((int)Dueler_Name.charAt(Index));

						Rob.keyPress(KeyCode);
						Rob.keyRelease(KeyCode);
						
					}
	    			
	    			
	    			The_Guild.getController().removeSingleRoleFromMember(Player_2, The_Guild.getRolesByName(Role, true).get(0)).queue();
	    			
					Rob.keyPress(KeyEvent.VK_ENTER);
					Rob.keyRelease(KeyEvent.VK_ENTER);
	    			
	    		}
	    		else if(The_Message.getContentRaw().startsWith("p!duel"))
	    		{
	    			
	    			
	    		}
    		
    		}
    		catch (AWTException e)
    		{
				
				e.printStackTrace();
				
			}

    	}

    		/*if((The_User.getName().equals("Ty_Ler") || The_User.getName().equals("Pokécord")) && The_Channel.getId().equals("468229112105009153"))
        	{
        		
        		//The_Message.delete().queue();

        	}*/
    	
    /*}*/

    
    
    
    ////////////////////////////////////// TESTING METHODS /////////////////////////////////////////////////////////
    ////////////////////////////////////// TESTING METHODS /////////////////////////////////////////////////////////
    ////////////////////////////////////// TESTING METHODS /////////////////////////////////////////////////////////
    
    
	//////////////////////////////////////////////////////////////////////////////////////////////
	//////////////////////////////////////////////////////////////////////////////////////////////
	//////////////////////////////////////////////////////////////////////////////////////////////
    private static double getDifferencePercent(BufferedImage img1, BufferedImage img2)
    {
    	
        int width = img1.getWidth();
        int height = img1.getHeight();
        int width2 = img2.getWidth();
        int height2 = img2.getHeight();
        
        
       //if (width != width2 || height != height2)
            //throw new IllegalArgumentException(String.format("Images must have the same dimensions: (%d,%d) vs. (%d,%d)", width, height, width2, height2));

        
        //System.out.println(width + " " + height);
        //System.out.println(width2 + " " + height2);
        int add_color = 0;
        int add_color2 = 0;
        int hold = 0;
        int color = 0, color2 = 0;

        long diff = 0;
        
        
        for (int y = 0; y < height; y++)
        {
        	
            for (int x = 0; x < width; x++)
            {
            	
            	if(img1.getRGB(x, y) < -1600000 || img2.getRGB(x, y) < -1600000)
        		{
        		
        			//img1.setRGB(x, y, -1);
        			//img2.setRGB(x, y, -1);
        		
        		}

            	
            	hold = pixelDiff(img1.getRGB(x, y), img2.getRGB(x, y));
                
            	//(466,212) (793,552)
            	//if((x >= 466 & x <= 793) && (y <= 212))
            		//System.out.println(img1.getRGB(x, y));
            		
            	//if((img1.getRGB(x, y)>>24) != 0xff)
                {
            		diff += hold;
            		//System.out.println(Hold);
                }
                
                color = img1.getRGB(x, y);
                color2 = img2.getRGB(x, y);
                
                
                if((((color >> 16) & 0xff) > 90 && ((color >> 8) & 0xff) > 50 && (color & 0xff) > 50) &&
                		(((color2 >> 16) & 0xff) > 90 && ((color2 >> 8) & 0xff) > 50 && (color2 & 0xff) > 50))
                {
                	add_color += img1.getRGB(x, y);
                	add_color2 += img2.getRGB(x, y);
                	//diff += hold;
                	//System.out.println(add_color - add_color2);
                }
                
            }
            /*if(y == 1)
            	System.out.println("...");
            else if(y == 2)
            	System.out.println("...");*/
        }
        
        
        long maxDiff = 3L * 255 * width * height;
        //System.out.println(add_color - add_color2);
        //System.out.println(add_color + " " + add_color2);
        //System.out.println(diff);
 
        return 100.0 * diff / maxDiff;
    }
 
	//////////////////////////////////////////////////////////////////////////////////////////////
	//////////////////////////////////////////////////////////////////////////////////////////////
	//////////////////////////////////////////////////////////////////////////////////////////////
    private static int pixelDiff(int rgb1, int rgb2)
    {
    	
        int r1 = (rgb1 >> 16) & 0xff;
        int g1 = (rgb1 >>  8) & 0xff;
        int b1 =  rgb1        & 0xff;
        //System.out.println(r1 + " " + g1 + " " + b1);
        int r2 = (rgb2 >> 16) & 0xff;
        int g2 = (rgb2 >>  8) & 0xff;
        int b2 =  rgb2        & 0xff;
        //if(r2 == 0)
        //System.out.println(r2 + " " + g2 + " " + b2);
        
        return Math.abs(r1 - r2) + Math.abs(g1 - g2) + Math.abs(b1 - b2);
        
    }
    
	//////////////////////////////////////////////////////////////////////////////////////////////
	//////////////////////////////////////////////////////////////////////////////////////////////
	//////////////////////////////////////////////////////////////////////////////////////////////
    private static void Single_Pokemon()
    {
    	
    	try
    	{
    		
    		Robot Rob = new Robot();
    		
    		Rob.delay(4 * 1000);
    		
    		String Key1 = "";
			
			//Rob.delay(3 * 1000);
			
			//***************************************************************************************************
			BufferedImage Pokemon1 = Rob.createScreenCapture(new Rectangle(400, 290, 300, 300));
			int Skip_Color = -13421252;
			int Save_Data = Pokemon1.getWidth() / 2;
			
			
			for (int Y = 0, Flag = 0; Y < Pokemon1.getHeight(); Y++)
	        {
	        	
	            for (int X = 0; X < Save_Data; X++)
	            {
	            	
	            	if((Flag == 0) && (Pokemon1.getRGB(X, Y) != Skip_Color))
	            	{
	            		
	            		Key1 += Integer.toString(X);
	            	
	            		Flag = 1;
	            		
	            	}
	            	else if((Flag == 1) && (Pokemon1.getRGB(X, Y) == Skip_Color))
	            	{
	            		
	            		Key1 += Integer.toString(X);
	            	
	            		Flag = 0;
	            		
	            	}
	            	
	            }

	        }
			//***************************************************************************************************
    		/*Rob.keyPress(KeyEvent.VK_1);
    		Rob.keyRelease(KeyEvent.VK_1);
    		
    		Rob.delay(4 * 1000);*/
    		
    		//***************************************************************************************************
    		
    		
    		String Key2 = "";
    		
    		BufferedImage Pokemon2 = Rob.createScreenCapture(new Rectangle(400, 120, 300, 500));
			int The_Color = -13421252;
			
			
			for (int Y = 0; Y < Pokemon2.getHeight(); Y++)
	        {
	        	
				if(Pokemon2.getRGB(0, Y) == The_Color)
				{
					
					Y += 60;
					
					for(int Y2 = Y + 280, Flag = 0; Y < Y2; Y++)
					{
						
						for(int X = 0; X < Save_Data; X++)
						{
							
			            	if((Flag == 0) && (Pokemon2.getRGB(X, Y) != Skip_Color))
			            	{
			            		
			            		Key2 += Integer.toString(X);
			            	
			            		Flag = 1;
			            		
			            	}
			            	else if((Flag == 1) && (Pokemon2.getRGB(X, Y) == Skip_Color))
			            	{
			            		
			            		Key2 += Integer.toString(X);
			            	
			            		Flag = 0;
			            		
			            	}
							
						}
					
					}
					
					break;
						
				}
					
	        }
			//***************************************************************************************************
    		
    		//System.out.println(Key1.substring(2, Key1.length()).equals(Key2.substring(2, Key2.length())));
    		//System.out.println(Key1.length() + " " + Key2.length());
			
			/*for(int Index = 0; Index < Key1.length()-2; Index++)
			{
				
				if(Key1.charAt(Index) != Key2.charAt(Index))
					System.out.println(Index + " " + Key1.charAt(Index+2) + " " + Key2.charAt(Index));
				
			}*/
			System.out.println(Key2);
			//System.out.println(Key1.compareTo(Key2));
			
    		Rob.keyPress(KeyEvent.VK_2);
    		Rob.keyRelease(KeyEvent.VK_2);
    		
    	}
    	catch(Exception E)
    	{
    		
    		
    	}
    	
    }
    
	//////////////////////////////////////////////////////////////////////////////////////////////
	//////////////////////////////////////////////////////////////////////////////////////////////
	//////////////////////////////////////////////////////////////////////////////////////////////
    private static void Find_Width()
    {
    	
    	try
    	{
    		
    		Robot Rob = new Robot();
    		
    		Rob.delay(3 * 1000);
    		
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
						
						//Catch_Em_All();
						
						break;
						
					}
					else
						Total = 0;
	            
				}
				else if((Flag == 0) && (Pokemon1.getRGB(0, Y) != The_Color))
					Flag = 1;

	        }

			
			Rob.keyPress(KeyEvent.VK_2);
    		Rob.keyRelease(KeyEvent.VK_2);
    		
    	}
    	catch(Exception E)
    	{
    		
    		
    	}
    	
    }
    
	//////////////////////////////////////////////////////////////////////////////////////////////
	//////////////////////////////////////////////////////////////////////////////////////////////
	//////////////////////////////////////////////////////////////////////////////////////////////
    private static void Find_Color()
    {
    	
    	try
    	{
    		
    		Robot Rob = new Robot();
    		
    		Rob.delay(3 * 1000);
    		
    		BufferedImage Pokemon = Rob.createScreenCapture(new Rectangle(410, 270, 5, 5));
    		
    		
    		for (int Y = 0; Y < Pokemon.getHeight(); Y++)
	        {
	        	
	            for (int X = 0; X < Pokemon.getWidth(); X++)
	            	System.out.println(Pokemon.getRGB(X, Y));
	            
	        }
    		
    		Rob.keyPress(KeyEvent.VK_2);
    		Rob.keyRelease(KeyEvent.VK_2);
    		
    	}
    	catch(Exception E)
    	{

    	}
    	
    }
    
	//////////////////////////////////////////////////////////////////////////////////////////////
	//////////////////////////////////////////////////////////////////////////////////////////////
	//////////////////////////////////////////////////////////////////////////////////////////////
    private static void Test_Update()
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

			
			System.out.println(Key);
			
			Mouse.keyPress(KeyEvent.VK_2);
    		Mouse.keyRelease(KeyEvent.VK_2);
			
		}
		catch (Exception e)
		{
			
			e.printStackTrace();
			
		}
		
	}
  
}
