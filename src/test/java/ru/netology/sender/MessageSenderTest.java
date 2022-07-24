package ru.netology.sender;

import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.Test;
import org.mockito.ArgumentCaptor;
import org.mockito.Mockito;
import ru.netology.entity.Country;
import ru.netology.entity.Location;
import ru.netology.geo.GeoService;
import ru.netology.geo.GeoServiceImpl;
import ru.netology.i18n.LocalizationService;
import ru.netology.i18n.LocalizationServiceImpl;

import java.util.HashMap;
import java.util.Map;

class MessageSenderTest {

    @Test
    void messageSenderTest() {
        // подготавливаем данные:
        GeoService geoService = Mockito.mock(GeoServiceImpl.class);
        Mockito.when(geoService.byIp(GeoServiceImpl.MOSCOW_IP))
                .thenReturn(new Location("Moscow", Country.RUSSIA, "Lenina", 15));
        Mockito.when(geoService.byIp(GeoServiceImpl.NEW_YORK_IP))
                .thenReturn(new Location("New York", Country.USA, " 10th Avenue", 32));

        LocalizationService localizationService = Mockito.mock(LocalizationService.class);
        Mockito.when(localizationService.locale(Country.RUSSIA))
                .thenReturn("Добро пожаловать");
        Mockito.when(localizationService.locale(Country.USA))
                .thenReturn("Welcome");

        // вызываем целевой метод:
        MessageSender messageSender = new MessageSenderImpl(geoService, localizationService);
        Map<String, String> headers1 = new HashMap<String, String>();
        headers1.put(MessageSenderImpl.IP_ADDRESS_HEADER, "172.0.32.11");
        String preferences1 = messageSender.send(headers1);

        Map<String, String> headers2 = new HashMap<String, String>();
        headers2.put(MessageSenderImpl.IP_ADDRESS_HEADER, "96.44.183.149");
        String preferences2 = messageSender.send(headers2);

        Map<String, String> headers3 = new HashMap<String, String>();
        headers3.put(MessageSenderImpl.IP_ADDRESS_HEADER, "");
        String preferences3 = messageSender.send(headers3);


        // производим проверку (сравниваем ожидание и результат):
        Assertions.assertEquals("Добро пожаловать", preferences1);
        Assertions.assertEquals("Welcome", preferences2);
        Assertions.assertEquals("Welcome", preferences3);
        Mockito.verify(geoService, Mockito.times(1)).byIp(GeoServiceImpl.MOSCOW_IP);
    }
}