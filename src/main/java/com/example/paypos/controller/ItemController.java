package com.example.paypos.controller;


import com.example.paypos.dto.paginated.PageResponseDTO;
import com.example.paypos.dto.request.ItemSaveRequestDTO;
import com.example.paypos.dto.response.ItemGetResponseDTO;
import com.example.paypos.service.ItemService;
import com.example.paypos.util.StandardResponse;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;


import java.util.List;

@RestController
@RequestMapping("api/v1/item")
@CrossOrigin
public class ItemController {

    @Autowired
    private ItemService itemService;

//    @PostMapping(
//            path = "/save" )
//    public String saveItem(@RequestBody ItemSaveRequestDTO itemSaveRequest){
//        String message = itemService.saveItem(itemSaveRequest);
//        return message;
//
//    }

    @PostMapping(
            path = "/save" )

    public ResponseEntity<StandardResponse> saveItem(@RequestBody ItemSaveRequestDTO itemSaveRequestDTO){
        String message = itemService.saveItem(itemSaveRequestDTO);
//        ResponseEntity<StandardResponse> response = new ResponseEntity<StandardResponse> (
//                new StandardResponse(201, "Success", message ), HttpStatus.CREATED
//        );

        return new ResponseEntity<StandardResponse> (
               new StandardResponse(201, "Success", message ),
                HttpStatus.CREATED
      );

    }


    @GetMapping(
            path = "/get-by-name",
            params = "name"
    )
    public List<ItemGetResponseDTO> getItemByNameAndStatus(@RequestParam(value = "name") String itemName){
        List<ItemGetResponseDTO> itemDTOS = itemService.getItemByNameAndStatus(itemName);
        return itemDTOS;
    }

    @GetMapping(
            path = "/get-by-name-with-mapstruct",
            params = "name"
    )
    public List<ItemGetResponseDTO> getItemByNameAndStatusByMapstruct(@RequestParam(value = "name") String itemName){
        List<ItemGetResponseDTO> itemDTOS = itemService.getItemByNameAndStatusByMapstruct(itemName);
        return itemDTOS;
    }

    @GetMapping(
            path = "/get-all-item-by-status",
            params = {"activeStatus", "page", "size"}
    )
    public ResponseEntity<StandardResponse> getItesByActiveStatus(
            @RequestParam(value = "activeStatus") boolean activeStatus,
            @RequestParam(value = "page") int page,
            @RequestParam(value = "size") int size

    ){
//        List<ItemGetResponseDTO> itemDTOS = itemService.getItesByActiveStatus(activeStatus, page, size);

        PageResponseDTO pageResponseDTO = itemService.getItesByActiveStatusPaginated(activeStatus, page, size);
        return new ResponseEntity<StandardResponse> (
                new StandardResponse(201, "Success", pageResponseDTO ),
                HttpStatus.OK
        );
    }



}
