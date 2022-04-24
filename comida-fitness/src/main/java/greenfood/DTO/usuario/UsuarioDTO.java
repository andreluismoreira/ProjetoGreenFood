package greenfood.DTO.usuario;

import greenfood.DTO.CartaoDTO;
import greenfood.DTO.EnderecoDTO;

public class UsuarioDTO {

    public Long id;

    public String nome;

    public String cpf;

    public String email;

    public String telefone;

    public EnderecoDTO endereco;

    public CartaoDTO cartao;

    public String dataCriacao;
}
