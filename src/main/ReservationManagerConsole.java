package main;

import java.io.FileNotFoundException;
import java.io.IOException;
import java.util.LinkedList;
import java.util.Scanner;

public class ReservationManagerConsole { 
	
	 public static void main(String[] args) throws findNotFileException 
	    {
		 String firstname;
		 String lastname;
		 String address;
		 LinkedList <Client> clients = new <Client>LinkedList();
		 int nbRow=5;
		 int nbCol=5;
		 int i=0;
		 char dep='A';
		 int inc=0;
		 int max=0;
		 Theater theater = null;
		 //Theater theater= new Theater(nbRow,nbCol);
		 System.out.println("Welcome to the Reservation Manager");
		 String filenameClient="src/main/client.bak";
		 LinkedList<Client> list =null;
		 try
		 {
			java.io.File fichierC = new java.io.File(filenameClient);
			if(fichierC.exists())
			{
				list = Serializer.loadFromFile(filenameClient);
				for(inc=0;inc<list.size();inc++)
				{
					clients.add(list.get(inc));
				}
				for(Client c :clients)
				{
					if (max<c.getId())
					{
						max=c.getId();
					}
				}
				Client.setCurrentId(max);
				System.out.println("File download client.bak");
				
				
			}
				
		 }
		 catch(ClassNotFoundException ex) 
		 { // This exception occures when trying to load a class that no longer exist
			 ex.printStackTrace();
			 System.exit(-1);
			 } 
		 catch(IOException ex) 
		 {
			 ex.printStackTrace();
			 System.exit(-1);
			 }
		 try
		 {
			java.io.File fichier = new java.io.File("src/main/theater.csv.bak");
			Scanner scan = null;
				try 
				{
					scan = new Scanner(fichier);
					theater = new Theater("theater.csv.bak");
					System.out.println("File download csv.bak");
				}
			catch (FileNotFoundException e) 
			 {
				 System.err.println(e);
				 theater = new Theater("theater.csv");
				 System.out.println("File download .csv");
			 }
			
		 }
		 catch (findNotFileException e) 
		 {
			 System.err.println(e);
		 }
		    while (true)
	        {
	        	System.out.println("What do you want to do ? [h for help]");
	        	Scanner scan = new Scanner(System.in);
	        	String choice = scan.next();
	        	if(choice.length() > 0) 
	        	{
	        		switch(choice)
	        		{
	        		case "h": System.out.print("h: print this help screen\nq: quit the program\nmr: make a reservation\ncr :cancel a reservation\nst :show theater\nlc : show the client list\nac : add a new client\nrc : remove a client\n");break;
	        		case "mr": 
	        			try 
	        			{
	        				for(Client c:clients) 
		        			{ // Print the potential selection
		        				System.out.println("Client n°"+c.getId()+" : "+c.getLastname()+" "+c.getFirstname()+" ("+c.getAddress()+")");
		        			}
		        			System.out.println("Please enter the id of the client to be removed or -1 to cancel the action."); // Ask for an id
	        				int id;
	        				Client selectedClient = null; // Make the hypothesis of erroneous id
	        				try 
	        				{
	        					id = scan.nextInt(); // Get the id from the user
		        				for(Client c:clients) 
		        				{ // Search for a matching id
		        					if(c.getId() == id) 
		        					{ // If found, stop searching
		        						selectedClient = c;
		        						break;
		        					}
		        				}
	        				}
	        				catch(RuntimeException ex) 
	        				{ // Catch potential error
		        				System.err.println("This is not a valid number !");
		        				scan.nextLine(); // Remove what provoke the error
		        			}
	        				System.out.println(theater.toString());
							Scanner scanPlace = new Scanner(System.in);
							System.out.println("What row ?");
				        	String Row = scanPlace.next();
				        	if (dep==Row.charAt(0))
				        	{
				        		i=0;
				        	}
				        	else
				        	{
				        		while(dep!=Row.charAt(0))
				        		{
				        			dep++;
				        			i++;
				        		}
				        	}
				        	
				        	System.out.println("What col ?");
				        	int Col = scanPlace.nextInt();
				        	theater.makeReservation(i, Col-1);
				        	selectedClient.addSeat(new Seat(i,Col,theater.getSymbole(i, Col),true));
				        	dep='A';
				        	i=0;
				        	System.out.println(theater.toString());
				        	try {
								Serializer.saveToFile("src/main/client.bak", clients);
								System.out.println("enter");
							} catch (IOException e) {
								// TODO Auto-generated catch block
								e.printStackTrace();
							}
				        	
						} 
	        			catch (InvalidActionException e) 
	        			{
	        				System.err.println(e+"\n");
						}
	        			break;		
	        		case "cr": 
	        			try 
	        			{
	        				for(Client c:clients) 
		        			{ // Print the potential selection
		        				System.out.println("Client n°"+c.getId()+" : "+c.getLastname()+" "+c.getFirstname()+" ("+c.getAddress()+")");
		        			}
		        			System.out.println("Please enter the id of the client to be removed or -1 to cancel the action."); // Ask for an id
	        				int id;
	        				Client selectedClient = null; // Make the hypothesis of erroneous id
	        				try 
	        				{
	        					id = scan.nextInt(); // Get the id from the user
		        				for(Client c:clients) 
		        				{ // Search for a matching id
		        					if(c.getId() == id) 
		        					{ // If found, stop searching
		        						selectedClient = c;
		        						break;
		        					}
		        				}
	        				}
	        				catch(RuntimeException ex) 
	        				{ // Catch potential error
		        				System.err.println("This is not a valid number !");
		        				scan.nextLine(); // Remove what provoke the error
		        			}
	        				System.out.println(theater.toString());
	        				Scanner scanPlace = new Scanner(System.in);
							System.out.println("What row ?");
				        	String Row = scanPlace.next();
				        	if (dep==Row.charAt(0))
				        	{
				        		i=0;
				        	}
				        	else
				        	{
				        		while(dep!=Row.charAt(0))
				        		{
				        			dep++;
				        			i++;
				        		}
				        	}
				        	
				        	System.out.println("What col ?");
				        	int Col = scanPlace.nextInt();
				        	theater.cancelReservation(i, Col-1);
				        	System.out.println(list);
				        	selectedClient.removeSeat(new Seat(i,Col,theater.getSymbole(i, Col),false));
				        	System.out.println(list);
				        	dep='A';
				        	i=0;
				        	System.out.println(theater.toString());
				        	try {
								Serializer.saveToFile("src/main/client.bak", clients);
								System.out.println("enter");
							} catch (IOException e) {
								// TODO Auto-generated catch block
								e.printStackTrace();
							}
						} 
	        			catch (InvalidActionException e) 
	        			{
							// TODO Auto-generated catch block
	        				System.err.println(e);
						}
	        			break;
	        		case "st":
	        			System.out.println(theater.toString());
	        			break;
	        		case "lc":
	        			if (clients.isEmpty())
	        			{
	        				System.out.print("Client List :");
	        				System.out.println("[]");
	        			}
	        			else
	        			{
	        				System.out.println("Client List :");
		        			for(Client c:clients)
		        			{ // Print the potential selection
		        				
		        				System.out.println("["+c.getLastname()+" "+c.getFirstname()+" "+c.getSeats()+"]");
		        			}
	        			}
	        			

	        			break;
	        		case "ac":
	        			try
	        			{
		        			System.out.print("Lastname : ");
		        			Scanner scanLast= new Scanner(System.in);
		        			lastname=scanLast.next();
		        			System.out.print("Firstname : ");
		        			firstname=scanLast.next();
		        			System.out.print("Address : ");
		        			address=scanLast.next();
		        			clients.add(new Client(lastname,firstname,address));
		        			Serializer.saveToFile("src/main/client.bak", clients);
		        			System.out.println(lastname+" "+firstname+" was added with sucess !");
		        			break;
	        			}
	        			catch(IOException ex)
	        			{
	        				ex.printStackTrace();
	        				System.exit(-1);
	        			}
	        			
	        		case "rc":
	        			try
	        			{
	        				for(Client c:clients) 
		        			{ // Print the potential selection
		        				System.out.println("Client n°"+c.getId()+" : "+c.getLastname()+" "+c.getFirstname()+" ("+c.getAddress()+")");
		        			}
		        			System.out.println("Please enter the id of the client to be removed or -1 to cancel the action."); // Ask for an id
	        				int id;
	        				Client selectedClient = null; // Make the hypothesis of erroneous id
	        				try 
	        				{
	        					id = scan.nextInt(); // Get the id from the user
		        				for(Client c:clients) 
		        				{ // Search for a matching id
		        					if(c.getId() == id) 
		        					{ // If found, stop searching
		        						selectedClient = c;
		        						break;
		        					}
		        				}
		        				if(selectedClient != null) 
		        				{
		        					clients.remove(selectedClient);
		        					Serializer.saveToFile("src/main/client.bak", clients);
		        				}
		        				if(selectedClient == null && id != -1) 
		        				{
		        				System.err.println("Invalid selection");
		        				}
		        			} 
	        				catch(RuntimeException ex) 
	        				{ // Catch potential error
		        				System.err.println("This is not a valid number !");
		        				scan.nextLine(); // Remove what provoke the error
		        			}

		        			break;
	        			}
	        			catch(IOException ex)
	        			{
	        				ex.printStackTrace();
	        				System.exit(-1);
	        			}
	        		case "sr":
	        			for(Client c:clients) 
						{ // Print the potential selection
							System.out.println("Client n°"+c.getId()+" : "+c.getLastname()+" "+c.getFirstname()+" ("+c.getAddress()+")");
						}
						System.out.println("Please enter the id of the client to be removed or -1 to cancel the action."); // Ask for an id
						int id;
						Client selectedClient = null; // Make the hypothesis of erroneous id
						try 
						{
							id = scan.nextInt(); // Get the id from the user
							for(Client c:clients) 
							{ // Search for a matching id
								if(c.getId() == id) 
								{ // If found, stop searching
									selectedClient = c;
									break;
								}
							}
							System.out.println(selectedClient.getExplicitedCost().toString());
						}
						
						catch(RuntimeException ex) 
						{ // Catch potential error
							System.err.println("This is not a valid number !");
							scan.nextLine(); // Remove what provoke the error
						}
		        			break;
	        		case "q": 
	        			System.out.print("Bye bye !\n");
	        			theater.save();
	        			System.exit(0);scan.close();break;
	        		}
	        	}
	    }
	  }

}
