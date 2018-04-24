package com.magic.digger.feature.bestseller.service.alltrades;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

public class CombinationBrowserService {
    private final int colNum;
    private final int rawNum;

    private Integer combinations[];

    public CombinationBrowserService(int colNum, int rawNum) {
        this.colNum = colNum;
        this.rawNum = rawNum;

        this.combinations = new Integer[colNum];
        setPreviousColToZero(colNum);
    }

    public boolean next() {
        int i = findFirstColToIncrease();
        if (i < colNum) {
            if (combinations[i] == 0) {
                setPreviousColToZero(i + 1);
            }
            combinations[i]++;
            return true;
        } else {
            return false;
        }
    }

    private void setPreviousColToZero(int maxCol) {
        int i = 0;
        while (i < maxCol) {
            combinations[i++] = 0;
        }
    }

    private int findFirstColToIncrease() {
        int i = 0;
        while (i < colNum && combinations[i] == (rawNum - 1)) {
            i++;
        }
        return i;
    }

    public List<Integer> getCurrentCombination() {
        return new ArrayList<Integer>(Arrays.asList(combinations));
    }

    @Override public String toString() {
        return "CombinationBrowser{" +
                "combinations=" + Arrays.toString(combinations) +
                '}';
    }
}
