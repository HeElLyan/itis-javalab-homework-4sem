package ru.he.repositories.impl;

import ru.he.repositories.FileInfoRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.dao.EmptyResultDataAccessException;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.jdbc.core.RowMapper;
import org.springframework.jdbc.support.GeneratedKeyHolder;
import org.springframework.jdbc.support.KeyHolder;
import org.springframework.stereotype.Component;
import ru.he.model.FileInfo;

import java.sql.PreparedStatement;
import java.sql.Statement;
import java.util.List;
import java.util.Optional;

@Component
public class FileInfoRepositoryImpl implements FileInfoRepository {

    //language=SQL
    public static final String SQL_SELECT_ALL = "SELECT * FROM file_info";

    //language=SQL
    public static final String SQL_SELECT_BY_ID = "SELECT * FROM file_info WHERE id = ?";

    //language=SQL
    public static final String SQL_SELECT_BY_STORAGE_NAME = "SELECT * FROM file_info WHERE storage_filename = ?";

    //language=SQL
    private static final String SQL_UPDATE = "UPDATE file_info SET storage_filename = ?, original_filename = ?, size = ?, type = ?, url = ? WHERE id = ?";

    //language=SQL
    private static final String SQL_DELETE_BY_ID = "DELETE FROM file_info WHERE id = ?";

    //language=SQL
    private static final String SQL_INSERT = "INSERT INTO file_info(storage_filename, original_filename, size, type, url) VALUES (?, ?, ?, ?, ?)";

    @Autowired
    private JdbcTemplate jdbcTemplate;

    private RowMapper<FileInfo> fileInfoRowMapper = (row, rowNumber) ->
            FileInfo.builder()
                    .id(row.getBigDecimal("id").longValue())
                    .storageFileName(row.getString("storage_filename"))
                    .originalFileName(row.getString("original_filename"))
                    .size(row.getLong("size"))
                    .type(row.getString("type"))
                    .path(row.getString("url"))
                    .build();

    @Override
    public List<FileInfo> findAll() {
        return jdbcTemplate.query(SQL_SELECT_ALL, fileInfoRowMapper);
    }

    @Override
    public Optional<FileInfo> findByStorageFileName(String storageFileName) {
        try {
            FileInfo fileInfo = jdbcTemplate.queryForObject(SQL_SELECT_BY_STORAGE_NAME, fileInfoRowMapper, storageFileName);
            return Optional.ofNullable(fileInfo);
        } catch (EmptyResultDataAccessException e) {
            return Optional.empty();
        }
    }

    @Override
    public Optional<FileInfo> find(Long id) {
        try {
            FileInfo fileInfo = jdbcTemplate.queryForObject(SQL_SELECT_BY_ID, fileInfoRowMapper, id);
            return Optional.ofNullable(fileInfo);
        } catch (EmptyResultDataAccessException e) {
            return Optional.empty();
        }
    }

    @Override
    public void update(FileInfo entity) {
        jdbcTemplate.update(SQL_UPDATE, entity.getStorageFileName(), entity.getOriginalFileName(), entity.getSize().toString(), entity.getType(), entity.getType(), entity.getId());
    }

    @Override
    public void save(FileInfo entity) {
        KeyHolder keyHolder = new GeneratedKeyHolder();

        jdbcTemplate.update(connection -> {
            PreparedStatement statement = connection
                    .prepareStatement(SQL_INSERT, Statement.RETURN_GENERATED_KEYS);
            statement.setString(1, entity.getStorageFileName());
            statement.setString(2, entity.getOriginalFileName());
            statement.setLong(3, entity.getSize());
            statement.setString(4, entity.getType());
            statement.setString(5, entity.getPath());
            return statement;
        }, keyHolder);

        entity.setId(keyHolder.getKey().longValue());}

    @Override
    public void delete(Long id) {
        jdbcTemplate.update(SQL_DELETE_BY_ID, id);
    }
}
