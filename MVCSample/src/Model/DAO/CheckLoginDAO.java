package Model.DAO;
import java.util.ArrayList;

import Model.BEANS.*;

public class CheckLoginDAO {
	public boolean isExistUser(String userName, String password) {
		return true;
	}
	
	public ArrayList<Wife> getWifeList(String userName) {
		ArrayList<Wife> result = new ArrayList<Wife>();
		Wife wife = new Wife();
		wife.setName("Nguyen Thi No");
		wife.setAddress("Lo gach");
		wife.setAlive(false);
		result.add(wife);
		
		wife = new Wife();
		wife.setName("Chi Pheo");
		wife.setAddress("Ho ca");
		wife.setAlive(true);
		result.add(wife);
		return result;
	}
}
