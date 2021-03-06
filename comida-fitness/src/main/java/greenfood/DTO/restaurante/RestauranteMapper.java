package greenfood.DTO.restaurante;

import greenfood.entity.Restaurante;
import org.mapstruct.Mapper;
import org.mapstruct.Mapping;
import org.mapstruct.MappingTarget;

@Mapper(componentModel = "cdi")
public interface RestauranteMapper {

    @Mapping(target="nome", source ="nomeFantasia")
    Restaurante toRestaurante(AdicionarRestauranteDTO dto);

    @Mapping(target="nome", source ="nomeFantasia")
    void toRestaurante(AtualizarRestauranteDTO dto, @MappingTarget Restaurante restaurante);

    @Mapping(target = "dataDeCriacao", dateFormat = "DD/mm/YYYY")
    RestauranteDTO toRestauranteDTO(Restaurante r);
}
