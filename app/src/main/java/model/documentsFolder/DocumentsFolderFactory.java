package model.documentsFolder;

public class DocumentsFolderFactory {

    public static DocumentsFolder getDocumentsFolder(final String folderPath){
        return new DocumentsFolderImpl(folderPath);
    }

}
