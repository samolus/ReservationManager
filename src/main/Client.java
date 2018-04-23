package main;

import java.io.Serializable;
import java.util.LinkedList;
import java.util.List;

public class Client implements Serializable{
	
	private static int currentId;
	private int id;
	private String lastname;
	private String firstname;
	private String address;
	public LinkedList<Seat> seats = new LinkedList<Seat>();
	
	public Client (String lastname,String firstname,String address)
	{
		this.lastname=lastname;
		this.firstname=firstname;
		this.address=address;
		id=currentId++;
	}
	
	public int getId()
	{
		return id;
	}
	
	public String getFirstname ()
	{
		return firstname;
	}
	
	public String getLastname ()
	{
		return lastname;
	}
	
	public String getAddress ()
	{
		return address;
	}
	
	public String toString()
	{
		StringBuffer buf = new StringBuffer();
		buf.append(firstname).append(" ").append(lastname);
		return buf.toString();
	}
	
	public String getFullString()
	{
		StringBuffer buf = new StringBuffer();
		buf.append(id).append(" ").append(firstname).append(" ").append(lastname).append(" ").append(address);
		return buf.toString();
	}
	public static void setCurrentId(int id)
	{
		currentId=id+1;
	}
	public void addSeat(Seat s)
	{
		seats.add(s);
	}
	public void removeSeat(Seat s)
	{
		seats.remove(s);
	}
	public List<Seat> getSeats()
	{
		return seats;
	}
	public double getReservationCost()
	{
		for (SeatType s : SeatType.values())
		{
			return s.getPrice();
		}
		return 0.0;
	}
	public String getExplicitedCost()
	{
		String exPlicitedCost = "";
		for(Seat s : seats){
			exPlicitedCost += s.toString()+ " ("+s.getType().getPrice()+"€)\n";
		}
		exPlicitedCost += "Total : "+getReservationCost()+"€";
		
		return exPlicitedCost;
	}
}
