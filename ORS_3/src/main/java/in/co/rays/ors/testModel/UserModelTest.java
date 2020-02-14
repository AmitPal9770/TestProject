package in.co.rays.ors.testModel;

import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Iterator;
import java.util.List;

import in.co.rays.ors.dto.UserDTO;
import in.co.rays.ors.model.UserModelHibImpl;
import in.co.rays.ors.model.UserModelInt;

public class UserModelTest {

	private static UserModelInt model = new UserModelHibImpl();
	private static SimpleDateFormat sdf = new SimpleDateFormat("dd/MM/yy");

	public static void main(String[] args) {
		// testadd();
		// testupdate();
		// testdelete();
		// testfindbypk();
		// testfindbyname();
		// testsearch();
		// testlist();

		testauthicate();		
	}


	public static void testauthicate() {

		UserDTO dto = new UserDTO();
		try {
			dto.setLogin("apamitrocks03545@gmail.com");
			dto.setPassword("Amit@123");
			dto = model.authenticate(dto.getLogin(), dto.getPassword());
			if(dto!= null){
				System.out.println("Succesfull login");
			}else{
			System.out.println("fail");
			}
		} catch (Exception e) {
			e.printStackTrace();
		}
	}

	public static void testlist() {
		UserDTO dto = new UserDTO();
		List list = new ArrayList<UserDTO>();
		try {
			list = model.list();
			Iterator<UserDTO> it = list.iterator();
			while (it.hasNext()) {
				dto = (UserDTO) it.next();
				System.out.println(dto.getFirstName());
				System.out.println(dto.getLastName());
			}
		} catch (Exception e) {
			e.printStackTrace();
		}

	}

	public static void testsearch() {
		UserDTO dto = new UserDTO();
		List list = new ArrayList<UserDTO>();
		try {
			dto.setFirstName("rahul");
			;
			list = model.search(dto);
			Iterator<UserDTO> it = list.iterator();
			while (it.hasNext()) {
				dto = (UserDTO) it.next();

				System.out.println(dto.getFirstName());
				System.out.println(dto.getLastName());
			}
		} catch (Exception e) {
			e.printStackTrace();
		}

	}

	public static void testfindbyname() {

		UserDTO dto = new UserDTO();
		try {
			dto = model.findbylogin("rahul@gmial.com");
			System.out.println(dto.getFirstName());
		} catch (Exception e) {
			e.printStackTrace();
		}
	}

	public static void testfindbypk() {
		UserDTO dto = new UserDTO();
		try {
			dto = model.findbypk(1);
			System.out.println(dto.getFirstName());

		} catch (Exception e) {
			e.printStackTrace();
		}

	}

	public static void testdelete() {
		UserDTO dto = new UserDTO();
		try {
			dto.setId(3);
			model.delete(dto);
			System.out.println("deleted");

		} catch (Exception e) {
			e.printStackTrace();
		}
	}

	public static void testupdate() {

		UserDTO dto = new UserDTO();
		try {
			dto.setId(3);
			dto.setFirstName("ankur");
			dto.setLastName("ss");
			dto.setLogin("ankur@gmial.com");
			dto.setPassword("ankur");
			dto.setDob(sdf.parse("12/4/1990"));
			dto.setMobileNo("987654321");
			dto.setRoleId(3L);
			dto.setGender("male");

			model.update(dto);
			System.out.println("update");
		} catch (Exception e) {
			e.printStackTrace();
		}

	}

	public static void testadd() {
		UserDTO dto = new UserDTO();

		try {
			dto.setFirstName("Ankur");
			dto.setLastName("Agrawal");
			dto.setLogin("ankur@gmial.com");
			dto.setPassword("1234");
			dto.setDob(sdf.parse("12/4/1990"));
			dto.setMobileNo("123456712");
			dto.setRoleId(2L);
			dto.setGender("male");

			model.add(dto);
			System.out.println("inserted");
		} catch (Exception e) {
			e.printStackTrace();
		}
	}

}
