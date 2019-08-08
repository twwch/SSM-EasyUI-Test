package com.chtw.controller;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseBody;

import com.chtw.pojo.Student;
import com.chtw.service.StuService;
import com.github.pagehelper.PageInfo;

@Controller
@RequestMapping("/stu")
public class StuController {
	
	//自动注入StuService
	@Autowired
	private StuService stuService;
	
	@RequestMapping("/getAll")
	public String getAll(Model model) {
		List<Student> sList = stuService.getAll();
		model.addAttribute("sList", sList);
		return "index";
	}

	/**
	 * 分页查询
	 * @param page
	 * @param rows
	 * @return
	 */
	@RequestMapping("/fenye")
	@ResponseBody
	public Map<String,Object> getFenye(@RequestParam(defaultValue="1",required=false) int page, int rows){
		PageInfo<Student> info = stuService.getFenye(page, rows);
		if(info!=null) {
			Map<String,Object> maps = new HashMap<String, Object>();
			maps.put("total", info.getTotal());
			maps.put("rows", info.getList());
			return maps;
		}
		return null;
	}
	
	/**
	 * 删除数据
	 * @param stu
	 * @return
	 */
	@RequestMapping("/dele")
	@ResponseBody
	public Map<String,Integer> dele(Student stu) {
		int i = stuService.dele(stu.getSid());
		Map<String,Integer> maps = new HashMap<String, Integer>();
		maps.put("code", i);
		return maps;
	}
	
	/**
	 * 增加
	 * @param stu
	 * @return
	 */
	@RequestMapping("/add")
	@ResponseBody
	public Map<String,Integer> add(Student stu) {
		System.out.println(stu.getSname()+"-"+stu.getPassword()+"-"+stu.getScore());
		int i = stuService.add(stu);
		Map<String,Integer> maps = new HashMap<String, Integer>();
		maps.put("code", i);
		return maps;
	}
	
	/**
	 * 查询需要修改的学生信息
	 * @param stu
	 * @return
	 */
	@RequestMapping("/goedit")
	@ResponseBody
	public Map<String,Object> goedit(Student stu){
		Student student = stuService.sele(stu.getSid());
		Map<String,Object> maps = new HashMap<String, Object>();
		maps.put("code", 1);
		maps.put("stu", student);
		return maps;
	}
	
	/**
	 * 修改学生信息
	 * @param stu
	 * @return
	 */
	@RequestMapping("/edit")
	@ResponseBody
	public Map<String,Object> edit(Student stu){
		System.out.println(stu.getSid()+"-"+stu.getSname()+"-"+stu.getPassword()+"-"+stu.getScore());
		int i = stuService.edit(stu);
		Map<String,Object> maps = new HashMap<String, Object>();
		maps.put("code", i);
		return maps;
	}
}
