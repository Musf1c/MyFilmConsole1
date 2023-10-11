package entity;

public class AdminUser extends User {


    public AdminUser(String nickName, String login, String password, UserRole userRole) {
        super(nickName, login, password, userRole);
    }
    public AdminUser(User user) {
        super(user.nickName, user.login, user.getPassword(), user.getUserRole() );
    }
    public void updateData(String nickName, String login, String password, UserRole userRole) {
        this.nickName = nickName;
        this.login = login;
        this.password = password;
        this.userRole = userRole;
    }
    @Override
    public String toString() {
        return "AdminUser{" +
                ", nickName='" + nickName + '\'' +
                ", login='" + login + '\'' +
                ", userRole='" + userRole + '\'' +
                '}';
    }
}
