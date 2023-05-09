package crookedCourse;

public record Progress(int raced, int racing, int toRace) {

    public String toString() {
        return "Progress{" +
                "raced=" + raced +
                ", racing=" + racing +
                ", toRace=" + toRace +
                "}";
    }
}
