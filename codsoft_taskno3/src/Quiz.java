import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

class Quiz extends JFrame implements ActionListener {

    private static final int QUESTION_LIMIT = 4;
    private static final int TIMER_DURATION = 20;

    private int index = 0;
    private int correctGuesses = 0;
    private int result;

    private JTextField textfield = new JTextField();
    private JTextArea textarea = new JTextArea();
    private JButton[] buttons = new JButton[4];
    private JLabel[] answerLabels = new JLabel[4];
    private JLabel secondsLeft = new JLabel();
    private JTextField numberRight = new JTextField();
    private JTextField percentage = new JTextField();

    private Question[] questions;
    private QuizTimer quizTimer;

    public Quiz(Question[] questions) {
        this.questions = questions;
        this.quizTimer = new QuizTimer(TIMER_DURATION, e -> updateTimer());

        initializeFrame();
        initializeComponents();
        nextQuestion();
    }

    private void initializeFrame() {
        setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        setSize(650, 650);
        getContentPane().setBackground(new Color(36, 47, 65)); // Background color
        setLayout(null);
        setResizable(false);
        setLocationRelativeTo(null); // Center the window
        setTitle("Football Quiz");
    }

    private void initializeComponents() {
        textfield.setBounds(0, 0, 650, 50);
        textfield.setBackground(new Color(97, 212, 195)); // Light teal
        textfield.setForeground(new Color(36, 47, 65));
        textfield.setFont(new Font("Arial", Font.BOLD, 30));
        textfield.setBorder(BorderFactory.createBevelBorder(1));
        textfield.setHorizontalAlignment(JTextField.CENTER);
        textfield.setEditable(false);

        textarea.setBounds(0, 50, 650, 50);
        textarea.setBackground(new Color(97, 212, 195)); // Light teal
        textarea.setForeground(new Color(36, 47, 65));
        textarea.setFont(new Font("Arial", Font.BOLD, 25));
        textarea.setBorder(BorderFactory.createBevelBorder(1));
        textarea.setLineWrap(true);
        textarea.setWrapStyleWord(true);
        textarea.setEditable(false);

        for (int i = 0; i < buttons.length; i++) {
            buttons[i] = new JButton();
            buttons[i].setBounds(0, 100 + i * 100, 100, 100);
            buttons[i].setFont(new Font("Arial", Font.BOLD, 35));
            buttons[i].setFocusable(false);
            buttons[i].addActionListener(this);
            buttons[i].setText(String.valueOf((char) ('A' + i)));
            buttons[i].setBackground(new Color(97, 212, 195)); // Light teal

            answerLabels[i] = new JLabel();
            answerLabels[i].setBounds(125, 100 + i * 100, 500, 100);
            answerLabels[i].setBackground(new Color(97, 212, 195)); // Light teal
            answerLabels[i].setForeground(new Color(36, 47, 65));
            answerLabels[i].setFont(new Font("Arial", Font.PLAIN, 35));
            answerLabels[i].setHorizontalAlignment(JLabel.LEFT);

            add(buttons[i]);
            add(answerLabels[i]);
        }

        secondsLeft.setBounds(535, 510, 100, 100);
        secondsLeft.setBackground(new Color(255, 82, 82)); // Coral
        secondsLeft.setForeground(new Color(36, 47, 65));
        secondsLeft.setFont(new Font("Arial", Font.BOLD, 60));
        secondsLeft.setBorder(BorderFactory.createBevelBorder(1));
        secondsLeft.setOpaque(true);
        secondsLeft.setHorizontalAlignment(JTextField.CENTER);
        secondsLeft.setText(String.valueOf(TIMER_DURATION));

        numberRight.setBounds(225, 225, 200, 100);
        numberRight.setBackground(new Color(97, 212, 195)); // Light teal
        numberRight.setForeground(new Color(36, 47, 65));
        numberRight.setFont(new Font("Arial", Font.BOLD, 50));
        numberRight.setBorder(BorderFactory.createBevelBorder(1));
        numberRight.setHorizontalAlignment(JTextField.CENTER);
        numberRight.setEditable(false);

        percentage.setBounds(225, 325, 200, 100);
        percentage.setBackground(new Color(97, 212, 195)); // Light teal
        percentage.setForeground(new Color(36, 47, 65));
        percentage.setFont(new Font("Arial", Font.BOLD, 50));
        percentage.setBorder(BorderFactory.createBevelBorder(1));
        percentage.setHorizontalAlignment(JTextField.CENTER);
        percentage.setEditable(false);

        add(secondsLeft);
        add(textarea);
        add(textfield);
        setVisible(true);
    }

    private void nextQuestion() {
        if (index < QUESTION_LIMIT) {
            textfield.setText("Question " + (index + 1));
            textarea.setText(questions[index].getText());
            for (int i = 0; i < buttons.length; i++) {
                buttons[i].setEnabled(true);
                answerLabels[i].setText(questions[index].getOptions()[i]);
                answerLabels[i].setForeground(new Color(25, 255, 0));
            }
            startTimer();
        } else {
            results();
        }
    }

    private void startTimer() {
        quizTimer.stop();
        secondsLeft.setText(String.valueOf(TIMER_DURATION));
        quizTimer.start();
    }

    private void updateTimer() {
        int remainingSeconds = quizTimer.getSeconds() - 1;
        secondsLeft.setText(String.valueOf(remainingSeconds));
        quizTimer.setSeconds(remainingSeconds);

        if (remainingSeconds <= 0) {
            displayAnswer();
        }
    }

    @Override
    public void actionPerformed(ActionEvent e) {
        for (int i = 0; i < buttons.length; i++) {
            buttons[i].setEnabled(false);
            if (e.getSource() == buttons[i]) {
                checkAnswer((char) ('A' + i));
                displayAnswer();
            }
        }
    }

    private void checkAnswer(char userAnswer) {
        if (userAnswer == questions[index].getAnswer()) {
            correctGuesses++;
        }
    }

    private void displayAnswer() {
        quizTimer.stop();

        for (int i = 0; i < buttons.length; i++) {
            if (questions[index].getAnswer() != (char) ('A' + i)) {
                answerLabels[i].setForeground(new Color(255, 0, 0));
            }
        }

        Timer pause = new Timer(2000, e -> {
            for (int i = 0; i < buttons.length; i++) {
                answerLabels[i].setForeground(new Color(36, 47, 65));
            }
            index++;
            nextQuestion();
        });
        pause.setRepeats(false);
        pause.start();
    }

    private void results() {
        for (int i = 0; i < buttons.length; i++) {
            buttons[i].setEnabled(false);
        }

        result = (int) ((correctGuesses / (double) QUESTION_LIMIT) * 100);

        textfield.setText("RESULTS!");
        textfield.setBackground(new Color(97, 212, 195)); // Light teal
        textarea.setText("");
        textarea.setBackground(new Color(97, 212, 195)); // Light teal
        for (int i = 0; i < buttons.length; i++) {
            answerLabels[i].setText("");
            answerLabels[i].setBackground(new Color(97, 212, 195)); // Light teal
        }

        numberRight.setText("(" + correctGuesses + "/" + QUESTION_LIMIT + ")");
        percentage.setText(result + "%");

        add(numberRight);
        add(percentage);
    }
}