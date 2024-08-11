package com.example.paypos.service.impl;

import com.example.paypos.dto.paginated.PageResponseDTO;
import com.example.paypos.dto.request.ItemSaveRequestDTO;
import com.example.paypos.dto.response.ItemGetResponseDTO;
import com.example.paypos.entity.Item;
import com.example.paypos.repo.ItemRepo;
import com.example.paypos.service.ItemService;
import com.example.paypos.util.mappers.ItemMapper;
import org.modelmapper.ModelMapper;
import org.modelmapper.TypeToken;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.dao.DuplicateKeyException;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.stereotype.Service;

import java.io.NotActiveException;
import java.util.List;

@Service
public class ItemServiceIMPL implements ItemService {
    @Autowired
    private ItemRepo itemRepo;

    @Autowired
    private ModelMapper modelMapper;

    @Autowired
    private ItemMapper itemMapper;


    @Override
    public String saveItem(ItemSaveRequestDTO itemSaveRequestDTO) {

        Item item = modelMapper.map(itemSaveRequestDTO, Item.class);

        if (!itemRepo.existsById(item.getItemId())) {
            itemRepo.save(item);
            return item.getItemName() + " saved successful";

        } else {
            throw new DuplicateKeyException("Already added this id");
        }
    }

    @Override
    public List<ItemGetResponseDTO> getItemByNameAndStatus(String itemName) {
        boolean b = true;

        List<Item> items = itemRepo.findAllByItemNameEqualsAndActiveStatusEquals(itemName, b);

        if (items.size() > 0) {
            List<ItemGetResponseDTO> itemGetResponseDTOS = modelMapper.map(items, new TypeToken<List<ItemGetResponseDTO>>() {

            }.getType());
            return itemGetResponseDTOS;

        } else {
            throw new RuntimeException("Item is not Active");
        }
    }

    @Override
    public List<ItemGetResponseDTO> getItemByNameAndStatusByMapstruct(String itemName) {
        boolean b = true;

        List<Item> items = itemRepo.findAllByItemNameEqualsAndActiveStatusEquals(itemName, b);

        if (items.size() > 0) {

            List<ItemGetResponseDTO> itemGetResponseDTOS = itemMapper.entityListToDtoList(items);
            return itemGetResponseDTOS;

        } else {
            throw new RuntimeException("Item is not Active");
        }
    }

    @Override
    public List<ItemGetResponseDTO> getItesByActiveStatus(boolean activeStatus) {
        boolean b = true;

        List<Item> items = itemRepo.findAllByActiveStatusEquals(activeStatus);

        if (items.size() > 0) {

            List<ItemGetResponseDTO> itemGetResponseDTOS = itemMapper.entityListToDtoList(items);
            return itemGetResponseDTOS;

        } else {
            throw new RuntimeException("Item is not Active");
        }
    }

    @Override
    public PageResponseDTO getItesByActiveStatusPaginated(boolean activeStatus, int page, int size) {
        Page<Item> items = itemRepo.findAllByActiveStatusEquals(activeStatus, PageRequest.of(page, size));
        int count = itemRepo.countAllByActiveStatusEquals(activeStatus);
        if (items.getSize() < 1) {
            throw  new RuntimeException("No Data");
        } else {
            PageResponseDTO pageResponseDTO = new PageResponseDTO(
                    itemMapper.ListDTOpage(items),
                    count

            );

            return pageResponseDTO;
        }

    }
}
