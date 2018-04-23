package main;

public enum SeatType {
	 SCENE("S", -1.0), OBSTACLE("X", -1.0), FIRST_CATEGORY("a", 125.0), SECOND_CATEGORY("b", 80.0),
	 THIRD_CATEGORY("c",50.0), FOURTH_CATEGORY("d",20.0), FIFTH_CATEGORY("e",10.0);
	
	private String symbole;
	private double price;
	
	private SeatType(String symbole, double price)
	{
		this.symbole=symbole;
		this.price=price;
	}
	
	public static SeatType getSeatTypeFromSymbole(String symbole)
	{
			for(SeatType s:SeatType.values())
			{
				if(s.toString().toLowerCase().equals(symbole.toLowerCase()))
				{
					return s;
				}
			}
			return null;
	}
			
	
	public String getSymbole()
	{
		return symbole;
	}
	
	public double getPrice()
	{
		return price;
	}
	
	@Override
	public String toString()
	{
		return symbole;
	}
}
