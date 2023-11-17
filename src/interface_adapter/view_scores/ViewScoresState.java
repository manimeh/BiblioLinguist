package interface_adapter.view_scores;

public class ViewScoresState {
    private String viewScoresMessage = null;

    //A copy constructor is needed for states to construct a state based on a copy
    public ViewScoresState(ViewScoresState copy) {
        viewScoresMessage = copy.viewScoresMessage;
    }

    // Because of the previous copy constructor, the default constructor must be explicit.
    public ViewScoresState() {
    }

    public void setViewScoresMessage(String s) {
        this.viewScoresMessage = s;
    }

    public String getViewScoresMessage() {
        return viewScoresMessage;
    }
}
