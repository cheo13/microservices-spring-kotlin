package com.example.students

import org.springframework.boot.autoconfigure.SpringBootApplication
import org.springframework.boot.runApplication
import org.springframework.cloud.client.discovery.EnableDiscoveryClient

@SpringBootApplication
@EnableDiscoveryClient
class StudentsApplication

fun main(args: Array<String>) {
	runApplication<StudentsApplication>(*args)
}
