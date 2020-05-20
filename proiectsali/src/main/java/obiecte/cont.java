package obiecte;

public class cont {
    private static int idcont;
    private String username;
    private String password;
    public cont(String u, String p){
        idcont++;
        username=u;
        password=p;

    }

    public String getPassword() {
        return password;
    }

    public String getUsername() {
        return username;
    }

    public int getIdcont() {
        return idcont;
    }

    public void setPassword(String password) {
        this.password = password;
    }

    public void setUsername(String username) {
        this.username = username;
    }

    public void setIdcont(int idcont) {
        this.idcont = idcont;
    }
}
