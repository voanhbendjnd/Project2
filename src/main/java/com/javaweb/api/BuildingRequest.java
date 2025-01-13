package com.javaweb.api;
import java.util.List;
import java.util.Map;

public class BuildingRequest {
	private Map<String, Object> bodys;
	private List <String> typecode;
	public Map<String, Object> getBodys() {
		return bodys;
	}
	public void setBodys(Map<String, Object> bodys) {
		this.bodys = bodys;
	}
	public List<String> getTypecode() {
		return typecode;
	}
	public void setTypecode(List<String> typecode) {
		this.typecode = typecode;
	}
	
}
