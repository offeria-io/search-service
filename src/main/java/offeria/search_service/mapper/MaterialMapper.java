package offeria.search_service.mapper;

import offeria.search_service.domain.Material;
import offeria.search_service.dto.MaterialDTO;
import org.mapstruct.Mapper;
import org.mapstruct.MappingConstants;

import java.util.List;

/**
 * MapStruct Mapper for Material Entity and DTO.
 */
@Mapper(componentModel = MappingConstants.ComponentModel.SPRING)
public interface MaterialMapper {

    MaterialDTO toDTO(Material material);

    Material toEntity(MaterialDTO materialDTO);

    List<MaterialDTO> toDTOList(List<Material> materials);
}
