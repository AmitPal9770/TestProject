package in.co.rays.ors.testModel;

import java.util.ArrayList;
import java.util.Iterator;
import java.util.List;

import in.co.rays.ors.dto.CourseDTO;
import in.co.rays.ors.dto.FacultyDTO;
import in.co.rays.ors.model.FacultyModelHibImpl;
import in.co.rays.ors.model.FacultyModelInt;

public class FacultyModelTest {

	private static FacultyModelInt model = new FacultyModelHibImpl();

	public static void main(String[] args) {

		// testadd();
		// testupdate();
		// testdelete();
		// testfindbyname();
		// testfindbypk();
		 //testsearch();
		 testlist();

	}

	public static void testlist() {
		FacultyDTO dto = new FacultyDTO();
		List list = new ArrayList<CourseDTO>();

		try {
			list = model.list();
			Iterator<FacultyDTO> it = list.iterator();
			while (it.hasNext()) {
				dto = (FacultyDTO) it.next();

				System.out.println(dto.getId());
				System.out.println(dto.getFirstName());
				System.out.println(dto.getLastName());
				System.out.println(dto.getGender());
				System.out.println(dto.getDateofjoining());
				System.out.println(dto.getQualification());
				System.out.println(dto.getEmailId());
				System.out.println(dto.getMobileno());
	/*			System.out.println(dto.getCollegeName());
				System.out.println(dto.getCourseName());
				System.out.println(dto.getSubjectName());
	*/		}
		} catch (Exception e) {
			e.printStackTrace();
		}
	}

	public static void testsearch() {
		FacultyDTO dto = new FacultyDTO();
		List list = new ArrayList<CourseDTO>();

		dto.setId(1);
		try {
			list = model.search(dto);
			Iterator<FacultyDTO> it = list.iterator();
			while (it.hasNext()) {
				dto = it.next();

				System.out.println(dto.getId());
				System.out.println(dto.getFirstName());
				System.out.println(dto.getLastName());
				System.out.println(dto.getGender());
				System.out.println(dto.getDateofjoining());
				System.out.println(dto.getQualification());
				System.out.println(dto.getEmailId());
				System.out.println(dto.getMobileno());
/*				System.out.println(dto.getCollegeName());
				System.out.println(dto.getCourseName());
				System.out.println(dto.getSubjectName());*/
			}
		} catch (Exception e) {
			e.printStackTrace();
		}
	}

	public static void testfindbypk() {
		FacultyDTO dto = new FacultyDTO();

		try {
			dto = model.findbypk(3);

			System.out.println(dto.getId());
			System.out.println(dto.getFirstName());
			System.out.println(dto.getLastName());
			System.out.println(dto.getGender());
			System.out.println(dto.getDateofjoining());
			System.out.println(dto.getQualification());
			System.out.println(dto.getEmailId());
			System.out.println(dto.getMobileno());
/*			System.out.println(dto.getCollegeName());
			System.out.println(dto.getCourseName());
			System.out.println(dto.getSubjectName());*/
		} catch (Exception e) {
			e.printStackTrace();

		}
	}

	public static void testfindbyname() {
		FacultyDTO dto = new FacultyDTO();
		try {
			dto = model.findByEmail("Amit");

		//	System.out.println(dto.getId());
			System.out.println(dto.getFirstName());
			System.out.println(dto.getLastName());
			System.out.println(dto.getGender());
			System.out.println(dto.getDateofjoining());
			System.out.println(dto.getQualification());
			System.out.println(dto.getEmailId());
			System.out.println(dto.getMobileno());
/*			System.out.println(dto.getCollegeName());
			System.out.println(dto.getCourseName());
			System.out.println(dto.getSubjectName());*/

		} catch (Exception e) {
			e.printStackTrace();

		}

	}

	public static void testdelete() {
		FacultyDTO dto = new FacultyDTO();
		try {
			dto.setId(2);
			model.delete(dto);
		} catch (Exception e) {
			e.printStackTrace();
		}

	}

	public static void testupdate() {

		FacultyDTO dto = new FacultyDTO();
		try {

			dto.setId(2);
			dto.setFirstName("Amita");
			dto.setLastName("pal");
			dto.setGender("female");
		//	dto.setDateofjoining(12-5-1990);
			dto.setQualification("BCA");
			dto.setEmailId("apamitrocks03545@gmail.com");
			dto.setMobileno("9999988822");
			dto.setCollegeid(2);
//			dto.setCollegeName("kamal");
			dto.setCourseId(1);
//			dto.setCourseName("BCA");
			dto.setSubjectId(1);
	//		dto.setSubjectName("Account");
			model.update(dto);
		} catch (Exception e) {
			e.printStackTrace();
		}

	}

	public static void testadd() {
		FacultyDTO dto = new FacultyDTO();
		try {
	//		dto.setId(1);
			dto.setFirstName("Ankur");
			dto.setLastName("patel");
			dto.setGender("male");
		//	dto.setDateofjoining(12-5-1990);
			dto.setQualification("BbbCA");
			dto.setEmailId("apamitrocks03545@gmail.com");
			dto.setMobileno("9999988822");
			dto.setCollegeid(3);
//			dto.setCollegeName("Shri GUjratiii");
			dto.setCourseId(2);
//			dto.setCourseName("MCA");
			dto.setSubjectId(3);
//			dto.setSubjectName("Physics");
			model.add(dto);
		} catch (Exception e) {
			e.printStackTrace();
		}

	}

}
