package com.kyonggi.Capstone_Develop.support.file;

import com.kyonggi.Capstone_Develop.domain.file.RawFileData;
import com.kyonggi.Capstone_Develop.exception.NotUploadException;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Component;

import java.io.File;
import java.io.IOException;
import java.util.List;

@Slf4j
@Component
public class Uploader {
    @Value("${file.dir}")
    private String fileDir;

    private String getFullPath(String filename) {
        return fileDir + filename;
    }

    public void uploadFiles(final List<RawFileData> rawFileDatas) {
        rawFileDatas.stream()
                .forEach(rawFileData -> uploadFile(rawFileData));
    }

    public void uploadFile(final RawFileData rawFileData) {
        validateFileExists(rawFileData);
        sendFileToStore(rawFileData);
    }

    private void sendFileToStore(RawFileData rawFileData) {
        try {
            rawFileData.getMultipartFile()
                    .transferTo(
                            new File(getFullPath(rawFileData.getStoreFileName()))
                    );
        } catch (IOException e) {
            log.error("파일 업로드에 실패했습니다. {}", e.getMessage());
            throw new RuntimeException("파일 업로드에 실패했습니다.");
        }
    }

    private void validateFileExists(final RawFileData rawFileData) {
        if (rawFileData == null) {
            throw new NotUploadException();
        }
    }
}
