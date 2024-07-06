package com.example.grades.service

import com.example.grades.client.StudentFeignClient
import com.example.grades.dto.StudentDto
import com.example.grades.entity.Grade
import com.example.grades.repository.GradeRepository
import org.springframework.beans.factory.annotation.Autowired
import org.springframework.http.HttpStatus
import org.springframework.stereotype.Service
import org.springframework.web.server.ResponseStatusException

@Service
class GradeService(private val studentClient: StudentFeignClient) {

    @Autowired
    lateinit var gradeRepository: GradeRepository

    fun getStudents(): List<StudentDto> {
        return studentClient.getStudents()
    }

    fun getAllGrades(): List<Grade> {
        val grades = gradeRepository.findAll()
        if (grades.isEmpty()) {
            throw ResponseStatusException(HttpStatus.NOT_FOUND, "No grades found")
        }
        return grades
    }

    fun getGradeById(id: Long): Grade {
        return gradeRepository.findById(id)
            .orElseThrow { ResponseStatusException(HttpStatus.NOT_FOUND, "Grade not found with id: $id") }
    }

    fun createGrade(grade: Grade): Grade {
        return gradeRepository.save(grade)
    }

    fun updateGrade(id: Long, gradeDetails: Grade): Grade {
        val existingGrade = gradeRepository.findById(id)
            .orElseThrow { ResponseStatusException(HttpStatus.NOT_FOUND, "Grade not found with id: $id") }

        existingGrade.apply {
            subject = gradeDetails.subject
            grade = gradeDetails.grade
            semester = gradeDetails.semester
            year = gradeDetails.year
        }
        return gradeRepository.save(existingGrade)
    }

    fun deleteGrade(id: Long): Boolean {
        val existingGrade = gradeRepository.findById(id)
            .orElseThrow { ResponseStatusException(HttpStatus.NOT_FOUND, "Grade not found with id: $id") }
        gradeRepository.delete(existingGrade)
        return true
    }
}
