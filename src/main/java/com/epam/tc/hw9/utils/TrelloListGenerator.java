package com.epam.tc.hw9.utils;

import com.epam.tc.hw9.dto.TrelloListDto;

public class TrelloListGenerator {
    public static TrelloListDto getNewTrelloList() {
        return TrelloListDto.builder().name("my super list 1").build();
    }
}
