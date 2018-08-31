package vn.vnext.enity;

public class User {
    private String email;
    private String password;
    private String result;

    public User() {
    }

    public User(String email, String password, String result) {
        this.email = email;
        this.password = password;
        this.result = result;
    }

    public String getEmail() {
        return email;
    }

    public void setEmail(String email) {
        this.email = email;
    }

    public String getPassword() {
        return password;
    }

    public void setPassword(String password) {
        this.password = password;
    }

    public String getResult() {
        return result;
    }

    public void setResult(String result) {
        this.result = result;
    }
}
