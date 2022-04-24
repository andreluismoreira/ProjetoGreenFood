package greenfood.DTO.pedido;

import greenfood.entity.Prato;
import greenfood.entity.Usuario;

public class PedidoDTO {

    public Long id;

    public Usuario usuario;

    public Prato prato;

    public Integer quantidade;
}
