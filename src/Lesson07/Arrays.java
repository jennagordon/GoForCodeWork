package Lesson07;

public class Arrays {
    public static void main(String[] args) {

        int [] teamScores;

        teamScores = new int [5];

        teamScores[0] = 2;
        teamScores[1] = 45;
        teamScores[2] = 4;
        teamScores[3] = 8;
        teamScores[4] = 99;

        int [] golfScores = {72, 74, 68,71};

        int currentGolfScore = golfScores[0];

        golfScores[2] = 70;

        for (int i = 0; i < golfScores.length; i++) {
            System.out.println(golfScores[i]);
        }

        for (int currentScore : golfScores) {
            System.out.println(currentScore);
        }
    }
}
