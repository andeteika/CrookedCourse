package crookedCourse;

import java.time.LocalDate;
import java.time.Period;

/**
 * Rabbit class, which is the actual contestant of the crooked course competition.
 * @author Adam Bisinella
 */
public class Rabbit {

    /**
     * The name of the rabbit.
     */
    private final String name;
    /**
     * The date of birth of the rabbit.
     */
    private final LocalDate dateOfBirth;
    /**
     * The owner of the rabbit.
     */
    private final Contestant owner;
    /**
     * If the rabbit is healthy or not.
     */
    private boolean isHealthy;

    /**
     * Rabbit parameterized constructor for the creation of a new Rabbit.
     * @param owner the owner of the rabbit.
     * @param name the name of the rabbit.
     * @param dateOfBirth the date of birth of the rabbit.
     * @param isHealthy if the rabbit is healthy or not.
     */
    public Rabbit(Contestant owner, String name, LocalDate dateOfBirth, boolean isHealthy) throws IllegalArgumentException {
        this.owner = owner;
        this.name = name;
        this.dateOfBirth = dateOfBirth;
        this.isHealthy = isHealthy;

        if(monthsOld() < 4) throw new IllegalArgumentException(this.name + " cannot enter the competition. It must be at least 4 months old.");
    }

    /**
     * It sets the medical predicament of the rabbit.
     * @param healthy if the rabbit is healthy or not.
     */
    public void setHealthy(boolean healthy) {
        isHealthy = healthy;
    }

    /**
     * It returns the name of the rabbit.
     * @return the name of the rabbit.
     */
    public String name() {
        return name;
    }

    /**
     * It returns the date of birth of the rabbit.
     * @return the date of birth.
     */
    public LocalDate dateOfBirth() {
        return dateOfBirth;
    }

    /**
     * It returns the owner of the rabbit, which is a contestant in the competition.
     * @return the owner of the rabbit.
     */
    public Contestant getOwner() {
        return owner;
    }

    /**
     * It returns the medical predicament of the rabbit.
     * @return if the rabbit is healthy or not.
     */
    public boolean isHealthy() {
        return isHealthy;
    }

    /**
     * It returns the age of the rabbit in months.
     * @return the age of the rabbit in months.
     */
    public int monthsOld() {
        int yearsOld = Period.between(this.dateOfBirth, LocalDate.now()).getYears() * 12;
        int currentYearMonthsOld = Period.between(this.dateOfBirth, LocalDate.now()).getMonths();
        return yearsOld + currentYearMonthsOld;
    }
    /**
     * It returns a String representation of the rabbit.
     * @return representation of the rabbit in String.
     */
    @Override
    public String toString() {
        return "Rabbit{" +
                "owner=" + owner.name() +
                ", name=" + name +
                ", dateOfBirth=" + dateOfBirth.toString() +
                ", isHealthy=" + isHealthy +
                '}';
    }
}