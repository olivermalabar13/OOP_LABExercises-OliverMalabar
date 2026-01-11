import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.util.Random;
import javax.swing.*;
import javax.swing.border.Border;

/**
 * ArithmeticGameV1.java
 * Styled purple variant of your classmate’s ArithmeticGame
 */
enum GameLevel {
    LEVEL1(1, 100),
    LEVEL2(101, 500),
    LEVEL3(501, 1000);

    private final int min;
    private final int max;

    GameLevel(int min, int max) {
        this.min = min;
        this.max = max;
    }

    public int getMin() { return min; }
    public int getMax() { return max; }
    public String getDisplay() { return min + "-" + max; }
}

class QuestionGenerator {
    private final Random random = new Random();
    private int num1;
    private int num2;
    private String operator = "+";
    private int correctAnswer;

    public void generateQuestion(String operator, GameLevel level) {
        this.operator = operator;
        int min = level.getMin();
        int max = level.getMax();

        switch (operator) {
            case "+":
                num1 = rand(min, max);
                num2 = rand(min, max);
                correctAnswer = num1 + num2;
                break;
            case "-":
                num1 = rand(min, max);
                num2 = rand(min, max);
                if (num2 > num1) { int t = num1; num1 = num2; num2 = t; }
                correctAnswer = num1 - num2;
                break;
            case "*":
                num1 = rand(min, max);
                num2 = rand(min, max);
                correctAnswer = num1 * num2;
                break;
            case "/":
                num2 = rand(Math.max(1, min), Math.max(1, max));
                int quotient = rand(min, Math.max(min, max / Math.max(1, num2)));
                if (quotient < 1) quotient = 1;
                num1 = num2 * quotient;
                correctAnswer = num1 / num2;
                break;
            case "%":
                num2 = rand(1, Math.max(1, max));
                num1 = rand(min, max);
                correctAnswer = num1 % num2;
                break;
            default:
                num1 = rand(min, max);
                num2 = rand(min, max);
                correctAnswer = num1 + num2;
                this.operator = "+";
        }
    }

    private int rand(int min, int max) {
        return random.nextInt((max - min) + 1) + min;
    }

    public int getNum1() { return num1; }
    public int getNum2() { return num2; }
    public String getOperator() { return operator; }
    public int getCorrectAnswer() { return correctAnswer; }
}

public class ArithmeticGameV1 extends JFrame implements ActionListener {
    // === PURPLE THEME ===
    private static final Color BG = new Color(245, 240, 255);
    private static final Color CARD = new Color(255, 255, 255);
    private static final Color ACCENT = new Color(160, 100, 255);
    private static final Font TITLE_FONT = new Font("SansSerif", Font.BOLD, 28);
    private static final Font BODY_FONT = new Font("SansSerif", Font.BOLD, 16);

    private final QuestionGenerator generator = new QuestionGenerator();
    private String selectedOperation = "+";
    private GameLevel selectedLevel = GameLevel.LEVEL1;

    private final JTextField answerField = new JTextField(10);
    private final JLabel num1Label = new JLabel("0", SwingConstants.CENTER);
    private final JLabel operatorLabel = new JLabel("+", SwingConstants.CENTER);
    private final JLabel num2Label = new JLabel("0", SwingConstants.CENTER);
    private final JLabel correctScoreLabel = new JLabel("0", SwingConstants.CENTER);
    private final JLabel incorrectScoreLabel = new JLabel("0", SwingConstants.CENTER);
    private final JLabel streakLabel = new JLabel("STREAK: 0", SwingConstants.CENTER);
    private final JButton submitButton = new JButton("SUBMIT");
    private final ButtonGroup operationGroup = new ButtonGroup();
    private final ButtonGroup levelGroup = new ButtonGroup();

    private int correctCount = 0;
    private int incorrectCount = 0;
    private int streak = 0;

    // === NEW FIELD FOR PLAYER NAME ===
    private String playerName;

    public ArithmeticGameV1() {
        // === ASK PLAYER NAME FIRST ===
        playerName = JOptionPane.showInputDialog(null,
                "Welcome to Arithmetic Game!\nPlease enter your name:",
                "Player Introduction", JOptionPane.QUESTION_MESSAGE);

        if (playerName == null || playerName.trim().isEmpty()) {
            playerName = "Player";
        }

        installNimbusLaf();
        setTitle("Arithmetic Game V1");
        setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        setLayout(new BorderLayout(10, 10));
        getContentPane().setBackground(BG);

        add(createQuestionPanel(), BorderLayout.NORTH);
        add(createControlsPanel(), BorderLayout.CENTER);
        add(createScorePanel(), BorderLayout.SOUTH);

        submitButton.addActionListener(this);
        submitButton.setBackground(ACCENT);
        submitButton.setForeground(Color.WHITE);
        submitButton.setFont(new Font("SansSerif", Font.BOLD, 18));
        submitButton.setFocusPainted(false);
        submitButton.setBorder(BorderFactory.createEmptyBorder(12, 24, 12, 24));
        submitButton.setCursor(new Cursor(Cursor.HAND_CURSOR));

        generateNewQuestion();

        pack();
        setResizable(false);
        setLocationRelativeTo(null);
        setVisible(true);
    }

