package com.kyonggi.Capstone_Develop.domain;

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
    private Long id;
    
    @Column(name = "content", length = 255, nullable = false)
    private String content;
    
    @Column(name = "fix", nullable = false)
    private boolean fix;
    
    @Column(name = "title", length = 255, nullable = false)
    private String title;
    
    @Column(name = "views", nullable = false)
    private Integer views;
    
    @OneToMany(mappedBy = "noticeBoard")
    private List<Comment> comments = new ArrayList<>();
    
    public NoticeBoard(String content, boolean fix, String title, Integer views) {
        this.content = content;
        this.fix = fix;
        this.title = title;
        this.views = views;
    }
    
    public void changeContent(String content) {
        this.content = content;
    }
    
    public void changeFix(boolean fix) {
        this.fix = fix;
    }
    
    public void changeTitle(String title) {
        this.title = title;
    }
}
