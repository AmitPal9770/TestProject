package in.co.rays.ors.util;



import java.util.Collections;
import java.util.HashMap;
import java.util.List;
import java.util.Set;

import in.co.rays.ors.dto.DropdownList;


/**
 * HTML Utility class to produce HTML contents like Dropdown List.
 * 
 * @author SUNRAYS Technologies
 * @version 1.0
 * @Copyright (c) SUNRAYS Technologies
 * 
 */

public class HTMLUtility {

	/**
	 * Create HTML SELECT list from MAP paramters values
	 * 
	 * @param name
	 * @param selectedVal
	 * @param map
	 * @return
	 */

	public static String getList(String name, String selectedVal, HashMap<String, String> map) {

		StringBuffer sb = new StringBuffer(
				"<select  class='form-control' name='" + name + "'>");

		Set<String> keys = map.keySet();
		String val = null;
		boolean select = true;
		if (select) {

			sb.append(
					"<option  selected value=''>------------Select------------------------</option>");
		}

		for (String key : keys) {
			val = map.get(key);
			if (key.trim().equals(selectedVal)) {
				sb.append("<option selected value='" + key + "'>" + val + "</option>");
			} else {
				sb.append("<option value='" + key + "'>" + val + "</option>");
			}
		}

		sb.append("</select>");
		System.out.println("get list 1=========" + sb.toString());

		return sb.toString();
	}

	/**
	 * Create HTML SELECT List from List parameter
	 * 
	 * @param name
	 * @param selectedVal
	 * @param list
	 * @return
	 */
	public static String getList(String name, String selectedVal, List list) {

		Collections.sort(list);

		StringBuffer sb = new StringBuffer(
				"<select class='form-control'  name='" + name + "'>");

		boolean select = true;
		if (select) {

			sb.append(
					"<option  selected value=''>------------"+name+"----------------</option>");
		}

		List<DropdownList> dd = (List<DropdownList>) list;

		// StringBuffer sb = new StringBuffer( "<select style='width: 163px;
		// height: 23px;' class='form-control' name='" + name + "'>");

		String key = null;
		String val = null;

		for (DropdownList obj : dd) {
			key = obj.getKey();
			val = obj.getValue();

			if (key.trim().equals(selectedVal)) {
				sb.append("<option selected value='" + key + "'>" + val + "</option>");
			} else {
				sb.append("<option value='" + key + "'>" + val + "</option>");
			}
		}
		sb.append("</select>");
		System.out.println("get list 2=========" + sb.toString());
		return sb.toString();
	}

}
