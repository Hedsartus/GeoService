package ru.netology.i18n;

import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import ru.netology.entity.Country;
import ru.netology.geo.GeoServiceImpl;

import static org.junit.jupiter.api.Assertions.*;

class LocalizationServiceImplTest {
    private LocalizationService localizationService;

    @BeforeEach
    public void initTest() {
        this.localizationService = new LocalizationServiceImpl();
    }

    @Test
    void locale() {
        Assertions.assertEquals("Добро пожаловать", this.localizationService.locale(Country.RUSSIA));
        Assertions.assertEquals("Welcome", this.localizationService.locale(Country.USA));
        Assertions.assertEquals("Welcome", this.localizationService.locale(Country.BRAZIL));
    }
}