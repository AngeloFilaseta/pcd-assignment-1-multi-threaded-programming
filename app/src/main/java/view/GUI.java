package view;

import com.intellij.uiDesigner.core.GridConstraints;
import com.intellij.uiDesigner.core.GridLayoutManager;
import controller.wordsCounter.AfterWordsCounterCompletion;
import controller.wordsCounter.WordsCounter;
import model.sharedResources.SharedResources;
import model.sharedResources.orderedMap.OnMapPutStrategy;
import model.sharedResources.orderedMap.comparators.OrderedMapComparators;
import model.sharedResources.bagOfTasks.BagOfTasks;

import javax.swing.*;
import java.awt.*;

public class GUI extends JFrame {

    private JTextField docPathTextField;
    private JTextField nWordsTextField;
    private JButton startButton;
    private JButton pauseButton;
    private JTextPane textPane;
    private JPanel mainPanel;
    private JTextField ignoredFileTextField;

    private String textPaneContent = "";

    private static final int nProcessorsAvailable = Runtime.getRuntime().availableProcessors();
    private static SharedResources sharedResources;

    public static void main(String[] args) {
        JFrame jFrame = new GUI();
        jFrame.setVisible(true);
    }

    private GUI() {
        super("Assignment 1");
        this.setContentPane(this.mainPanel);
        this.setDefaultCloseOperation(WindowConstants.EXIT_ON_CLOSE);
        this.setSize(800, 600);
        initializeViewState();
        definePauseButtonBehavior();
        defineStartButtonBehavior();
    }

    private void definePauseButtonBehavior() {
        this.pauseButton.addActionListener(e -> {
            BagOfTasks bagOfTasks = sharedResources.getBagOfTasks();
            if (bagOfTasks.isPaused()) {
                bagOfTasks.resume();
                pauseButton.setText("Pause");
            } else {
                bagOfTasks.pause();
                pauseButton.setText("Resume");
            }
        });
    }

    private void defineStartButtonBehavior() {
        this.startButton.addActionListener(e -> {
            printOnTextPane("Loading files...");
            duringComputationViewState();
            startThread();
        });
    }

    private void startThread() {
        try {
            OnMapPutStrategy onMapPutStrategy = () -> SwingUtilities.invokeLater(() -> textPane.setText(textPaneContent + sharedResources
                    .getOrderedMap()
                    .getOrderedEntry(
                            OrderedMapComparators.orderByMaxToMixByValueComparator(),
                            sharedResources.getArgsContainer().getNMostFrequentWords()
                    ).toString()));
            WordsCounter wordsCounter = new WordsCounter(getArguments(), onMapPutStrategy);
            sharedResources = wordsCounter.getSharedResources();
            AfterWordsCounterCompletion afterWordsCounterCompletion = (sharedResources, chrono) -> SwingUtilities.invokeLater(() -> {
                printOnTextPane("Time elapsed: " + chrono.getTime() + "ms");
                printOnTextPane("Total words counted: " + sharedResources.getOrderedMap().getUnorderedMap().values().stream().mapToInt(value -> value).sum());
                printOnTextPane("Top " + sharedResources.getArgsContainer().getNMostFrequentWords() + " words used:");
                printOnTextPane(sharedResources
                        .getOrderedMap()
                        .getOrderedEntry(
                                OrderedMapComparators.orderByMaxToMixByValueComparator(),
                                sharedResources.getArgsContainer().getNMostFrequentWords()
                        )
                );
                initializeViewState();
            });
            new Thread(() -> wordsCounter.execute(nProcessorsAvailable, afterWordsCounterCompletion)).start();
            /*
            , new AfterPageElaborationCompletion() {
                @Override
                public void executeAfterPageElaborationCompletion(SharedResources sharedResources) {
                    SwingUtilities.invokeLater(new Runnable() {
                        @Override
                        public void run() {
                            textPane.setText(textPaneContent + sharedResources
                                    .getOrderedMap()
                                    .getOrderedEntry(
                                            OrderedMapComparators.orderByMaxToMixByValueComparator(),
                                            sharedResources.getArgsContainer().getNMostFrequentWords()
                                    ).toString());
                        }
                    });
                }
            })
             */
        } catch (Exception exception) {
            exception.printStackTrace();
        }
    }

    private String[] getArguments() {
        String[] args = new String[3];
        args[0] = (nWordsTextField.getText());
        args[1] = (ignoredFileTextField.getText());
        args[2] = (docPathTextField.getText());
        return args;
    }

    private void printOnTextPane(final Object content) {
        textPaneContent += content.toString() + "\n";
        textPane.setText(textPaneContent);
    }

    private void initializeViewState() {
        startButton.setEnabled(true);
        pauseButton.setEnabled(false);
        docPathTextField.setEnabled(true);
        ignoredFileTextField.setEnabled(true);
        nWordsTextField.setEnabled(true);
    }

    private void duringComputationViewState() {
        startButton.setEnabled(false);
        pauseButton.setEnabled(true);
        docPathTextField.setEnabled(false);
        ignoredFileTextField.setEnabled(false);
        nWordsTextField.setEnabled(false);
    }

