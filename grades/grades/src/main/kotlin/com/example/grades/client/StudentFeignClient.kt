package com.example.grades.client

import com.example.grades.dto.StudentDto
import org.springframework.cloud.openfeign.FeignClient
import org.springframework.web.bind.annotation.GetMapping

//@FeignClient(name = "STUDENT-MS", url = "\${external.student.api.base-url}")
@FeignClient(name = "student-ms")
interface StudentFeignClient {

    @GetMapping("/students")
    fun getStudents():List<StudentDto>

}