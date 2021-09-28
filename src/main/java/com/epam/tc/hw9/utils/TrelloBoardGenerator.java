package com.epam.tc.hw9.utils;

import com.epam.tc.hw9.dto.TrelloBoardDto;

public class TrelloBoardGenerator {
    public static TrelloBoardDto getNewTrelloBoard() {
        return TrelloBoardDto
            .builder()
            .name("board created by builder2")
            .build();
    }
}