    private JPanel createQuestionPanel() {
        JPanel panel = new JPanel(new BorderLayout());
        panel.setBackground(BG);
        panel.setBorder(BorderFactory.createEmptyBorder(12, 12, 0, 12));

        // === NEW WELCOME MESSAGE ===
        JLabel welcomeLabel = new JLabel("Welcome back, " + playerName + "!", SwingConstants.CENTER);
        welcomeLabel.setFont(new Font("SansSerif", Font.BOLD, 22));
        welcomeLabel.setForeground(new Color(80, 0, 150));
        panel.add(welcomeLabel, BorderLayout.NORTH);

        JLabel header = new JLabel("Ready, Set, Solve!", SwingConstants.CENTER);
        header.setFont(TITLE_FONT);
        header.setForeground(Color.BLACK);
        panel.add(header, BorderLayout.CENTER);

        JPanel row = new JPanel(new FlowLayout(FlowLayout.CENTER, 20, 10));
        row.setBackground(BG);
        Font bigFont = new Font("SansSerif", Font.BOLD, 48);
        Dimension labelSize = new Dimension(150, 80);

        setupLabel(num1Label, bigFont, labelSize);
        setupLabel(num2Label, bigFont, labelSize);
        operatorLabel.setFont(bigFont);
        JLabel equalsLabel = new JLabel("=", SwingConstants.CENTER);
        equalsLabel.setFont(bigFont);

        answerField.setFont(bigFont);
        answerField.setPreferredSize(labelSize);
        answerField.setHorizontalAlignment(SwingConstants.CENTER);
        answerField.setBorder(BorderFactory.createLineBorder(ACCENT, 3));
        answerField.addActionListener(e -> submitButton.doClick());

        row.add(num1Label);
        row.add(operatorLabel);
        row.add(num2Label);
        row.add(equalsLabel);
        row.add(answerField);
        row.add(submitButton);

        panel.add(row, BorderLayout.SOUTH);
        return panel;
    }

    private void setupLabel(JLabel lbl, Font font, Dimension size) {
        lbl.setFont(font);
        lbl.setPreferredSize(size);
        lbl.setOpaque(true);
        lbl.setBackground(CARD);
        lbl.setBorder(BorderFactory.createCompoundBorder(createShadowBorder(),
                BorderFactory.createLineBorder(new Color(180, 160, 255), 2)));
    }

    private JPanel createControlsPanel() {
        JPanel panel = new JPanel(new GridLayout(1, 2, 30, 0));
        panel.setBackground(BG);
        panel.setBorder(BorderFactory.createEmptyBorder(0, 12, 0, 12));

        JPanel opPanel = new JPanel(new GridLayout(6, 1));
        opPanel.setBackground(CARD);
        opPanel.setBorder(BorderFactory.createTitledBorder("OPERATIONS:"));

        String[] operations = {"+", "-", "*", "/", "%"};
        String[] opDisplay = {"ADDITION (+)", "SUBTRACTION (-)", "MULTIPLICATION (*)", "DIVISION (/)", "MODULO (%)"};

        for (int i = 0; i < operations.length; i++) {
            JRadioButton opButton = new JRadioButton(opDisplay[i]);
            opButton.setActionCommand(operations[i]);
            opButton.addActionListener(this::handleControlSelection);
            opButton.setBackground(CARD);
            opButton.setFont(BODY_FONT);
            opButton.setForeground(Color.BLACK);
            operationGroup.add(opButton);
            opPanel.add(opButton);
            if (operations[i].equals(selectedOperation)) opButton.setSelected(true);
        }

        JPanel levelPanel = new JPanel(new GridLayout(6, 1));
        levelPanel.setBackground(CARD);
        levelPanel.setBorder(BorderFactory.createTitledBorder("LEVEL:"));

        for (GameLevel level : GameLevel.values()) {
            JRadioButton levelButton = new JRadioButton("LEVEL " + level.name().substring(5) + " (" + level.getDisplay() + ")");
            levelButton.setActionCommand(level.name());
            levelButton.addActionListener(this::handleControlSelection);
            levelButton.setBackground(CARD);
            levelButton.setFont(BODY_FONT);
            levelButton.setForeground(Color.BLACK);
            levelGroup.add(levelButton);
            levelPanel.add(levelButton);
            if (level == selectedLevel) levelButton.setSelected(true);
        }

        panel.add(opPanel);
        panel.add(levelPanel);
        return panel;
    }

