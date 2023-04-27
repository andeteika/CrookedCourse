package crookedCourse;

// TODO: Implement Performance record.
/**
 * The Performance is used to distinguish the different performances of a rabbit in the crooked course.
 */
public record Performance(Levels level, int faultJumps, int raceTime) {

    /**
     * The Levels enumeration contains all the different courses a rabbit must go through to complete its
     * performance in the competition.
     */
    public enum Levels {
        EASY, MEDIUM, DIFFICULT, ELITE
    }

    /**
     * It returns the String representation of the performance.
     *
     * @return performance's String representation.
     */
    @Override
    public String toString() {
        return "Score{level=" + level + ", faultJumps=" + faultJumps + ", raceTime=" + raceTime + "}";
    }
}