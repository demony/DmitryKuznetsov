package com.epam.tc.hw9.assertions;

import static org.assertj.core.api.Assertions.assertThat;

import com.epam.tc.hw9.dto.TrelloBoardDto;

public class TrelloBoardAssertions {

    private TrelloBoardDto board;

    public TrelloBoardAssertions(TrelloBoardDto board) {
        this.board = board;
    }

    public TrelloBoardAssertions verifyBoardName(String boardName) {
        assertThat(board.getName()).as("Check board name").isEqualTo(boardName);
        return this;
    }

    public TrelloBoardAssertions verifyBoardId(String boardId) {
        assertThat(board.getId()).as("Check board name").isEqualTo(boardId);
        return this;
    }
}
