package com.example.paypos.service;

import com.example.paypos.dto.paginated.PageResponseDTO;
import com.example.paypos.dto.request.ItemSaveRequestDTO;
import com.example.paypos.dto.response.ItemGetResponseDTO;

import java.util.List;

public interface ItemService {
    String saveItem(ItemSaveRequestDTO itemSaveRequest);

    List<ItemGetResponseDTO> getItemByNameAndStatus(String itemName);

    List<ItemGetResponseDTO> getItemByNameAndStatusByMapstruct(String itemName);

    List<ItemGetResponseDTO> getItesByActiveStatus(boolean activeStatus);

    PageResponseDTO getItesByActiveStatusPaginated(boolean activeStatus, int page, int size);
}
