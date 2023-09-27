package com.kyonggi.Capstone_Develop.service;

import com.kyonggi.Capstone_Develop.domain.schedule.Schedule;
import com.kyonggi.Capstone_Develop.domain.schedule.ScheduleBoard;
import com.kyonggi.Capstone_Develop.exception.NotFoundScheduleBoardException;
import com.kyonggi.Capstone_Develop.exception.NotFoundScheduleException;
import com.kyonggi.Capstone_Develop.repository.ScheduleBoardRepository;
import com.kyonggi.Capstone_Develop.repository.ScheduleRepository;
import com.kyonggi.Capstone_Develop.service.dto.schedule.AllScheduleResponseDto;
import com.kyonggi.Capstone_Develop.service.dto.schedule.ScheduleResponseDto;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.time.LocalDate;
import java.util.List;
import java.util.stream.Collectors;

import static com.kyonggi.Capstone_Develop.utils.ConstantMessage.SCHEDULE_NUMBER;

@Service
@Transactional
@RequiredArgsConstructor
public class ScheduleService {
    private final ScheduleRepository scheduleRepository;
    
    private final ScheduleBoardRepository scheduleBoardRepository;
    
    public AllScheduleResponseDto findAllSchedule() {
        List<Schedule> schedules = scheduleRepository.findAll();
        List<ScheduleResponseDto> scheduleResponseDtos = schedules.stream()
                .map(schedule -> ScheduleResponseDto.from(schedule))
                .collect(Collectors.toList());
    
        ScheduleBoard scheduleBoard = scheduleBoardRepository.findById(SCHEDULE_NUMBER)
                .orElseThrow(() -> new NotFoundScheduleBoardException(SCHEDULE_NUMBER));
    
        return AllScheduleResponseDto.of(scheduleBoard, scheduleResponseDtos);
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
