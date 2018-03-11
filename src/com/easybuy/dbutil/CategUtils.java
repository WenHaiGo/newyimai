package com.easybuy.dbutil;

import java.util.List;

import com.easybuy.model.EPCateg;

public class CategUtils {
	private int id;
	private String name;
	// 子类
	private List<EPCateg> list;
	
	//一个类应该有 id 父类id 名称
	public int getId() {
		return id;
	}

	public void setId(int id) {
		this.id = id;
	}

	public String getName() {
		return name;
	}

	public void setName(String name) {
		this.name = name;
	}

	public List<EPCateg> getList() {
		return list;
	}

	public void setList(List<EPCateg> list) {
		this.list = list;
	}

}
