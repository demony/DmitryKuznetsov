package com.epam.tc.hw9.dto.generators;

import com.epam.tc.hw9.dto.TrelloListDto;

public class TrelloListGenerator {
    public static TrelloListDto getNewTrelloList(String name) {
        return TrelloListDto
            .builder()
            .name(name)
            .build();
    }
}
