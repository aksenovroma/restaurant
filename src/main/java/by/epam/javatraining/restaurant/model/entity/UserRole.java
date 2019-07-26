package by.epam.javatraining.restaurant.model.entity;

public enum UserRole {
    CLIENT("client"), COURIER("courier"), ADMIN("admin");

    private String role;

    UserRole(String userRole) {
        this.role = userRole;
    }

    public String getRole() {
        return role;
    }
}
