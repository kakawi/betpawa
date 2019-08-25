package com.hlebon.betpawa.mapper.api;

public class EnumMapper {

    public static <T extends Enum<T>> T map(final Enum sourceEnumValue, final Class<T> targetEnumType) {
        return Enum.valueOf(targetEnumType, sourceEnumValue.name());
    }
}
