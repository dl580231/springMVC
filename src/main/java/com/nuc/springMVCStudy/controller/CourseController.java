package com.nuc.springMVCStudy.controller;

import java.io.File;
import java.io.IOException;
import java.util.Map;

import org.apache.commons.io.FileUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.multipart.MultipartFile;

import com.nuc.springMVCStudy.entity.Course;
import com.nuc.springMVCStudy.service.CourseService;


@RequestMapping("/course")
@Controller
public class CourseController {

	@Autowired
	private CourseService courseService;
	
	//1.接受查询参数方式请求参数
	//http://localhost:8800/course/view?courseId=123
	/*@RequestMapping(value="/view",method=RequestMethod.GET)
	public String ViewCourse(@RequestParam("courseId") Integer courseId,Model model) {
		Course course = courseService.getCoursebyId(courseId);
		model.addAttribute("course",course);
		return "course_overview";
	}*/
	
	//2.接受路径变量方式的请求参数
	//http://localhost:8080/course/view/123
	@RequestMapping(value="/view/{id}",method=RequestMethod.GET)
	public String ViewCourse(@PathVariable("id") Integer courseId,Map<String,Course> map) {
		Course course = courseService.getCoursebyId(courseId);
		map.put("course",course);
		return "course_overview";
	}
	
	
	@RequestMapping(params= {"add"})
	public String addCourse() {
		return "course_admin/edit";
	}
	
	
	@RequestMapping(value="save",method=RequestMethod.POST)
	public String doSave(@ModelAttribute Course course) {
		course.setCourseId(3);
		System.out.println(course);
		return "redirect:/course/view/"+course.getCourseId();
	}
	
	
	@RequestMapping("/upload")
	public String upload() {
		return "course_admin/file";
	}
	
	
	@RequestMapping(value="/doUpload",method=RequestMethod.POST)
	public String doUpload(@RequestParam("file") MultipartFile file) throws IOException {
		if(!file.isEmpty()) {
			FileUtils.copyInputStreamToFile(file.getInputStream(),new File("C:\\Users\\lenovo\\Desktop\\upload", file.getOriginalFilename()));
			System.out.println("contentType="+file.getContentType());
			System.out.println("name="+file.getName());
			System.out.println("size="+file.getSize());
		}else {
			System.out.println("文件空了");
		}
		return "success";
	}
	
	
	//用ContentNegotationViewResolver视图解析器来返回正确的数据格式
	@RequestMapping(value="/json0/{courseId}",method=RequestMethod.GET)
	public @ResponseBody Course getCourseJsonById(@PathVariable Integer courseId) {
		return courseService.getCoursebyId(courseId);
	}
	
	
	@RequestMapping(value="/json1/{courseId}",method=RequestMethod.GET)
	public ResponseEntity<Course> getCourseJsonById1(@PathVariable Integer courseId){
		return new ResponseEntity<Course>(courseService.getCoursebyId(courseId),HttpStatus.OK);
	}
}
