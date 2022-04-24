package greenfood.entity;

import com.fasterxml.jackson.annotation.JsonIgnore;
import io.quarkus.hibernate.orm.panache.PanacheEntityBase;
import lombok.Getter;
import lombok.Setter;

import javax.persistence.*;
import java.io.Serializable;
import java.math.BigDecimal;
import java.util.ArrayList;
import java.util.List;

@Entity
@Table(name="prato")
@Getter
@Setter
public class Prato extends PanacheEntityBase implements Serializable {
	
	@Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    public Long id;
	
	public String nome;
	
	public String descricao;
	
	public BigDecimal preco;

	@ManyToOne
	public Restaurante restaurante;

}
