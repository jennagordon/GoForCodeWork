package dvdlibrary.dao;

public interface DVDLibraryAuditDao {

    public void writeAuditEntry(String entry) throws DVDLibraryPersistenceException;

}
