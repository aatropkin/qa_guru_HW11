package quru.qa;

import com.codeborne.selenide.Configuration;
import com.codeborne.selenide.FileDownloadMode;
import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.Test;

import java.io.File;
import java.io.FileInputStream;
import java.io.InputStream;
import java.nio.charset.StandardCharsets;

import static com.codeborne.selenide.Condition.text;
import static com.codeborne.selenide.Selenide.*;

public class SelenideFilesTest {

//    static {
//        Configuration.fileDownload = FileDownloadMode.PROXY;
//    }


    @Test
    void downloadFileTest() throws Exception {
        open("https://github.com/junit-team/junit5/blob/main/README.md");
        File downloaded = $("a[data-testid='raw-button']").download();


        try (InputStream is = new FileInputStream(downloaded)) {
            byte[] content = is.readAllBytes();
            String contentAsString = new String(content, StandardCharsets.UTF_8);
            Assertions.assertTrue(contentAsString.contains("Latest"));
        }
    }

    @Test
    void fileUploadTest() {
        open("https://fineuploader.com/demos.html");
        sleep(4000);
        $("input[type='file']").uploadFromClasspath("dog.png");
        $(".qq-file-info").shouldHave(text("dog.png"));
        sleep(3000);

    }




}
