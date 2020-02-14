package in.co.rays.ors.testModel;

import java.util.ArrayList;
import java.util.Iterator;
import java.util.List;

import in.co.rays.ors.dto.CourseDTO;
import in.co.rays.ors.dto.SubjectDTO;
import in.co.rays.ors.model.SubjectModelHibImpl;
import in.co.rays.ors.model.SubjectModelInt;

public class SubjectModelTest {

	private static SubjectModelInt model = new SubjectModelHibImpl();

	public static void main(String[] args) {

		//testadd();
		 //testupdate();
		// testdelete();
		 //testfindbyname();
		 //testfindbypk();
		// testsearch();
		 testlist();

	}

	public static void testlist() {
SubjectDTO dto = new SubjectDTO();
List list = new ArrayList<CourseDTO>();

		try {
			list = model.list();
			Iterator<SubjectDTO> it = list.iterator();
			while (it.hasNext()) {
				dto = (SubjectDTO) it.next();

				System.out.println(dto.getId());
				System.out.println(dto.getSubjectName());
				System.out.println(dto.getDescription());;
	//			System.out.println(dto.getCourseName());

			}
		} catch (Exception e) {
			e.printStackTrace();
		}
	}

	public static void testsearch() {
		SubjectDTO dto = new SubjectDTO();
		List list = new ArrayList<CourseDTO>();

		dto.setId(1);
		try {
			list = model.search(dto);
			Iterator<SubjectDTO> it = list.iterator();
			while (it.hasNext()) {
				dto = it.next();

				System.out.println(dto.getId());
				System.out.println(dto.getSubjectName());
				System.out.println(dto.getDescription());
				;
			//	System.out.println(dto.getCourseName());

			}
		} catch (Exception e) {
			e.printStackTrace();
		}
	}

	public static void testfindbypk() {
		SubjectDTO dto = new SubjectDTO();
		try {
			dto = model.findbypk(3);

			System.out.println(dto.getId());
			System.out.println(dto.getSubjectName());
			System.out.println(dto.getDescription());

	//		System.out.println(dto.getCourseName());

		} catch (Exception e) {
			e.printStackTrace();

		}
	}

	public static void testfindbyname() {
		SubjectDTO dto = new SubjectDTO();
		try {
			dto = model.findbyname("Account");

			System.out.println(dto.getId());
			System.out.println(dto.getSubjectName());
			System.out.println(dto.getDescription());
			System.out.println(dto.getCourseId());
	//		System.out.println(dto.getCourseName());

		} catch (Exception e) {
			e.printStackTrace();

		}

	}

	public static void testdelete() {
		SubjectDTO dto = new SubjectDTO();
		try {
			dto.setId(2);
			model.delete(dto);
		} catch (Exception e) {
			e.printStackTrace();
		}

	}

	public static void testupdate() {

		SubjectDTO dto = new SubjectDTO();
		try {

			dto.setId(2);
			dto.setSubjectName("MAths");
			dto.setDescription("aaaaaaaaa");
			dto.setCourseId(1);
	//		dto.setCourseName("MCA");

			model.update(dto);
		} catch (Exception e) {
			e.printStackTrace();
		}

	}

	public static void testadd() {
		SubjectDTO dto = new SubjectDTO();
		try {
			dto.setId(1);
			dto.setSubjectName("Physics");
			dto.setDescription("pppppp");
			dto.setCourseId(1);
	//		dto.setCourseName("MCA");
			
			model.add(dto);
		} catch (Exception e) {
			e.printStackTrace();
		}

	}

}
