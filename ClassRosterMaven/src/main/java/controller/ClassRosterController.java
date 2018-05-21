package controller;

import dao.ClassRosterDao;
import dao.ClassRosterPersistenceException;
import dto.Student;
import service.ClassRosterDataValidationException;
import service.ClassRosterDuplicateIdException;
import service.ClassRosterServiceLayer;
import ui.ClassRosterView;
import ui.UserIO;
import ui.UserIOConsoleImpl;

import java.util.List;

public class ClassRosterController {
    ClassRosterView view;

    // Replace the field declaration for the ClassRosterDao:
    private ClassRosterDao dao;
    // with a declaration for the ClassRosterServiceLayer:
    private ClassRosterServiceLayer service;

    public ClassRosterController(ClassRosterServiceLayer service, ClassRosterView view) {
        this.service = service;
        this.view = view;
    }

    private UserIO io = new UserIOConsoleImpl();

    public void run() {
        boolean keepGoing = true;
        int menuSelection = 0;
        try {
            while (keepGoing) {

                menuSelection = getMenuSelection();

                switch (menuSelection) {
                    case 1:
                        listStudents();
                        break;
                    case 2:
                        createStudent();
                        break;
                    case 3:
                        viewStudent();
                        break;
                    case 4:
                        removeStudent();
                        break;
                    case 5:
                        keepGoing = false;
                        break;

                    default:
                        unknownCommand();
                }
            }
            exitMessage();
        } catch (ClassRosterPersistenceException e) {
            view.displayErrorMessage(e.getMessage());
        }
    }

    private int getMenuSelection() {
        return view.printMenuAndGetSelection();
    }

    private void createStudent() throws ClassRosterPersistenceException {
        view.displayCreateStudentBanner();
        boolean hasErrors = false;
        do {
            Student currentStudent = view.getNewStudentInfo();
            try {
                service.createStudent(currentStudent);
                view.displayCreateSuccessBanner();
                hasErrors = false;
            } catch (ClassRosterDuplicateIdException | ClassRosterDataValidationException e) {
                hasErrors = true;
                view.displayErrorMessage(e.getMessage());
            }
        } while (hasErrors);

        /*  CODE BEFORE REFACTORING
        Student newStudent = view.getNewStudentInfo();
        dao.addStudent(newStudent.getStudentId(), newStudent);
        view.displayCreateSuccessBanner();
        */
    }
    private void listStudents() throws ClassRosterPersistenceException {
        view.displayDisplayAllBanner();
        List<Student> studentList = service.getAllStudents();
        view.displayStudentList(studentList);
    }
    private void viewStudent() throws ClassRosterPersistenceException {
        view.displayDisplayStudentBanner();
        String studentId = view.getStudentIdChoice();
        Student student = service.getStudent(studentId);
        view.displayStudent(student);
    }
    private void removeStudent() throws ClassRosterPersistenceException {
        view.displayRemoveStudentBanner();
        String studentId = view.getStudentIdChoice();
        service.removeStudent(studentId);
        view.displayRemoveSuccessBanner();
    }
    private void unknownCommand() {
        view.displayUnknownCommandBanner();
    }
    private void exitMessage() {
        view.displayExitBanner();
    }
    public ClassRosterController(ClassRosterDao dao, ClassRosterView view) {
       this.dao = dao;
        this.view = view;
    }
}
