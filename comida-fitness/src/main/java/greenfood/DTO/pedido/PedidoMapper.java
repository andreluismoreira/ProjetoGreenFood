package greenfood.DTO.pedido;

import greenfood.DTO.prato.AdicionarPratoDTO;
import greenfood.DTO.prato.AtualizarPratoDTO;
import greenfood.DTO.prato.PratoDTO;
import greenfood.DTO.restaurante.RestauranteDTO;
import greenfood.entity.Pedido;
import greenfood.entity.Prato;
import greenfood.entity.Restaurante;
import org.mapstruct.Mapper;
import org.mapstruct.Mapping;
import org.mapstruct.MappingTarget;

@Mapper(componentModel = "cdi")
public interface PedidoMapper {

    PedidoDTO toPedidoDTO(Pedido p);

    }
