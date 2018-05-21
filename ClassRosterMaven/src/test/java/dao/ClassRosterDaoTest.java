package dao;

import dto.Student;

import java.util.List;

import static org.junit.Assert.*;

public class ClassRosterDaoTest {

    private ClassRosterDao dao = new ClassRosterDaoFileImpl();

    @org.junit.Before
    public void setUp() throws Exception {
        List<Student> studentList = dao.getAllStudents();
        for(Student tempStudent : studentList) {
            dao.removeStudent(tempStudent.getStudentId());
        }
    }

    @org.junit.After
    public void tearDown() throws Exception {
    }

    @org.junit.Test
    public void testAddGetStudent() throws Exception {
        Student student = new Student("001");
        student.setFirstName("Joe");
        student.setLastName("Smith");
        student.setCohort("Java-May-2000");

        dao.addStudent(student.getStudentId(), student);

        Student fromDao = dao.getStudent(student.getStudentId());

        assertEquals(student, fromDao);
    }

    @org.junit.Test
    public void testTestAllStudents() throws Exception {
        // student 1
        Student student1 = new Student("001");
        student1.setFirstName("Joe");
        student1.setLastName("Smith");
        student1.setCohort("Java-May-2000");

        dao.addStudent(student1.getStudentId(), student1);

        // student 2
        Student student2 = new Student("002");
        student2.setFirstName("John");
        student2.setLastName("Doe");
        student2.setCohort(".NET-May-2000");

        dao.addStudent(student2.getStudentId(), student2);

        // ask dao for students
        // get list size (the expected is 2 because we added 2 students
        assertEquals(2, dao.getAllStudents().size());
    }
// DON'T NEED THIS CAUSE WE TESTED IT IN THE ADD TEST
//    @org.junit.Test
//    public void getStudent() {
//    }

    @org.junit.Test
    public void removeStudent() throws Exception {
        // student 1
        Student student1 = new Student("001");
        student1.setFirstName("Joe");
        student1.setLastName("Smith");
        student1.setCohort("Java-May-2000");

        dao.addStudent(student1.getStudentId(), student1);

        // student 2
        Student student2 = new Student("002");
        student2.setFirstName("John");
        student2.setLastName("Doe");
        student2.setCohort(".NET-May-2000");

        dao.addStudent(student2.getStudentId(), student2);

        // remove student 1
        // new list size is expected to be 1
        dao.removeStudent(student1.getStudentId());
        assertEquals(1, dao.getAllStudents().size());
        // if removed that student object should return null
        // so we are testing that is true
        assertNull(dao.getStudent(student1.getStudentId()));

        // repeating for student 2
        // no more student objects in list
        dao.removeStudent(student2.getStudentId());
        assertEquals(0, dao.getAllStudents().size());
        assertNull(dao.getStudent(student2.getStudentId()));
    }
}