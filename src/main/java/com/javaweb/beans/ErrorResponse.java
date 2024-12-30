package com.javaweb.beans;

import java.util.ArrayList;

public class ErrorResponse {
	private String er;
	private ArrayList<String> detail = new ArrayList<>();
	public String getEr() {
		return er;
	}
	public void setEr(String er) {
		this.er = er;
	}
	public ArrayList<String> getDetail() {
		return detail;
	}
	public void setDetail(ArrayList<String> detail) {
		this.detail = detail;
	}
	
}
