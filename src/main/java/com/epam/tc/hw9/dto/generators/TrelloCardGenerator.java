package com.epam.tc.hw9.dto.generators;

import com.epam.tc.hw9.dto.TrelloCardDto;

public class TrelloCardGenerator {
    public static TrelloCardDto getNewTrelloCard(String name) {
        return TrelloCardDto
            .builder()
            .name(name)
            .build();
    }
}
