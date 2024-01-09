import javax.swing.SwingUtilities;

public class Main {

    public static void main(String[] args) {
        SwingUtilities.invokeLater(new Runnable() {
            @Override
            public void run() {
                Question[] questions = {
                        new Question("Which country won the FIFA World Cup in 2018?", new String[]{"France", "Croatia", "Brazil", "Germany"}, 'A'),
                        new Question("Who is the all-time top scorer in the history of the FIFA World Cup?", new String[]{"Pele", "Miroslav Klose", "Diego Maradona", "Lionel Messi"}, 'B'),
                        new Question("In which year did the first FIFA World Cup take place?", new String[]{"1930", "1950", "1966", "1982"}, 'A'),
                        new Question("Which player has won the most Ballon d'Or awards?", new String[]{"Lionel Messi", "Cristiano Ronaldo", "Zinedine Zidane", "Michel Platini"}, 'A')
                };

                new Quiz(questions);
            }
        });
    }
}
