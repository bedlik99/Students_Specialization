package Data.Models;

/**
 * Student class includes the fields that are loaded from JSONFile. Object of class student is being held in list.
 */
public class Student {

    private int id;
    private String firstName;
    private String lastName;
    private long albumsNumber;
    private Specialization specialization = new Specialization();
    private String specializationAssigned;
    private double averageGrade;


    public Specialization getSpecialization() {
        return specialization;
    }

    public void setSpecialization(Specialization specialization) {
        this.specialization = specialization;
    }

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public String getFirstName() {
        return firstName;
    }

    public void setFirstName(String firstName) {
        this.firstName = firstName;
    }

    public String getLastName() {
        return lastName;
    }

    public void setLastName(String lastName) {
        this.lastName = lastName;
    }

    public long getAlbumsNumber() {
        return albumsNumber;
    }

    public void setAlbumsNumber(long albumsNumber) {
        this.albumsNumber = albumsNumber;
    }


    public String getSpecializationAssigned() {
        return specializationAssigned;
    }

    public void setSpecializationAssigned(String specializationAssigned) {
        this.specializationAssigned = specializationAssigned;
    }

    public double getAverageGrade() {
        return averageGrade;
    }

    public void setAverageGrade(double averageGrade) {
        this.averageGrade = averageGrade;
    }

    @Override
    public String toString() {
        return "Student{" +
                "id=" + id +
                ", firstName=' " + firstName + '\'' +
                ", lastName=' " + lastName + '\'' +
                ", albumsNumber= " + albumsNumber +
                ", specialization= " + specialization +
                ", specializationGiven=' " + specializationAssigned + '\'' +
                ", averageGrade= " + averageGrade +
                '}';
    }
}
