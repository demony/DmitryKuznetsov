package com.epam.tc.hw9.services;

import com.epam.tc.hw9.dto.TrelloBoardDto;
import java.util.Arrays;
import java.util.Optional;

public class MembersService extends AbstractService {

    public MembersService(String baseUri, String basePath, String key, String token) {
        super(baseUri, basePath, key, token);
    }

    public TrelloBoardDto[] getAllBoards() {
        return getRequest("me/boards").as(TrelloBoardDto[].class);
    }

    public TrelloBoardDto getBoardByName(String boardName) {
        Optional<TrelloBoardDto> firstFindBoardOptional =
            Arrays.stream(getAllBoards()).filter(x -> boardName.equals(x.getName())).findFirst();
        TrelloBoardDto trelloBoardDto = null;
        if (firstFindBoardOptional.isPresent()) {
            trelloBoardDto  = firstFindBoardOptional.get();
        }
        return trelloBoardDto;
    }
}
