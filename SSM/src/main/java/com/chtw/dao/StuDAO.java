package com.chtw.dao;

import java.util.List;

import com.chtw.pojo.Student;

public interface StuDAO {

	/**
	 * 获取全部学生信息
	 * @return
	 */
	public List<Student> getAll();
}
