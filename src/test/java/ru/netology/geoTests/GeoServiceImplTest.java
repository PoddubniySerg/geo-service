package ru.netology.geoTests;

import org.junit.jupiter.api.*;
import org.junit.jupiter.params.ParameterizedTest;
import org.junit.jupiter.params.provider.Arguments;
import org.junit.jupiter.params.provider.MethodSource;
import ru.netology.entity.Country;
import ru.netology.entity.Location;
import ru.netology.geo.GeoService;
import ru.netology.geo.GeoServiceImpl;

import java.util.stream.Stream;

public class GeoServiceImplTest {

    private static GeoService geoService;

    @BeforeAll
    public static void startTests() {
        geoService = new GeoServiceImpl();
        System.out.println("Start GeoServiceImplTest");
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

    @ParameterizedTest
    @MethodSource("sourceWithoutNull")
    public void byIpWithoutNullTest(String ip, Location expected) {
        //act
        Location location = geoService.byIp(ip);
        //assert
        Assertions.assertEquals(location.getCountry(), expected.getCountry());
    }

    public static Stream<Arguments> sourceWithoutNull() {
        return Stream.of(
                Arguments.of("127.0.0.1", new Location(null, null, null, 0)),
                Arguments.of("172.0.32.11", new Location("Moscow", Country.RUSSIA, "Lenina", 15)),
                Arguments.of("96.44.183.149", new Location("New York", Country.USA, " 10th Avenue", 32)),
                Arguments.of("172.44.123.25", new Location("Moscow", Country.RUSSIA, null, 0)),
                Arguments.of("96.0.0.0", new Location("New York", Country.USA, null, 0))
        );
    }

    @Test
    public void byIpWithEmptyIpTest() {
        //arrange
        String ip = "";
        //act
        Location location = geoService.byIp(ip);
        //assert
        Assertions.assertNull(location);
    }

    @Test
    public void byIpWithNullTest() {
        //arrange
        String ip = null;
        Class<NullPointerException> expected = NullPointerException.class;
        //assert
        Assertions.assertThrows(expected,
                //act
                () -> geoService.byIp(ip));
    }
}
