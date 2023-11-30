package entity.user;

public class UserFactory {
    private static int id;

    public User createUser(String name, float[][] scores) {
        return new User(id++, name, scores);
    }
}
