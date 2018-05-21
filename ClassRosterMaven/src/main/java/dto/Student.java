package dto;

import java.util.Objects;

public class Student {
    private String firstName;
    private String lastName;
    private String studentId;
    private String cohort; // Java or .Net + cohort month/year

    public Student(String studentId) {
        this.studentId = studentId;
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

    public String getStudentId() {
        return studentId;
    }

    public String getCohort() {
        return cohort;
    }

    public void setCohort(String cohort) {
        this.cohort = cohort;
    }

    @Override
    public String toString() {
        return "ID: " + studentId + " |Name: " + firstName + " "
                + lastName + " |Cohort: " + cohort;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (!(o instanceof Student)) return false;
        Student student = (Student) o;
        return Objects.equals(firstName, student.firstName) &&
                Objects.equals(lastName, student.lastName) &&
                Objects.equals(studentId, student.studentId) &&
                Objects.equals(cohort, student.cohort);
    }

    @Override
    public int hashCode() {

        return Objects.hash(firstName, lastName, studentId, cohort);
    }
}
