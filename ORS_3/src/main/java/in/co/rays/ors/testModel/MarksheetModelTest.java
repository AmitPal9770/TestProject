package in.co.rays.ors.testModel;

import java.util.ArrayList;
import java.util.Iterator;
import java.util.List;

import in.co.rays.ors.dto.MarksheetDTO;
import in.co.rays.ors.dto.RoleDTO;
import in.co.rays.ors.exception.ApplicationException;
import in.co.rays.ors.exception.DuplicateRecordException;
import in.co.rays.ors.model.MarksheetModelHibImpl;
import in.co.rays.ors.model.MarksheetModelInt;

public class MarksheetModelTest {
	private static MarksheetModelInt model = new MarksheetModelHibImpl();

	public static void main(String[] args) {
		// testadd();
		// testupdate();
		// testdelete();
		// testfindbyname();
		//testfindbypk();
		 //testsearch();
	//	 testlist();
		testmeritlist();
	}

	public static void testmeritlist() {
		MarksheetDTO dto = new MarksheetDTO();
		List list = new ArrayList();
		try {
			list = model.list();
			if (list.size() > 0) {
				Iterator it = list.iterator();
				while (it.hasNext()) {
					dto = (MarksheetDTO) it.next();
					System.out.println(dto.getStudentname());
					System.out.println(dto.getPhysics());
					System.out.println(dto.getMaths());
					System.out.println(dto.getChemistry());
				}
			}
		} catch (ApplicationException e) {
			e.printStackTrace();
		}		
	}

	public static void testlist() {
		MarksheetDTO dto = new MarksheetDTO();
		List list = new ArrayList();
		try {
			list = model.list();
			if (list.size() > 0) {
				Iterator it = list.iterator();
				while (it.hasNext()) {
					dto = (MarksheetDTO) it.next();
					System.out.println(dto.getStudentname());
					System.out.println(dto.getPhysics());
					System.out.println(dto.getMaths());
					System.out.println(dto.getChemistry());
				}
			}
		} catch (ApplicationException e) {
			e.printStackTrace();
		}
	}

	public static void testsearch() {

		MarksheetDTO dto = new MarksheetDTO();
		List list = new ArrayList();
		try {
			dto.setId(4);
			// dto.setName("mansi"); 
			list = model.search(dto);
			Iterator it = list.iterator();
			while (it.hasNext()) {
				dto = (MarksheetDTO) it.next();
				System.out.println(dto.getStudentname());
				System.out.println(dto.getPhysics());
				System.out.println(dto.getMaths());
				System.out.println(dto.getChemistry());
			}
		} catch (ApplicationException e) {
			e.printStackTrace();
		}
	}

	public static void testfindbypk() {
		MarksheetDTO dto = new MarksheetDTO();
		try {
			dto = model.findbypk(3);
			System.out.println(dto.getStudentname());
			System.out.println(dto.getPhysics());
			System.out.println(dto.getMaths());
			System.out.println(dto.getChemistry());
		} catch (ApplicationException e) {
			e.printStackTrace();
		}
	}

	public static void testfindbyname() {

		MarksheetDTO dto = new MarksheetDTO();
		try {
			dto = model.findbyRollNo("ab123");
			// System.out.println(dto.getId());
			// System.out.println(dto.getRollNo());
			System.out.println(dto.getStudentname());
			System.out.println(dto.getPhysics());
			System.out.println(dto.getMaths());
			System.out.println(dto.getChemistry());
		} catch (ApplicationException e) {
			e.printStackTrace();
		}

	}

	public static void testdelete() {
		MarksheetDTO dto = new MarksheetDTO();
		dto.setId(1);
		try {
			model.delete(dto);
			System.out.println("Deleted....!!!");
		} catch (ApplicationException e) {
			e.printStackTrace();
			System.out.println("Exception ......!!!");
		}

	}

	public static void testupdate() throws DuplicateRecordException {

		MarksheetDTO dto = new MarksheetDTO();

		try {
			dto.setId(2);
			dto.setRollNo("ab123");
			dto.setStudentId(2L);
			dto.setStudentname("mayank");
			dto.setPhysics(34);
			dto.setMaths(34);
			dto.setChemistry(34);

			model.update(dto);
			System.out.println("Update............!!!!!!!");
		} catch (ApplicationException e) {
			e.printStackTrace();
			System.out.println("Exception............!!!!!!!");
		}
	}

	public static void testadd() throws DuplicateRecordException {

		MarksheetDTO dto = new MarksheetDTO();
		// dto.setId(1L);
		dto.setRollNo("ac123");
		dto.setStudentId(2L);
		dto.setStudentname("Sanjay ");
		dto.setPhysics(34);
		dto.setMaths(65);
		dto.setChemistry(76);
		try {
			model.add(dto);
			System.out.println("data inserted succesfully");
		} catch (ApplicationException e) {
			e.printStackTrace();
			System.out.println("data inserted succesfully exception ........");
		}

	}

}
