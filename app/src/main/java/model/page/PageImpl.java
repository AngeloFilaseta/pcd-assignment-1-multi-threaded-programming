package model.page;

import java.util.Arrays;
import java.util.List;
import java.util.Objects;

public class PageImpl implements Page {

    private final static String DELIMITER = "\\s+";
    private final String pageContent;

    public PageImpl(final String content) {
        this.pageContent = content;
    }

    @Override
    public List<String> getWords() {
        return split(removePunctuation(pageContent.toLowerCase()));
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        PageImpl page = (PageImpl) o;
        return Objects.equals(pageContent, page.pageContent);
    }

    @Override
    public int hashCode() {
        return Objects.hash(pageContent);
    }


    private String removePunctuation(final String string){
        return string.replaceAll("\\p{Punct}", "");
    }

    private List<String> split(final String string){
        return Arrays.asList(string.split(DELIMITER));
    }
}
