package com.kyonggi.Capstone_Develop.domain.file;

import com.kyonggi.Capstone_Develop.exception.InvalidFileExtensionException;
import lombok.Getter;
import lombok.RequiredArgsConstructor;

import java.util.Arrays;

@Getter
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

    public static String getExtensionFromFileName(final String uploadFileName) {
        String fileExtension = extractFileExtension(uploadFileName);

        FileExtension findFileExtension = Arrays.stream(values())
                .filter(extension -> extension.value.equals(fileExtension))
                .findFirst()
                .orElseThrow(() -> new InvalidFileExtensionException(fileExtension));

        return findFileExtension.getValue();
    }

    private static String extractFileExtension(String uploadFileName) {
        return uploadFileName.substring(uploadFileName.lastIndexOf("."));
    }

    public static boolean isValidExtension(final String fileName) {
        final String fileExtension = extractFileExtension(fileName);

        return Arrays.stream(values())
                .anyMatch(extension -> extension.value.equals(fileExtension));
    }
}
