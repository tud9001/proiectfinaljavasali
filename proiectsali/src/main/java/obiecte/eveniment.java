package obiecte;

import jdk.vm.ci.meta.Local;

import java.time.LocalDate;
import java.util.Date;

public class eveniment {
    private static int ideveniment;
    private String nume;
    private Date datainceput;
    private Date datafinal;
    private int idsali;
    private int idcont;
    public eveniment(String n, Date di, Date df, int s, int c){
        nume=n;
        datainceput=di;
        datafinal=df;
        idsali=s;
        idcont=c;
        ideveniment++;
    }

    public String getNume() {
        return nume;
    }

    public int getIdcont() {
        return idcont;
    }

    public int getIdsali() {
        return idsali;
    }

    public Date getDatafinal() {
        return datafinal;
    }

    public Date getDatainceput() {
        return datainceput;
    }

    public void setNume(String nume) {
        this.nume = nume;
    }

    public void setDatainceput(Date datainceput) {
        this.datainceput = datainceput;
    }

    public void setDatafinal(Date datafinal) {
        this.datafinal = datafinal;
    }

    public void setIdcont(int idcont) {
        this.idcont = idcont;
    }

    public void setIdsali(int idsali) {
        this.idsali = idsali;
    }
}
