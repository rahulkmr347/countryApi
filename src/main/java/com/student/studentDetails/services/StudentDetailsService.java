package com.student.studentDetails.services;

import com.student.studentDetails.core.entity.Student;
import com.student.studentDetails.core.repository.StudentRepositoryImpl;
import com.student.studentDetails.dto.StudentRequestDto;
import com.student.studentDetails.dto.StudentResponseDto;
import org.apache.commons.lang3.StringUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import javax.persistence.EntityNotFoundException;
import javax.validation.ValidationException;
import javax.validation.constraints.NotNull;
import java.util.ArrayList;
import java.util.List;
import java.util.Objects;

@Service
public class StudentDetailsService {

    @Autowired
    StudentRepositoryImpl studentRepository;

    public void saverStudentDetails(StudentRequestDto studentRequestDto) {

        studentValidation(studentRequestDto);
        Student student = new Student();
        student = getStudent(student, studentRequestDto);
        studentRepository.save(student);
    }

    private Student getStudent(Student student , StudentRequestDto studentRequestDto){

        student.setStudentName(studentRequestDto.getStudentName());
        student.setEmailId(studentRequestDto.getEmailId());
        student.setStudentClass(studentRequestDto.getStudentClass());
        student.setMobile(studentRequestDto.getMobile());
        student.setActiveStatus(true);
        if(StringUtils.isBlank(studentRequestDto.getStudentName())){
            student.setCreatedBy(studentRequestDto.getStudentName());
            student.setModifiedBy(studentRequestDto.getStudentName());
        }else {
            student.setModifiedBy(studentRequestDto.getStudentName());
        }
        return student;
    }

    private void studentValidation(StudentRequestDto studentRequestDto){
        Student oldStudent = studentRepository.findByMobileAndEmailId(studentRequestDto.getMobile(), studentRequestDto.getEmailId());
        if(oldStudent != null){
            throw new ValidationException("Student is already present with this mobile number"
                    + studentRequestDto.getMobile() + "or email" + studentRequestDto.getEmailId());
        }
    }

    public StudentResponseDto getStudentDetailsById(@NotNull Long id) {
        Student student = studentRepository.findByStudentId(id);
        if(Objects.isNull(student)){
            throw new EntityNotFoundException("Student is not found with the id :" + id);
        }
        StudentResponseDto studentResponseDto = new StudentResponseDto();
        studentResponseDto = getStudentResponse(studentResponseDto, student);

        return studentResponseDto;
    }

    private StudentResponseDto getStudentResponse(StudentResponseDto studentResponseDto , Student student){

        studentResponseDto.setStudentName(student.getStudentName());
        studentResponseDto.setEmailId(student.getEmailId());
        studentResponseDto.setStudentClass(student.getStudentClass());
        studentResponseDto.setMobile(student.getMobile());
        studentResponseDto.setActiveStatus(student.isActiveStatus());
        return studentResponseDto;
    }

    public void updateStudentDetails(Long id, StudentRequestDto studentRequestDto) {

        Student student = studentRepository.findByStudentId(id);
        if(Objects.isNull(student)){
            throw new EntityNotFoundException("Student is not Available for the id :" + id);
        }
        student = getStudent(student , studentRequestDto);
        studentRepository.save(student);
    }

    public void deleteStudent(Long id) {

        Student student = studentRepository.findByStudentId(id);
        if(student == null){
            throw new EntityNotFoundException("Student is not Available for the id :" + id);
        }
        student.setActiveStatus(false);
        studentRepository.save(student);
    }

    public List<StudentResponseDto> getStudentList(String studentClass) {
        List<StudentResponseDto> studentResponseDtos= new ArrayList<>();

        List<Student> students = studentRepository.findByStudentClass(studentClass);
        for (Student student: students) {
            StudentResponseDto studentResponseDto = new StudentResponseDto();
            studentResponseDto = getStudentResponse(studentResponseDto, student);
            studentResponseDtos.add(studentResponseDto);
        }
        return studentResponseDtos;

    }
}
