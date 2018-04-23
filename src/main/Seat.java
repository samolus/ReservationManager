package main;

import java.io.Serializable;

public class Seat implements Serializable {
	private int row;
	private int col;
	private SeatType type;
	private boolean isBooked;
	
	public Seat(int row, int col, SeatType type, boolean isBooked)
	{
		this.row=row;
		this.col=col;
		this.type=type;
		this.isBooked=isBooked;
	}
	
	public SeatType getType()
	{
		return type;
	}
	
	public boolean isBooked()
	{
		return isBooked;
	}

	public void setBooked(boolean isBooked)
	{
		this.isBooked=isBooked;
	}
	
	public String toString()
	{
		char rowLetter = (char) ('A'+row);
		return ""+rowLetter+col;
	}

}
