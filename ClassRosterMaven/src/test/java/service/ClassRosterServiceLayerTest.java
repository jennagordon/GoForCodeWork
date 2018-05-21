package service;

import dao.ClassRosterAuditDao;
import dao.ClassRosterAuditDaoStubImpl;
import dao.ClassRosterDao;
import dao.ClassRosterDaoStubImpl;
import dto.Student;
import org.junit.After;
import org.junit.Before;
import org.junit.Test;
import org.springframework.context.ApplicationContext;
import org.springframework.context.support.ClassPathXmlApplicationContext;

import static org.junit.Assert.*;

public class ClassRosterServiceLayerTest {

    private ClassRosterServiceLayer service;

    // constructor to use the stub files
    public ClassRosterServiceLayerTest() {
        //BEFORE SPRING REFACTOR
//        ClassRosterDao dao = new ClassRosterDaoStubImpl();
//        ClassRosterAuditDao auditDao = new ClassRosterAuditDaoStubImpl();
//
//        service = new ClassRosterServiceLayerImpl(dao, auditDao);

        ApplicationContext ctx = new ClassPathXmlApplicationContext("applicationContext.xml");
        service = ctx.getBean("serviceLayer", ClassRosterServiceLayer.class);
    }

    @Before
    public void setUp() throws Exception {

    }

    @After
    public void tearDown() throws Exception {
    }

    @Test
    public void testCreateStudent() throws Exception {
        // creating new student
        Student student = new Student("0002");
        student.setFirstName("Sally");
        student.setLastName("Smith");
        student.setCohort("Java-Jan-2015");

        // testing the business rules
        // don't care what the dao is doing
        service.createStudent(student);
    }

    @Test
    public void testCreateStudentDuplicateId() throws Exception {
        // creating new student
        Student student = new Student("001");
        student.setFirstName("Sally");
        student.setLastName("Smith");
        student.setCohort("Java-Jan-2015");

        // trying to catch the duplicate ID exception
        try {
            service.createStudent(student);
            fail("Expected ClassRosterDuplicateIdException was not thrown");

        } catch (ClassRosterDuplicateIdException e) {
            return;
        }
    }

    @Test
    public void testCreateStudentInvalidData() throws Exception {
        // creating new student
        Student student = new Student("0002");
        // created invalid data with first name
        student.setFirstName("");
        student.setLastName("Smith");
        student.setCohort("Java-Jan-2015");

        // trying to catch the data validation exception
        try {
            service.createStudent(student);
            fail("Expected ClassRosterDataValidationException was not thrown");

        } catch (ClassRosterDataValidationException e) {
            return;
        }
    }

    @Test
    public void testGetAllStudents() throws Exception {
        // Stubbed out version only has one student so
        // we are expecting one
        assertEquals(1, service.getAllStudents().size());
    }

    @Test
    public void testGetStudent() throws Exception {
        // test we get back a student for 001
        Student student = service.getStudent("001");
        assertNotNull(student);
        // test we don't get back a student for 002
        student = service.getStudent("999");
        assertNull(student);
    }

    @Test
    public void removeStudent() throws Exception {
        // test we get back a student for 001
        // and can remove
        Student student = service.removeStudent("001");
        assertNotNull(student);
        // test we don't get back a student for 002
        // so we can't remove
        student = service.removeStudent("999");
        assertNull(student);
    }
}