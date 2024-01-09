class Question {
    private String text;
    private String[] options;
    private char answer;

    public Question(String text, String[] options, char answer) {
        this.text = text;
        this.options = options;
        this.answer = answer;
    }

    public String getText() {
        return text;
    }

    public String[] getOptions() {
        return options;
    }

    public char getAnswer() {
        return answer;
    }
}
