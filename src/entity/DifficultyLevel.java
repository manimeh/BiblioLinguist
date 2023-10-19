package entity;

public enum DifficultyLevel
{
    BEGINNER("Beginner", 55, 100),
    INTERMEDIATE("Intermediate", 40, 55),
    ADVANCED("Advanced", 10, 40);

    private final String name;
    private final int minReadRange;
    private final int maxReadRange;

    DifficultyLevel(String name, int minReadRange, int maxReadRange)
    {
        this.name = name;
        this.minReadRange = minReadRange;
        this.maxReadRange = maxReadRange;
    }

    public String getName() {
        return name;
    }

    public int getMinReadRange() {
        return minReadRange;
    }

    public int getMaxReadRange() {
        return maxReadRange;
    }

    public double getAvgOfReadRange()
    {
        return (minReadRange + maxReadRange)/2d;
    }
}
