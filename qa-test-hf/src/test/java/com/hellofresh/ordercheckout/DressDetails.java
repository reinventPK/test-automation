package com.hellofresh.ordercheckout;

import java.util.Arrays;

/* =========================================================
 * POJO to store Dress information. 
 * =========================================================*/

public class DressDetails {

	private String id;
	private String dressName;
	private String[] color;
	private String[] size;
	
	public String getId() {
		return id;
	}
	
	public String getDressName() {
		return dressName;
	}
	
	public String[] getColor() {
		return color;
	}
	
	public String[] getSize() {
		return size;
	}
	
	public void setId(String id) {
		this.id = id;
	}
	
	public void setDressName(String dressName) {
		this.dressName = dressName;
	}
	
	public void setColor(String[] color) {
		this.color = color;
	}
	
	public void setSize(String[] size) {
		this.size = size;
	}
	
	@Override
	public String toString() {
		return "DressDetails [id=" + id + ", dressName=" + dressName + ", color=" + Arrays.toString(color) + ", size="
				+ Arrays.toString(size) + "]";
	}
	
}
