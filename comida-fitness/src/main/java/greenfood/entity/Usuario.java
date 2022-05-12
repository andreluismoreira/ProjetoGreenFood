package greenfood.entity;

import com.fasterxml.jackson.annotation.JsonFormat;
import io.quarkus.hibernate.orm.panache.PanacheEntityBase;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;
import org.hibernate.annotations.CreationTimestamp;

import javax.persistence.*;
import java.sql.Timestamp;

@Getter
@Setter
@Entity
@AllArgsConstructor
@NoArgsConstructor
@Table(name="usuario")
public class Usuario extends PanacheEntityBase {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    public long id;

    @Column
    public String nome;

    @Column
    public String cpf;

    @Column
    public String email;

    @Column
    public String telefone;

    @OneToOne(cascade = CascadeType.ALL)
    public Endereco endereco;

    @OneToOne(cascade = CascadeType.ALL)
    public Cartao cartao;

    @JsonFormat(pattern = "dd/MM/YYYY")
    @CreationTimestamp
    public Timestamp dataCriacao;

}
