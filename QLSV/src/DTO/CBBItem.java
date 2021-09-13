package DTO;

public class CBBItem {
	public int Value;
	public String Text;
	public String toString()
	{
		return Text;				
	}
	
	public CBBItem() {}

	public CBBItem(String text, int value) {
		Text = text;
		Value = value;
	}
}
