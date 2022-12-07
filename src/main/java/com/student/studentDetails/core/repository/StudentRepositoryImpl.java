package com.student.studentDetails.core.repository;

import com.student.studentDetails.core.entity.Student;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.List;


@Repository
public interface StudentRepositoryImpl extends JpaRepository<Student, Long> {

    Student findByMobileAndEmailId(String mobile, String emailId);
    Student findByStudentId(Long id);
    List<Student> findByStudentClass(String studentClass);
}
