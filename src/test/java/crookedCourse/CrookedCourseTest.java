package crookedCourse;

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

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertTrue;
import static org.junit.jupiter.api.Assertions.fail;

import static org.assertj.core.api.Assertions.assertThat;

@DisplayName("Crooked Course")
@ExtendWith(RabbitsParameterResolver.class)
public class CrookedCourseTest {

    private CrookedCourse course;
    private Map<String, Rabbit> rabbits;

    @BeforeEach
    void init(Map<String, Rabbit> rabbits) {
        course = new CrookedCourse();
        this.rabbits = rabbits;
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
    class AfterAddingRabbits {

        @Test
        @DisplayName("contains two Rabbits when two Rabbits are added")
        void courseContainsTwoRabbitsWhenTwoRabbitsAreAdded() {
            course.addRabbit(rabbits.get("Bronson"), rabbits.get("Abraham"));
            Map<Rabbit, Set<Performance>> records = course.getRecords();
            assertEquals(records.size(), 2, "Crooked Course should contains 2 rabbits.");
        }

        @Test
        @DisplayName("contains Rabbits but not Scores")
        void courseContainsRabbitsButNotRaceScores() {
            course.addRabbit(rabbits.get("Bronson"), rabbits.get("Abraham"), rabbits.get("Niles"));
            Map<Rabbit, Set<Performance>> records = course.getRecords();
            records.forEach((rabbit, scores) -> assertTrue(scores.isEmpty(), "Rabbit should not contain scores."));
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

        @Test
        @DisplayName("declines the entry if a Rabbit is unlawful")
        void courseRefrainsTheAddingOfUnlawfulRabbits() {
            try {
                Rabbit paddy = new Rabbit(
                        new Contestant("Mario Stallone", LocalDate.of(2006, Month.NOVEMBER, 24)),
                        "Paddy", LocalDate.of(2023, Month.MARCH, 21), true);
                course.addRabbit(paddy);
                fail();
            } catch (Exception exception) {
                assertTrue(exception instanceof IllegalArgumentException, "Should throw IllegalArgumentException.");
            }
        }

        @Test
        @DisplayName("declines the entry if the Owner of a Rabbit is unlawful")
        void courseRefrainsTheAddingOfRabbitsWithUnlawfulOwners() {
            try {
                Rabbit grace = new Rabbit(
                        new Contestant("Loren Gray", LocalDate.of(2020, Month.APRIL, 23)),
                                "Grace", LocalDate.of(2020, Month.JULY, 12), true);
                course.addRabbit(grace);
                fail();
            } catch (Exception exception) {
                assertTrue(exception instanceof IllegalArgumentException, "Should throw IllegalArgumentException.");
            }
        }
    }

    @Nested
    @DisplayName("in progress")
    class inProgress {

        @Test
        @DisplayName("is 0% completed and 100% to-define when no rabbit is added yet")
        void progress100PercentToInsert() {
            Progress progress = course.progress();
            assertThat(progress.completed()).isEqualTo(0);
            assertThat(progress.toRace()).isEqualTo(100);
        }

        @Test
        @DisplayName("is 40% completed and 60% to-define when 2 rabbits raced and 3 haven't yet")
        void progressWith40PercentCompletedAnd60PercentToDefine() {
            course.addRabbit();
            Progress progress = course.progress();
            assertThat(progress.completed()).isEqualTo(40);
            assertThat(progress.toRace()).isEqualTo(60);
        }

    }
}