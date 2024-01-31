package com.kyonggi.Capstone_Develop.repository;

import com.kyonggi.Capstone_Develop.domain.schedule.ScheduleBoard;
import org.springframework.data.jpa.repository.JpaRepository;

public interface ScheduleBoardRepository extends JpaRepository<ScheduleBoard, Long> {
}
