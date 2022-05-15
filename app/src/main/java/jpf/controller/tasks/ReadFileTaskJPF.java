package jpf.controller.tasks;

import controller.tasks.PageTask;
import controller.tasks.Task;
import jpf.model.util.SentenceGenerator;
import model.page.PageFactory;
import model.page.PageFactoryImpl;
import model.sharedResources.SharedResources;

public class ReadFileTaskJPF implements Task {

    private final SharedResources sharedResources;
    private final static int NUMBER_OF_SENTENCES = 10;

    public ReadFileTaskJPF(final SharedResources sharedResources){
        this.sharedResources = sharedResources;
    }

    @Override
    public void execute() {
        PageFactory pageFactory = new PageFactoryImpl();
        String sentences = SentenceGenerator.getSentences(NUMBER_OF_SENTENCES);
        new PageTask(
                pageFactory.makePage(sentences), sharedResources
        ).execute();


    }
}
