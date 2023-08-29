package com.kyonggi.Capstone_Develop.domain.schedule;

import com.kyonggi.Capstone_Develop.domain.BaseEntity;
import com.kyonggi.Capstone_Develop.exception.DateMismatchException;
import lombok.AccessLevel;
import lombok.Getter;
import lombok.NoArgsConstructor;

import javax.persistence.*;
import java.time.LocalDate;

import static java.time.LocalDate.now;

@Getter
@Entity
@Table(name = "schedule")
@NoArgsConstructor(access = AccessLevel.PROTECTED)
public class Schedule extends BaseEntity {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "schedule_id")
    private Long id;
    
    @Enumerated(EnumType.STRING)
    @Column(name = "step", nullable = false)
    private Step step;
    
    @Enumerated(EnumType.STRING)
    @Column(name = "status", nullable = false)
    private Status status;
    
    @Column(name = "start_date", nullable = false)
    private LocalDate startDate;
    
    @Column(name = "end_date", nullable = false)
    private LocalDate endDate;
    
    public Schedule(Step step, Status status, LocalDate startDate, LocalDate endDate) {
        validateDate(startDate, endDate);
        this.step = step;
        this.status = status;
        this.startDate = startDate;
        this.endDate = endDate;
    }
    
    private void validateDate(LocalDate startDate, LocalDate endDate) {
        if (startDate.isAfter(endDate)) {
            throw new DateMismatchException();
        }
    }
    
    public void changeStartDate(LocalDate startDate) {
        this.startDate = startDate;
    }
    
    public void changeEndDate(LocalDate endDate) {
        this.endDate = endDate;
    }
    
    public void changeStatus() {
        this.status = Status.of(now(), startDate, endDate);
    }
}
