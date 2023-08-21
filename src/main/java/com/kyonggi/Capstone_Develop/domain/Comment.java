package com.kyonggi.Capstone_Develop.domain;

import com.kyonggi.Capstone_Develop.domain.student.Student;
import lombok.AccessLevel;
import lombok.NoArgsConstructor;

import javax.persistence.*;

@Entity
@Table(name = "comment")
@NoArgsConstructor(access = AccessLevel.PROTECTED)
public class Comment extends BaseEntity{
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;
    
    @ManyToOne
    @JoinColumn(name = "notice_board_id", nullable = false)
    private NoticeBoard noticeBoard;
    
    @ManyToOne
    @JoinColumn(name = "student_id", nullable = false)
    private Student student;
    
    @Column(name = "content", length = 255, nullable = false)
    private String content;
    
    public Comment(NoticeBoard noticeBoard, Student student, String content) {
        this.noticeBoard = noticeBoard;
        this.student = student;
        this.content = content;
    }
}
