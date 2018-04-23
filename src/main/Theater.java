package main;

import java.io.*;
import java.util.*;

public class Theater {
	private Seat[][] seats;
	private String filename; 
	
	public Theater(int NbRow, int NbCol)
	{
		seats = new Seat [NbRow][NbCol];
		int i,j;
		for (i=0;i<NbRow;i++)
		{
			for(j=0;j<NbCol;j++)
			{
				seats[i][j]=new Seat(i,j,SeatType.FIRST_CATEGORY,false);
			}

		}
	}
	public Theater(String filename) throws findNotFileException
	{
		this.filename=filename;
		java.io.File fichier = new java.io.File("src/main/"+filename);
		Scanner scan = null;
		try {
			scan = new Scanner(fichier);
			scan.useDelimiter(scan.delimiter()+"|;+");
			int row1= scan.nextInt();
			int col1=scan.nextInt();
			seats= new Seat[row1][col1];
			while(scan.hasNext()) {
				int i,j;
				String symbole = null;
				for (i=0;i<row1;i++)
				{
					for(j=0;j<col1;j++)
					{
						do
						{
							symbole=scan.next();
							symbole.toLowerCase();
						}while(SeatType.getSeatTypeFromSymbole(symbole) == null);
						
						seats[i][j]=new Seat(i,j,SeatType.getSeatTypeFromSymbole(symbole),Character.isUpperCase(symbole.charAt(0)));
					}
				}
			}
		} catch (FileNotFoundException e) {
			// TODO Auto-generated catch block
			System.err.println("non");
		}
		
	}
	
	public void makeReservation(int row, int col) throws InvalidActionException
	{
		if (seats[row][col].isBooked())
		{
			throw new InvalidActionException("'/'!'\'This space is not valid for reservation'/'!''\' " );
		}
		else
		{
			seats[row][col].setBooked(true);
		}
        	
        	
	}
	
	public void cancelReservation(int row, int col) throws InvalidActionException
	{
		if (seats[row][col].isBooked())
		{
			seats[row][col].setBooked(false);
		}
		else
		{
			throw new InvalidActionException("'/'!'\'This space is not valid for cancelation'/'!''\' " );
		}
	}
	
	public String toString()
	{
		StringBuffer buf = new StringBuffer();
		char maj='A';
		int i,j;
		for (i=0;i<seats.length;i++)
		{
			buf.append(maj);
			for(j=0;j<seats[0].length;j++)
			{
				if(seats[i][j].isBooked())
				{
				buf.append(" ").append(seats[i][j].getType().getSymbole().toUpperCase());
				}
				else
				{
					buf.append(" ").append(seats[i][j].getType().getSymbole());
				}
			}
			buf.append("\n");
			maj++;
		}
		buf.append(" ");
		for(i=1;i<seats.length;i++)
		{
			buf.append(" ").append(i);
		}
		return buf.toString();
	}
	
	public void save()
	{
		int i,j;
		FileWriter fw=null;
		try
		{
			if (filename=="theater.csv.bak")
			{
				fw = new FileWriter("src/main/"+filename);
			}
			if (filename!="theater.csv.bak")
			{
				fw = new FileWriter("src/main/"+filename+".bak");
			}
			fw.write(getNbRow()+" "+getNbCol()+"\n");
			for(i=0;i<seats.length;i++)
			{
				for(j=0;j<seats[0].length;j++)
				{
					if(j!=0)
					{
						fw.write(";");
					}
					if (seats[i][j].isBooked())
					{
						fw.write(seats[i][j].getType().getSymbole().toUpperCase().toString());
					}
					else
					{
						fw.write(seats[i][j].getType().getSymbole().toString());
					}
					
					
				}
				fw.write("\n");
			}
			fw.close();
		}
		catch(IOException e)
		{
			System.err.println(e.getMessage());
			System.exit(-1);
		}
	}
	public int getNbRow()
	{
		return seats.length;
	}
	public int getNbCol()
	{
		return seats[0].length;
	}
	
	public SeatType getSymbole(int row, int col)
	{
		return seats[row][col].getType();
	}

}
