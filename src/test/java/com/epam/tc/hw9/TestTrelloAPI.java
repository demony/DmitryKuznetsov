package com.epam.tc.hw9;

import static org.assertj.core.api.AssertionsForInterfaceTypes.assertThat;

import com.epam.tc.hw9.assertions.TrelloBoardAssertions;
import com.epam.tc.hw9.assertions.TrelloCardAssertions;
import com.epam.tc.hw9.assertions.TrelloListAssertions;
import com.epam.tc.hw9.dto.TrelloBoardDto;
import com.epam.tc.hw9.dto.TrelloCardDto;
import com.epam.tc.hw9.dto.TrelloListDto;
import com.epam.tc.hw9.utils.TrelloBoardGenerator;
import com.epam.tc.hw9.utils.TrelloCardGenerator;
import com.epam.tc.hw9.utils.TrelloListGenerator;
import org.testng.annotations.AfterSuite;
import org.testng.annotations.BeforeClass;
import org.testng.annotations.Test;

public class TestTrelloAPI {

    private static TrelloAPI trelloAPI;
    private static final String NEW_BOARD_NAME = "Another board name";
    private static final String NEW_LIST_NAME = "Another board name";

    @BeforeClass
    public void setup() {
        trelloAPI = TrelloAPI.initTrelloAPI();
        trelloAPI.removeAllBoards();
    }

    @AfterSuite
    public void teardown() {
        System.out.println("Boards left " + trelloAPI.boardService.boardsId.toString());
        trelloAPI.boardService.boardsId.forEach((idBoard, exists) -> {
            if (exists) {
                trelloAPI.boardService.delete(idBoard);
                System.out.println("Delete board with id = " + idBoard);
            }
        });
    }

    @Test(description = "Check board creation")
    public void createBoardTest() {
        TrelloBoardDto boardTemplate = TrelloBoardGenerator.getNewTrelloBoard();
        TrelloBoardDto boardNew = trelloAPI.boardService.create(boardTemplate);
        TrelloBoardDto boardRead = trelloAPI.boardService.getById(boardNew.getId());
        new TrelloBoardAssertions(boardRead)
            .verifyBoardName(boardTemplate.getName())
            .verifyBoardId(boardNew.getId());
    }

    @Test(description = "Check board update")
    public void updateBoardTest() {
        TrelloBoardDto boardTemplate = TrelloBoardGenerator.getNewTrelloBoard();
        TrelloBoardDto boardNew = trelloAPI.boardService.create(boardTemplate);
        boardNew.setName(NEW_BOARD_NAME);
        boardNew = trelloAPI.boardService.update(boardNew);
        TrelloBoardDto boardRead = trelloAPI.boardService.getById(boardNew.getId());
        new TrelloBoardAssertions(boardRead)
            .verifyBoardName(NEW_BOARD_NAME);
    }

    @Test(description = "Check board delete")
    public void deleteBoardTest() {
        TrelloBoardDto boardExample = TrelloBoardGenerator.getNewTrelloBoard();
        TrelloBoardDto boardNew = trelloAPI.boardService.create(boardExample);
        trelloAPI.boardService.delete(boardNew);
        assertThat(trelloAPI.boardService.getById(boardNew.getId()))
                               .as("Board was deleted")
                               .isNull();
    }

    @Test(description = "Correct list create")
    public void createListTest() {
        TrelloBoardDto boardTemplate = TrelloBoardGenerator.getNewTrelloBoard();
        TrelloBoardDto boardNew = trelloAPI.boardService.create(boardTemplate);
        TrelloListDto listTemplate = TrelloListGenerator.getNewTrelloList();
        listTemplate.setIdBoard(boardNew.getId());
        TrelloListDto trelloListNew = trelloAPI.listService.createList(listTemplate);
        TrelloListDto trelloListRead = trelloAPI.listService.getListById(trelloListNew.getId());
        new TrelloListAssertions(trelloListRead)
            .verifyListName(listTemplate.getName())
            .verifyBoardId(boardNew.getId());
    }

    @Test(description = "Correct list update")
    public void updateListTest() {
        TrelloBoardDto boardTemplate = TrelloBoardGenerator.getNewTrelloBoard();
        TrelloBoardDto boardNew = trelloAPI.boardService.create(boardTemplate);
        TrelloListDto listTemplate = TrelloListGenerator.getNewTrelloList();
        listTemplate.setIdBoard(boardNew.getId());
        TrelloListDto trelloListNew = trelloAPI.listService.createList(listTemplate);
        trelloListNew.setName(NEW_LIST_NAME);
        trelloListNew = trelloAPI.listService.updateList(trelloListNew);
        TrelloListDto trelloListRead = trelloAPI.listService.getListById(trelloListNew.getId());
        new TrelloListAssertions(trelloListRead)
            .verifyListName(NEW_LIST_NAME)
            .verifyBoardId(boardNew.getId());
    }

    @Test(description = "Correct list deletion")
    public void createCardTest() {
        TrelloBoardDto boardTemplate = TrelloBoardGenerator.getNewTrelloBoard();
        TrelloBoardDto boardNew = trelloAPI.boardService.create(boardTemplate);
        TrelloListDto trelloListTemplate = TrelloListGenerator.getNewTrelloList();
        trelloListTemplate.setIdBoard(boardNew.getId());
        TrelloListDto trelloListNew = trelloAPI.listService.createList(trelloListTemplate);
        TrelloCardDto trelloCardTemplate = TrelloCardGenerator.getNewTrelloCard();
        trelloCardTemplate.setIdList(trelloListNew.getId());
        TrelloCardDto trelloCardNew = trelloAPI.cardService.createCard(trelloCardTemplate);
        new TrelloCardAssertions(trelloCardNew)
            .verifyCardName(trelloCardTemplate.getName())
            .verifyListId(trelloListNew.getId())
            .verifyBoardId(boardNew.getId());
    }
}
