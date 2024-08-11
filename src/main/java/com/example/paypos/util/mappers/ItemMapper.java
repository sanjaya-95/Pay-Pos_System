package com.example.paypos.util.mappers;


import com.example.paypos.dto.response.ItemGetResponseDTO;
import com.example.paypos.entity.Item;
import org.mapstruct.Mapper;
import org.springframework.data.domain.Page;

import java.util.List;

@Mapper(componentModel = "spring" )
public interface ItemMapper  {
    //ItemList -----> ItemResponseDTO
    List<ItemGetResponseDTO> entityListToDtoList(List<Item> items);

    //page<Item> items---> List<ItemGetResponseDTO> list;

    List<ItemGetResponseDTO>ListDTOpage(Page<Item> items);
}


