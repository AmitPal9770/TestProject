package in.co.rays.ors.testModel;

import java.util.ArrayList;
import java.util.Iterator;
import java.util.List;

import javax.sound.midi.Synthesizer;

import in.co.rays.ors.dto.RoleDTO;
import in.co.rays.ors.exception.ApplicationException;
import in.co.rays.ors.exception.DuplicateRecordException;
import in.co.rays.ors.model.RoleModelHiblmpl;
import in.co.rays.ors.model.RoleModelInt;

public class RoleModelTest {

	// RoleModelInt role=ModelFactory.getInstance().getRoleModel();
	// RoleModelInt model = ModelFactory.getInstance().getRoleModel();

	private static RoleModelInt model = new RoleModelHiblmpl();

	public static void main(String[] args) {
	//	testadd();
	//	testupdate();
	//	testdelete();
		testfindbyname();
	//	testfindbypk();
	//	testsearch();
	//	testlist();
		
		
	}
	
	public static void testlist() {
		RoleDTO dto = new RoleDTO();
		List list = new ArrayList();
		try{
		list = model.list(1,10);
		if(list.size() >0){
			Iterator it = list.iterator();
			while(it.hasNext()){
				dto = (RoleDTO)it.next();
				System.out.println(dto.getId());
				System.out.println(dto.getName());
				System.out.println(dto.getDescription());
				System.out.println(dto.getCreatedby());
				
			}
		}
		}catch(ApplicationException e ){
			e.printStackTrace();
		}
	}

	public static void testsearch() {
	
		
		RoleDTO dto = new RoleDTO();
		List list = new ArrayList();
		try{
			dto.setId(3);
	//	dto.setName("mansi");
		list = model.search(dto, 0, 0);
		Iterator it = list.iterator();
		while(it.hasNext()){
				dto =(RoleDTO) it.next();
				System.out.println(dto.getId());
				System.out.println(dto.getName());
				System.out.println(dto.getDescription());
		}
		}catch(ApplicationException e){
			e.printStackTrace();
		}
	}

	public static void testfindbypk() {
		RoleDTO dto = new RoleDTO();
		try{
		dto = model.findbypk(3);
		System.out.println(dto.getId());
		System.out.println(dto.getName());
		System.out.println(dto.getDescription());
		}catch(ApplicationException e ){
			e.printStackTrace();
		}
	}

	public static void testfindbyname() {
		RoleDTO dto = new RoleDTO();
		try{
			dto = model.findbyname("Mansi");
		System.out.println(dto.getId());
		System.out.println(dto.getName());
		System.out.println(dto.getDescription());
		}catch(ApplicationException e){
			e.printStackTrace();
		}
		
	}

	public static void testdelete() {
		RoleDTO dto = new RoleDTO();
		dto.setId(1);
		try{
		model.delete(dto);
		System.out.println("Deleted....!!!");
		}catch(ApplicationException e ){
			e.printStackTrace();
			System.out.println("Exception ......!!!");
		}
		
		
	}

	public static void testupdate() throws DuplicateRecordException {
	
		RoleDTO dto = new RoleDTO();
	//	dto.setId(1);
		dto.setName("Mansi");
		dto.setDescription("Pagal");
		try{
		model.update(dto);
		System.out.println("Update............!!!!!!!");
		}catch(ApplicationException e){
			e.printStackTrace();
			System.out.println("Exception............!!!!!!!");			
		}
	}

	public static void testadd() throws DuplicateRecordException {

		RoleDTO dto = new RoleDTO();
		// dto.setId(1L);
		dto.setName("mansi");
		dto.setDescription("student");
		try {
			model.add(dto);
			System.out.println("data inserted succesfully");
		} catch (ApplicationException e) {
			e.printStackTrace();
			System.out.println("data inserted succesfully exception ........");
		}

	}

	

}
