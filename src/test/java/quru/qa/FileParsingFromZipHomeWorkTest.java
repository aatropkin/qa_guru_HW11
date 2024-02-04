package quru.qa;

import com.codeborne.pdftest.PDF;
import com.codeborne.xlstest.XLS;
import com.opencsv.CSVReader;
import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;

import java.io.InputStream;
import java.io.InputStreamReader;
import java.util.List;
import java.util.zip.ZipEntry;
import java.util.zip.ZipInputStream;

@DisplayName("Чтения и проверка файлов PDF, ZIP, XLSX из zip")
public class FileParsingFromZipHomeWorkTest {
    private final ClassLoader cl = FileParsingTest.class.getClassLoader();

    @Test
    @DisplayName("Чтения и проверка CSV файла из архива ZIP")
    void zipParsingCsvTest() throws Exception {
        try (InputStream is = cl.getResourceAsStream("homework.zip");
             ZipInputStream zis = new ZipInputStream(is)) {
            ZipEntry entry;
            while ((entry = zis.getNextEntry()) != null) {
                if (entry.getName().endsWith(".csv")) {
                    CSVReader csvReader = new CSVReader(new InputStreamReader(zis));
                    List<String[]> content = csvReader.readAll();

                    Assertions.assertArrayEquals(new String[]{"salary", "100500"}, content.get(0));
                    Assertions.assertArrayEquals(new String[]{"time", "8"}, content.get(1));
                }
            }
        }
    }


    @Test
    @DisplayName("Чтения и проверка XLSX файла из архива ZIP")
    void zipXlsxParsingTest() throws Exception {
        try (InputStream is = cl.getResourceAsStream("homework.zip");
             ZipInputStream zis = new ZipInputStream(is)) {
            ZipEntry entry;
            while ((entry = zis.getNextEntry()) != null) {
                if (entry.getName().endsWith(".xlsx")) {
                    XLS xls = new XLS(zis);
                    Assertions.assertEquals("Name", xls.excel.getSheet("Лист1").getRow(0).getCell(0).getStringCellValue());
                    Assertions.assertEquals("age", xls.excel.getSheet("Лист1").getRow(1).getCell(0).getStringCellValue());
                    Assertions.assertEquals("Petrov", xls.excel.getSheet("Лист1").getRow(0).getCell(1).getStringCellValue());
                    Assertions.assertEquals(99.0, xls.excel.getSheet("Лист1").getRow(1).getCell(1).getNumericCellValue());
                }
            }
        }
    }

    @Test
    @DisplayName("Чтение и проверка  PDF файла из архива ZIP")
    void zipPdfParsingTest() throws Exception {
        try (InputStream is = cl.getResourceAsStream("homework.zip");
             ZipInputStream zis = new ZipInputStream(is)) {
            ZipEntry entry;
            while ((entry = zis.getNextEntry()) != null) {
                if (entry.getName().endsWith(".pdf")) {
                    PDF pdf = new PDF(zis);
                    Assertions.assertTrue(pdf.text.contains("PDF Test File"));
                    Assertions.assertEquals(" PDF Test Page", pdf.title.toString());
                }
            }
        }
    }
}
