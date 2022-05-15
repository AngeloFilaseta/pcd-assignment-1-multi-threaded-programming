package jpf.model.documentsFolder;

import model.documentsFolder.DocumentsFolder;

import java.io.File;
import java.util.Arrays;
import java.util.HashSet;
import java.util.Set;

public class DocumentsFolderJPF implements DocumentsFolder {

    private final Set<String> documentsName;

    public DocumentsFolderJPF(final String folderPath) {
        final String[] fileNames = new File(folderPath).list();
        this.documentsName = new HashSet<>();
        if(fileNames != null){
            this.documentsName.addAll(Arrays.asList(fileNames));
        } else{
            this.documentsName.addAll(Arrays.asList("Test1.pdf", "Test2.pdf"));
        }
    }

    @Override
    public Set<String> getPDFDocumentsName() {
        return documentsName;
    }

}
