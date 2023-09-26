package skylee.storage;

import java.time.format.DateTimeFormatter;
import java.util.Locale;

/**
 * Defines string literals or configurations used for file storage.
 */
public class Config {
    public static final String PATH_SAVE = "./data/skylee.txt";
    public static final String DELIMITER = " | ";
    public static final String DELIMITER_REGEX = " \\| ";
    public static final DateTimeFormatter DATE_TIME_FORMAT =
            DateTimeFormatter.ofPattern("d MMM yyyy", Locale.ENGLISH);
}
