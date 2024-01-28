package com.kyonggi.Capstone_Develop.domain.noticeboard;

import com.kyonggi.Capstone_Develop.domain.BaseEntity;
import lombok.AccessLevel;
import lombok.Getter;
import lombok.NoArgsConstructor;

import javax.persistence.*;

@Getter
@Entity
@NoArgsConstructor(access = AccessLevel.PROTECTED)
@Table(name = "notice_board_upload_file")
public class UploadFile extends BaseEntity {
    @Id @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "upload_file_id")
    private Long id;

    @Column(name = "upload_file_name", length = 255, nullable = false)
    private String uploadFileName;

    @Column(name = "store_file_name", length = 255, nullable = false)
    private String storeFilename;

    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "notice_board_id", foreignKey = @ForeignKey(name = "fk_upload_file_notice_board"), nullable = false)
    private NoticeBoard noticeBoard;

    public UploadFile(String uploadFileName, String storeFilename, NoticeBoard noticeBoard) {
        this.uploadFileName = uploadFileName;
        this.storeFilename = storeFilename;
        this.noticeBoard = noticeBoard;
    }
}