    {
// GUI initializer generated by IntelliJ IDEA GUI Designer
// >>> IMPORTANT!! <<<
// DO NOT EDIT OR ADD ANY CODE HERE!
        $$$setupUI$$$();
    }

    /**
     * Method generated by IntelliJ IDEA GUI Designer
     * >>> IMPORTANT!! <<<
     * DO NOT edit this method OR call it in your code!
     *
     * @noinspection ALL
     */
    private void $$$setupUI$$$() {
        mainPanel = new JPanel();
        mainPanel.setLayout(new GridLayoutManager(5, 2, new Insets(0, 0, 0, 0), -1, -1));
        final JLabel label1 = new JLabel();
        label1.setText("DocPath");
        mainPanel.add(label1, new GridConstraints(0, 0, 1, 1, GridConstraints.ANCHOR_WEST, GridConstraints.FILL_NONE, GridConstraints.SIZEPOLICY_FIXED, GridConstraints.SIZEPOLICY_FIXED, null, null, null, 0, false));
        docPathTextField = new JTextField();
        mainPanel.add(docPathTextField, new GridConstraints(0, 1, 1, 1, GridConstraints.ANCHOR_WEST, GridConstraints.FILL_HORIZONTAL, GridConstraints.SIZEPOLICY_WANT_GROW, GridConstraints.SIZEPOLICY_FIXED, null, new Dimension(150, -1), null, 0, false));
        final JLabel label2 = new JLabel();
        label2.setText("N Words");
        mainPanel.add(label2, new GridConstraints(1, 0, 1, 1, GridConstraints.ANCHOR_WEST, GridConstraints.FILL_NONE, GridConstraints.SIZEPOLICY_FIXED, GridConstraints.SIZEPOLICY_FIXED, null, null, null, 0, false));
        final JLabel label3 = new JLabel();
        label3.setText("Ignored File");
        mainPanel.add(label3, new GridConstraints(2, 0, 1, 1, GridConstraints.ANCHOR_WEST, GridConstraints.FILL_NONE, GridConstraints.SIZEPOLICY_FIXED, GridConstraints.SIZEPOLICY_FIXED, null, new Dimension(70, 47), null, 0, false));
        nWordsTextField = new JTextField();
        mainPanel.add(nWordsTextField, new GridConstraints(1, 1, 1, 1, GridConstraints.ANCHOR_WEST, GridConstraints.FILL_HORIZONTAL, GridConstraints.SIZEPOLICY_WANT_GROW, GridConstraints.SIZEPOLICY_FIXED, null, new Dimension(150, -1), null, 0, false));
        final JPanel panel1 = new JPanel();
        panel1.setLayout(new GridLayoutManager(1, 1, new Insets(0, 0, 0, 0), -1, -1));
        mainPanel.add(panel1, new GridConstraints(3, 1, 2, 1, GridConstraints.ANCHOR_CENTER, GridConstraints.FILL_BOTH, GridConstraints.SIZEPOLICY_CAN_SHRINK | GridConstraints.SIZEPOLICY_CAN_GROW, GridConstraints.SIZEPOLICY_CAN_SHRINK | GridConstraints.SIZEPOLICY_CAN_GROW, null, null, null, 0, false));
        textPane = new JTextPane();
        panel1.add(textPane, new GridConstraints(0, 0, 1, 1, GridConstraints.ANCHOR_CENTER, GridConstraints.FILL_BOTH, GridConstraints.SIZEPOLICY_WANT_GROW, GridConstraints.SIZEPOLICY_WANT_GROW, null, new Dimension(150, 50), null, 0, false));
        ignoredFileTextField = new JTextField();
        mainPanel.add(ignoredFileTextField, new GridConstraints(2, 1, 1, 1, GridConstraints.ANCHOR_WEST, GridConstraints.FILL_HORIZONTAL, GridConstraints.SIZEPOLICY_WANT_GROW, GridConstraints.SIZEPOLICY_FIXED, null, new Dimension(150, -1), null, 0, false));
        startButton = new JButton();
        startButton.setText("Start");
        mainPanel.add(startButton, new GridConstraints(3, 0, 1, 1, GridConstraints.ANCHOR_CENTER, GridConstraints.FILL_NONE, GridConstraints.SIZEPOLICY_CAN_SHRINK | GridConstraints.SIZEPOLICY_CAN_GROW, GridConstraints.SIZEPOLICY_FIXED, null, null, null, 0, false));
        pauseButton = new JButton();
        pauseButton.setText("Pause");
        mainPanel.add(pauseButton, new GridConstraints(4, 0, 1, 1, GridConstraints.ANCHOR_CENTER, GridConstraints.FILL_NONE, GridConstraints.SIZEPOLICY_CAN_SHRINK | GridConstraints.SIZEPOLICY_CAN_GROW, GridConstraints.SIZEPOLICY_FIXED, null, null, null, 0, false));
    }

    /**
     * @noinspection ALL
     */
    public JComponent $$$getRootComponent$$$() {
        return mainPanel;
    }

}
