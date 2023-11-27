package view;

import entity.quiz.ActiveQuizDisplay;
import entity.reading.Reading;
import entity.user.User;
import interface_adapter.submit_quiz.SubmitQuizController;
import interface_adapter.submit_quiz.SubmitQuizState;
import interface_adapter.submit_quiz.SubmitQuizViewModel;

import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.beans.PropertyChangeEvent;
import java.beans.PropertyChangeListener;


public class GameView extends JSplitPane implements ActionListener, PropertyChangeListener {
    public static final String VIEW_NAME = "Game View";

    private final SubmitQuizViewModel submitQuizViewModel;
    private final SubmitQuizController submitQuizController;

    private final JButton submitQuiz;

    public GameView (SubmitQuizViewModel submitQuizViewModel, SubmitQuizController submitQuizController) {
        this.submitQuizViewModel = submitQuizViewModel;
        this.submitQuizController = submitQuizController;
        this.submitQuizViewModel.addPropertyChangeListener(this);

        ButtonsPanel buttonsPanel = new ButtonsPanel();
        String buttonFont = "SansSerif";
        Color buttonBackgroundColor2 = new Color(69, 196, 174);
        int buttonCurvature = 25;

        SubmitQuizState submitQuizState = submitQuizViewModel.getState();
//        ActiveQuizDisplay quizDisplay = submitQuizState.getQuiz().activeDisplay();
        User user = submitQuizState.getUser();
        Reading reading = submitQuizState.getReading();


        // Left half of panel
        JPanel view1 = new JPanel();
        view1.setLayout(new BoxLayout(view1, BoxLayout.Y_AXIS));

        JLabel header1 = new JLabel("Reading");
        header1.setAlignmentX(Component.CENTER_ALIGNMENT);
        header1.setFont(new Font(header1.getFont().getFontName(), Font.PLAIN, 25));
        view1.add(header1);

        String testText = "The Late Bronze Age collapse was a time of widespread societal collapse during the 12th century BC, between c. 1200 and 1150, and was associated with environmental change, mass migration and destruction of cities.[1] The collapse affected a large area of the Eastern Mediterranean (North Africa and Southeast Europe) and the Near East, in particular Egypt, eastern Libya, the Balkans, the Aegean, Anatolia, and the Caucasus. It was sudden, violent, and culturally disruptive for many Bronze Age civilizations, and it brought a sharp economic decline to regional powers, notably ushering in the Greek Dark Ages.\n" +
                "\n" +
                "The palace economy of Mycenaean Greece, the Aegean region, and Anatolia that characterized the Late Bronze Age disintegrated, transforming into the small isolated village cultures of the Greek Dark Ages, which lasted from around 1100 to the beginning of the better-known Archaic age around 750 BC. The Hittite Empire of Anatolia and the Levant collapsed, while states such as the Middle Assyrian Empire in Mesopotamia and the New Kingdom of Egypt survived but were weakened. Conversely, some cultures such as the Phoenicians enjoyed increased autonomy and power with the waning military presence of Egypt and Assyria in West Asia.\n" +
                "\n" +
                "Competing theories of the cause of the Late Bronze Age collapse have been proposed since the 19th century, with most involving the violent destruction of cities and towns. These include volcanic eruptions, droughts, disease, invasions by the Sea Peoples or migrations of the Dorians, economic disruptions due to increased ironworking, and changes in military technology and methods that brought the decline of chariot warfare. Earthquakes had also been proposed as causal, but recent research suggests that earthquakes were not as influential as previously believed.[2] Following the collapse, gradual changes in metallurgic technology led to the subsequent Iron Age across Eurasia and Africa during the 1st millennium BC.";

        JLabel title = new JLabel("Title: " + "title"/*reading.display().title()*/);
        JLabel author = new JLabel("Author: " + "author"/*reading.display().author()*/);
        JTextArea readingText = new JTextArea(testText/*reading.display().text()*/);

        title.setAlignmentX(Component.CENTER_ALIGNMENT);
        author.setAlignmentX(Component.CENTER_ALIGNMENT);
        readingText.setAlignmentX(Component.CENTER_ALIGNMENT);
        readingText.setLineWrap(true);
        readingText.setWrapStyleWord(true);
        readingText.setEditable(false);

        view1.add(title);
        view1.add(author);
        view1.add(readingText);


        // Right half of panel
        JPanel view2 = new JPanel();
        view2.setLayout(new BoxLayout(view2, BoxLayout.Y_AXIS));


        JLabel header2 = new JLabel("Quiz");
        header2.setAlignmentX(Component.CENTER_ALIGNMENT);
        header2.setFont(new Font(header2.getFont().getFontName(), Font.PLAIN, 25));
        JPanel headerPanel = new JPanel();
        headerPanel.add(header2);
        view2.add(headerPanel);


//        String[] questions = quizDisplay.questions();
//        String[][] choices = quizDisplay.choices();

//        For testing:
        String[] questions = {
                "What is the capital of France?",
                "Which programming language is Java based on?",
                "What is the largest planet in our solar system?"
        };
        String[][] choices = {
                {"Paris", "Berlin", "London", "Rome"},
                {"C++", "Python", "JavaScript", "C#"},
                {"Jupiter", "Saturn", "Mars", "Earth"}
        };
        submitQuizViewModel.getState().setAnswers(new Integer[3]);

        // Add each button group and question label to the view
//        for (int i = 0; i < questions.length; i++) {
//            JLabel questionLabel = new JLabel(questions[i]);
//            view2.add(questionLabel);
//
//            ButtonGroup buttonGroup = new ButtonGroup();
//            for (int j = 0; j < choices[i].length; j++) {
//                JRadioButton choice = new JRadioButton(choices[i][j]);
//                buttonGroup.add(choice);
//                view2.add(choice);
//            }
//        }
        for (int i = 0; i < questions.length; i++) {
            QuestionPanel questionPanel = new QuestionPanel(questions[i], choices[i], submitQuizViewModel, i);
            view2.add(questionPanel);
        }



        // Add submit quiz button to right view
        submitQuiz = buttonsPanel.addGradientButton("Submit", buttonFont, buttonBackgroundColor2,
                Color.BLACK, buttonCurvature);
        submitQuiz.setMaximumSize(new Dimension(100, 30));
        submitQuiz.setAlignmentX(Component.CENTER_ALIGNMENT);
        view2.add(new JPanel().add(submitQuiz));


        // Add views to splitPane
        setOrientation(JSplitPane.HORIZONTAL_SPLIT);
        setLeftComponent(new JScrollPane(view1));
        setRightComponent(new JScrollPane(view2));
        setResizeWeight(0.5);


        submitQuiz.addActionListener(
                evt -> {
                    if (evt.getSource().equals(submitQuiz)) {
//                        submitQuizController.execute(submitQuizState.getQuiz(), submitQuizState.getAnswers(), user);
                    }
                });
//                evt -> {
//                    if (evt.getSource().equals(submitQuiz)) {
//                        Integer[] userAnswers = new Integer[questions.length];
//
//                        Component[] components = view2.getComponents();
//
//                        int radioButtonCount = 0;
//                        for (Component component : components) {
//                            if (component instanceof JRadioButton) {
//                                // Check if the radio button is selected
//                                if (((JRadioButton) component).isSelected()) {
//                                    // Save the user's answer
//                                    String answer = ((JRadioButton) component).getText();
//                                    for (int i = 0; i < choices[radioButtonCount].length; i++) {
//                                        if (choices[radioButtonCount][i].equals(answer)) {
//                                            userAnswers[radioButtonCount] = i; // Index of the target string in the array
//                                        }
//                                    }
//                                    radioButtonCount += 1;
//                                }
//                            } else if (component instanceof JButton && component.equals(submitQuiz)) {
//                                // Submit button found, break the loop
//                                break;
//                            }
//                        }
//
//                        if (radioButtonCount == questions.length) {
////                            GameView.this.submitQuizController.execute(submitQuizState.getQuiz(), userAnswers, user);
//                            System.out.println(userAnswers);
//                        }
//                        else {
//                            JOptionPane.showMessageDialog(null,
//                                    "Please answer all questions", "Error", JOptionPane.ERROR_MESSAGE);
//                        }
//                    }
//                }
//        );
    }

    @Override
    public void actionPerformed(ActionEvent e) {

    }

    @Override
    public void propertyChange(PropertyChangeEvent evt) {

    }
}
