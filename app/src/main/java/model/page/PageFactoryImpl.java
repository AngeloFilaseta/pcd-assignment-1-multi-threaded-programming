package model.page;

public class PageFactoryImpl implements PageFactory {

    @Override
    public Page makePage(final String content) {
        return new PageImpl(content);
    }
}
