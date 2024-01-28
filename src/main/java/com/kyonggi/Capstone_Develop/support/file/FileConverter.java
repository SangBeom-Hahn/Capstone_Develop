package com.kyonggi.Capstone_Develop.support.file;

import com.kyonggi.Capstone_Develop.domain.file.FileExtension;
import com.kyonggi.Capstone_Develop.domain.file.RawFileData;
import com.kyonggi.Capstone_Develop.support.UuidHolder;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.util.CollectionUtils;
import org.springframework.web.multipart.MultipartFile;

import java.util.List;
import java.util.stream.Collectors;

@Slf4j
@RequiredArgsConstructor
public class FileConverter {

    public static RawFileData convertAssignmentFile(final MultipartFile file, UuidHolder uuidHolder) {
        if (file == null || file.isEmpty()) {
            return null;
        }

        final String originalFilename = file.getOriginalFilename();

        return new RawFileData(
                originalFilename,
                getUUID(uuidHolder) + FileExtension.getExtensionFromFileName(originalFilename),
                file
        );
    }

    public static List<RawFileData> convertAssignmentFiles(final List<MultipartFile> files, UuidHolder uuidHolder) {
        if (CollectionUtils.isEmpty(files)) {
            return List.of();
        }

        return files.stream()
                .map(
                        file -> {
                            final String originalFilename = file.getOriginalFilename();

                            return new RawFileData(
                                    originalFilename,
                                    getUUID(uuidHolder) + FileExtension.getExtensionFromFileName(originalFilename),
                                    file
                            );
                        }
                )
                .collect(Collectors.toList());
    }

    private static String getUUID(UuidHolder uuidHolder) {
        return uuidHolder.random();
    }
}
