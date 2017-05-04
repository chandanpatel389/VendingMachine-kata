
public enum CoinType {
	
	PENNY("Penny", "1", 1),
	DIME("Dime", "2", 10),
	QUARTER("Quarter", "3", 25),
	NICKLE("Nickle", "4", 5);
	
	private String display;
	private String type;
	private int cent;
	
	CoinType (String display, String type, int cent)
	{
		this.display = display;
		this.type = type;
		this.cent = cent;
	}
	
	public String getDisplay() {
		return display;
	}

	public String getType() {
		return type;
	}
	
	public int getCent() {
		return cent;
	}

}
