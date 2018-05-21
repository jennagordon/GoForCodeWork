package advice;

import dao.VendingMachineDaoAudit;
import dao.VendingMachinePersistenceException;
import dto.Item;
import org.aspectj.lang.JoinPoint;
import org.aspectj.lang.annotation.AfterReturning;

public class LoggingAdvice {

    VendingMachineDaoAudit auditDao;

    public LoggingAdvice(VendingMachineDaoAudit auditDao) {
        this.auditDao = auditDao;
    }

    public void createAuditEntry(JoinPoint jp, Object name) {
        Object[] args = jp.getArgs();
        String auditEntry = jp.getSignature().getName() + ": ";
        for(Object currentArg : args) {
            auditEntry += currentArg;
        }
        try{
            auditDao.writeAuditEntry(auditEntry + " " + name.toString());
        } catch (VendingMachinePersistenceException e) {
            System.err.println("ERROR: Could not create audit entry in LoggingAdvice.");
        }
    }

    public void logAfterThrowing(JoinPoint jp, Exception error) {
        Object[] args = jp.getArgs();
        String auditEntry = jp.getSignature().getName() + ": ";
        for(Object currentArg : args) {
            auditEntry += currentArg;
        }
        try{
            // writing to the file
            // used error.getClass() so the print out is just the error time and
            // not the message presented to the user... when would you want this???
            auditDao.writeAuditEntry(auditEntry + " " + error.getClass().getSimpleName());
        } catch (VendingMachinePersistenceException e) {
            System.err.println("ERROR: Could not create audit entry in LoggingAdvice.");
        }

    }
}
