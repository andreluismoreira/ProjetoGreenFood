package greenfood.DTO.prato;

import greenfood.entity.Pedido;
import greenfood.entity.Restaurante;

import java.math.BigDecimal;

public class PratoDTO {

    public long id;

    public String nome;

    public String descricao;

    public Restaurante restaurante;

    public BigDecimal preco;
}
