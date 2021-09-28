package com.epam.tc.hw9.utils;

import com.epam.tc.hw9.dto.TrelloCardDto;

public class TrelloCardGenerator {
    public static TrelloCardDto getNewTrelloCard() {
        return TrelloCardDto
            .builder()
            .name("Card 2")
            .build();
    }
}
