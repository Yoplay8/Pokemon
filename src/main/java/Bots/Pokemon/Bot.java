package Bots.Pokemon;


import net.dv8tion.jda.core.entities.Guild;
import net.dv8tion.jda.core.entities.Message;
import net.dv8tion.jda.core.entities.MessageChannel;
import net.dv8tion.jda.core.entities.User;
import net.dv8tion.jda.core.events.message.MessageReceivedEvent;
import net.dv8tion.jda.core.hooks.ListenerAdapter;


public class Bot extends ListenerAdapter
{

	protected Auto Auto_Features = new Auto();
	protected Credentials Cred = new Credentials();
	private Catching Catch = new Catching(Auto_Features);
	
	protected String Auto = "", Reset = "reset";
	private String Delim = "poke";
	
	private String P_One = "1";
	private final String Help = "help", Password = "pass";
	
	private String P_Two = "2";
	private final String Update = "update", Free_Catch = "free", Assist_Catch = "assist";
	
    
	////////////////////////////////////////////////////////////////////////////////////////
	////////////////////////////////////////////////////////////////////////////////////////
	////////////////////////////////////////////////////////////////////////////////////////
    public void Recieve_Commands(MessageReceivedEvent E)
    {
    	
    	User The_User = E.getAuthor();
    	
    	Message The_Message = E.getMessage();
    	String[] Broken_Message = The_Message.getContentStripped().toLowerCase().split(" ");
    	
    	MessageChannel The_Channel = E.getChannel();
    	Guild The_Guild = E.getGuild();
    	
    	
    	if((Broken_Message.length >= 3) && (Broken_Message[0].equals(Delim)))
    	{
    		
    		String Directory = Broken_Message[0] + Broken_Message[1];
    		
    		
	    	if(Directory.equals(Delim + P_One))
	    	{
	    		
	    		The_Message.delete().complete();
	    		
	    		Page_1(Broken_Message, The_User);
	
	    	}
	    	else if(Cred.Get_User().equals(The_User.getId()))
	    	{
	    		
	    		if(Auto != "")
	    			Autoing(The_User, The_Message, The_Channel.getId());
	    		else if(Directory.equals(Delim + P_Two))
	        	{
	    			
	    			The_Message.delete().complete();
	        		
	        		Page_2(Broken_Message, The_Channel.getId(), The_Guild.getId());
	    			
	        	}
	
	    	}
    	
    	}
    	else if(Cred.Get_Pokemon_Bot().equals(The_User.getId()))
    		Autoing(The_User, The_Message, The_Channel.getId());
    	
    }
    
	////////////////////////////////////////////////////////////////////////////////////////
	////////////////////////////////////////////////////////////////////////////////////////
	////////////////////////////////////////////////////////////////////////////////////////
    private void Page_1(String[] Command, User The_User)
    {
    	
    	final String[] Command_Help =
    		{"```md\n",
    			"<poke (page) (command) (info)>\n\n",
    			"Page 1 Credentials\n",
    				"\t" + Password + " (user id)\n",
    				"\t" + Help + "\n\n",
    			"Page 2 Single Autoing\n",
    			"\t" + Update + "\n",
    			"\t" + Free_Catch + "\n",
    			"\t" + Assist_Catch + "\n",
    			"\t" + Reset + "\n",
    			"```"};
    	
    	
		switch(Command[2])
		{
		
			case Password:
				
				if((Command.length > 3) && (Cred.Get_User() == "") && (Cred.Get_Password() == Integer.parseInt(Command[3])))
				{
					
					Cred.Set_User(The_User.getId());
				
					Auto_Features.Auto_Talk(Password, 0, false);
					
				}
					
				break;
				
			case Help:

				The_User.openPrivateChannel().queue((channel) ->
				{
					
					channel.sendMessage(String.join("", Command_Help)).queue();
					
				});
				
				break;
				
		}
    	
    }
    
	////////////////////////////////////////////////////////////////////////////////////////
	////////////////////////////////////////////////////////////////////////////////////////
	////////////////////////////////////////////////////////////////////////////////////////
	private void Autoing(User The_User, Message The_Message, String Channel_Id)
    {
    	
		if(The_Message.getContentStripped().toLowerCase().equals(Delim + P_Two + Reset))
		{
			
			Auto = "";
		
			Auto_Features.Auto_Talk("Stopped", 0, false);
			
		}
		else
		{
		
	    	switch(Auto)
	    	{
	    	
	    		case Free_Catch:

	    			Catch.Screen_Em_All();
	    			
	    			break;
	    			
	    		case Assist_Catch:

	    			if(Cred.Get_Channel().equals(Channel_Id) && Cred.Get_Pokemon_Bot().equals(The_User.getId()) && (The_Message.getEmbeds().size() > 0) && The_Message.getEmbeds().get(0).getTitle().contains("A wild pokémon has appeared!"))
	    			{
	    				
	    				Catch.Pokemon_Count = 1;
	    				
	    				Catch.Screen_Em_All();
	    				
	    			}
	    			
	    			break;
	    			
	    		case Update:
	    			
	    			if(Cred.Get_Channel().equals(Channel_Id))
	    				Catch.Update_Pokemon(The_User, The_Message);
	    			
	    			break;
	    	
	    	}
    	
		}
    	
    }
    
	////////////////////////////////////////////////////////////////////////////////////////
	////////////////////////////////////////////////////////////////////////////////////////
	////////////////////////////////////////////////////////////////////////////////////////
    private void Page_2(String[] Command, String Channel_Id, String Guild_Id)
    {
    	
    	switch(Command[2])
    	{
    	
    		case Free_Catch:
    			
    			Auto = Free_Catch;
    			Catch.Pokemon_Count = 9;
   
    			
    			Catch.Populate_Pokemon_Hash();
    			
    			break;
    			
    		case Assist_Catch:
    			
    			Auto = Assist_Catch;
    			
    			Cred.Set_Channel(Channel_Id);
    			Cred.Set_Guild(Guild_Id);
   		
    			
    			Catch.Populate_Pokemon_Hash();
    			
    			break;
    			
    		case Update:
    			
    			Auto = Update;
    			
    			Cred.Set_Channel(Channel_Id);
    			Cred.Set_Guild(Guild_Id);
    			
    			Auto_Features.Auto_Talk("p!pokedex", 0, true);
    			
    			break;
    	
    	}
    	
    }
	
}
