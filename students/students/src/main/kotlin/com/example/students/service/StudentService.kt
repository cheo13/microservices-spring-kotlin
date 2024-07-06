package com.example.students.service

import com.example.students.entity.Student
import com.example.students.repository.StudentRepository
import org.springframework.beans.factory.annotation.Autowired
import org.springframework.http.HttpStatus
import org.springframework.stereotype.Service
import org.springframework.web.server.ResponseStatusException

@Service
class StudentService {

    @Autowired
    lateinit var studentRepository: StudentRepository

    fun getAllStudents(): List<Student> {
        val students = studentRepository.findAll()
        if (students.isEmpty()) {
            throw ResponseStatusException(HttpStatus.NOT_FOUND, "No students found")
        }
        return students
    }

    fun getStudentById(id: Long): Student {
        return studentRepository.findById(id)
            .orElseThrow { ResponseStatusException(HttpStatus.NOT_FOUND, "Student not found with id: $id") }
    }

    fun createStudent(student: Student): Student {
        return studentRepository.save(student)
    }

    fun updateStudent(updatedStudent: Student): Student {
        return studentRepository.findById(updatedStudent.id!!)
            .map { existingStudent ->
                existingStudent.apply {
                    name = updatedStudent.name
                    age = updatedStudent.age
                    sex = updatedStudent.sex
                    email = updatedStudent.email
                }
                studentRepository.save(existingStudent)
            }.orElseThrow { ResponseStatusException(HttpStatus.NOT_FOUND, "Student not found with id: ${updatedStudent.id}") }
    }

    fun deleteStudent(id: Long): Boolean {
        return studentRepository.findById(id)
            .map { existingStudent ->
                studentRepository.delete(existingStudent)
                true
            }.orElseThrow { ResponseStatusException(HttpStatus.NOT_FOUND, "Student not found with id: $id") }
    }
}
