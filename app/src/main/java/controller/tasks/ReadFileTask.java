package controller.tasks;

import model.page.PageFactory;
import model.page.PageFactoryImpl;
import model.sharedResources.SharedResources;
import org.apache.pdfbox.pdmodel.PDDocument;
import org.apache.pdfbox.pdmodel.encryption.AccessPermission;
import org.apache.pdfbox.text.PDFTextStripper;

import java.io.File;
import java.io.IOException;
import java.nio.charset.StandardCharsets;

public class ReadFileTask implements Task{

    private final String fileNameToParse;
    private final SharedResources sharedResources;

    public ReadFileTask(
            final String fileNameToParse,
            final SharedResources sharedResources){
        this.fileNameToParse = fileNameToParse;
        this.sharedResources = sharedResources;
    }

    @Override
    public void execute() {
        try {
            File fileToParse = new File(sharedResources.getArgsContainer().getDocumentPath() + File.separator + fileNameToParse);
            PDDocument document = PDDocument.load(fileToParse);
            AccessPermission ap = document.getCurrentAccessPermission();
            if (ap.canExtractContent()) {
                PageFactory pageFactory = new PageFactoryImpl();
                PDFTextStripper stripper = new PDFTextStripper();
                stripper.setSortByPosition(false);
                stripper.setStartPage(1);
                stripper.setEndPage(document.getNumberOfPages());
                String documentContent = new String(stripper.getText(document).trim().getBytes(), StandardCharsets.UTF_8);
                document.close();
                new PageTask(
                        pageFactory.makePage(documentContent), sharedResources
                ).execute();
            } else {
                System.out.println(fileToParse.getName() + " skipped, you do not have permission to extract text");
            }
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

}
