package service.converter;

import Entity.SampleItem;
import org.springframework.stereotype.Component;
import service.dto.SampleItemDto;

@Component
public class SampleConverter {

    public SampleItemDto convert(SampleItem entity) {
        SampleItemDto dto = new SampleItemDto();
        dto.setItemName(entity.getItemName());
        return dto;
    }

}
