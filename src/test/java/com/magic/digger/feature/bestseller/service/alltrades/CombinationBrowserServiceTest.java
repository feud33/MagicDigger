package com.magic.digger.feature.bestseller.service.alltrades;

import static org.assertj.core.api.AssertionsForClassTypes.assertThat;

import java.util.List;

import org.junit.Before;
import org.junit.Test;

public class CombinationBrowserServiceTest {

    private CombinationBrowserService combinationBrowserService;

    @Before
    public void setup() {
        combinationBrowserService = new CombinationBrowserService(3, 2);
    }

    @Test
    public void shouldGet000() {
        List<Integer> currentCombination = combinationBrowserService.getCurrentCombination();
        assertThat(currentCombination.get(0)).isEqualTo(0);
        assertThat(currentCombination.get(1)).isEqualTo(0);
        assertThat(currentCombination.get(2)).isEqualTo(0);
    }

    @Test
    public void shouldGetPosAfter100() throws Exception {
        combinationBrowserService.next();

        List<Integer> currentCombination = combinationBrowserService.getCurrentCombination();
        assertThat(currentCombination.get(0)).isEqualTo(1);
        assertThat(currentCombination.get(1)).isEqualTo(0);
        assertThat(currentCombination.get(2)).isEqualTo(0);
    }

    @Test
    public void shouldGetPosAfter010() throws Exception {
        combinationBrowserService.next();
        combinationBrowserService.next();

        List<Integer> currentCombination = combinationBrowserService.getCurrentCombination();
        assertThat(currentCombination.get(0)).isEqualTo(0);
        assertThat(currentCombination.get(1)).isEqualTo(1);
        assertThat(currentCombination.get(2)).isEqualTo(0);
    }

    @Test
    public void shouldGetPosAfter110() throws Exception {
        System.out.println(combinationBrowserService.toString());
        combinationBrowserService.next();
        combinationBrowserService.next();
        combinationBrowserService.next();

        List<Integer> currentCombination = combinationBrowserService.getCurrentCombination();
        assertThat(currentCombination.get(0)).isEqualTo(1);
        assertThat(currentCombination.get(1)).isEqualTo(1);
        assertThat(currentCombination.get(2)).isEqualTo(0);
    }

    @Test
    public void shouldGetPosAfter001() throws Exception {
        combinationBrowserService.next();
        combinationBrowserService.next();
        combinationBrowserService.next();
        combinationBrowserService.next();

        List<Integer> currentCombination = combinationBrowserService.getCurrentCombination();
        assertThat(currentCombination.get(0)).isEqualTo(0);
        assertThat(currentCombination.get(1)).isEqualTo(0);
        assertThat(currentCombination.get(2)).isEqualTo(1);
    }

    @Test
    public void shouldGetPosAfter111() throws Exception {
        combinationBrowserService.next();
        combinationBrowserService.next();
        combinationBrowserService.next();
        combinationBrowserService.next();
        combinationBrowserService.next();
        combinationBrowserService.next();
        combinationBrowserService.next();
        boolean next = combinationBrowserService.next();

        assertThat(next).isFalse();
    }
}
