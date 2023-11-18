package entity;

public enum DifficultyLevel
{
    BEGINNER(0, "Beginner", 55, 100),
    INTERMEDIATE(1, "Intermediate", 40, 55),
    ADVANCED(2, "Advanced", 10, 40);

    private final int index;
    private final String name;
    private final int minReadRange;
    private final int maxReadRange;

    DifficultyLevel(int index, String name, int minReadRange, int maxReadRange)
    {
        this.index = index;
        this.name = name;
        this.minReadRange = minReadRange;
        this.maxReadRange = maxReadRange;
    }

    public int getIndex() {
        return index;
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
