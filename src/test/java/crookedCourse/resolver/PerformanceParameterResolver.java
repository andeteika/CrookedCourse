package crookedCourse.resolver;

import crookedCourse.Performance;
import org.junit.jupiter.api.extension.ExtensionContext;
import org.junit.jupiter.api.extension.ParameterContext;
import org.junit.jupiter.api.extension.ParameterResolutionException;
import org.junit.jupiter.api.extension.ParameterResolver;

import java.lang.reflect.Parameter;
import java.util.HashMap;
import java.util.Map;
import java.util.Objects;

public class PerformanceParameterResolver implements ParameterResolver {

    @Override
    public boolean supportsParameter(ParameterContext parameterContext, ExtensionContext extensionContext) throws ParameterResolutionException {
        Parameter parameter = parameterContext.getParameter();
        return Objects.equals(parameter.getParameterizedType().getTypeName(), "java.util.Map<java.lang.String, crookedCourse.Performance>");
    }

    @Override
    public Object resolveParameter(ParameterContext parameterContext, ExtensionContext extensionContext) throws ParameterResolutionException {
        Map<String, Performance> performances = new HashMap<>();

        performances.put("Legit Easy Performance",
                new Performance(Performance.Levels.EASY, 1, 210, true, false, true));
        performances.put("Legit Medium Performance",
                new Performance(Performance.Levels.MEDIUM, 3, 234, true, false, true));
        performances.put("Legit Difficult Performance",
                new Performance(Performance.Levels.DIFFICULT, 5, 248, true, false, true));
        performances.put("Legit Elite Performance",
                new Performance(Performance.Levels.EASY, 1, 210, true, false, true));
        performances.put("Unlawful Easy Performance",
                new Performance(Performance.Levels.EASY, 1, 210, true, false, true));

        return performances;
    }
}
