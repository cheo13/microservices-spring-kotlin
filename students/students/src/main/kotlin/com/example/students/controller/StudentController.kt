package com.example.students.controller

import com.example.students.entity.Student
import com.example.students.service.StudentService
import org.springframework.beans.factory.annotation.Autowired
import org.springframework.http.HttpStatus
import org.springframework.http.ResponseEntity
import org.springframework.web.bind.annotation.*

@RestController
@CrossOrigin(methods = [RequestMethod.GET, RequestMethod.POST, RequestMethod.PATCH, RequestMethod.PUT, RequestMethod.DELETE])
@RequestMapping("/students")
class StudentController {

    @Autowired
    lateinit var studentService: StudentService

    @GetMapping
    fun getAllStudents(): ResponseEntity<List<Student>> {
        val students = studentService.getAllStudents()
        return ResponseEntity(students, HttpStatus.OK)
    }

    @GetMapping("/{id}")
    fun getStudentById(@PathVariable("id") id: Long): ResponseEntity<Student> {
        val student = studentService.getStudentById(id)
        return ResponseEntity(student, HttpStatus.OK)
    }

    @PostMapping
    fun createStudent(@RequestBody student: Student): ResponseEntity<Student>{
        val createdStudent = studentService.createStudent(student)
        return ResponseEntity(createdStudent, HttpStatus.CREATED)
    }


    @PatchMapping("/{id}")
    fun updateStudent(@PathVariable id: Long, @RequestBody studentDetails: Student): ResponseEntity<Student> {
        return ResponseEntity(studentService.updateStudent(studentDetails), HttpStatus.OK)
    }

    @DeleteMapping("/{id}")
    fun deleteStudent(@PathVariable("id") id: Long): Boolean? {
        studentService.deleteStudent(id)
        return true
    }
}