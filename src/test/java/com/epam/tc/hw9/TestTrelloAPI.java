package com.epam.tc.hw9;

import static org.assertj.core.api.AssertionsForInterfaceTypes.assertThat;

import com.epam.tc.hw9.assertions.TrelloBoardAssertions;
import com.epam.tc.hw9.assertions.TrelloCardAssertions;
import com.epam.tc.hw9.assertions.TrelloListAssertions;
import com.epam.tc.hw9.dto.TrelloBoardDto;
import com.epam.tc.hw9.dto.TrelloCardDto;
import com.epam.tc.hw9.dto.TrelloListDto;
import com.epam.tc.hw9.dto.generators.TrelloBoardGenerator;
import com.epam.tc.hw9.dto.generators.TrelloCardGenerator;
import com.epam.tc.hw9.dto.generators.TrelloListGenerator;
import org.testng.annotations.AfterSuite;
import org.testng.annotations.BeforeSuite;
import org.testng.annotations.BeforeTest;
import org.testng.annotations.Test;

public class TestTrelloAPI {

    private static TrelloAPI trelloAPI;
    private static final String DEFAULT_BOARD_NAME = "Board for tests";
    private static final String DEFAULT_CARD_NAME = "Card for tests";
    private static final String DEFAULT_LIST_NAME = "List for tests";
    private static final String NEW_BOARD_NAME = "Another board name";
    private static final String NEW_LIST_NAME = "Another board name";

    private TrelloBoardDto boardNew;

    @BeforeSuite
    public void setup() {
        trelloAPI = TrelloAPI.initTrelloAPI();
        trelloAPI.removeAllBoards();
    }

    @BeforeTest
    public void setupTest() {
        TrelloBoardDto boardTemplate = TrelloBoardGenerator.getNewTrelloBoard(DEFAULT_BOARD_NAME);
        boardNew = trelloAPI.boardService.create(boardTemplate);
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
        TrelloBoardDto boardRead = trelloAPI.boardService.getById(boardNew.getId());
        new TrelloBoardAssertions(boardRead)
            .verifyBoardName(DEFAULT_BOARD_NAME)
            .verifyBoardId(boardNew.getId());
    }

    @Test(description = "Check board update")
    public void updateBoardTest() {
        boardNew.setName(NEW_BOARD_NAME);
        boardNew = trelloAPI.boardService.update(boardNew);
        TrelloBoardDto boardRead = trelloAPI.boardService.getById(boardNew.getId());
        new TrelloBoardAssertions(boardRead)
            .verifyBoardName(NEW_BOARD_NAME);
    }

    @Test(description = "Check board delete")
    public void deleteBoardTest() {
        TrelloBoardDto boardTemplate = TrelloBoardGenerator.getNewTrelloBoard(DEFAULT_BOARD_NAME);
        TrelloBoardDto boardForDelete = trelloAPI.boardService.create(boardTemplate);
        trelloAPI.boardService.delete(boardForDelete);
        assertThat(trelloAPI.boardService.getById(boardForDelete.getId()))
                               .as("Board was deleted")
                               .isNull();
    }

    @Test(description = "Correct list create")
    public void createListTest() {
        TrelloListDto listTemplate = TrelloListGenerator.getNewTrelloList(DEFAULT_LIST_NAME);
        listTemplate.setIdBoard(boardNew.getId());
        TrelloListDto trelloListNew = trelloAPI.listService.createList(listTemplate);
        TrelloListDto trelloListRead = trelloAPI.listService.getListById(trelloListNew.getId());
        new TrelloListAssertions(trelloListRead)
            .verifyListName(listTemplate.getName())
            .verifyBoardId(boardNew.getId());
    }

    @Test(description = "Correct list update")
    public void updateListTest() {
        TrelloListDto listTemplate = TrelloListGenerator.getNewTrelloList(DEFAULT_LIST_NAME);
        listTemplate.setIdBoard(boardNew.getId());
        TrelloListDto trelloListNew = trelloAPI.listService.createList(listTemplate);
        trelloListNew.setName(NEW_LIST_NAME);
        trelloListNew = trelloAPI.listService.updateList(trelloListNew);
        TrelloListDto trelloListRead = trelloAPI.listService.getListById(trelloListNew.getId());
        new TrelloListAssertions(trelloListRead)
            .verifyListName(NEW_LIST_NAME)
            .verifyBoardId(boardNew.getId());
    }

    @Test(description = "Correct card creation")
    public void createCardTest() {
        TrelloListDto trelloListTemplate = TrelloListGenerator.getNewTrelloList(DEFAULT_LIST_NAME);
        trelloListTemplate.setIdBoard(boardNew.getId());
        TrelloListDto trelloListNew = trelloAPI.listService.createList(trelloListTemplate);
        TrelloCardDto trelloCardTemplate = TrelloCardGenerator.getNewTrelloCard(DEFAULT_CARD_NAME);
        trelloCardTemplate.setIdList(trelloListNew.getId());
        TrelloCardDto trelloCardNew = trelloAPI.cardService.createCard(trelloCardTemplate);
        new TrelloCardAssertions(trelloCardNew)
            .verifyCardName(trelloCardTemplate.getName())
            .verifyListId(trelloListNew.getId())
            .verifyBoardId(boardNew.getId());
    }
}
