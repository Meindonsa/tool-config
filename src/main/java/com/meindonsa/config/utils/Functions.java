package com.meindonsa.config.utils;

import com.meindonsa.config.exception.FunctionalException;

import java.net.URI;
import java.net.URISyntaxException;
import java.util.HashSet;
import java.util.List;
import java.util.Random;
import java.util.Set;
import java.util.UUID;

public class Functions {

    private Functions() {}

    public static String generateUniqueIdentifier(int size) {
        return UUID.randomUUID().toString().substring(0, size).replace("-", "").toLowerCase();
    }

    public static String geneUniqueNumberIdentifier(int size) {
        double min = Math.pow(10, size);
        double max = Math.pow(10, size + 1) - 1;
        int randomNumber = (int) (Math.random() * (max + 1 - min) + min);
        return Integer.toString(randomNumber);
    }

    public static URI createURI(final String uri) {
        try {
            return new URI(uri);
        } catch (URISyntaxException e) {
            throw new FunctionalException(e.toString());
        }
    }

    public static <T> List<T> getRemainingList(List<T> list, T element) {
        int index = list.indexOf(element);
        if (index == -1) {
            return null; // Element not found in the list
        }
        return list.subList(index, list.size());
    }

    public static String generateUpperCaseString(String name) {
        if (isInvalidString(name)) throw new FunctionalException(Messages.ERR_INVALID_DATA);

        return name.toUpperCase().trim().replace(" ", "_");
    }

    public static String generateLowerCaweString(String name) {
        if (isInvalidString(name)) throw new FunctionalException(Messages.ERR_INVALID_DATA);

        return name.toLowerCase().trim().replace(" ", "_");
    }

    public static String toCapitalize(String text) {
        return Character.toUpperCase(text.charAt(0)) + text.substring(1).toLowerCase();
    }

    public static boolean isInvalidString(String string) {
        return string == null || string.trim().isEmpty();
    }

    public static boolean isInvalidList(List<?> lists) {
        return lists == null || lists.isEmpty();
    }

    public static boolean isNullObject(Object obj) {
        return obj == null;
    }

    public static List<Integer> getRandomNumbers(int maxNumber, int numberOfElements) {
        Set<Integer> uniqueNumbers = new HashSet<>();
        Random random = new Random();

        while (uniqueNumbers.size() < numberOfElements) {
            int randomNumber = random.nextInt(maxNumber) + 1;
            uniqueNumbers.add(randomNumber);
        }
        return MapperUtils.mapAll(uniqueNumbers, int.class);
    }
}
