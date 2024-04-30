import jakarta.persistence.*;

import javax.persistence.Entity;

import javax.persistence.Id;
import javax.persistence.Table;

@Entity
@Table(name = "drivers")
@EntityListeners(DriverListener.class) // Attach the event listener
public class Driver {
    @Id
    @GeneratedValue(strategy = GenerationType.SEQUENCE, generator = "driver_seq")
    @SequenceGenerator(name = "driver_seq", sequenceName = "driver_sequence", allocationSize = 1)
    private Long did; // Change to did

    private String name;

    private int experience;
    // Instead of a boolean, we'll store the active status as a String in the database
    @Convert(converter = ActiveStatusConverter.class)
    private Boolean active;
    public Driver() {
    }

    public Driver(String name) {
        this.name = name;
    }

    public Driver(String name, int experience) {
        this.name = name;
        this.experience = experience;
    }



    public Long getDid() {
        return did;
    }

    public void setDid(Long did) {
        this.did = did;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public int getExperience() {
        return experience;
    }

    public void setExperience(int experience) {
        this.experience = experience;
    }

    // Method to update driver details with optional delay
    public void updateDriverDetails(String newName, int newExperience) {
        // Update the name and experience of the driver
        this.name = newName;
        this.experience = newExperience;
    }
}
