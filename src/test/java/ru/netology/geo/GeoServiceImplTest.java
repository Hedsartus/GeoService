package ru.netology.geo;

import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import ru.netology.entity.Country;

import static org.junit.jupiter.api.Assertions.*;

class GeoServiceImplTest {
    private GeoService geoService;

    @BeforeEach
    public void initTest() {
        System.out.println("Starting nest GeoService");
        this.geoService = new GeoServiceImpl();
    }

    @Test
    void byIpTest() {
        String fakeIp = "125.100.12.2";
        String trueIp = "172.0.32.11";
        String trueIp1 = "96.44.183.149";

        Assertions.assertNull(this.geoService.byIp(fakeIp));
        Assertions.assertEquals("Moscow", this.geoService.byIp(trueIp).getCity());
        Assertions.assertEquals(Country.USA, this.geoService.byIp(trueIp1).getCountry());
    }
}