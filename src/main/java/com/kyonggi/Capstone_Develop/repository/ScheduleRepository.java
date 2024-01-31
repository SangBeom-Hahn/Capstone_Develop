package com.kyonggi.Capstone_Develop.repository;

import com.kyonggi.Capstone_Develop.domain.schedule.Schedule;
import org.springframework.data.jpa.repository.JpaRepository;

public interface ScheduleRepository extends JpaRepository<Schedule, Long> {
}
