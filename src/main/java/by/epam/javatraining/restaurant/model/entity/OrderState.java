package by.epam.javatraining.restaurant.model.entity;

public enum OrderState {
    PAID("paid"), NOT_PAID("not_paid");

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
