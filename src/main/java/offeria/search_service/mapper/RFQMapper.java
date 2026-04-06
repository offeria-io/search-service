package offeria.search_service.mapper;

import offeria.search_service.domain.RFQ;
import offeria.search_service.dto.RFQDTO;
import org.mapstruct.Mapper;
import org.mapstruct.MappingConstants;

import java.util.List;

/**
 * MapStruct Mapper for RFQ Entity and DTO.
 */
@Mapper(componentModel = MappingConstants.ComponentModel.SPRING)
public interface RFQMapper {

    RFQDTO toDTO(RFQ rfq);

    RFQ toEntity(RFQDTO rfqDTO);

    List<RFQDTO> toDTOList(List<RFQ> rfqs);
}
