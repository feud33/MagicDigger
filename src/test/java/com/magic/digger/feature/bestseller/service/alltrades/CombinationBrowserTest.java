package com.magic.digger.feature.bestseller.service.alltrades;

import static org.assertj.core.api.AssertionsForClassTypes.assertThat;

import java.util.List;

import org.junit.Before;
import org.junit.Test;

public class CombinationBrowserTest {

    private CombinationBrowser combinationBrowser;

    @Before
    public void setup() {
        combinationBrowser = new CombinationBrowser(3, 2);
    }

    @Test
    public void shouldGet000() {
        List<Integer> currentCombination = combinationBrowser.getCurrentCombination();
        assertThat(currentCombination.get(0)).isEqualTo(0);
        assertThat(currentCombination.get(1)).isEqualTo(0);
        assertThat(currentCombination.get(2)).isEqualTo(0);
    }

    @Test
    public void shouldGetPosAfter100() throws Exception {
        combinationBrowser.next();

        List<Integer> currentCombination = combinationBrowser.getCurrentCombination();
        assertThat(currentCombination.get(0)).isEqualTo(1);
        assertThat(currentCombination.get(1)).isEqualTo(0);
        assertThat(currentCombination.get(2)).isEqualTo(0);
    }

    @Test
    public void shouldGetPosAfter010() throws Exception {
        combinationBrowser.next();
        combinationBrowser.next();

        List<Integer> currentCombination = combinationBrowser.getCurrentCombination();
        assertThat(currentCombination.get(0)).isEqualTo(0);
        assertThat(currentCombination.get(1)).isEqualTo(1);
        assertThat(currentCombination.get(2)).isEqualTo(0);
    }

    @Test
    public void shouldGetPosAfter110() throws Exception {
        System.out.println(combinationBrowser.toString());
        combinationBrowser.next();
        combinationBrowser.next();
        combinationBrowser.next();

        List<Integer> currentCombination = combinationBrowser.getCurrentCombination();
        assertThat(currentCombination.get(0)).isEqualTo(1);
        assertThat(currentCombination.get(1)).isEqualTo(1);
        assertThat(currentCombination.get(2)).isEqualTo(0);
    }

    @Test
    public void shouldGetPosAfter001() throws Exception {
        combinationBrowser.next();
        combinationBrowser.next();
        combinationBrowser.next();
        combinationBrowser.next();

        List<Integer> currentCombination = combinationBrowser.getCurrentCombination();
        assertThat(currentCombination.get(0)).isEqualTo(0);
        assertThat(currentCombination.get(1)).isEqualTo(0);
        assertThat(currentCombination.get(2)).isEqualTo(1);
    }

    @Test
    public void shouldGetPosAfter111() throws Exception {
        combinationBrowser.next();
        combinationBrowser.next();
        combinationBrowser.next();
        combinationBrowser.next();
        combinationBrowser.next();
        combinationBrowser.next();
        combinationBrowser.next();
        boolean next = combinationBrowser.next();

        assertThat(next).isFalse();
    }
}
