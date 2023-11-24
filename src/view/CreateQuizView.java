package view;

import entity.DifficultyLevel;
import entity.language.Language;
import entity.reading.ReadingType;
import interface_adapter.create_quiz.CreateQuizController;
import interface_adapter.create_quiz.CreateQuizState;
import interface_adapter.create_quiz.CreateQuizViewModel;
import interface_adapter.start_new_game.StartNewGameViewModel;

import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.ItemEvent;
import java.beans.PropertyChangeEvent;
import java.beans.PropertyChangeListener;
import java.util.Arrays;

public class CreateQuizView extends BackgroundImagePanel implements ActionListener, PropertyChangeListener {
    public static final String VIEW_NAME = "create quiz";

    private final CreateQuizController createQuizController;
    private final CreateQuizViewModel createQuizViewModel;

    private final JComboBox<String> languageDropdown;
    private final JComboBox<String> difficultyDropdown;
    private final JComboBox<String> readingTypeDropdown;

    private final JButton takeQuiz;

    public CreateQuizView(CreateQuizController createQuizController, CreateQuizViewModel createQuizViewModel,
                          Image backgroundImage, Image headerImage) {
        super(backgroundImage);
        this.createQuizController = createQuizController;
        this.createQuizViewModel = createQuizViewModel;
        createQuizViewModel.addPropertyChangeListener(this);

        ButtonsPanel buttonPanel = new ButtonsPanel();
        String buttonFont = "SansSerif";
        Color buttonBackgroundColor = new Color(144, 172, 245);
        int buttonCurvature = 25;

        JLabel title = new JLabel(new ImageIcon(headerImage));
        title.setAlignmentX(Component.CENTER_ALIGNMENT);

        languageDropdown = createDropdowns(Arrays.stream(Language.values()).map(Language::getName)
                .toArray(String[]::new));
        LabeledDropdownPanel languageInfo = new LabeledDropdownPanel(createJLabel(CreateQuizViewModel.LANGUAGE_DROPDOWN_LABEL),
                languageDropdown);

        difficultyDropdown = createDropdowns(Arrays.stream(DifficultyLevel.values()).map(DifficultyLevel::getName)
                .toArray(String[]::new));
        LabeledDropdownPanel difficultyInfo = new LabeledDropdownPanel(createJLabel(CreateQuizViewModel.DIFFICULTY_DROPDOWN_LABEL),
                difficultyDropdown);

        readingTypeDropdown = createDropdowns(Arrays.stream(ReadingType.values()).map(ReadingType::getName)
                .toArray(String[]::new));
        LabeledDropdownPanel readingTypeInfo = new LabeledDropdownPanel(createJLabel(CreateQuizViewModel.READING_TYPE_DROPDOWN_LABEL),
                readingTypeDropdown);

        takeQuiz = buttonPanel.addGradientButton(CreateQuizViewModel.TAKE_QUIZ_BUTTON_LABEL,
                buttonFont, buttonBackgroundColor, Color.BLACK, buttonCurvature);
        buttonPanel.addSpacer();
        buttonPanel.addSpacer();

        takeQuiz.addActionListener(
                evt -> {
                    if (evt.getSource().equals(takeQuiz)) {
                        CreateQuizState currentState = createQuizViewModel.getState();

                        CreateQuizView.this.createQuizController.execute(
                                currentState.getReadingLanguage(),
                                currentState.getReadingDifficulty(),
                                currentState.getReadingType()
                        );
                    }
                }
        );

        languageDropdown.addItemListener(evt -> {
            if (evt.getStateChange() == ItemEvent.SELECTED)
            {
                String languageName = languageDropdown.getItemAt(languageDropdown.getSelectedIndex());
                Language language = Arrays.stream(Language.values())
                        .filter(lang -> languageName.equals(lang.getName()))
                        .findFirst()
                        .orElseThrow(() -> new IllegalArgumentException("No language with name " + languageName));

                createQuizViewModel.getState().setReadingLanguage(language);
            }
        });

        difficultyDropdown.addItemListener(evt -> {
            if (evt.getStateChange() == ItemEvent.SELECTED)
            {
                String difficultyName = difficultyDropdown.getItemAt(difficultyDropdown.getSelectedIndex());
                DifficultyLevel difficulty = Arrays.stream(DifficultyLevel.values())
                        .filter(diff -> difficultyName.equals(diff.getName()))
                        .findFirst()
                        .orElseThrow(() -> new IllegalArgumentException("No difficulty with name " + difficultyName));

                createQuizViewModel.getState().setReadingDifficulty(difficulty);
            }
        });

        readingTypeDropdown.addItemListener(evt -> {
            if (evt.getStateChange() == ItemEvent.SELECTED)
            {
                String readingTypeName = readingTypeDropdown.getItemAt(readingTypeDropdown.getSelectedIndex());
                ReadingType readingType = Arrays.stream(ReadingType.values())
                        .filter(typ -> readingTypeName.equals(typ.getName()))
                        .findFirst()
                        .orElseThrow(() -> new IllegalArgumentException("No reading with name " + readingTypeName));

                createQuizViewModel.getState().setReadingType(readingType);
            }
        });

        this.setLayout(new BoxLayout(this, BoxLayout.Y_AXIS));

        this.add(title);
        this.add(languageInfo);
        this.add(difficultyInfo);
        this.add(readingTypeInfo);
        this.add(buttonPanel);
    }

    private JLabel createJLabel(String labelName)
    {
        JLabel label = new JLabel(labelName);
        label.setFont(new Font("Monospaced", Font.BOLD, 16));
        label.setForeground(Color.WHITE);
        return label;
    }

    private JComboBox<String> createDropdowns(String[] choices)
    {
        JComboBox<String> dropdown = new JComboBox<>(choices);
        dropdown.setFont(new Font("Dialog", Font.PLAIN, 16));
        return dropdown;
    }

    @Override
    public void actionPerformed(ActionEvent e) {

    }

    @Override
    public void propertyChange(PropertyChangeEvent evt) {
        CreateQuizState state = (CreateQuizState) evt.getNewValue();

        if (state.getReadingLanguage() != null) {
            languageDropdown.setSelectedItem(createQuizViewModel.getState().getReadingLanguage().getName());
        }

        if (state.getReadingDifficulty() != null) {
            difficultyDropdown.setSelectedItem(createQuizViewModel.getState().getReadingDifficulty().getName());
        }

        if (state.getReadingType() != null) {
            readingTypeDropdown.setSelectedItem(createQuizViewModel.getState().getReadingType().getName());
        }
    }
}
