package org.almiso.daggerdemo.util;


import java.util.Random;

public class MockRandom extends Random {

    @Override
    public int nextInt() {
        return 47;
    }
}
