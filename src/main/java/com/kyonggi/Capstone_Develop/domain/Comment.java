package com.kyonggi.Capstone_Develop.domain;

import com.kyonggi.Capstone_Develop.domain.student.Student;
import lombok.AccessLevel;
import lombok.Getter;
import lombok.NoArgsConstructor;

import javax.persistence.*;

@Entity
@Getter
@Table(name = "comment")
@NoArgsConstructor(access = AccessLevel.PROTECTED)
public class Comment extends BaseEntity{
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "comment_id")
    private Long id;
    
    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "notice_board_id", nullable = false)
    private NoticeBoard noticeBoard;
    
    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "student_id", nullable = false)
    private Student student;
    
    @Column(name = "content", length = 255, nullable = false)
    private String content;
    
    public Comment(NoticeBoard noticeBoard, Student student, String content) {
        this.noticeBoard = noticeBoard;
        this.student = student;
        this.content = content;
    }
    
    public void changeContent(String content) {
        this.content = content;
        
    }
}
