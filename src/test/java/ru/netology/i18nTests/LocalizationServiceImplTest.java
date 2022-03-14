package ru.netology.i18nTests;

import org.junit.jupiter.api.*;
import org.junit.jupiter.params.ParameterizedTest;
import org.junit.jupiter.params.provider.Arguments;
import org.junit.jupiter.params.provider.MethodSource;
import ru.netology.entity.Country;
import ru.netology.i18n.LocalizationService;
import ru.netology.i18n.LocalizationServiceImpl;

import java.util.stream.Stream;

public class LocalizationServiceImplTest {

    private static LocalizationService localizationService;

    @BeforeAll
    public static void startTests() {
        localizationService = new LocalizationServiceImpl();
        System.out.println("Start LocalizationServiceImplTest");
    }

    @AfterAll
    public static void completeTests() {
        System.out.println("Complete GeoServiceImplTest");
    }

    @BeforeEach
    public void startTest() {
        System.out.println("Start test");
    }

    @AfterEach
    public void completeTest() {
        System.out.println("Complete test");
    }

    //
    @Test
    public void localeTest() {
        //arrange
        Country country = null;
        Class<NullPointerException> expected = NullPointerException.class;
        //assert
        Assertions.assertThrows(expected,
                //act
                () -> localizationService.locale(country));
    }

    @ParameterizedTest
    @MethodSource("source")
    public void testByIpWithoutNull(Country country, String expected) {
        //act
        String message = localizationService.locale(country);
        //assert
        Assertions.assertEquals(message, expected);
    }

    static Stream<Arguments> source() {
        return Stream.of(
                Arguments.of(Country.USA, "Welcome"),
                Arguments.of(Country.RUSSIA, "Добро пожаловать"),
                Arguments.of(Country.BRAZIL, "Welcome"),
                Arguments.of(Country.GERMANY, "Welcome")
        );
    }
}