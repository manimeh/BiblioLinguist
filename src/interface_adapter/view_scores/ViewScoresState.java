package interface_adapter.view_scores;

import entity.user.User;

public class ViewScoresState {
    private User user;

    public ViewScoresState(User user) {
        this.user = user;
    }

    public ViewScoresState() {}

    // Getting functions
    public User getUser() {
        return this.user;
    }

    // Setting functions
    public void setUser(User user) {
        this.user = user;
    }
}
