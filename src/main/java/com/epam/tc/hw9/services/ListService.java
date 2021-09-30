package com.epam.tc.hw9.services;

import com.epam.tc.hw9.dto.TrelloListDto;
import java.util.HashMap;
import java.util.Map;

public class ListService extends AbstractService {

    public ListService(String baseUri, String basePath, String key, String token) {
        super(baseUri, basePath, key, token);
    }

    public TrelloListDto getListById(String idList) {
        return getRequest(idList).as(TrelloListDto.class);
    }

    public TrelloListDto createList(TrelloListDto trelloListDto) {
        Map<String, Object> queryParams = new HashMap<>();
        queryParams.put("idBoard", trelloListDto.getIdBoard());
        queryParams.put("name", trelloListDto.getName());
        return postRequest("", queryParams).as(TrelloListDto.class);
    }

    public TrelloListDto updateList(TrelloListDto trelloListDto) {
        Map<String, Object> queryParams = new HashMap<>();
        queryParams.put("name", trelloListDto.getName());
        return putRequest(trelloListDto.getId(), queryParams).as(TrelloListDto.class);
    }
}
