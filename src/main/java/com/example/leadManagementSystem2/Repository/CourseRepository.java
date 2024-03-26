package com.example.leadManagementSystem2.Repository;

import org.springframework.data.jpa.repository.JpaRepository;

import com.example.leadManagementSystem2.Entity.Course;

public interface CourseRepository extends JpaRepository<Course, Long> {

}
