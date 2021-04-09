package vott.sqlgeneration;

import lombok.Data;

@Data
public class TableDetails {
    private String tableName;
    private String primaryKeyColumnName;
    private String[] columnNames;
}
