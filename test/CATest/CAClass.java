package CATest;

public enum CAClass {
    VIEW("View"),
    VIEW_MODEL("ViewModel"),
    CONTROLLER("Controller"),
    PRESENTER("Presenter"),
    INPUT_DATA("InputData"),
    OUTPUT_DATA("OutputData"),
    INPUT_BOUNDARY("InputBoundary"),
    OUTPUT_BOUNDARY("OutputBoundary"),
    INTERACTOR("Interactor"),
    DATA_ACCESS_INTERFACE("DataAccessInterface"),
    DATA_ACCESS_OBJECT("DataAccessObject"),
    ENTITY("");


    private final String className;

    CAClass(String className) {
        this.className = className;
    }

    public String getClassName() {
        return className;
    }
}
