package com.example.grades.controller

import com.example.grades.client.StudentFeignClient
import com.example.grades.dto.StudentDto
import com.example.grades.entity.Grade
import com.example.grades.service.GradeService
import org.springframework.beans.factory.annotation.Autowired
import org.springframework.http.HttpStatus
import org.springframework.http.ResponseEntity
import org.springframework.web.bind.annotation.*

@RestController
@CrossOrigin(methods = [RequestMethod.GET, RequestMethod.POST, RequestMethod.PATCH, RequestMethod.PUT, RequestMethod.DELETE])
@RequestMapping("/grades")
class GradeController(private val studentFeignClient: StudentFeignClient) {

    @Autowired
    lateinit var gradeService: GradeService

    @GetMapping
    fun getAllGrades(): ResponseEntity<List<Grade>> {
        val grades = gradeService.getAllGrades()
        return ResponseEntity(grades, HttpStatus.OK)
    }


    @GetMapping("/students")
    fun getAllStudents(): ResponseEntity<Map<String, Any>> {
        val students = studentFeignClient.getStudents()
        val grades = gradeService.getAllGrades()

        val response = mapOf(
            "students" to students,
            "grades" to grades
        )
        return ResponseEntity.ok(response)
    }
    @GetMapping("/{id}")
    fun getGradeById(@PathVariable("id") id: Long): ResponseEntity<Grade> {
        val grade = gradeService.getGradeById(id)
        return if (grade != null) {
            ResponseEntity(grade, HttpStatus.OK)
        } else {
            ResponseEntity(HttpStatus.NOT_FOUND)
        }
    }

    @PostMapping
    fun createGrade(@RequestBody grade: Grade): ResponseEntity<Grade> {
        val createdGrade = gradeService.createGrade(grade)
        return ResponseEntity(createdGrade, HttpStatus.CREATED)
    }

    @PatchMapping("/{id}")
    fun updateGrade(@PathVariable id: Long, @RequestBody gradeDetails: Grade): ResponseEntity<Grade> {
        val updatedGrade = gradeService.updateGrade(id, gradeDetails)
        return if (updatedGrade != null) {
            ResponseEntity(updatedGrade, HttpStatus.OK)
        } else {
            ResponseEntity(HttpStatus.NOT_FOUND)
        }
    }

    @DeleteMapping("/{id}")
    fun deleteGrade(@PathVariable("id") id: Long): ResponseEntity<Void> {
        return if (gradeService.deleteGrade(id)) {
            ResponseEntity(HttpStatus.NO_CONTENT)
        } else {
            ResponseEntity(HttpStatus.NOT_FOUND)
        }
    }
}
