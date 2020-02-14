package in.co.rays.ors.testModel;

import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Iterator;
import java.util.List;

import in.co.rays.ors.dto.FacultyDTO;
import in.co.rays.ors.dto.RoleDTO;
import in.co.rays.ors.dto.StudentDTO;
import in.co.rays.ors.exception.ApplicationException;
import in.co.rays.ors.exception.DuplicateRecordException;
import in.co.rays.ors.model.StudentModelHibImpl;
import in.co.rays.ors.model.StudentModelInt;

public class StudentModelTest {

	private static StudentModelInt model = new StudentModelHibImpl();
	private static SimpleDateFormat sdf = new SimpleDateFormat("dd/MM/yy");

	public static void main(String[] args) throws ParseException {

		// testadd();
		// testupdate();
		// testdelete();
		// testfindbyname();
	//	testfindbypk();
		// testsearch();
		 testlist();

	}

	public static void testadd() {
		StudentDTO dto = new StudentDTO();

		try {
			// dto.setId(1);
			dto.setFirstName("ankit");
			dto.setLastName("sharma");
			dto.setCollegeId(2L);
	//		dto.setCollegeName("Kamal");
			dto.setDob(sdf.parse("12/4/1990"));
			dto.setMobileNo("9893228590");
			dto.setEmailid("amit@gmail.com");

			model.add(dto);

		} catch (Exception e) {
			e.printStackTrace();
		}
	}

	public static void testlist() {
		StudentDTO dto = new StudentDTO();
		List list = new ArrayList();
		try {
			list = model.list(1, 10);
			if (list.size() > 0) {
				Iterator it = list.iterator();
				while (it.hasNext()) {
					dto = (StudentDTO) it.next();
					System.out.println(dto.getId());
					System.out.println(dto.getFirstName());
					System.out.println(dto.getLastName());
					System.out.println(dto.getCollegeId());
			//		System.out.println(dto.getCollegeName());
					System.out.println(dto.getDob());
					System.out.println(dto.getMobileNo());
					
					System.out.println(dto.getEmailid());
				}
			}
		} catch (ApplicationException e) {
			e.printStackTrace();
		}
	}

	public static void testsearch() {

		StudentDTO dto = new StudentDTO();
		List list = new ArrayList<StudentDTO>();
	
		try {
			dto.setId(3);
			// dto.setName("mansi");
			list = model.search(dto);
			Iterator <StudentDTO>it = list.iterator();
			while (it.hasNext()) {
				dto = (StudentDTO) it.next();
				System.out.println(dto.getId());
				System.out.println(dto.getFirstName());
				System.out.println(dto.getLastName());
				System.out.println(dto.getCollegeId());
	//			System.out.println(dto.getCollegeName());
				System.out.println(dto.getDob());
				System.out.println(dto.getMobileNo());

				System.out.println(dto.getEmailid());
			}
		} catch (ApplicationException e) {
			e.printStackTrace();
		}
	}

	public static void testfindbypk() {
		StudentDTO dto = new StudentDTO();

		try {
			dto = model.findByPK(3);
			System.out.println(dto.getId());
			System.out.println(dto.getFirstName());
			System.out.println(dto.getLastName());
			System.out.println(dto.getCollegeId());
	//		System.out.println(dto.getCollegeName());
			System.out.println(dto.getDob());
			System.out.println(dto.getMobileNo());

			System.out.println(dto.getEmailid());
		} catch (ApplicationException e) {
			e.printStackTrace();
		}
	}

	public static void testfindbyname() {
		StudentDTO dto = new StudentDTO();
		try {
			dto = model.findByEmailId("amit");
			System.out.println(dto.getId());
			System.out.println(dto.getFirstName());
			System.out.println(dto.getLastName());
			System.out.println(dto.getCollegeId());
	//		System.out.println(dto.getCollegeName());
			System.out.println(dto.getDob());
			System.out.println(dto.getMobileNo());

			System.out.println(dto.getEmailid());
		} catch (ApplicationException e) {
			e.printStackTrace();
		}

	}

	public static void testdelete() {

		StudentDTO dto = new StudentDTO();
		dto.setId(1);
		try {
			model.delete(dto);
			System.out.println("Deleted....!!!");
		} catch (ApplicationException e) {
			e.printStackTrace();
			System.out.println("Exception ......!!!");
		}

	}

	public static void testupdate() throws ParseException, DuplicateRecordException {

		StudentDTO dto = new StudentDTO();

		dto.setId(1);
		dto.setFirstName("ankit");
		dto.setLastName("sharma");
		dto.setCollegeId(2L);
	//	dto.setCollegeName("Kamal");
		dto.setDob(sdf.parse("12/4/1990"));
		dto.setMobileNo("9893228590");
		dto.setEmailid("amit@gmail.com");

		try {
			model.update(dto);
			;
			System.out.println("Update............!!!!!!!");
		} catch (ApplicationException e) {
			e.printStackTrace();
			System.out.println("Exception............!!!!!!!");
		}
	}

}
