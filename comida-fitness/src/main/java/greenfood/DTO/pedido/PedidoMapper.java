package greenfood.DTO.pedido;

import greenfood.DTO.restaurante.RestauranteDTO;
import greenfood.entity.Pedido;
import greenfood.entity.Restaurante;
import org.mapstruct.Mapper;

@Mapper(componentModel = "cdi")
public interface PedidoMapper {

    Pedido toPedido(AdicionarPedidoDTO dto);

    PedidoDTO toPedidoDTO(Pedido p);

}
