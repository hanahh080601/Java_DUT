import java.io.*;
public class BT4_4 {
	public String nhapchuoi() throws IOException
	{
		InputStreamReader lv = new InputStreamReader(System.in);
		BufferedReader br = new BufferedReader(lv);
		String s = br.readLine();
		return s;
	}
	
	private String upperFirst(String st)
	{
		String st1 = "";
		  boolean found = false;
		  for (int i = 0; i < st.length(); i++) {
			  char c = st.charAt(i);
			  if (!found && c >= 'a' && c <= 'z') {
				  c = (char) (c-32);
				  found = true;
			  }
			  else if (Character.isWhitespace(c)) {
		      found = false;
			  }
			  st1 += c;
		  }
		  return st1;
	}
	
	private String printLine(String st)
	{
		String st1 = "\n ";
		boolean found = false;
		  for (int i = 0; i < st.length(); i++) {
			  char c = st.charAt(i);
			  if (!found) {
				  found = true;
			  }
			  else if (Character.isWhitespace(c)) { 
		      found = false;
		      st1 += "\n";
			  }
			  st1 += c;
		  }
		  return st1;
	}
	
	private String printNA(String st)
	{
		String st1 = "";
		for(int i = 0; i < st.length(); i++)
		{
			char c = st.charAt(i);
			if(c == 'u' || c == 'e' || c == 'o' || c == 'a'|| c == 'i' || c == 'U' 
					|| c == 'E' ||  c == 'O' || c == 'A' || c == 'I')
			{
				st1 += c;
			}
		}
		return st1;
	}
	
	private int Count(String st)
	{
		int count = 0;
		boolean found = false;
		  for (int i = 0; i < st.length(); i++) {
			  char c = st.charAt(i);
			  if (!found) {
				  found = true;
			  }
			  else if (Character.isWhitespace(c)) { 
		      found = false;
		      count += 1;
			  }
		  }
		  return count + 1;
	}
	
	public static void main(String args[])
	{
		BT4_4 bt = new BT4_4();
		String string = "";
		try
		{
			System.out.print("Nhap chuoi ki tu: ");
			string = bt.nhapchuoi();
		}
		catch(Exception e) {}
		System.out.println("BT4.4a. Chuyen ki tu dau tien moi tu thanh chu hoa: " + bt.upperFirst(string));
		System.out.println("BT4.4b. In ra moi tu tren moi dong: " + bt.printLine(string));
		System.out.println("BT4.4c. In ra nguyen am cua chuoi: " + bt.printNA(string));
		System.out.println("BT4.4d. So tu co trong chuoi: " + bt.Count(string));
	}
}
