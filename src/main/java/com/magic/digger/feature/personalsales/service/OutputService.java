package com.magic.digger.feature.personalsales.service;

import java.io.BufferedWriter;
import java.io.FileOutputStream;
import java.io.IOException;
import java.io.OutputStreamWriter;
import java.io.Writer;
import java.util.Collections;
import java.util.List;

import org.springframework.stereotype.Service;

import com.magic.digger.feature.common.service.cardmarket.Card;

@Service
public class OutputService {
    public void printCsv(List<Card> cards, String user) throws IOException {
        Collections.sort(cards);

        Writer file = new BufferedWriter(new OutputStreamWriter(
                new FileOutputStream("c:/tmp/magic_" + user + ".csv"), "ISO-8859-1"));
        try {
            for( Card card : cards) {
                String outputString = card.getCardName() + ";" + card.getLanguage() + ";" + card.getQuantity();
                outputString += ";" + (float)card.getPrice()/100 + ";" + card.getComment();
                if (true) {
                    file.write(outputString + "\n");
                } else {
                    System.out.println(outputString);
                }
            }
        } finally {
            file.close();
        }
    }
}
