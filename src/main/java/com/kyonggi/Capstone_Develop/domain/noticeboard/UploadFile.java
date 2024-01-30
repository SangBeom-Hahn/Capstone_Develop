package com.kyonggi.Capstone_Develop.domain.noticeboard;

import com.kyonggi.Capstone_Develop.domain.BaseEntity;
import com.kyonggi.Capstone_Develop.support.file.Uploader;
import lombok.AccessLevel;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.core.io.UrlResource;

import javax.persistence.*;
import java.net.MalformedURLException;

@Getter
@Entity
@Slf4j
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

    public UrlResource findResource() {
        try {
            return new UrlResource("file:" + getFullPath());
        } catch (MalformedURLException e) {
            log.error("파일을 불러오지 못 했습니다. {} ", e.getMessage());
            throw new RuntimeException(e);
        }
    }

    private String getFullPath() {
        return Uploader.getFullPath(storeFilename);
    }
}
