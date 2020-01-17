package com.grey.sample.service.converter;

import com.grey.sample.entity.SampleItem;
import org.springframework.stereotype.Component;
import com.grey.sample.service.dto.SampleItemDto;

@Component
public class SampleConverter {

    public SampleItemDto convert(SampleItem entity) {
        SampleItemDto dto = new SampleItemDto();
        dto.setItemName(entity.getItemName());
        return dto;
    }

}
