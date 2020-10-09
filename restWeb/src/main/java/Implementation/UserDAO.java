package Implementation;

import java.awt.List;
import java.util.ArrayList;

import javax.xml.crypto.Data;

import com.openlogix.db.MySql;

public class UserDAO {
	ArrayList<User> users = new ArrayList<User>();

	public UserDAO() {

	}

	public void create(User user) {
		users.add(user);
	}

	public String getUsers() {
		MySql mySql = new MySql("localhost",3306,"root","","checking");
		mySql.Connect();
		String data = "";
		try {
			data = mySql.performQuery("Select * from users");
		} catch (Exception e) {
			e.printStackTrace(); // TODO: handle exception
		}
		return data;
	}
}