package com.kyonggi.Capstone_Develop.domain.file;

import com.kyonggi.Capstone_Develop.exception.InvalidFileExtensionException;
import lombok.RequiredArgsConstructor;

import java.util.Arrays;

@RequiredArgsConstructor
public enum FileExtension {
    TXT(".txt"),
    DOC(".doc"),
    DOCX(".docx"),
    HWP(".hwp"),
    HWPX(".hwpx"),
    PDF(".pdf"),
    PPT(".ppt"),
    PPTX(".pptx");

    private final String value;

    public static FileExtension getExtensionFromFileName(final String uploadFileName) {
        String fileExtension = extractFileExtension(uploadFileName);

        return Arrays.stream(values())
                .filter(extension -> extension.value.equals(fileExtension))
                .findFirst()
                .orElseThrow(() -> new InvalidFileExtensionException(fileExtension));
    }

    private static String extractFileExtension(String uploadFileName) {
        return uploadFileName.substring(uploadFileName.lastIndexOf("."));
    }
}
