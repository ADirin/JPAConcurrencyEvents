import javax.persistence.PrePersist;
import javax.persistence.PreUpdate;
import javax.persistence.PreRemove;

public class DriverListener {

    @PrePersist
    public void prePersistDriver(Driver driver) {
        System.out.println("Driver is about to be persisted: " + driver.getName());
    }

    @PreUpdate
    public void preUpdateDriver(Driver driver) {
        System.out.println("Driver is about to be updated: " + driver.getName());
    }

    @PreRemove
    public void preRemoveDriver(Driver driver) {
        System.out.println("Driver is about to be removed: " + driver.getName());
    }
}
