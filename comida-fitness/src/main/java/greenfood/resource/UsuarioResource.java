package greenfood.resource;


import greenfood.DTO.usuario.AdicionarUsuarioDTO;
import greenfood.DTO.usuario.AtualizarUsuarioDTO;
import greenfood.DTO.usuario.UsuarioDTO;
import greenfood.DTO.usuario.UsuarioMapper;
import greenfood.entity.Usuario;
import greenfood.exception.ConstraintViolationResponse;
import org.eclipse.microprofile.openapi.annotations.media.Content;
import org.eclipse.microprofile.openapi.annotations.media.Schema;
import org.eclipse.microprofile.openapi.annotations.responses.APIResponse;
import org.eclipse.microprofile.openapi.annotations.tags.Tag;

import javax.inject.Inject;
import javax.transaction.Transactional;
import javax.validation.Valid;
import javax.ws.rs.*;
import javax.ws.rs.core.MediaType;
import javax.ws.rs.core.Response;
import java.util.List;
import java.util.Optional;
import java.util.stream.Collectors;
import java.util.stream.Stream;

@Path("/usuarios")
@Produces(MediaType.APPLICATION_JSON)
@Consumes(MediaType.APPLICATION_JSON)
public class UsuarioResource {

    @Inject
    UsuarioMapper usuarioMapper;

    @POST
    @Transactional
    @APIResponse(responseCode = "201", description = " Caso o usuario seja cadastrado com sucesso")
    @APIResponse(responseCode = "400", content = @Content(schema = @Schema(allOf = ConstraintViolationResponse.class)))
    @Tag(name = "Cadastro-Usuario")
    public Response adicionarUsuario (@Valid AdicionarUsuarioDTO dto) {
        Usuario usuario = usuarioMapper.toUsuario(dto);
        usuario.persist();
        return Response.status(Response.Status.CREATED).build();
    }

    @GET
    @Tag(name = "Cadastro-Usuario")
    public List<UsuarioDTO> buscarUsuario() {
        Stream<Usuario> usuarios = Usuario.streamAll();
        return usuarios.map(r ->
                usuarioMapper.toUsuarioDTO(r)).collect(Collectors.toList());
    }

    @PUT
    @Path("{id}")
    @Transactional
    @Tag(name="Cadastro-Usuario")
    public void atualizar (@PathParam("id") Long id, AtualizarUsuarioDTO dto) {
        Optional<Usuario> usuarioOp = Usuario.findByIdOptional(id);
        if(usuarioOp.isEmpty()) {
            throw new NotFoundException();
        }
        Usuario usuario = usuarioOp.get();
        usuarioMapper.toUsuario(dto, usuario);
        usuario.persist();
    }

    @DELETE
    @Path("{id}")
    @Transactional
    @Tag(name="Cadastro-Usuario")
    public void delete (@PathParam("id") Long id) {
        Optional<Usuario> usuarioOp = Usuario.findByIdOptional(id);
        //validar se tem prato cadastrado e avisar que precisa apagar os pratos antes do restaurante
        usuarioOp.ifPresentOrElse(Usuario::delete, () ->{
            throw new NotFoundException();
        });
    }
}