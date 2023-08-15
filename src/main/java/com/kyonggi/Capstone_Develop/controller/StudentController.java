package com.kyonggi.Capstone_Develop.controller;

import com.kyonggi.Capstone_Develop.controller.dto.student.StudentRequest;
import com.kyonggi.Capstone_Develop.service.StudentService;
import com.kyonggi.Capstone_Develop.service.dto.student.StudentSignUpResponseDto;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import javax.validation.Valid;
import java.net.URI;

@RestController
@RequestMapping("/api/students")
public class StudentController {
    private final StudentService studentService;
    
    public StudentController(StudentService studentService) {
        this.studentService = studentService;
    }

    @PostMapping
    public ResponseEntity<StudentSignUpResponseDto> join(@RequestBody @Valid StudentRequest studentRequest) {
        StudentSignUpResponseDto studentSignUpResponseDto = studentService.save(studentRequest.toServiceDto());
        return ResponseEntity
                .created(URI.create("/api/students/" + studentSignUpResponseDto.getId()))
                .body(studentSignUpResponseDto);
    }
}
