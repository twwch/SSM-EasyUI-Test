package com.chtw.service;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.chtw.dao.StuDAO;
import com.chtw.dao.StudentMapper;
import com.chtw.pojo.Student;
import com.github.pagehelper.PageHelper;
import com.github.pagehelper.PageInfo;

@Service
public class StuService {
	
	//自动注入StuDAO
	@Autowired
	private StuDAO stuDAO;
	
	@Autowired
	private StudentMapper studentMapper;
	
	public List<Student> getAll(){
		return stuDAO.getAll();
	}

	/**
	 * 分页查询
	 * @param nowpage
	 * @param size
	 * @return
	 */
	public PageInfo<Student> getFenye(int page, int size) {
		PageHelper.startPage(page, size);
		List<Student> sList = stuDAO.getAll();
		PageInfo<Student> info = new PageInfo<Student>(sList);
		return info;
	}

	/**
	 * 删除数据
	 * @param sid
	 * @return
	 */
	public int dele(int sid) {
		return studentMapper.deleteByPrimaryKey(sid);
	}

	/**
	 * 增加
	 * @param stu
	 * @return
	 */
	public int add(Student stu) {
		/*StudentExample example = new StudentExample();
		StudentExample.Criteria cre = example.createCriteria();
		cre.andSidEqualTo(sid);*/
		return studentMapper.insertSelective(stu);
	}

	/**
	 * 查询需要哦修改的学生信息
	 * @param sid
	 * @return
	 */
	public Student sele(int sid) {
		return studentMapper.selectByPrimaryKey(sid);
	}

	/**
	 * 修改
	 * @param stu
	 * @return
	 */
	public int edit(Student stu) {
		return studentMapper.updateByPrimaryKeySelective(stu);
	}
}