    private JPanel createScorePanel() {
        JPanel panel = new JPanel(new GridLayout(1, 3, 10, 5));
        panel.setBackground(BG);
        panel.setBorder(BorderFactory.createEmptyBorder(0, 12, 12, 12));

        correctScoreLabel.setFont(new Font("SansSerif", Font.BOLD, 22));
        correctScoreLabel.setForeground(new Color(0, 150, 0));
        correctScoreLabel.setOpaque(true);
        correctScoreLabel.setBackground(CARD);

        streakLabel.setFont(new Font("SansSerif", Font.BOLD, 22));
        streakLabel.setForeground(Color.BLACK);
        streakLabel.setOpaque(true);
        streakLabel.setBackground(CARD);

        incorrectScoreLabel.setFont(new Font("SansSerif", Font.BOLD, 22));
        incorrectScoreLabel.setForeground(new Color(200, 0, 0));
        incorrectScoreLabel.setOpaque(true);
        incorrectScoreLabel.setBackground(CARD);

        panel.add(correctScoreLabel);
        panel.add(streakLabel);
        panel.add(incorrectScoreLabel);
        return panel;
    }

    private void handleControlSelection(ActionEvent e) {
        String cmd = e.getActionCommand();
        if ("+-*/%".contains(cmd)) {
            selectedOperation = cmd;
            operatorLabel.setText(cmd);
        } else {
            selectedLevel = GameLevel.valueOf(cmd);
        }
        generateNewQuestion();
    }

    private void generateNewQuestion() {
        generator.generateQuestion(selectedOperation, selectedLevel);
        num1Label.setText(String.valueOf(generator.getNum1()));
        num2Label.setText(String.valueOf(generator.getNum2()));
        operatorLabel.setText(generator.getOperator());
        answerField.setText("");
        answerField.requestFocusInWindow();
    }

    private void updateScore(boolean correct) {
        if (correct) {
            correctCount++;
            streak++;
            correctScoreLabel.setText("CORRECT: " + correctCount);
            streakLabel.setText("STREAK: " + streak);
        } else {
            incorrectCount++;
            streak = 0;
            incorrectScoreLabel.setText("INCORRECT: " + incorrectCount);
            streakLabel.setText("STREAK: 0");
        }
    }

    @Override
    public void actionPerformed(ActionEvent e) {
        if (e.getSource() == submitButton) checkAnswer();
    }

    private void checkAnswer() {
        try {
            String raw = answerField.getText().trim();
            int correctAnswer = generator.getCorrectAnswer();
            int userAnswer = Integer.parseInt(raw);
            boolean isCorrect = (userAnswer == correctAnswer);

            String feedback = isCorrect
                    ? "✅ Correct! Well done!"
                    : "❌ Incorrect. The correct answer is " + correctAnswer + ".";
            JOptionPane.showMessageDialog(this, feedback, "Result",
                    isCorrect ? JOptionPane.INFORMATION_MESSAGE : JOptionPane.ERROR_MESSAGE);

            updateScore(isCorrect);
            generateNewQuestion();
        } catch (NumberFormatException ex) {
            JOptionPane.showMessageDialog(this, "Please enter a valid number.", "Input Error", JOptionPane.WARNING_MESSAGE);
            answerField.requestFocusInWindow();
        }
    }

    public static void main(String[] args) {
        SwingUtilities.invokeLater(() -> new ArithmeticGameV1());
    }

    private static void installNimbusLaf() {
        try {
            for (UIManager.LookAndFeelInfo info : UIManager.getInstalledLookAndFeels()) {
                if ("Nimbus".equals(info.getName())) {
                    UIManager.setLookAndFeel(info.getClassName());
                    break;
                }
            }
        } catch (Exception ignore) {}
    }

    private static Border createShadowBorder() {
        Color shadow = new Color(0, 0, 0, 25);
        return BorderFactory.createCompoundBorder(
                BorderFactory.createMatteBorder(0, 0, 6, 6, shadow),
                BorderFactory.createMatteBorder(0, 0, 0, 0, new Color(0,0,0,0))
        );
    }
}
