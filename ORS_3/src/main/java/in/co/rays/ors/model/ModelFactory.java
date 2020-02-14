package in.co.rays.ors.model;
/**
 * Factory of Model classes
 *
 * @author SUNRAYS Technologies
 * @version 1.0
 * @Copyright (c) SUNRAYS Technologies
 *
 */

import java.util.HashMap;
import java.util.ResourceBundle;

/**
 * @author SunilOS
 *
 */
public class ModelFactory { 
	private static ResourceBundle bundle = ResourceBundle.getBundle("in.co.rays.ors.bundle.system");

	private static final String DATABASE = bundle.getString("DATABASE");
	private static ModelFactory mFactory = null;
	/**
	 * Cache of Model classes
	 */
	private static HashMap modelCache = new HashMap();

	/**
	 * Constructor is private so no other class can create instance of Model
	 * Locator
	 */
	private ModelFactory() {

	}

	/**
	 * Get the instance of Model Locator
	 *
	 * @return mFactory
	 */
	public static ModelFactory getInstance() {
		if (mFactory == null) {
			mFactory = new ModelFactory();
		}
		return mFactory;
	}

	

	public UserModelInt getUserModel() {
		UserModelInt userModel = (UserModelInt) modelCache.get("userModel");
		if (userModel == null) {
			if ("Hibernate".equals(DATABASE)) {
				userModel = new UserModelHibImpl();
			}
			if ("JDBC".equals(DATABASE)) {
				userModel = new UserModel();
			}
			modelCache.put("userModel", userModel);
		}
		return userModel;
	}


	public RoleModelInt getRoleModel() {
		RoleModelInt roleModel = (RoleModelInt) modelCache.get("roleModel");
		if (roleModel == null) {
			if ("Hibernate".equals(DATABASE)) {
				roleModel = new RoleModelHiblmpl();
			}
			if ("JDBC".equals(DATABASE)) {
				roleModel = new RoleModel();
			}
			modelCache.put("roleModel", roleModel);
		}

		return roleModel;

	}
	
	public StudentModelInt getStudentModel() {
		StudentModelInt studentModel = (StudentModelInt) modelCache.get("studentModel");
		if (studentModel == null) {
			if ("Hibernate".equals(DATABASE)) {
				studentModel = new StudentModelHibImpl();
			}
			if ("JDBC".equals(DATABASE)) {
				studentModel = new StudentModel() ;
			}
			modelCache.put("studentModel", studentModel);
		}
		return studentModel;
	}	

	public SubjectModelInt getSubjectModel() {
		SubjectModelInt subjectModel = (SubjectModelInt) modelCache.get("subjectModel");
		if (subjectModel == null) {
			if ("Hibernate".equals(DATABASE)) {
				subjectModel = new SubjectModelHibImpl();
			}
			if ("JDBC".equals(DATABASE)) {
				subjectModel = new SubjectModel();
			}
			modelCache.put("subjectModel", subjectModel);
		}
		return subjectModel;
	}
	
	public FacultyModelInt getFacultyModel() {
		FacultyModelInt facultyModel = (FacultyModelInt) modelCache.get("facultyModel");
		if (facultyModel == null) {
			if ("Hibernate".equals(DATABASE)) {
				facultyModel = new FacultyModelHibImpl();
			}
			if ("JDBC".equals(DATABASE)) {
				facultyModel = new FacultyModel();
			}
			modelCache.put("facultyModel", facultyModel);
		}
		return facultyModel;
	}
	
	public CollegeModelInt getCollegeModel() {
		CollegeModelInt collegeModel = (CollegeModelInt) modelCache.get("collegeModel");
		if (collegeModel == null) {
			if ("Hibernate".equals(DATABASE)) {
				collegeModel = new CollegeModelHibImpl();
			}
			if ("JDBC".equals(DATABASE)) {
				collegeModel = new CollegeModel() ;
			}
			modelCache.put("collegeModel", collegeModel);
		}
		return collegeModel;
	}	
	
	
	public CourseModelInt getCourseModel() {
		CourseModelInt courseModel = (CourseModelInt) modelCache.get("courseModel");
		if (courseModel == null) {
			if ("Hibernate".equals(DATABASE)) {
				courseModel = new CourseModelHibImpl();
			}
			if ("JDBC".equals(DATABASE)) {
				courseModel = new CourseModel() ;
			}
			modelCache.put("studentModel", courseModel);
		}
		return courseModel;
	}	

	
	public TimeTableModelInt getTimeTableModel() {
		TimeTableModelInt timetableModel = (TimeTableModelInt) modelCache.get("timetableModel");
		if (timetableModel == null) {
			if ("Hibernate".equals(DATABASE)) {
				timetableModel = new TimeTableModelHibImpl();
			}
			if ("JDBC".equals(DATABASE)) {
				timetableModel = new TimeTableModel() ;
			}
			modelCache.put("timetableModel", timetableModel);
		}
		return timetableModel;
	}
	
	
	public MarksheetModelInt getMarksheetModel() {
		MarksheetModelInt marksheetModel = (MarksheetModelInt) modelCache.get("marksheetModel");
		if (marksheetModel == null) {
			if ("Hibernate".equals(DATABASE)) {
				marksheetModel = new MarksheetModelHibImpl();
			}
			if ("JDBC".equals(DATABASE)) {
				marksheetModel = new MarksheetModel();
			}
			modelCache.put("marksheetModel", marksheetModel);
		}

		return marksheetModel;
	}


	}
