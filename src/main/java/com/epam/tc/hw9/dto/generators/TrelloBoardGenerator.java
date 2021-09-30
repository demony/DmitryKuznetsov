package com.epam.tc.hw9.dto.generators;

import com.epam.tc.hw9.dto.TrelloBoardDto;

public class TrelloBoardGenerator {
    public static TrelloBoardDto getNewTrelloBoard(String name) {
        return TrelloBoardDto
            .builder()
            .name(name)
            .build();
    }
}
