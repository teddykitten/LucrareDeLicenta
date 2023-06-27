package com.example.licentaapp.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import ucv.ro.catalina.ilie.licenta.model.Schedule;

public interface ScheduleRepository extends JpaRepository<Schedule, Long> {
}
