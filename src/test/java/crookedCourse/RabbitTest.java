package crookedCourse;

import crookedCourse.resolver.RabbitsParameterResolver;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Nested;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;

import java.time.LocalDate;
import java.time.Month;
import java.util.Map;

import static org.junit.jupiter.api.Assertions.*;

@DisplayName("Rabbit")
@ExtendWith(RabbitsParameterResolver.class)
public class RabbitTest {

    private Map<String, Rabbit> rabbits;

    @BeforeEach
    void init(Map<String, Rabbit> rabbits) {
        this.rabbits = rabbits;
    }

    @Test
    @DisplayName("string representation is in the desired format")
    void stringRepresentationOfRabbit() {
        Rabbit bronson = rabbits.get("Bronson");
        String desiredToStringRepresentation = "Rabbit{owner=Eldon Jackson, name=Bronson, dateOfBirth=2022-05-29, isHealthy=true}";
        assertEquals(desiredToStringRepresentation, bronson.toString(), "toString() should return '" + desiredToStringRepresentation + "'");
    }

    @Nested
    @DisplayName("whose age")
    class RabbitAgeTest {
        @Test
        @DisplayName("must be over four months")
        void rabbitYoungerThanFourMonthsThrowsException() {
            try {
                new Rabbit(new Contestant("Mario Stallone", LocalDate.of(2006, Month.NOVEMBER, 24)), "Paddy", LocalDate.of(2023, Month.MARCH, 21), true);
                fail();
            } catch (Exception exception) {
                assertTrue(exception instanceof IllegalArgumentException, "Should throw IllegalArgumentException");
            }

        }

        @Test
        @DisplayName("is exactly four months")
        void rabbitWhichIsExactlyFourMonthsOldIsAllowedToCompete() {
            LocalDate fourMonthsAgo = LocalDate.now().minusMonths(4);
            try {
                Rabbit brody = new Rabbit( new Contestant("Ryan Gosling", LocalDate.of(2000, Month.MAY, 17)),
                            "Brody", fourMonthsAgo, true);
                assertEquals(brody.monthsOld(), 4, "Rabbit should be exactly 4 months old");
            } catch(IllegalArgumentException exception) {
                fail();
            }
        }

        @Test
        @DisplayName("is over four months")
        void rabbitOlderThanFourMonthsIsAllowedToCompete() {
            try {
                Rabbit tarzan = rabbits.get("Tarzan");
                assertTrue(tarzan.monthsOld() >= 4);
            } catch (IllegalArgumentException exception) {
                fail();
            }
        }
    }

    @Nested
    @DisplayName("Owner whose age")
    class OwnerTest {

        @Test
        @DisplayName("must be over seven years")
        void contestantCreationShouldFailWhenTheyAreYoungerThanSevenYears() {
            try {
                Contestant gary = new Contestant("Gary Delhi", LocalDate.of(LocalDate.now().getYear() - 5, Month.JULY, 3));
                new Rabbit(gary, "Jerry", LocalDate.of(2019, Month.MARCH, 21), true);
            } catch (Exception exception) {
                assertTrue(exception instanceof IllegalArgumentException, "Should throw IllegalArgumentException");
            }
        }

        @Test
        @DisplayName("is exactly seven years")
        void contestantCreationIsLegitWhenTheyAreExactlySevenYearsOld() {
            LocalDate sevenYearsAgo = LocalDate.now().minusYears(7);
            try {
                Contestant jack = new Contestant("Ryan Gosling", sevenYearsAgo);
                new Rabbit( jack, "Brody", LocalDate.of(2000, Month.MAY, 17), true);
                assertEquals(jack.yearsOld(), 7, "Contestant should be exactly 7 years old");
            } catch(IllegalArgumentException exception) {
                fail();
            }
        }

        @Test
        @DisplayName("is over seven years")
        void contestantCreationIsLEgitWhenTheyAreOlderThanSevenYears() {
            try {
                Rabbit tarzan = rabbits.get("Tarzan");
                assertTrue(tarzan.getOwner().yearsOld() >= 7);
            } catch (IllegalArgumentException exception) {
                fail();
            }
        }
    }




}
