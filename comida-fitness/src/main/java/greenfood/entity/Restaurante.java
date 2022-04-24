package greenfood.entity;

import com.fasterxml.jackson.annotation.JsonFormat;
import io.quarkus.hibernate.orm.panache.PanacheEntityBase;
import lombok.Getter;
import lombok.Setter;
import org.hibernate.annotations.CreationTimestamp;
import org.hibernate.annotations.UpdateTimestamp;

import javax.persistence.*;
import java.util.Date;

@Entity
@Table(name="restaurante")
@Getter
@Setter
public class Restaurante extends PanacheEntityBase {

	@Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    public Long id;
	
	public String proprietario;
	
	public String cnpj;
	
	public String nome;
	
	@OneToOne(cascade = CascadeType.ALL)
	public Localizacao localizacao;

	@JsonFormat(pattern = "dd/MM/YYYY")
	@CreationTimestamp
	public Date dataDeCriacao;

	@JsonFormat(pattern = "dd/MM/YYYY")
	@UpdateTimestamp
	public Date dataDeAtualizacao;
	
}
