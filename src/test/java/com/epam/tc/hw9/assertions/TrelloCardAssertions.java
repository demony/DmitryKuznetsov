package com.epam.tc.hw9.assertions;

import static org.assertj.core.api.Assertions.assertThat;

import com.epam.tc.hw9.dto.TrelloCardDto;

public class TrelloCardAssertions {
    private TrelloCardDto trelloCard;

    public TrelloCardAssertions(TrelloCardDto trelloList) {
        this.trelloCard = trelloList;
    }

    public TrelloCardAssertions verifyCardName(String cardName) {
        assertThat(trelloCard.getName()).as("Check card name").isEqualTo(cardName);
        return this;
    }

    public TrelloCardAssertions verifyListId(String listId) {
        assertThat(trelloCard.getIdList()).as("Check list id").isEqualTo(listId);
        return this;
    }

    public TrelloCardAssertions verifyBoardId(String boardId) {
        assertThat(trelloCard.getIdBoard()).as("Check board id").isEqualTo(boardId);
        return this;
    }
}
