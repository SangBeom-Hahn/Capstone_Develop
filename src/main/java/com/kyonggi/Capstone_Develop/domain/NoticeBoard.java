package com.kyonggi.Capstone_Develop.domain;

import com.kyonggi.Capstone_Develop.domain.student.Student;
import lombok.AccessLevel;
import lombok.Getter;
import lombok.NoArgsConstructor;

import javax.persistence.*;
import java.util.ArrayList;
import java.util.List;

@Entity
@Getter
@Table(name = "notice_board")
@NoArgsConstructor(access = AccessLevel.PROTECTED)
public class NoticeBoard extends BaseEntity {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "notice_board_id")
    private Long id;
    
    @Column(name = "content", length = 255, nullable = false)
    private String content;
    
    @Column(name = "fix", nullable = false)
    private Boolean fix;
    
    @Column(name = "title", length = 255, nullable = false)
    private String title;
    
    @Column(name = "views", nullable = false)
    private Integer views;
    
    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "student_id", foreignKey = @ForeignKey(name = "fk_notice_board_student"), nullable = false)
    private Student author;
    
    @OneToMany(mappedBy = "noticeBoard", fetch = FetchType.LAZY)
    private List<Comment> comments = new ArrayList<>();
    
    public NoticeBoard(String content, Boolean fix, String title, Integer views, Student author) {
        this.content = content;
        this.fix = fix;
        this.title = title;
        this.views = views;
        this.author = author;
    }
    
    public void changeContent(String content) {
        this.content = content;
    }
    
    public void changeFix(Boolean fix) {
        this.fix = fix;
    }
    
    public void changeTitle(String title) {
        this.title = title;
    }
    
    public void view() {
        this.views += 1;
    }
    
    public void addComment(Comment comment) {
        this.comments.add(comment);
    }
    
    public boolean isAuthor(Long authorId) {
        return this.author.isSameStudent(authorId);
    }
    
    public String getAuthorLoginId() {
        return this.author.getLoginId();
    }
}
