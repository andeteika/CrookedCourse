package crookedCourse.resolver;

import crookedCourse.Contestant;
import crookedCourse.Rabbit;
import org.junit.jupiter.api.extension.ExtensionContext;
import org.junit.jupiter.api.extension.ParameterContext;
import org.junit.jupiter.api.extension.ParameterResolutionException;
import org.junit.jupiter.api.extension.ParameterResolver;

import java.lang.reflect.Parameter;
import java.time.LocalDate;
import java.time.Month;
import java.util.Map;
import java.util.HashMap;
import java.util.Objects;

public class RabbitsParameterResolver implements ParameterResolver {

    @Override
    public boolean supportsParameter(ParameterContext parameterContext, ExtensionContext extensionContext) throws ParameterResolutionException {
        Parameter parameter = parameterContext.getParameter();
        return Objects.equals(parameter.getParameterizedType().getTypeName(), "java.util.Map<java.lang.String, crookedCourse.Rabbit>");
    }

    @Override
    public Object resolveParameter(ParameterContext parameterContext, ExtensionContext extensionContext) throws ParameterResolutionException {
        Map<String, Rabbit> rabbits = new HashMap<>();

        // Legit entries
        rabbits.put("Bronson", new Rabbit(
                new Contestant("Eldon Jackson", LocalDate.of(1989, Month.MAY, 5)),
                "Bronson", LocalDate.of(2022, Month.MAY, 29), true));
        rabbits.put("Abraham", new Rabbit(
                new Contestant("Lorenzo Pearce", LocalDate.of(2000, Month.APRIL, 28)),
                "Abraham", LocalDate.of(2019, Month.AUGUST, 22), true));
        rabbits.put("Niles", new Rabbit(
                new Contestant("Rocky Woods", LocalDate.of(1997, Month.NOVEMBER, 24)),
                "Niles", LocalDate.of(2022, Month.MAY, 29), true));
        rabbits.put("Vic", new Rabbit(
                new Contestant("Guy Fleming", LocalDate.of(1995, Month.JANUARY, 9)),
                "Vic", LocalDate.of(2022, Month.MAY, 29), true));
        rabbits.put("Cocoa", new Rabbit(
                new Contestant("Basil Wise", LocalDate.of(2002, Month.APRIL, 8)),
                "Cocoa", LocalDate.of(2022, Month.MAY, 29), true));
        rabbits.put("Kalamazoo", new Rabbit(
                new Contestant("Phoebe Salazar", LocalDate.of(1996, Month.APRIL, 23)),
                "Kalamazoo", LocalDate.of(2022, Month.MAY, 29), true));
        rabbits.put("Tarzan", new Rabbit(
                new Contestant("Nelson Ball", LocalDate.of(1992, Month.SEPTEMBER, 9)),
                "Tarzan", LocalDate.of(2022, Month.MAY, 29), true));
        rabbits.put("Biscuit", new Rabbit(
                new Contestant("Sybil Wade", LocalDate.of(1994, Month.SEPTEMBER, 24)),
                "Biscuit", LocalDate.of(2022, Month.MAY, 29), true));
        rabbits.put("Ricky", new Rabbit(
                new Contestant("Camille Bishop", LocalDate.of(1990, Month.JULY, 28)),
                "Ricky", LocalDate.of(2022, Month.MAY, 29), true));
        rabbits.put("Carissa", new Rabbit(
                new Contestant("Ash Mack", LocalDate.of(2003, Month.JULY, 28)),
                "Carissa", LocalDate.of(2022, Month.MAY, 29), true));

        return rabbits;
    }
}