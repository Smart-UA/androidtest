package home.smart.cn.ua.androidtest;

public class Account{
        private String login;
        private String md5password;

    public Account(String login, String md5password) {
        this.login = login;
        this.md5password = md5password;
    }

    public String getLogin() {
        return login;
    }

    public void setLogin(String login) {
        this.login = login;
    }

    public String getMd5password() {
        return md5password;
    }

    public void setMd5password(String md5password) {
        this.md5password = MD5.hash(md5password);
    }
}
