import java.sql.*;
import java.util.*;

public class QuizApp {
    public static void main(String[] args) {
        Scanner sc = new Scanner(System.in);
        Connection conn = DBConnection.getConnection();

        if (conn == null) {
            System.out.println("Database connection failed!");
            return;
        }

        try {
            // Load Questions
            List<Question> questions = new ArrayList<>();
            String query = "SELECT * FROM questions";
            Statement stmt = conn.createStatement();
            ResultSet rs = stmt.executeQuery(query);

            while (rs.next()) {
                questions.add(new Question(
                        rs.getInt("id"),
                        rs.getString("question_text"),
                        rs.getString("option_a"),
                        rs.getString("option_b"),
                        rs.getString("option_c"),
                        rs.getString("option_d"),
                        rs.getString("correct_answer")
                ));
            }

            int score = 0;
            System.out.println("=== Online Quiz Application ===");

            for (Question q : questions) {
                System.out.println("\nQ" + q.getId() + ": " + q.getQuestionText());
                System.out.println("A. " + q.getOptionA());
                System.out.println("B. " + q.getOptionB());
                System.out.println("C. " + q.getOptionC());
                System.out.println("D. " + q.getOptionD());
                System.out.print("Your Answer: ");
                String ans = sc.nextLine().trim().toUpperCase();

                if (ans.equals(q.getCorrectAnswer().toUpperCase())) {
                    score++;
                }
            }

            System.out.println("\nQuiz Finished! Your Score: " + score + "/" + questions.size());

            // Store result in leaderboard
            System.out.print("Enter your name: ");
            String name = sc.nextLine();

            String insertQuery = "INSERT INTO leaderboard (name, score) VALUES (?, ?)";
            PreparedStatement ps = conn.prepareStatement(insertQuery);
            ps.setString(1, name);
            ps.setInt(2, score);
            ps.executeUpdate();

            // Show leaderboard
            System.out.println("\n=== Leaderboard ===");
            ResultSet lb = stmt.executeQuery("SELECT * FROM leaderboard ORDER BY score DESC LIMIT 5");
            while (lb.next()) {
                System.out.println(lb.getString("name") + " - " + lb.getInt("score"));
            }

        } catch (Exception e) {
            e.printStackTrace();
        }
    }
}
