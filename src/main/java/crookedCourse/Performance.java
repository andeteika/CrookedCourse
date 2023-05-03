package crookedCourse;

// TODO: Implement Performance score() method.

/**
 * The Performance is used to distinguish the different performances of a rabbit in the crooked course.
 */
public class Performance {

    /**
     * The Levels enumeration contains all the different courses a rabbit must go through to complete its
     * performance in the competition.
     */
    public enum Levels {

        EASY(30, 45, 8, 1),
        MEDIUM(38, 65, 10, 2),
        DIFFICULT(45, 80, 10, 3),
        ELITE(50, 80, 12, 5);

        private final int maximumHeight;
        private final int maximumLength;
        private final int minimumJumps;
        private final int factor;
        Levels(int maximumHeight, int maximumLength, int minimumJumps, int factor) {
            this.maximumHeight = maximumHeight;
            this.maximumLength = maximumLength;
            this.minimumJumps = minimumJumps;
            this.factor = factor;
        }

        public String getLevelName() {
            String difficultyName = "";
            switch (factor) {
                case 1 -> difficultyName = "Easy";
                case 2 -> difficultyName = "Medium";
                case 3 -> difficultyName = "Difficult";
                case 4 -> difficultyName = "Elite";
            }
            return difficultyName;
        }

        @Override
        public String toString() {
            return getLevelName()+"{" +
                    "maximumHeight="+this.maximumHeight +
                    ", maximumLength="+this.maximumLength +
                    ", minimumJumps="+this.minimumJumps +
                    ", factor="+this.factor +
                    "}";
        }
    }

    private final int TIME_LIMIT;
    private final Levels level;
    private final int faultJumps;
    private final int raceTime;
    private final boolean courseJumpedInOrder;
    private final boolean rabbitWasForced;
    private final boolean ownerWasInFront;

    /**
     * Performance constructor that sets the performance done by a rabbit in a certain level of the course.
     * @param level level of the performance.
     * @param faultJumps number of fault jumps.
     * @param raceTime race time.
     * @param courseJumpedInOrder if the obstacles jumps were in order.
     * @param rabbitWasForced if the rabbit was forced to jump.
     * @param ownerWasInFront if the owner was always in front of the rabbit.
     */
    public Performance(Levels level, int faultJumps, int raceTime, boolean courseJumpedInOrder, boolean rabbitWasForced, boolean ownerWasInFront) {
        this.level = level;
        this.faultJumps = faultJumps;
        this.raceTime = raceTime;
        this.courseJumpedInOrder = courseJumpedInOrder;
        this.rabbitWasForced = rabbitWasForced;
        this.ownerWasInFront = ownerWasInFront;

        this.TIME_LIMIT = 250;
    }

    /**
     * It gets the time limit in which the rabbits need to perform the circuit.
     * @return the time limit.
     */
    public int getTIME_LIMIT() {
        return TIME_LIMIT;
    }

    /**
     * It gets the difficulty level of the performance.
     * @return the difficulty level.
     */
    public Levels getLevel() {
        return level;
    }

    /**
     * It gets the number of fault jumps done by rabbit during the performance.
     * @return the number of fault jumps.
     */
    public int getFaultJumps() {
        return faultJumps;
    }

    /**
     * It gets the race time in which the rabbit completed the performance.
     * @return the race time.
     */
    public int getRaceTime() {
        return raceTime;
    }

    /**
     * It says if the rabbit jumped this difficulty level circuit in order.
     * @return if the level obstacles were jumped in order.
     */
    public boolean isCourseJumpedInOrder() {
        return courseJumpedInOrder;
    }

    /**
     * It says if the rabbit was forced to jump.
     *
     * @return
     */
    public boolean isRabbitWasForced() {
        return rabbitWasForced;
    }

    public boolean isOwnerWasInFront() {
        return ownerWasInFront;
    }

    /**
     * It returns the score of a Performance.
     *
     * @return score.
     */
    public double score() {
        if(raceTime <= 250 && courseJumpedInOrder && !rabbitWasForced && ownerWasInFront) {
            double timeFactor = (double) raceTime / TIME_LIMIT;
            return (faultJumps * level.factor) / timeFactor;
        }
        return 0;
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