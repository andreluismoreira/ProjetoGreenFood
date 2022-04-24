package greenfood.entity;

import com.fasterxml.jackson.annotation.JsonFormat;
import com.fasterxml.jackson.annotation.JsonIgnore;
import io.quarkus.hibernate.orm.panache.PanacheEntityBase;
import lombok.*;
import org.hibernate.annotations.CreationTimestamp;

import javax.persistence.*;
import java.sql.Timestamp;
import java.util.ArrayList;
import java.util.List;

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
