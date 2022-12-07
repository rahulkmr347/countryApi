package com.student.studentDetails.controller;


import com.student.studentDetails.dto.StudentRequestDto;
import com.student.studentDetails.dto.StudentResponseDto;
import com.student.studentDetails.services.StudentDetailsService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import javax.validation.constraints.NotNull;
import java.util.List;

@RestController
@RequestMapping(path = "/student", produces = "application/xml")
public class StudentDetailsController {

    @Autowired
    StudentDetailsService studentDetailsService;

    @PostMapping(value = "/register")
    public ResponseEntity<String> saveStudentDetails(@RequestBody StudentRequestDto studentRequestDto){
        studentDetailsService.saverStudentDetails(studentRequestDto);
        return ResponseEntity.ok("Student added Success");
    }

    @GetMapping(value = "/{id}")
    public StudentResponseDto getStudentById(@NotNull @PathVariable(name = "id") Long id){
        return studentDetailsService.getStudentDetailsById(id);
    }

    @PutMapping(value = "/update/{id}")
    public ResponseEntity<String> updateUserDetails(@PathVariable(name = "id") Long id, @RequestBody StudentRequestDto studentRequestDto){
        studentDetailsService.updateStudentDetails(id, studentRequestDto);
        return ResponseEntity.ok("Student updated Success");
    }

    @DeleteMapping(value = "delete/{id}")
    public ResponseEntity<String> deleteStudent(@PathVariable(name = "id") Long id){
        studentDetailsService.deleteStudent(id);
        return ResponseEntity.ok("Student deleted Success");
    }

    @GetMapping("/get-all/{studentClass}")
    public List<StudentResponseDto> getStudentList(@PathVariable("studentClass") String studentClass){
        return studentDetailsService.getStudentList(studentClass);
    }

}
