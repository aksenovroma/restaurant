package by.epam.javatraining.restaurant.model.entity;

import java.util.Objects;

public class User extends Entity{
    private String name;
    private String login;
    private String password;
    private String photo = "https://www.greenmountainenergy.com/wp-content/uploads/2017/05/my-account-nav-cta@2x.png";
    private UserRole userRole = UserRole.CLIENT;

    public User() {}

    public User(String name, String login, String password, String photo, UserRole userRole) {
        this.name = name;
        this.login = login;
        this.password = password;
        this.photo = photo;
        this.userRole = userRole;
    }

    public User(String name, String login, String password) {
        this.name = name;
        this.login = login;
        this.password = password;
    }

    public User(int id, String name, String login, String password, String photo, UserRole userRole) {
        super(id);
        this.name = name;
        this.login = login;
        this.password = password;
        this.photo = photo;
        this.userRole = userRole;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getLogin() {
        return login;
    }

    public void setLogin(String login) {
        this.login = login;
    }

    public String getPassword() {
        return password;
    }

    public void setPassword(String password) {
        this.password = password;
    }

    public String getPhoto() {
        return photo;
    }

    public void setPhoto(String photo) {
        this.photo = photo;
    }

    public UserRole getUserRole() {
        return userRole;
    }

    public void setUserRole(UserRole userRole) {
        this.userRole = userRole;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        User user = (User) o;
        return Objects.equals(name, user.name) &&
                Objects.equals(login, user.login) &&
                Objects.equals(password, user.password) &&
                Objects.equals(photo, user.photo) &&
                userRole == user.userRole;
    }

    @Override
    public int hashCode() {
        return Objects.hash(name, login, password, photo, userRole);
    }

    @Override
    public String toString() {
        return "User{" +
                super.toString() +
                ", name='" + name + '\'' +
                ", login='" + login + '\'' +
                ", password='" + password + '\'' +
                ", photo='" + photo + '\'' +
                ", userRole=" + userRole +
                '}';
    }
}
