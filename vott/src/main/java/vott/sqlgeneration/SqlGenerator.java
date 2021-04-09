package vott.sqlgeneration;

import java.util.Arrays;
import java.util.List;
import java.util.stream.Collectors;

public class SqlGenerator {

    public String generatePartialUpsertSql(TableDetails tableDetails) {
        return generateUpsertSql(
            tableDetails,
            generateUpdatePlaceholders(
                tableDetails.getPrimaryKeyColumnName()
            )
        );
    }

    public String generateFullUpsertSql(TableDetails tableDetails) {
        return generateUpsertSql(
            tableDetails,
            generateUpdatePlaceholders(
                tableDetails.getPrimaryKeyColumnName(),
                tableDetails.getColumnNames()
            )
        );
    }

    private String generateUpsertSql(TableDetails tableDetails, String[] updatePlaceholders) {
        String[] valuePlaceholders = new String[tableDetails.getColumnNames().length];
        Arrays.fill(valuePlaceholders, "?");
        return String.format(
            "INSERT INTO `%s` (%s) VALUES (%s) ON DUPLICATE KEY UPDATE %s",
            tableDetails.getTableName(),
            Arrays.stream(tableDetails.getColumnNames())
                .map(c -> '`' + c + '`')
                .collect(Collectors.joining(", ")),
            String.join(", ", valuePlaceholders),
            String.join(", ", updatePlaceholders)
        );
    }

    private String[] generateUpdatePlaceholders(String primaryKey) {
        return generateUpdatePlaceholders(primaryKey, new String[0]);
    }

    private String[] generateUpdatePlaceholders(String primaryKey, String[] columnNames) {
        String nonNullPrimaryKey = primaryKey == null ? "id": primaryKey;

        String primaryKeyPlaceholder = String.format(
            "`%s` = LAST_INSERT_ID(`%s`)",
            nonNullPrimaryKey,
            nonNullPrimaryKey
        );

        if (columnNames.length == 0) {
            return new String[] { primaryKeyPlaceholder };
        }

        List<String> updatePlaceholders = Arrays.stream(columnNames)
            .filter(c -> !c.equals(nonNullPrimaryKey))
            .map(c -> String.format("`%s` = ?", c))
            .collect(Collectors.toList());

        updatePlaceholders.add(0, primaryKeyPlaceholder);

        return updatePlaceholders.toArray(new String[0]);
    }
}
