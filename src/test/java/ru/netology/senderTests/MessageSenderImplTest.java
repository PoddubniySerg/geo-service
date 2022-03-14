package ru.netology.senderTests;

import org.junit.jupiter.api.*;
import org.mockito.Mockito;
import ru.netology.entity.Country;
import ru.netology.entity.Location;
import ru.netology.geo.GeoService;
import ru.netology.i18n.LocalizationService;
import ru.netology.sender.MessageSenderImpl;

import java.util.HashMap;
import java.util.Map;

public class MessageSenderImplTest {

    @BeforeAll
    public static void startTests() {
        System.out.println("Start MessageSenderImplTest");
    }

    @AfterAll
    public static void completeTests() {
        System.out.println("Complete MessageSenderImplTest");
    }

    @BeforeEach
    public void startTest() {
        System.out.println("Start test");
    }

    @AfterEach
    public void completeTest() {
        System.out.println();
        System.out.println("Complete test");
    }

    @Test
    public void sendMethodTestForRussianLocation() {
        //arrange
        GeoService geoService = Mockito.mock(GeoService.class);
        Mockito.when(geoService.byIp(Mockito.any())).thenReturn(new Location(null, Country.RUSSIA, null, 0));

        LocalizationService localizationService = Mockito.mock(LocalizationService.class);
        Mockito.when(localizationService.locale(Country.RUSSIA)).thenReturn("Добро пожаловать");

        MessageSenderImpl messageSender = new MessageSenderImpl(geoService, localizationService);
        Map<String, String> headers = new HashMap<>();
        headers.put(MessageSenderImpl.IP_ADDRESS_HEADER, "anyIP");
        String expected = "Добро пожаловать";
        //act
        String message = messageSender.send(headers);
        //assert
        Assertions.assertEquals(message, expected);
    }

    @Test
    public void sendMethodTestForUSALocation() {
        //arrange
        GeoService geoService = Mockito.mock(GeoService.class);
        Mockito.when(geoService.byIp(Mockito.any())).thenReturn(new Location(null, Country.USA, null, 0));

        LocalizationService localizationService = Mockito.mock(LocalizationService.class);
        Mockito.when(localizationService.locale(Country.USA)).thenReturn("Welcome");

        MessageSenderImpl messageSender = new MessageSenderImpl(geoService, localizationService);
        Map<String, String> headers = new HashMap<>();
        headers.put(MessageSenderImpl.IP_ADDRESS_HEADER, "anyIP");
        String expected = "Welcome";
        //act
        String message = messageSender.send(headers);
        //assert
        Assertions.assertEquals(message, expected);
    }

    @Test
    public void sendMethodTestForIpIsEmpty() {
        //arrange
        GeoService geoService = Mockito.mock(GeoService.class);
        Mockito.when(geoService.byIp(Mockito.any())).thenReturn(new Location(null, Country.USA, null, 0));

        LocalizationService localizationService = Mockito.mock(LocalizationService.class);
        Mockito.when(localizationService.locale(Country.USA)).thenReturn("Welcome");

        MessageSenderImpl messageSender = new MessageSenderImpl(geoService, localizationService);
        Map<String, String> headers = new HashMap<>();
        headers.put(MessageSenderImpl.IP_ADDRESS_HEADER, "");
        String expected = "Welcome";
        //act
        String message = messageSender.send(headers);
        //assert
        Assertions.assertEquals(message, expected);
    }

    @Test
    public void sendMethodTestForIpIsNull() {
        //arrange
        GeoService geoService = Mockito.mock(GeoService.class);
        Mockito.when(geoService.byIp(Mockito.any())).thenReturn(new Location(null, Country.USA, null, 0));

        LocalizationService localizationService = Mockito.mock(LocalizationService.class);
        Mockito.when(localizationService.locale(Country.USA)).thenReturn("Welcome");

        MessageSenderImpl messageSender = new MessageSenderImpl(geoService, localizationService);
        Map<String, String> headers = new HashMap<>();
        headers.put(MessageSenderImpl.IP_ADDRESS_HEADER, null);
        String expected = "Welcome";
        //act
        String message = messageSender.send(headers);
        //assert
        Assertions.assertEquals(message, expected);
    }

    @Test
    public void sendMethodTestForGermanyIp() {
        //arrange
        GeoService geoService = Mockito.mock(GeoService.class);
        Mockito.when(geoService.byIp(Mockito.any())).thenReturn(new Location(null, Country.GERMANY, null, 0));

        LocalizationService localizationService = Mockito.mock(LocalizationService.class);
        Mockito.when(localizationService.locale(Country.GERMANY)).thenReturn("Welcome");

        MessageSenderImpl messageSender = new MessageSenderImpl(geoService, localizationService);
        Map<String, String> headers = new HashMap<>();
        headers.put(MessageSenderImpl.IP_ADDRESS_HEADER, "anyIP");
        String expected = "Welcome";
        //act
        String message = messageSender.send(headers);
        //assert
        Assertions.assertEquals(message, expected);
    }

    @Test
    public void sendMethodTestForBrazilIp() {
        //arrange

        GeoService geoService = Mockito.mock(GeoService.class);
        Mockito.when(geoService.byIp(Mockito.any())).thenReturn(new Location(null, Country.BRAZIL, null, 0));

        LocalizationService localizationService = Mockito.mock(LocalizationService.class);
        Mockito.when(localizationService.locale(Country.BRAZIL)).thenReturn("Welcome");

        MessageSenderImpl messageSender = new MessageSenderImpl(geoService, localizationService);
        Map<String, String> headers = new HashMap<>();
        headers.put(MessageSenderImpl.IP_ADDRESS_HEADER, "anyIP");
        String expected = "Welcome";
        //act
        String message = messageSender.send(headers);
        //assert
        Assertions.assertEquals(message, expected);
    }
}