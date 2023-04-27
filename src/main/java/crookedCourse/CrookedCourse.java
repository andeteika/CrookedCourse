package crookedCourse;

import java.util.Set;
import java.util.HashSet;
import java.util.Map;
import java.util.HashMap;
import java.util.Collections;
import java.util.Arrays;

/**
 * The CrookedCourse class represents the actual competition and it menages all the records and action of the course
 */
public class CrookedCourse {

    /**
     * This map contains all the rabbits and their scores for each level of difficulty.
     */
    private final HashMap<Rabbit, Set<Performance>> performances;

    /**
     * Empty constructor which creates and empty Map for storing the records of the rabbits.
     */
    public CrookedCourse() {
        performances = new HashMap<>();
    }

    /**
     * It returns the Map containing all the rabbits and their scores.
     *
     * @return Map of the rabbit performances.
     */
    public Map<Rabbit, Set<Performance>> getRecords() {
        return Collections.unmodifiableMap(performances);
    }

    /**
     * It adds a varargs of rabbits to the competition with a blank record of performances.
     *
     * @param rabbitsToAdd rabbits to add.
     */
    public void addRabbit(Rabbit... rabbitsToAdd) {
        Arrays.stream(rabbitsToAdd).forEach(rabbit -> {
            performances.put(rabbit, new HashSet<>());
        });
    }

    // TODO: implement a method that accepts a vararg of scores to be added.
    /**
     * It adds a performance to set of performances of a specified rabbit.
     * @param rabbit the rabbit.
     * @param scoreToAdd the score to be added.
     * @throws IllegalArgumentException thrown if a rabbit has already performed in a difficulty level.
     */
    public void recordPerformance(Rabbit rabbit, Performance scoreToAdd) throws IllegalArgumentException {
        if (performances.containsKey(rabbit)) {
            Set<Performance> scores = performances.get(rabbit);
            for (Performance score : scores) {
                if (score.level() == scoreToAdd.level())
                    throw new IllegalArgumentException("Rabbit " + rabbit.name() + " already has a " + scoreToAdd.level() + " level score.");
            }
            scores.add(scoreToAdd);
        } else throw new IllegalArgumentException("Rabbit" + rabbit.name() + " is not present in the competition.");
    }

    /**
     * Returns a Progress object which represent the competition progress, meant as collecting the rabbits' performances.
     * @return the progress of the competition.
     */
    public Progress progress() {
        return new Progress(0, 0, 100);
    }

    /**
     * Returns a String representation of the crooked course.
     * @return a String representation of the crooked course's records.
     */
    @Override
    public String toString() {
        return "CrookedCourse{records=" + performances + "}";
    }
}