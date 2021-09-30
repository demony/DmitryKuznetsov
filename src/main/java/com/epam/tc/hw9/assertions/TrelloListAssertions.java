package com.epam.tc.hw9.assertions;

import static org.assertj.core.api.Assertions.assertThat;

import com.epam.tc.hw9.dto.TrelloListDto;

public class TrelloListAssertions {
    private TrelloListDto trelloList;

    public TrelloListAssertions(TrelloListDto trelloList) {
        this.trelloList = trelloList;
    }

    public TrelloListAssertions verifyListName(String listName) {
        assertThat(trelloList.getName()).as("Check listName name").isEqualTo(listName);
        return this;
    }

    public TrelloListAssertions verifyListId(String listId) {
        assertThat(trelloList.getId()).as("Check list id").isEqualTo(listId);
        return this;
    }

    public TrelloListAssertions verifyBoardId(String boardId) {
        assertThat(trelloList.getIdBoard()).as("Check board id").isEqualTo(boardId);
        return this;
    }
}
