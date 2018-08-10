package Bots.Pokemon;

import java.awt.AWTException;
import java.awt.Robot;
import java.awt.Toolkit;
import java.awt.datatransfer.Clipboard;
import java.awt.datatransfer.DataFlavor;
import java.awt.datatransfer.StringSelection;
import java.awt.datatransfer.UnsupportedFlavorException;
import java.awt.event.KeyEvent;
import java.awt.event.MouseEvent;
import java.io.BufferedReader;
import java.io.BufferedWriter;
import java.io.File;
import java.io.FileReader;
import java.io.FileWriter;
import java.io.IOException;
import java.util.Formatter;

import javax.swing.JOptionPane;


public class Auto
{
	
	protected final String SCheck = "Check", SNew = "New File", SRead = "Read", SWrite = "Write", SClose = "Close";
	
    protected BufferedReader ReadF = null;
    protected BufferedWriter WriteF = null;
    
	
	////////////////////////////////////////////////////////////////////////////////////////
	////////////////////////////////////////////////////////////////////////////////////////
	////////////////////////////////////////////////////////////////////////////////////////
	protected void Auto_Talk(String Message, int Milliseconds, boolean Send_Message)
	{

    	try
		{
    		
    		Robot Rob = new Robot();

	        StringSelection SC = new StringSelection(Message);
	        Clipboard Clip = Toolkit.getDefaultToolkit().getSystemClipboard();
	        
    		
	        Rob.delay(Milliseconds);
	        

	        try
	        {
	        	
				do
				{
					
					Rob.mouseMove(400, 666);
					
				    Rob.mousePress(MouseEvent.BUTTON1_MASK);
					Rob.mouseRelease(MouseEvent.BUTTON1_MASK);
					
				    Rob.keyPress(KeyEvent.VK_A);
				    Rob.keyRelease(KeyEvent.VK_A);
				    
				    Rob.delay(1 * 300);
				    
				    Rob.mousePress(MouseEvent.BUTTON1_MASK);
					Rob.mouseRelease(MouseEvent.BUTTON1_MASK);
					Rob.mousePress(MouseEvent.BUTTON1_MASK);
					Rob.mouseRelease(MouseEvent.BUTTON1_MASK);
					
				    Rob.keyPress(KeyEvent.VK_CONTROL);
				    Rob.keyPress(KeyEvent.VK_C);  
				    Rob.keyRelease(KeyEvent.VK_C);
				    Rob.keyRelease(KeyEvent.VK_CONTROL);
				    
				    Rob.delay(1 * 100);

				    Rob.keyPress(KeyEvent.VK_BACK_SPACE);
				    Rob.keyRelease(KeyEvent.VK_BACK_SPACE);
				    
				}while(!(Clip.getData(DataFlavor.stringFlavor).hashCode() == 97));
				
			}
	        catch (UnsupportedFlavorException e)
	        {
				e.printStackTrace();
			}
	        catch (IOException e)
	        {
				e.printStackTrace();
			}
	        
	        
	        Clip.setContents(SC, null);
	        
	        
	        Rob.keyPress(KeyEvent.VK_CONTROL);
	        Rob.keyPress(KeyEvent.VK_V);  
	        Rob.keyRelease(KeyEvent.VK_V);
	        Rob.keyRelease(KeyEvent.VK_CONTROL);
	        
	        
	        if(Send_Message)
	        {
	        	
		        Rob.keyPress(KeyEvent.VK_ENTER);  
		        Rob.keyRelease(KeyEvent.VK_ENTER);
	        
	        }

		}
    	catch (AWTException E)
		{
			
			E.printStackTrace();
			
		}
		
	}
	
	////////////////////////////////////////////////////////////////////////////////////////
	////////////////////////////////////////////////////////////////////////////////////////
	////////////////////////////////////////////////////////////////////////////////////////
	protected void The_File(String File_Path, String Status)
	{

		switch(Status)
		{

			case SNew:

				// Create the file.
				try
				{
				
					Formatter New_File = new Formatter(File_Path);
					
					New_File.close();
				
				}
				catch(Exception E)
				{
				
					JOptionPane.showMessageDialog(null, "Error Finding File.", "Wheres The File?",
							JOptionPane.INFORMATION_MESSAGE);
				
				}
				
				break;
		
			case SCheck:

				File File = new File(File_Path);
				
				// If the file doesn't exist then make one.
				if (!File.exists())
				{
					
					// Create the file.
					try
					{
					
						Formatter New_File = new Formatter(File_Path);
						
						New_File.close();
					
					}
					catch(Exception E)
					{
					
						JOptionPane.showMessageDialog(null, "Error Finding File.", "Wheres The File?",
								JOptionPane.INFORMATION_MESSAGE);
					
					}
				
				}
				
				break;
				
			case SRead:
				
				try
				{
		            
		            FileReader File_Reader = new FileReader(File_Path);
		            
		            ReadF = new BufferedReader(File_Reader);
		            
				}
				catch(Exception E)
				{
				
					JOptionPane.showMessageDialog(null, "Error Finding File.", "Wheres The File?",
							JOptionPane.INFORMATION_MESSAGE);
				
				}
				
				break;
				
			case SWrite:
				
				try
				{
		            
		            FileWriter File_Writer = new FileWriter(File_Path);
		            
		            WriteF = new BufferedWriter(File_Writer);
		            
				}
				catch(Exception E)
				{
				
					JOptionPane.showMessageDialog(null, "Error Finding File.", "Wheres The File?",
							JOptionPane.INFORMATION_MESSAGE);
				
				}
				
				break;
				
			case SClose:
				
				try
				{
					
					if(WriteF != null)
					{
						
						WriteF.close();
						WriteF = null;
						
					}
					
					if(ReadF != null)
					{
						
						ReadF.close();
						ReadF = null;
						
					}
				
				}
				catch(Exception E)
				{
					
					
				}
				
				break;
			
		
		}
		
	}

}
