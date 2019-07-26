package by.epam.javatraining.restaurant.model.entity;

public enum OrderState {
    ACCEPTED("accepted"), NOT_ACCEPTED("not_accepted");

    private String state;

    OrderState(String state) {
        this.state = state;
    }

    public String getState() {
        return state;
    }

    public void setState(String state) {
        this.state = state;
    }
}
