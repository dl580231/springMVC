package com.nuc.springMVCStudy.service;

import org.springframework.stereotype.Service;

import com.nuc.springMVCStudy.entity.Course;



@Service
public interface CourseService {
	
	
	Course getCoursebyId(Integer courseId);
	

	
	

}
