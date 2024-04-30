import org.junit.jupiter.api.Test;
import java.sql.Date;
import java.time.LocalDate;

import static org.junit.jupiter.api.Assertions.assertEquals;

public class LocalDateConverterTest {

    private final LocalDateConverter converter = new LocalDateConverter();

    @Test
    public void testConvertToDatabaseColumn() {
        // It creates a LocalDate object representing a specific date.
        LocalDate localDate = LocalDate.of(2024, 4, 26);
//Then, it invokes the convertToDatabaseColumn
// method to convert this LocalDate object to a java.sql.Date object.
        // When
        Date expectedDate = Date.valueOf(localDate);
        Date actualDate = Date.valueOf(converter.convertToDatabaseColumn(Date.valueOf(localDate)));
//Finally, it asserts that the converted Date object matches
// the expected Date object created directly from the LocalDate object.
        // Then
        assertEquals(expectedDate.getTime(), actualDate.getTime());
    }

    @Test
    public void testConvertToEntityAttribute() {
        // It creates a java.sql.Date object representing a specific date.
        Date date = Date.valueOf(LocalDate.of(2024, 4, 26));

        // Then, it invokes the convertToEntityAttribute method
        // to convert this Date object to a LocalDate object.
        LocalDate expectedLocalDate = date.toLocalDate();
        LocalDate actualLocalDate = converter.convertToEntityAttribute(date.toLocalDate()).toLocalDate();

        // Finally, it asserts that the converted LocalDate object matches the expected LocalDate
        // object obtained by calling the toLocalDate method on the original Date object.
        assertEquals(expectedLocalDate, actualLocalDate);
    }
}
