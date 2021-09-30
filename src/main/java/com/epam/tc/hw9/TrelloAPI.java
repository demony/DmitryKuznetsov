package com.epam.tc.hw9;

import com.epam.tc.hw9.dto.TrelloBoardDto;
import com.epam.tc.hw9.services.BoardService;
import com.epam.tc.hw9.services.CardService;
import com.epam.tc.hw9.services.ListService;
import com.epam.tc.hw9.services.MembersService;
import com.epam.tc.hw9.utils.PropertiesReader;
import java.util.ArrayList;
import java.util.List;

public class TrelloAPI {
    public BoardService boardService;
    public ListService listService;
    public CardService cardService;
    public MembersService membersService;

    public static String BASE_URI = "";
    public static String TRELLO_KEY = "";
    public static String TRELLO_TOKEN = "";
    public static String TRELLO_BASE_PATH_BOARDS = "boards/";
    public static String TRELLO_BASE_PATH_LISTS = "lists/";
    public static String TRELLO_BASE_PATH_CARDS = "cards/";
    public static String TRELLO_BASE_PATH_MEMBERS = "members/";

    private TrelloAPI() {
        boardService = new BoardService(BASE_URI, TRELLO_BASE_PATH_BOARDS, TRELLO_KEY, TRELLO_TOKEN);
        listService = new ListService(BASE_URI, TRELLO_BASE_PATH_LISTS, TRELLO_KEY, TRELLO_TOKEN);
        cardService = new CardService(BASE_URI, TRELLO_BASE_PATH_CARDS, TRELLO_KEY, TRELLO_TOKEN);
        membersService = new MembersService(BASE_URI, TRELLO_BASE_PATH_MEMBERS, TRELLO_KEY, TRELLO_TOKEN);
    }

    public static TrelloAPI initTrelloAPI() {
        PropertiesReader propertiesReader = new PropertiesReader();
        BASE_URI = propertiesReader.getTrelloBaseUri();
        TRELLO_KEY = propertiesReader.getTrelloKey();
        TRELLO_TOKEN = propertiesReader.getTrelloToken();
        return new TrelloAPI();
    }

    public List<String> removeAllBoards() {
        TrelloBoardDto[] allBoards = membersService.getAllBoards();
        List<String> removedBoards = new ArrayList<>();
        for (TrelloBoardDto board : allBoards) {
            if (boardService.delete(board)) {
                removedBoards.add(board.getId());
            }
        }
        return  removedBoards;
    }
}
