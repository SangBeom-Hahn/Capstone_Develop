package com.kyonggi.Capstone_Develop.support;

import com.kyonggi.Capstone_Develop.service.ScheduleService;
import lombok.RequiredArgsConstructor;
import org.springframework.scheduling.annotation.EnableScheduling;
import org.springframework.scheduling.annotation.Scheduled;
import org.springframework.stereotype.Component;

@Component
@EnableScheduling
@RequiredArgsConstructor
public class ScheduleChecker {
    
    private final ScheduleService scheduleService;
    
    @Scheduled(cron = "0 0 0 * * ?") // logic to be executed 'every day at 00:00'
    public void updateSchedulesState() {
        scheduleService.updateScheduleState();
    }
}
