import javax.persistence.AttributeConverter;
import javax.persistence.Converter;

@Converter
public class ActiveStatusConverter implements AttributeConverter<Boolean, String> {

    @Override
    public String convertToDatabaseColumn(Boolean isActive) {
        return (isActive != null && isActive) ? "Y" : "N";
    }

    @Override
    public Boolean convertToEntityAttribute(String dbData) {
        return "Y".equalsIgnoreCase(dbData);
    }
}
