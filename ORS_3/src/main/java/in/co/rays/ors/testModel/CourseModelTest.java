package in.co.rays.ors.testModel;

import java.util.ArrayList;
import java.util.Iterator;
import java.util.List;

import in.co.rays.ors.dto.CourseDTO;
import in.co.rays.ors.model.CourseModelHibImpl;
import in.co.rays.ors.model.CourseModelInt;

public class CourseModelTest {

	public static CourseModelInt model = new CourseModelHibImpl();

	public static void main(String[] args) {
		// testadd();
		// testupdate();
		// testdelete();
		// testfindbyname();
		// testfindbypk();
		// testsearch();
		 testlist();
	}

	public static void testlist() {
		CourseDTO dto = new CourseDTO();
		
		List list = new ArrayList<CourseDTO>();
		
		try{
			list  = model.list();
			Iterator<CourseDTO> it = list.iterator();
			while (it.hasNext()) {
				dto = (CourseDTO) it.next();
				
				System.out.println(dto.getId());
				System.out.println(dto.getName());
				System.out.println(dto.getDuration());
				System.out.println(dto.getDescription());
			}
 		}catch(Exception e ){
 			e.printStackTrace();
 			
 		}
		
		
		
	}

	public static void testsearch() {

		CourseDTO dto = new CourseDTO();
		List list = new ArrayList<CourseDTO>();

		dto.setId(1);
		try {
			list = model.search(dto);
			Iterator<CourseDTO> it = list.iterator();
			while (it.hasNext()) {
				dto = it.next();

				System.out.println(dto.getId());
				System.out.println(dto.getName());
				System.out.println(dto.getDuration());
				System.out.println(dto.getDescription());
			}
		} catch (Exception e) {
			e.printStackTrace();
		}
	}

	public static void testfindbypk() {
		CourseDTO dto = new CourseDTO();

		try {
			dto = model.findbypk(1);
			System.out.println(dto.getId());
			System.out.println(dto.getName());
			System.out.println(dto.getDuration());
			System.out.println(dto.getDescription());
		} catch (Exception e) {
			e.printStackTrace();

		}
	}

	public static void testfindbyname() {
		CourseDTO dto = new CourseDTO();

		try {
			dto = model.findbyname("BCA");
			System.out.println(dto.getId());
			System.out.println(dto.getDuration());
			System.out.println(dto.getDescription());
		} catch (Exception e) {
			e.printStackTrace();

		}

	}

	public static void testdelete() {
		CourseDTO dto = new CourseDTO();
		try {
			dto.setId(3);
			model.delete(dto);
		} catch (Exception e) {
			e.printStackTrace();
		}

	}

	public static void testupdate() {

		CourseDTO dto = new CourseDTO();
		try {
			dto.setId(3);
			dto.setName("MBA");
			dto.setDuration("4 years");
			dto.setDescription("eeeeeeeeeeeeee");

			model.update(dto);
		} catch (Exception e) {
			e.printStackTrace();
		}

	}

	public static void testadd() {
		CourseDTO dto = new CourseDTO();

		try {
			// dto.setId(1);
			dto.setName("M.Com");
			dto.setDuration("2 years");
			dto.setDescription("Bachlore of Comerce");

			model.add(dto);
		} catch (Exception e) {
			e.printStackTrace();
		}

	}

}
