package dvdlibrary.advice;

import dvdlibrary.dao.DVDLibraryAuditDao;
import dvdlibrary.dao.DVDLibraryDao;
import dvdlibrary.dao.DVDLibraryPersistenceException;
import org.aspectj.lang.JoinPoint;

public class LoggingAdvice {

    DVDLibraryAuditDao auditDao;

    public LoggingAdvice(DVDLibraryAuditDao auditDao) {
        this.auditDao = auditDao;
    }

    public void createAuditEntry (JoinPoint jp) throws DVDLibraryPersistenceException {
        Object[] args = jp.getArgs();
        String auditEntry = jp.getSignature().getName() + ": ";
        for(Object currentArg : args) {
            auditEntry += currentArg;
        }
        try {
            auditDao.writeAuditEntry(auditEntry);
        } catch (DVDLibraryPersistenceException e) {
            System.err.println("ERROR: Could not create entry in LoggingAdvice.");
        }
    }
}
