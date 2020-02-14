package in.co.rays.ors.testModel;

import in.co.rays.ors.dto.CollegeDTO;
import in.co.rays.ors.exception.ApplicationException;
import in.co.rays.ors.exception.DuplicateRecordException;
import in.co.rays.ors.model.CollegeModel;
import in.co.rays.ors.model.CollegeModelInt;

public class CollegeJavaModelTest {

	public static CollegeModelInt model = new CollegeModel();

	public static void main(String[] args) throws DuplicateRecordException {

	//	 testAdd();
	//	 testDelete();
	//	 testUpdate();
	//	 testFindByName();
	//	 testFindByPK();
	//	 testSearch();
	//	testList();

	}
	public static void testAdd() throws DuplicateRecordException{

		try {
			CollegeDTO bean = new CollegeDTO();
			 bean.setId(6L);
			bean.setName("Jay");
			bean.setAddress("Bhalu");
			bean.setState("MP");
			bean.setCity("Indore");
			bean.setMobileNo("073124244");
			bean.setCreatedby("Admin");
			bean.setModifiedby("Admin");
			System.out.println("in add metthod");
			long pk = model.add(bean);
			System.out.println("Test add succ");
			/*Collegedto addedBean = model.findByPK(pk);
			if (addedBean == null) {
				System.out.println("Test add fail");
			}*/
		} catch (ApplicationException e) {
			e.printStackTrace();
		}

	}

	public static void testDelete() {

		try {
			CollegeDTO bean = new CollegeDTO();
			long pk = 6L;
			bean.setId(pk);
			model.delete(bean);
			System.out.println("Test Delete succ");
			CollegeDTO deletedBean = model.findbypk(pk);
			if (deletedBean != null) {
				System.out.println("Test Delete fail");
			}
		} catch (ApplicationException e) {
			e.printStackTrace();
		}
	}

	
	public static void testUpdate() {

		try {
			CollegeDTO bean = model.findbypk(3);
			
			bean.setName("Vikram University");
			bean.setAddress("Ujjain");
			model.update(bean);
			System.out.println("Test Update succ");
			/*CollegeBean updateBean = model.findByPK(3L);
			if (!"Vikram University".equals(updateBean.getName())) {
				System.out.println("Test Update fail");
			}*/
		} catch (ApplicationException e) {
			e.printStackTrace();
		} catch (DuplicateRecordException e) {
			e.printStackTrace();
		}

	}
	/*
	 
	*//**
	 * Tests find a College by Name.
	 *//*

	public static void testFindByName() {

		try {
			CollegeBean bean = model.findByName("Vikram university");
			if (bean == null) {
				System.out.println("Test Find By Name fail");
			}
			System.out.println(bean.getId());
			System.out.println(bean.getName());
			System.out.println(bean.getAddress());
			System.out.println(bean.getState());
			System.out.println(bean.getCity());
			System.out.println(bean.getPhoneNo());
			System.out.println(bean.getCreatedBy());
			System.out.println(bean.getCreatedDatetime());
			System.out.println(bean.getModifiedBy());
			System.out.println(bean.getModifiedDatetime());
		} catch (ApplicationException e) {
			e.printStackTrace();
		}

	}

	*//**
	 * Tests find a College by PK.
	 *//*
	public static void testFindByPK() {
		try {
			CollegeBean bean = new CollegeBean();
			long pk = 2L;
			bean = model.findByPK(pk);
			if (bean == null) {
				System.out.println("Test Find By PK fail");
			}
			System.out.println(bean.getId());
			System.out.println(bean.getName());
			System.out.println(bean.getAddress());
			System.out.println(bean.getState());
			System.out.println(bean.getCity());
			System.out.println(bean.getPhoneNo());
			System.out.println(bean.getCreatedBy());
			System.out.println(bean.getCreatedDatetime());
			System.out.println(bean.getModifiedBy());
			System.out.println(bean.getModifiedDatetime());
		} catch (ApplicationException e) {
			e.printStackTrace();
		}

	}

	*//**
	 * Tests search a College by Name
	 *//*

	public static void testSearch() {
		try {
			CollegeBean bean = new CollegeBean();
			List list = new ArrayList();
		//	bean.setName("LNCT");
			 bean.setAddress("ujjain");
			list = model.search(bean, 1, 10);
			if (list.size() < 0) {
				System.out.println("Test Search fail");
			}
			Iterator it = list.iterator();
			while (it.hasNext()) {
				bean = (CollegeBean) it.next();
				System.out.println(bean.getId());
				System.out.println(bean.getName());
				System.out.println(bean.getAddress());
				System.out.println(bean.getState());
				System.out.println(bean.getCity());
				System.out.println(bean.getPhoneNo());
				System.out.println(bean.getCreatedBy());
				System.out.println(bean.getCreatedDatetime());
				System.out.println(bean.getModifiedBy());
				System.out.println(bean.getModifiedDatetime());
			}
		} catch (ApplicationException e) {
			e.printStackTrace();
		}
	}

	*//**
	 * Tests get List a College.
	 *//*
	public static void testList() {

		try {
			CollegeBean bean = new CollegeBean();
			List list = new ArrayList();
			list = model.list(1, 10);
			if (list.size() < 0) {
				System.out.println("Test list fail");
			}
			Iterator it = list.iterator();
			while (it.hasNext()) {
				bean = (CollegeBean) it.next();
				System.out.println(bean.getId());
				System.out.println(bean.getName());
				System.out.println(bean.getAddress());
				System.out.println(bean.getState());
				System.out.println(bean.getCity());
				System.out.println(bean.getPhoneNo());
				System.out.println(bean.getCreatedBy());
				System.out.println(bean.getCreatedDatetime());
				System.out.println(bean.getModifiedBy());
				System.out.println(bean.getModifiedDatetime());
			}

		} catch (ApplicationException e) {
			e.printStackTrace();
		}
	}

*/
}
