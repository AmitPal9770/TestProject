package in.co.rays.ors.testModel;

import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Iterator;
import java.util.List;

import in.co.rays.ors.dto.MarksheetDTO;
import in.co.rays.ors.dto.TimeTableDTO;
import in.co.rays.ors.exception.ApplicationException;
import in.co.rays.ors.exception.DuplicateRecordException;
import in.co.rays.ors.model.TimeTableModelHibImpl;
import in.co.rays.ors.model.TimeTableModelInt;

public class TimeTableModelTest {

	private static TimeTableModelInt model =new  TimeTableModelHibImpl();
	private static SimpleDateFormat sdf = new SimpleDateFormat("dd/MM/yy");
	public static void main(String[] args) throws ParseException {
		

		// testadd();
		// testupdate();
//		 testdelete();
		
	//	testfindbypk();
	//	 testsearch();
	//	 testlist();
		
	}
	
	public static void testlist() {
TimeTableDTO dto = new TimeTableDTO();
		List list = new ArrayList();
		try {
			list = model.list();
			if (list.size() > 0) {
				Iterator it = list.iterator();
				while (it.hasNext()) {
					dto = (TimeTableDTO) it.next();
					System.out.println(dto.getCourseName());
					System.out.println(dto.getSubjectName());
					System.out.println(dto.getSemester());
					System.out.println(dto.getExamDate());
					System.out.println(dto.getExamTime());
				}
			}
		} catch (ApplicationException e) {
			e.printStackTrace();
		}
	}

	public static void testsearch() {

	TimeTableDTO dto = new TimeTableDTO();
		List list = new ArrayList();
		try {
			dto.setId(3);
			// dto.setName("mansi"); 
			list = model.search(dto);
			Iterator it = list.iterator();
			while (it.hasNext()) {
				dto = (TimeTableDTO) it.next();
				System.out.println(dto.getCourseName());
			System.out.println(dto.getSubjectName());
			System.out.println(dto.getSemester());
			System.out.println(dto.getExamDate());
			System.out.println(dto.getExamTime());
			}
		} catch (ApplicationException e) {
			e.printStackTrace();
		}
	}

	public static void testfindbypk() {
	TimeTableDTO dto = new TimeTableDTO();
		try {
			dto = model.findbypk(1);
			System.out.println(dto.getCourseName());
			System.out.println(dto.getSubjectName());
			System.out.println(dto.getSemester());
			System.out.println(dto.getExamDate());
			System.out.println(dto.getExamTime());
		} catch (ApplicationException e) {
			e.printStackTrace();
		}
	}

	public static void testdelete() {
	TimeTableDTO dto = new TimeTableDTO();
		dto.setId(4);
		try {
			model.delete(dto);
			System.out.println("Deleted....!!!");
		} catch (ApplicationException e) {
			e.printStackTrace();
			System.out.println("Exception ......!!!");
		}

	}


	public static void testupdate() throws ParseException, DuplicateRecordException {

TimeTableDTO dto = new TimeTableDTO();
		try {
			dto.setId(3);
	dto.setSubjectId(1L);
	dto.setSubjectName("Sabja");
	dto.setCourseId(1L);
	dto.setCourseName("GObi");
	dto.setSemester("3 years");
	dto.setExamDate(sdf.parse("12/4/1990"));
	dto.setExamTime("12 am");

			model.update(dto);
			System.out.println("Update............!!!!!!!");
		} catch (ApplicationException e) {
			e.printStackTrace();
			System.out.println("Exception............!!!!!!!");
		}
	}
	public static void testadd() throws ParseException, DuplicateRecordException {

TimeTableDTO dto = new TimeTableDTO();
		// dto.setId(1L);
	dto.setSubjectId(1L);
	dto.setSubjectName("Sabji");
	dto.setCourseId(1L);
	dto.setCourseName("Allo");
	dto.setSemester("3 years");
	dto.setExamDate(sdf.parse("12/4/1990"));
	dto.setExamTime("12 am");

try {
			model.add(dto);
			System.out.println("data inserted succesfully");
		} catch (ApplicationException e) {
			e.printStackTrace();
			System.out.println("data inserted succesfully exception ........");
		}

	}



}
