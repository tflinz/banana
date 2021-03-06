package com.tutelatechnologies.fun.bananafinder;

import java.util.Arrays;
import java.util.List;
import java.util.Random;

/**
 * Created by Tony on 29/06/2015.
 */
public class BananaFinderConcrete implements BananaFinder {

    /**
     * Default banana to use.
     */
    static final String DEFAULT_BANANA = "http://img3.wikia.nocookie.net/__cb20071019155930/uncyclopedia/images/7/7b/Dancing_banana.gif";

    /**
     * A list of available bananas.
     */
    private final List<String> bananas;
    /**
     * For randomized banana selection.
     */
    private final Random r;

    /**
     * Initializes list of bananas.
     */
    public BananaFinderConcrete() {
        bananas = Arrays.asList(DEFAULT_BANANA);
        r = new Random();
    }

    @Override
    public String getABanana() {
        return bananas.get(r.nextInt(bananas.size()));
    }
}
