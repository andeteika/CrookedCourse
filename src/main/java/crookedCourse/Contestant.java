package crookedCourse;

import java.time.LocalDate;
import java.time.temporal.ChronoUnit;

/**
 * Contestant class. Contestants function as owners of rabbits that are competing in the crooked course competition.
 * @author Adam Bisinella
 */
public record Contestant(String name, LocalDate dateOfBirth) {

    /**
     * Constructor which sets up the object contestant with name and date of birth. If the contestant is younger than
     * seven years an Exception is thrown.
     * @param name the name of the contestant.
     * @param dateOfBirth the date of birth of the contestant.
     */
    public Contestant(String name, LocalDate dateOfBirth) {
        this.name = name;
        this.dateOfBirth = dateOfBirth;

        if(yearsOld() < 7) throw new IllegalArgumentException("Contestant cannot be younger than seven years.");
    }

    /**
     * It returns how many years old is a contestant.
     * @return years old.
     */
    public int yearsOld() {
        return (int) ChronoUnit.YEARS.between(this.dateOfBirth(), LocalDate.now());
    }

    /**
     * It returns a String representation of the contestant.
     * @return rabbit's String representation.
     */
    @Override
    public String toString() {
        return "Contestant{name=" + name + ", dateOfBirth=" + dateOfBirth.toString() + "}";
    }
}