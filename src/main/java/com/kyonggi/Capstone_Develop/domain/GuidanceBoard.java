package com.kyonggi.Capstone_Develop.domain;

import lombok.AccessLevel;
import lombok.Getter;
import lombok.NoArgsConstructor;

import javax.persistence.*;

@Entity
@Getter
@Table(name = "guidance_board")
@NoArgsConstructor(access = AccessLevel.PROTECTED)
public class GuidanceBoard extends BaseEntity{
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "guidance_board_id")
    private Long id;
    
    @Column(name = "content", nullable = false, length = 15000)
    private String content;
    
    public GuidanceBoard(String content) {
        this.content = content;
    }
    
    public void changeContent(String content) {
        this.content = content;
    }
}
