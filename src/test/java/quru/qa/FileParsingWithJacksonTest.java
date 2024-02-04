package quru.qa;

import com.fasterxml.jackson.databind.ObjectMapper;
import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import quru.qa.model.Flat;

import java.io.InputStream;
import java.io.InputStreamReader;
import java.io.Reader;

public class FileParsingWithJacksonTest {
    private final ClassLoader cl = FileParsingTest.class.getClassLoader();

    @DisplayName("Парсинг json c помощью библиотеки Jackson")
    @Test
    void jsonParsingTestNextLevel() throws Exception {
        try (InputStream is = cl.getResourceAsStream("flat.json");
             Reader reader = new InputStreamReader(is)) {
            ObjectMapper objectMapper = new ObjectMapper();
            Flat flat = objectMapper.readValue(reader, Flat.class);

            Assertions.assertEquals("Aleksandr", flat.getOwner());
            Assertions.assertEquals(75, flat.getSquare());
            Assertions.assertEquals(15, flat.getNumber());
            Assertions.assertArrayEquals(
                    new String[]{"lounge", "kitchen", "bathroom", "bedroom"},
                    flat.getRooms().toArray()
            );
            Assertions.assertEquals("Moscow", flat.getAddress().getCity());
            Assertions.assertEquals(101000, flat.getAddress().getIndex());
            Assertions.assertEquals("Lenina", flat.getAddress().getStreet());
            Assertions.assertEquals(42, flat.getAddress().getHouse());
        }
    }
}
