package ru.he.repositories;

import ru.he.model.FileInfo;

import java.util.List;
import java.util.Optional;

public interface FileInfoRepository extends CrudRepository<Long, FileInfo> {

    List<FileInfo> findAll();

    Optional<FileInfo> findByStorageFileName(String storageFileName);

}
