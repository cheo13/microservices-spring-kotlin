package com.example.grades.entity

import jakarta.persistence.*
import java.util.Date

@Entity
@Table(name = "grades")
class Grade {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "id", updatable = false)
    var id: Long? = null

    @Column(name = "subject", nullable = false)
    var subject: String? = null

    @Column(name = "grade", nullable = false)
    var grade: String? = null

    @Column(name = "semester", nullable = false)
    var semester: String? = null

    @Column(name = "year", nullable = false)
    var year: Int? = null

}
