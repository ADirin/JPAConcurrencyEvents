import javax.persistence.AttributeConverter;
import javax.persistence.Converter;
import java.sql.Date;
import java.time.LocalDate;

@Converter(autoApply = true)
public class LocalDateConverter implements AttributeConverter<Date, LocalDate> {

    @Override
    public LocalDate convertToDatabaseColumn(Date attribute) {
        return attribute != null ? attribute.toLocalDate() : null;
    }

    @Override
    public Date convertToEntityAttribute(LocalDate dbData) {
        return dbData != null ? Date.valueOf(dbData) : null;
    }
}
