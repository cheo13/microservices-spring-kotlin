package com.example.students.entity

import jakarta.persistence.*
import java.time.LocalDateTime

@Entity
@Table(name = "students")
class Student {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "id", updatable = false)
    var id: Long? = null

    @Column(name = "name", nullable = false)
    var name: String? = null

    @Column(name = "age", nullable = false)
    var age: Int? = null

    @Column(name = "sex", nullable = false)
    var sex: String? = null

    @Column(name = "email", nullable = false, unique = true)
    var email: String? = null
}
