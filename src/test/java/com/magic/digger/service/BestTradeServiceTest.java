package com.magic.digger.service;

import java.util.regex.Matcher;
import java.util.regex.Pattern;

import org.junit.Test;
import org.mockito.InjectMocks;

public class BestTradeServiceTest {

    @InjectMocks
    private BestTradeService bestTradeService;

    @Test
    public void should() throws Exception {
        Pattern pattern = Pattern.compile("'(.*?)'");
        Matcher matcher = pattern.matcher("showMsgBox(this,'Français')");
        if (matcher.find()) {
            System.out.println(matcher.group(1));
        }
/*
        bestTradeService.getBestDeals();
*/
    }
}
