package com.example.demotest;

import java.util.HashMap;
import java.util.Map;

public class ClassWrapper {

	public static Map<String,Object> getWrapper(String wrapperString,Object object) {
		Map<String, Object> map = new HashMap<String,Object>();
		map.put(wrapperString, object);
		return map;
	}
	

}
