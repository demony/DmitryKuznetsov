package com.epam.tc.hw9.services;

import com.epam.tc.hw9.dto.TrelloCardDto;
import io.restassured.response.Response;
import java.util.HashMap;
import java.util.Map;
import org.apache.http.HttpStatus;

public class CardService extends AbstractService {

    public CardService(String baseUri, String basePath, String key, String token) {
        super(baseUri, basePath, key, token);
    }

    public TrelloCardDto prepareResult(Response response) {
        if (response.getStatusCode() == HttpStatus.SC_OK) {
            return response.as(TrelloCardDto.class);
        } else if (response.getStatusCode() == HttpStatus.SC_NOT_FOUND) {
            return null;
        } else {
            response.then().log().ifError();
            return null;
        }
    }

    public TrelloCardDto getCard(String idCard) {
        return getRequest(idCard).as(TrelloCardDto.class);
    }

    public TrelloCardDto createCard(TrelloCardDto trelloCardDto) {
        Map<String, Object> queryParams = new HashMap<>();
        queryParams.put("idList", trelloCardDto.getIdList());
        queryParams.put("name", trelloCardDto.getName());
        return postRequest("", queryParams).then().log().ifError().extract().as(TrelloCardDto.class);
    }

    public TrelloCardDto updateCard(TrelloCardDto trelloCardDto) {
        Map<String, Object> queryParams = new HashMap<>();
        queryParams.put("name", trelloCardDto.getName());
        return putRequest(trelloCardDto.getId(), queryParams).as(TrelloCardDto.class);
    }

    public boolean delete(TrelloCardDto trelloCardDto) {
        return this.delete(trelloCardDto.getId());
    }

    public boolean delete(String idCard) {
        return deleteRequest(idCard).path("_value") == null;
    }
}
