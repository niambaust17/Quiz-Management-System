import org.json.simple.JSONArray;
import org.json.simple.JSONObject;
import org.json.simple.parser.JSONParser;

import java.io.FileReader;
import java.io.FileWriter;
import java.util.Random;
import java.util.Scanner;

public class QuizManagementSystem {
    static Scanner scanner = new Scanner(System.in);

    public static void main(String[] args) {
        while (true) {
            System.out.println("\nLogin System");
            System.out.print("Enter your username: ");
            String username = scanner.nextLine();
            System.out.print("Enter your password: ");
            String password = scanner.nextLine();

            JSONObject user = authenticateUser(username, password);
            if (user != null) {
                String role = (String) user.get("role");
                if ("admin".equalsIgnoreCase(role)) {
                    adminMenu();
                } else if ("student".equalsIgnoreCase(role)) {
                    studentMenu(username);
                }
            } else {
                System.out.println("Invalid credentials. Try again.");
            }

            System.out.print("\nWould you like to start again? Press 's' to start or 'q' to quit: ");
            String again = scanner.nextLine();
            if (!again.equalsIgnoreCase("s")) break;
        }
    }

    static JSONObject authenticateUser(String username, String password) {
        try {
            JSONParser parser = new JSONParser();
            JSONArray users = (JSONArray) parser.parse(new FileReader("./src/main/resources/users.json"));
            for (Object obj : users) {
                JSONObject user = (JSONObject) obj;
                if (user.get("username").equals(username) && user.get("password").equals(password)) {
                    return user;
                }
            }
        } catch (Exception e) {
            System.out.println("Error reading users.json");
        }
        return null;
    }

    static void adminMenu() {
        System.out.println("Welcome admin! Please create new questions in the question bank.");

        while (true) {
            System.out.print("Press 's' to add question or 'q' to quit: ");
            String choice = scanner.nextLine();
            if (choice.equalsIgnoreCase("q")) break;

            try {
                JSONParser parser = new JSONParser();
                JSONArray quizArray = (JSONArray) parser.parse(new FileReader("./src/main/resources/quiz.json"));

                JSONObject questionObj = new JSONObject();
                System.out.print("Enter question: ");
                questionObj.put("question", scanner.nextLine());

                for (int i = 1; i <= 4; i++) {
                    System.out.print("Enter option " + i + ": ");
                    questionObj.put("option " + i, scanner.nextLine());
                }

                int answerKey;
                while (true) {
                    try {
                        System.out.print("Enter answer key (1-4): ");
                        answerKey = Integer.parseInt(scanner.nextLine());
                        if (answerKey >= 1 && answerKey <= 4) break;
                    } catch (Exception e) {
                        System.out.println("Invalid input. Please enter a number between 1 and 4.");
                    }
                }
                questionObj.put("answerkey", answerKey);

                quizArray.add(questionObj);

                FileWriter fw = new FileWriter("./src/main/resources/quiz.json");
                fw.write(quizArray.toJSONString());
                fw.flush();
                fw.close();

                System.out.println("Question saved to quiz.json successfully!");
            } catch (Exception e) {
                System.out.println("Error: Could not read or write quiz.json");
            }
        }
    }

    static void studentMenu(String username) {
        System.out.println("Welcome " + username + " to the quiz!");
        System.out.println("We will throw you 10 questions. Each MCQ mark is 1 and no negative marking.");
        System.out.print("Are you ready? Press 's' to start: ");

        if (!scanner.nextLine().equalsIgnoreCase("s")) return;

        JSONArray quiz;
        try {
            JSONParser parser = new JSONParser();
            quiz = (JSONArray) parser.parse(new FileReader("./src/main/resources/quiz.json"));

            if (quiz.size() == 0) {
                System.out.println("No questions available.");
                return;
            }
        } catch (Exception e) {
            System.out.println("Error loading quiz file.");
            return;
        }

        int score = 0;
        Random rand = new Random();

        for (int i = 0; i < 10; i++) {
            JSONObject q = (JSONObject) quiz.get(rand.nextInt(quiz.size()));
            System.out.println("\n[Question " + (i + 1) + "] " + q.get("question"));
            for (int j = 1; j <= 4; j++) {
                System.out.println(j + ". " + q.get("option " + j));
            }

            try {
                int answer = Integer.parseInt(scanner.nextLine());
                if (answer == ((Long) q.get("answerkey")).intValue()) {
                    score++;
                }
            } catch (Exception e) {
                System.out.println("Invalid input. Skipped.");
            }
        }

        System.out.println("\nResult:");
        if (score >= 8) {
            System.out.println("Excellent! You have got " + score + " out of 10");
        } else if (score >= 5) {
            System.out.println("Good. You have got " + score + " out of 10");
        } else if (score >= 3) {
            System.out.println("Very poor! You have got " + score + " out of 10");
        } else {
            System.out.println("Very sorry, you are failed. You have got " + score + " out of 10");
        }
    }
}


