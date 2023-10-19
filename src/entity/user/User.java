package entity.user;

import entity.DifficultyLevel;

import java.util.stream.IntStream;

public class User
{
    private int id;
    private String name;
    private int[][] scores;

    public User(int id, String name, int[][] scores) {
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

    private float calculateAvg(int[] scores)
    {
        return (float)IntStream.of(scores).sum()/scores.length;
    }
}
