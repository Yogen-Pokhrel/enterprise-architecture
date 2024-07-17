package com.eaproject.utils;

import com.eaproject.common.ProductCategory;
import com.eaproject.common.ProductTitle;

import java.util.Random;

public class Utils {
    private static final Random random = new Random();
    public static int getRandomNumber(int x, int y) {
        return random.nextInt((y - x) + 1) + x;
    }

    public static ProductCategory getRandomCategory() {
        ProductCategory[] categories = ProductCategory.values();
        return categories[random.nextInt(categories.length)];
    }

    public static ProductTitle getRandomProductTitle() {
        ProductTitle[] titles = ProductTitle.values();
        return titles[random.nextInt(titles.length)];
    }
}
