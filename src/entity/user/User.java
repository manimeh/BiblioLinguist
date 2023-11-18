package entity.user;

import entity.DifficultyLevel;

public class User
{
    private int id;
    private String name;
    private float[][] scores;

    public User(int id, String name, float[][] scores) {
        if (scores.length != DifficultyLevel.values().length)
        {
            throw new IllegalArgumentException("The score array does not have " +
                    "an element for each of the difficulty levels");
        }

        this.id = id;
        this.name = name;
        this.scores = scores;
    }

    public int getId() {
        return id;
    }

    public String getName() {
        return name;
    }

    public float[] getAvgScores()
    {
        float[] avgScores = new float[scores.length];

        for(int i = 0; i < avgScores.length; i++)
        {
            avgScores[i] = calculateAvg(scores[i]);
        }

        return avgScores;
    }

    private float calculateAvg(float[] scores)
    {
        float sum = 0.0f;

        for (float num : scores) {
            sum += num;
        }

        return sum/scores.length;
    }

    public void addScore(float score)
    {
        float[] newArray = new float[this.scores[0].length + 1];

        System.arraycopy(this.scores[0], 0, newArray, 0, this.scores[0].length);

        newArray[this.scores[0].length] = score;

        this.scores[0] = newArray;
    }

    public void addScore(float score, DifficultyLevel difficulty)
    {
        int difficultyNum = 0; // Set to beginner by default

        if (difficulty.getName().equals("Intermediate")) {
            difficultyNum = 1;
        }
        else if (difficulty.getName().equals("Advanced")) {
            difficultyNum = 2;
        }

        float[] newArray = new float[this.scores[difficultyNum].length + 1];

        System.arraycopy(this.scores[difficultyNum], 0, newArray, 0, this.scores[difficultyNum].length);

        newArray[this.scores[difficultyNum].length] = score;

        this.scores[difficultyNum] = newArray;
    }
}
