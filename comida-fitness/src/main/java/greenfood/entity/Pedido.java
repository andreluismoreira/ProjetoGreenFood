package greenfood.entity;

import io.quarkus.hibernate.orm.panache.PanacheEntityBase;
import lombok.Getter;
import lombok.Setter;

import javax.persistence.*;

@Entity
@Table(name="pedido")
@Getter
@Setter
public class Pedido extends PanacheEntityBase {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    public Long id;

    @ManyToOne()
    @JoinColumn(name = "usuario_id")
    public Usuario usuario;

    @ManyToOne()
    @JoinColumn(name = "prato_id")
    public Prato prato;

    public Integer quantidade;

}
