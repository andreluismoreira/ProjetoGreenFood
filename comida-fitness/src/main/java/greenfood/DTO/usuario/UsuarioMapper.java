package greenfood.DTO.usuario;

import greenfood.entity.Usuario;
import org.mapstruct.Mapper;
import org.mapstruct.Mapping;
import org.mapstruct.MappingTarget;

@Mapper(componentModel = "cdi")
public interface UsuarioMapper {

    Usuario toUsuario(AdicionarUsuarioDTO dto);

    void toUsuario(AtualizarUsuarioDTO dto, @MappingTarget Usuario usuario);

    @Mapping(target = "dataCriacao", dateFormat = "DD/mm/YYYY")
    UsuarioDTO toUsuarioDTO(Usuario u);

}
