package pl.sudokuboard.logger;

import ch.qos.cal10n.BaseName;
import ch.qos.cal10n.Locale;
import ch.qos.cal10n.LocaleData;

@BaseName("Exceptions")
@LocaleData({ @Locale("en_US"), @Locale("pl_PL") })
public enum I10N {
    WRONG_FIELD_VALUE,
    FILE_READ_ERROR,
    CLASS_NOT_FOUND_ERROR,
    FILE_SAVE_ERROR,
    NULL_CLONE_ATTEMPT,
    CLONE_NOT_SUPPORTED,
    INVALID_INDEX,
    NEW_GAME_CREATED,
    FAIL_CLONE_ATTEMPT,
    DB_CONNECTION_ERROR,
    DB_CREATE_SCHEMA_ERROR,
    DB_NULL_BOARD_ERROR,
    DB_QUERY_ERROR,
    DB_INSERT_BOARD_ERROR,
    DB_INSERT_FIELD_ERROR,
    DB_SELECT_BOARD_ERROR,
    DB_SELECT_FIELD_ERROR,
    DB_SELECT_BOARDS_ERROR,
}
