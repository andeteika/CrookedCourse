package crookedCourse;

import crookedCourse.resolver.PerformanceParameterResolver;
import crookedCourse.resolver.RabbitsParameterResolver;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Nested;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;

import java.time.LocalDate;
import java.time.Month;
import java.util.HashSet;
import java.util.Map;
import java.util.Set;

import static org.assertj.core.api.Assertions.assertThat;
import static org.junit.jupiter.api.Assertions.*;

@DisplayName("Crooked Course")
@ExtendWith(RabbitsParameterResolver.class)
@ExtendWith(PerformanceParameterResolver.class)
public class CrookedCourseTest {

    private CrookedCourse course;
    private Map<String, Rabbit> rabbits;
    private Map<String, Performance> performances;

    @BeforeEach
    void init(Map<String, Rabbit> rabbits, Map<String, Performance> performances) {
        course = new CrookedCourse();
        this.rabbits = rabbits;
        this.performances = performances;
    }

    @Nested
    @DisplayName("is empty")
    class IsEmpty {

        @Test
        @DisplayName("when no Rabbits have been added")
        void courseIsEmptyWhenNoRecordsAreAdded() {
            Map<Rabbit, Set<Performance>> records = course.getRecords();
            assertTrue(records.isEmpty(), "Crooked Course should be empty.");
        }

        @Test
        @DisplayName("when add() is called without parameters")
        void courseIsEmptyAddIsCalledWithoutParameters() {
            course.addRabbit();
            Map<Rabbit, Set<Performance>> records = course.getRecords();
            assertTrue(records.isEmpty(), "Crooked Course should be empty.");
        }
    }

    @Nested
    @DisplayName("adding Rabbit")
    class AddingRabbits {

        @Test
        @DisplayName("contains two Rabbits when two Rabbits are added")
        void courseContainsTwoRabbitsWhenTwoRabbitsAreAdded() {
            course.addRabbit(rabbits.get("Bronson"), rabbits.get("Abraham"));
            Map<Rabbit, Set<Performance>> records = course.getRecords();
            assertEquals(records.size(), 2, "Crooked Course should contains 2 rabbits.");
        }

        @Test
        @DisplayName("contains Rabbit but not Performances")
        void courseContainsRabbitsButNotRaceScores() {
            course.addRabbit(rabbits.get("Bronson"), rabbits.get("Abraham"), rabbits.get("Niles"));
            Map<Rabbit, Set<Performance>> records = course.getRecords();
            records.forEach((rabbit, scores) -> assertTrue(scores.isEmpty(), "Rabbit should not contain scores."));
        }

        @Nested
        @DisplayName("declines Rabbit")
        class DeclineRabbits {

            @Test
            @DisplayName("when the Rabbit is unlawful")
            void courseRefrainsTheAddingOfUnlawfulRabbits() {
                try {
                    Rabbit paddy = new Rabbit(new Contestant("Mario Stallone", LocalDate.of(2006, Month.NOVEMBER, 24)), "Paddy", LocalDate.of(2023, Month.MARCH, 21), true);
                    course.addRabbit(paddy);
                    fail();
                } catch (Exception exception) {
                    assertTrue(exception instanceof IllegalArgumentException, "Should throw IllegalArgumentException.");
                }
            }

            @Test
            @DisplayName("when the owner of the Rabbit is unlawful")
            void courseRefrainsTheAddingOfRabbitsWithUnlawfulOwners() {
                try {
                    Rabbit grace = new Rabbit(new Contestant("Loren Gray", LocalDate.of(2020, Month.APRIL, 23)), "Grace", LocalDate.of(2020, Month.JULY, 12), true);
                    course.addRabbit(grace);
                    fail();
                } catch (Exception exception) {
                    assertTrue(exception instanceof IllegalArgumentException, "Should throw IllegalArgumentException.");
                }
            }
        }
    }

    @Test
    @DisplayName("returns an immutable Records Map for the client")
    void courseRecordsReturnedShouldBeImmutableForTheClient() {
        Map<Rabbit, Set<Performance>> records = course.getRecords();
        try {
            records.put(rabbits.get("Bronson"), new HashSet<>());
            fail();
        } catch (Exception exception) {
            assertTrue(exception instanceof UnsupportedOperationException, "Should throw UnsupportedOperationException.");
        }
    }

    @Nested
    @DisplayName("adding Performances")
    class RecorderPerformance {

        @Test
        @DisplayName("to a Rabbit already enrolled")
        void courseRecordsThePerformancesOfTheRabbit() {
            course.addRabbit(rabbits.get("Bronson"));
            course.recordPerformance(rabbits.get("Bronson"), performances.get("Legit Easy Performance"), performances.get("Legit Medium Performance"));
            assertThat(course.getRecords().get(rabbits.get("Bronson"))).hasSize(2);
        }

        @Nested
        @DisplayName("declines entries")
        class DeclinePerformances {

            @Test
            @DisplayName("when the Rabbit was not enrolled first")
            void courseDeclinesThePerformancesWhenTheRabbitIsNotEnrolledIn() {
                try {
                    course.recordPerformance(rabbits.get("Bronson"), performances.get("Legit Easy Performance"), performances.get("Legit Medium Performance"));
                    fail();
                } catch (Exception exception) {
                    assertThat(exception).isInstanceOf(IllegalArgumentException.class);
                }
            }

            @Test
            @DisplayName("when a duplicate difficulty Level exists for that Rabbit")
            void courseDeclinesPerformancesWhenDuplicateLevelExists() {
                try {
                    course.addRabbit(rabbits.get("Bronson"));
                    course.recordPerformance(rabbits.get("Bronson"), performances.get("Legit Easy Performance"), performances.get("Legit Easy Performance"));
                    fail();
                } catch (Exception exception) {
                    assertThat(exception).isInstanceOf(IllegalArgumentException.class);
                }
            }
        }

    }

}