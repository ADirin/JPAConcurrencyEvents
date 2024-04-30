import javax.persistence.EntityManager;
import javax.persistence.EntityManagerFactory;
import javax.persistence.EntityTransaction;
import javax.persistence.Persistence;
import java.util.List;

public class Main {
    private static EntityManagerFactory emf; // Declare EntityManagerFactory variable

    public static void main(String[] args) {
        EntityManagerFactory emf = Persistence.createEntityManagerFactory("UserPU");
        EntityManager em = emf.createEntityManager();
        EntityTransaction tx = em.getTransaction();

        try {
            // Begin transaction
            tx.begin();

            // Create a new driver
            Car car = new Car("BMW");
            Driver driver = new Driver("Outi S", 10);
            driver.setDid(5L); // Manually assign the ID
            em.persist(driver);

            // Commit transaction
            tx.commit();

            // Update car details concurrently
            updateCarDetailsConcurrently(car);

            // Update driver's experience concurrently
            updateDriverExperienceConcurrently(driver, em);

            // Refresh the driver entity to get the latest state from the database
            em.refresh(driver);

            // Print driver's experience and updated car name
            System.out.println("Driver's experience: " + driver.getExperience());
            System.out.println("Updated car name: " + car.getName());

        } catch (Exception e) {
            if (tx != null && tx.isActive()) {
                tx.rollback();
            }
            e.printStackTrace();
        } finally {
            em.close();
            emf.close();
        }
    }

    private static void updateCarDetailsConcurrently(Car car) {
        // Simulate concurrent access by updating the car details in two different threads
        Thread thread1 = new Thread(() -> car.updateCarDetails("Honda"));
        Thread thread2 = new Thread(() -> car.updateCarDetails("Ford"));

        // Start the threads
        thread1.start();
        thread2.start();

        try {
            // Wait for the threads to finish
            thread1.join();
            thread2.join();
        } catch (InterruptedException e) {
            e.printStackTrace();
        }
    }

    private static void updateDriverExperienceConcurrently(Driver driver, EntityManager em) {
        // Simulate concurrent access by updating the driver's experience in two different threads
        Thread thread1 = new Thread(() -> {
            EntityManager em1 = emf.createEntityManager();
            EntityTransaction tx1 = em1.getTransaction();
            try {
                tx1.begin();
                Driver driver1 = em1.find(Driver.class, driver.getDid());
                driver1.setExperience(driver1.getExperience() + 1);
                tx1.commit();
            } catch (Exception e) {
                if (tx1 != null && tx1.isActive()) {
                    tx1.rollback();
                }
                e.printStackTrace();
            } finally {
                em1.close();
            }
        });

        Thread thread2 = new Thread(() -> {
            EntityManager em2 = emf.createEntityManager();
            EntityTransaction tx2 = em2.getTransaction();
            try {
                tx2.begin();
                Driver driver2 = em2.find(Driver.class, driver.getDid());
                driver2.setExperience(driver2.getExperience() + 1);
                tx2.commit();
            } catch (Exception e) {
                if (tx2 != null && tx2.isActive()) {
                    tx2.rollback();
                }
                e.printStackTrace();
            } finally {
                em2.close();
            }
        });

        // Start the threads
        thread1.start();
        thread2.start();

        try {
            // Wait for the threads to finish
            thread1.join();
            thread2.join();
        } catch (InterruptedException e) {
            e.printStackTrace();
        }
    }
}
