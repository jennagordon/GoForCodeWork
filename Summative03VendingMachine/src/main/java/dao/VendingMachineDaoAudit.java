package dao;

public interface VendingMachineDaoAudit {

    public void writeAuditEntry(String entry) throws VendingMachinePersistenceException;

}
