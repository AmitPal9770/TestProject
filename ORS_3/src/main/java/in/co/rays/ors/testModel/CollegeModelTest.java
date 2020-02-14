package in.co.rays.ors.testModel;

import java.util.ArrayList;
import java.util.Iterator;
import java.util.List;

import in.co.rays.ors.dto.CollegeDTO;
import in.co.rays.ors.model.CollegeModelHibImpl;
import in.co.rays.ors.model.CollegeModelInt;

public class CollegeModelTest {

	public static CollegeModelInt model = new CollegeModelHibImpl();

	public static void main(String[] args) {

		// testadd();
		// testdelete();
		// testupdate();
		// testfindbypk();
		// testfindbyname();
		// testsearch();
		testlist();
		
	}

	public static void testlist() {
		CollegeDTO dto = new CollegeDTO();
		List list = new ArrayList<CollegeDTO>();
		
		
		dto.setId(5);
		try{
			list = model.list();
			Iterator<CollegeDTO> it = list.iterator();
			while(it.hasNext()){
				dto = it.next();
				
				System.out.println(dto.getName());
				System.out.println(dto.getAddress());
				System.out.println(dto.getCity());
				System.out.println(dto.getState());
			}
		}catch(Exception e){
			e.printStackTrace();
		}
	}

	public static void testsearch() {
		CollegeDTO dto = new CollegeDTO();
		List list = new ArrayList<CollegeDTO>(); 
		try{
			
			dto.setId(3);
			
			list =  model.search(dto);
			Iterator<CollegeDTO> it = list.iterator();
			while (it.hasNext()) {
				 dto= (CollegeDTO) it.next();
				 System.out.println(dto.getName());
				 System.out.println(dto.getCity());
				 System.out.println(dto.getState());
				 System.out.println(dto.getAddress());
				 
			}
			}catch(Exception e){
				e.printStackTrace();
				
			}
	}

	public static void testfindbyname() {
		CollegeDTO dto = new CollegeDTO();

		try {
			dto = model.findbyname("Shri kamal GUjratiii");
			System.out.println(dto.getId());
			System.out.println(dto.getState());
			System.out.println(dto.getCity());
			System.out.println(dto.getMobileNo());
		} catch (Exception e) {
			e.printStackTrace();
		}
	}

	public static void testfindbypk() {
		CollegeDTO dto = new CollegeDTO();

		try {
			dto = model.findbypk(3);
			System.out.println(dto.getName());
			System.out.println(dto.getState());
			System.out.println(dto.getCity());
			System.out.println(dto.getMobileNo());
		} catch (Exception e) {
			e.printStackTrace();
		}
	}

	public static void testupdate() {

		CollegeDTO dto = new CollegeDTO();
		try {
			dto.setId(2);
			dto.setName("kamal");
			dto.setCity("pune");

			model.update(dto);
			System.out.println("............>>>");

		} catch (Exception e) {
			System.out.println("...........<<<<");
			e.printStackTrace();
		}
	}

	public static void testdelete() {
		CollegeDTO dto = new CollegeDTO();
		try {
			dto.setId(4);
			model.delete(dto);
			System.out.println("Data deleted");
		} catch (Exception e) {
			System.out.println("Exception ...........>>>>");
			e.printStackTrace();
		}
	}

	public static void testadd() {
		CollegeDTO dto = new CollegeDTO();
		try {
			// dto.setId(1);
			dto.setName("Shri kamal GUjratiii");
			dto.setAddress("Shri bhaeru nagarrr");
			dto.setCity("Bhopalllooo");
			dto.setState("UPppanda");
			dto.setMobileNo("8893228690");

			model.add(dto);
			System.out.println("Data inserted succesfully");
		} catch (Exception e) {
			System.out.println("Data not inserted succesfully");
			e.printStackTrace();

		}
	}

}
