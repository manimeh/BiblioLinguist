package interface_adapter.view_scores;

public class ViewScoresState {
    private String viewScoresMessage = null;

    public ViewScoresState(ViewScoresState copy) {
        viewScoresMessage = copy.viewScoresMessage;
    }

    public ViewScoresState() {
    }

    public void setViewScoresMessage(String s) {
        this.viewScoresMessage = s;
    }

    public String getViewScoresMessage() {
        return viewScoresMessage;
    }
}
