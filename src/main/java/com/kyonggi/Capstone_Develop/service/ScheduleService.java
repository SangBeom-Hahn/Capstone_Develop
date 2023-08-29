package com.kyonggi.Capstone_Develop.service;

import com.kyonggi.Capstone_Develop.domain.schedule.Schedule;
import com.kyonggi.Capstone_Develop.exception.NotFoundScheduleException;
import com.kyonggi.Capstone_Develop.repository.ScheduleRepository;
import com.kyonggi.Capstone_Develop.service.dto.schedule.ScheduleResponseDto;
import com.kyonggi.Capstone_Develop.service.dto.schedule.SchedulesResponseDto;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.time.LocalDate;
import java.util.List;
import java.util.stream.Collectors;

@Service
@Transactional
@RequiredArgsConstructor
public class ScheduleService {
    private final ScheduleRepository scheduleRepository;
    
    public SchedulesResponseDto findAllSchedule() {
        List<Schedule> schedules = scheduleRepository.findAll();
        List<ScheduleResponseDto> scheduleResponseDtos = schedules.stream()
                .map(schedule -> ScheduleResponseDto.from(schedule))
                .collect(Collectors.toList());
    
        return SchedulesResponseDto.from(scheduleResponseDtos);
    }
    
    public void updateSchedule(Long id, LocalDate startDate, LocalDate endDate) {
        Schedule schedule = scheduleRepository.findById(id)
                .orElseThrow(() -> new NotFoundScheduleException(id));
    
        changeSchedule(startDate, endDate, schedule);
    }
    
    private void changeSchedule(LocalDate startDate, LocalDate endDate, Schedule schedule) {
        schedule.changeStartDate(startDate);
        schedule.changeEndDate(endDate);
        schedule.changeStatus();
    }
    
    public void updateScheduleState() {
        List<Schedule> schedules = scheduleRepository.findAll();
        schedules.stream()
                .forEach(schedule -> schedule.changeStatus());
    }
}
