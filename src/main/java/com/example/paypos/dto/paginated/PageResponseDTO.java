package com.example.paypos.dto.paginated;


import com.example.paypos.dto.response.ItemGetResponseDTO;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.util.List;

@AllArgsConstructor
@NoArgsConstructor
@Data
public class PageResponseDTO {

    List<ItemGetResponseDTO> list;
    private long dataCount;
}
