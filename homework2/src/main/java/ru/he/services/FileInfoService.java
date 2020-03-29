package ru.he.services;

import ru.he.model.FileInfo;
import org.springframework.core.io.Resource;

public interface FileInfoService {

    FileInfo getFileInfoByStorageFilename(String url);

    Resource getResourceByFileInfo(FileInfo fileInfo);

    void save(FileInfo fileInfo);

}
