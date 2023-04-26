package com.accademyofl.loader.config;

import org.springframework.batch.item.ItemProcessor;

import com.accademyofl.loader.entities.Student;

public class StudentsProcessor implements ItemProcessor<Student, Student> {

	@Override
	public Student process(Student student) throws Exception {
		// TODO Auto-generated method stub
		return student;
	}

}
