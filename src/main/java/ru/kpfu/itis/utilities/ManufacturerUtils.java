package ru.kpfu.itis.utilities;

import ru.kpfu.itis.dto.Manufacturer;

public class ManufacturerUtils {

    // FIXME: use optional
    public static Manufacturer getByCountry(String country) {

        for (Manufacturer manufacturer : Manufacturer.values()) {
            if (manufacturer.getCountry().equals(country)) return manufacturer;
        }

        return null;
    }
}
