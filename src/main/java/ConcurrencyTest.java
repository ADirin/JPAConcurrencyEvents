import org.junit.jupiter.api.Test;
import static org.junit.jupiter.api.Assertions.assertEquals;

public class ConcurrencyTest {

    @Test
    public void testConcurrentAccess() throws InterruptedException {
        // Create separate instances of Car and Driver for each thread
        Car car = new Car();
        Driver driver = new Driver();

        // Create multiple threads to access the methods concurrently
        Thread thread1 = new Thread(() -> {
            car.updateCarDetails("Toyota");
        });

        Thread thread2 = new Thread(() -> {
            driver.updateDriverDetails("John", 5); // Update driver details
        });

        // Start the threads
        thread1.start();
        thread2.start();

        // Join the threads to wait for them to complete
        thread1.join();
        thread2.join();

        // Validate the results
        assertEquals("Toyota", car.getName());
        assertEquals("John", driver.getName());
        assertEquals(5, driver.getExperience());
    }
}
