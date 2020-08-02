package Data.Models;

public class Specialization {

    private String pick1;
    private String pick2;
    private String pick3;

    public String getPick1() {
        return pick1;
    }

    public void setPick1(String pick1) {
        this.pick1 = pick1;
    }

    public String getPick2() {
        return pick2;
    }

    public void setPick2(String pick2) {
        this.pick2 = pick2;
    }

    public String getPick3() {
        return pick3;
    }

    public void setPick3(String pick3) {
        this.pick3 = pick3;
    }

    @Override
    public String toString() {
        return "Specialization{" +
                "pick1='" + pick1 + '\'' +
                ", pick2='" + pick2 + '\'' +
                ", pick3='" + pick3 + '\'' +
                '}';
    }


}
