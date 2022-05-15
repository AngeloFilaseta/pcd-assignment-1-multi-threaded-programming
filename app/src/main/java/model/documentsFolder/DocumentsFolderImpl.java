package model.documentsFolder;

import java.io.File;
import java.util.*;

public class DocumentsFolderImpl implements DocumentsFolder {

    private final Set<String> documentsName;
    private final static String VALID_EXTENSION = ".pdf";

    protected DocumentsFolderImpl(final String folderPath) {
        String[] fileNames = new File(folderPath).list((file, s)-> s.endsWith(VALID_EXTENSION));
        this.documentsName = new HashSet<>();
        if (fileNames != null) {
            this.documentsName.addAll(Arrays.asList(fileNames));
        }
    }

    @Override
    public Set<String> getPDFDocumentsName() {
        return documentsName;
    }

}
