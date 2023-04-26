package com.accademyofl.loader.repository;

import org.springframework.data.jpa.repository.JpaRepository;

import com.accademyofl.loader.entities.Student;

public interface StudentRepository extends JpaRepository<Student,Long>{

}
