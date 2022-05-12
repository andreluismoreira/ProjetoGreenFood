package greenfood.DTO.usuario;

import greenfood.DTO.CartaoDTO;
import greenfood.DTO.EnderecoDTO;
import greenfood.exception.DTO;
import greenfood.exception.ValidDTO;

import javax.validation.constraints.NotEmpty;
import javax.validation.constraints.Pattern;
import javax.validation.constraints.Size;

@ValidDTO
public class AdicionarUsuarioDTO implements DTO  {

    @NotEmpty
    @Size(min = 4,max = 50)
    public String nome;

    @NotEmpty
    @Pattern(regexp = "[0-9]{3}\\.[0-9]{3}\\.[0-9]{3}\\-[0-9]{2}", message = "O CPF esta fora do padrão")
    public String cpf;

    @NotEmpty
    @Size(min = 12,max = 25)
    public String email;

    @NotEmpty
    @Size(min = 9,max = 15)
    public String telefone;

    public EnderecoDTO endereco;

    public CartaoDTO cartao;

}
