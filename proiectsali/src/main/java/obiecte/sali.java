package obiecte;

public class sali {
    private static int idsali;
    private String nume;
    public sali(String n){
        nume=n;
        idsali++;
    }

    public String getNume() {
        return nume;
    }

    public void setNume(String nume) {
        this.nume = nume;
    }
}
