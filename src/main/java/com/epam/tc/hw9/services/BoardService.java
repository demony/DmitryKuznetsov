package com.epam.tc.hw9.services;

import com.epam.tc.hw9.dto.TrelloBoardDto;
import io.restassured.response.Response;
import java.util.HashMap;
import java.util.Map;
import org.apache.http.HttpStatus;

public class BoardService extends AbstractService {

    public HashMap<String, Boolean> boardsId = new HashMap<>();

    public BoardService(String baseUri, String basePath, String key, String token) {
        super(baseUri, basePath, key, token);
    }

    public TrelloBoardDto prepareResult(Response response) {
        if (response.getStatusCode() == HttpStatus.SC_OK) {
            return response.as(TrelloBoardDto.class);
        } else if (response.getStatusCode() == HttpStatus.SC_NOT_FOUND) {
            return null;
        } else {
            response.then().log().ifError();
            return null;
        }
    }

    public TrelloBoardDto create(TrelloBoardDto trelloBoardDto) {
        Map<String, Object> queryParams = new HashMap<>();
        queryParams.put("name", trelloBoardDto.getName());
        TrelloBoardDto newBoard = postRequest("", queryParams).as(TrelloBoardDto.class);
        boardsId.put(newBoard.getId(), true);
        return newBoard;
    }

    public TrelloBoardDto update(TrelloBoardDto trelloBoardDto) {
        Map<String, Object> queryParams = new HashMap<>();
        queryParams.put("name", trelloBoardDto.getName());
        return putRequest(trelloBoardDto.getId(), queryParams).as(TrelloBoardDto.class);
    }

    public boolean delete(TrelloBoardDto trelloBoardDto) {
        return this.delete(trelloBoardDto.getId());
    }

    public boolean delete(String idBoard) {
        Response response = deleteRequest(idBoard);
        // somehow you don't have permissions for this board
        if (response.getStatusCode() == HttpStatus.SC_UNAUTHORIZED) {
            return false;
        }
        boolean theBoardIsDeleted = response.path("_value") == null;
        if (theBoardIsDeleted) {
            boardsId.put(idBoard, false);
        }
        return theBoardIsDeleted;
    }

    public TrelloBoardDto getById(String idBoard) {
        return prepareResult(getRequest(idBoard));
    }

}
