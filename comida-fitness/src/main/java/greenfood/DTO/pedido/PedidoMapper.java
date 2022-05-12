package greenfood.DTO.pedido;

import greenfood.entity.Pedido;
import org.mapstruct.Mapper;

@Mapper(componentModel = "cdi")
public interface PedidoMapper {

    Pedido toPedido(AdicionarPedidoDTO dto);

    PedidoDTO toPedidoDTO(Pedido p);
}
