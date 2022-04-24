package greenfood.resource;

import greenfood.DTO.pedido.AdicionarPedidoDTO;
import greenfood.DTO.pedido.PedidoDTO;
import greenfood.DTO.pedido.PedidoMapper;
import greenfood.entity.Pedido;
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

@Path("/pedidos")
@Tag(name="Pedidos")
@Produces(MediaType.APPLICATION_JSON)
@Consumes(MediaType.APPLICATION_JSON)
public class PedidoResouce {

    @Inject
    PedidoMapper pedidoMapper;

    @POST
    @Transactional
    @APIResponse(responseCode = "201", description = " Caso o pedido seja cadastrado com sucesso")
    @APIResponse(responseCode = "400",content = @Content(schema = @Schema(allOf= ConstraintViolationResponse.class)))
    public Response adicionar(@Valid AdicionarPedidoDTO dto) {
        Pedido pedido = pedidoMapper.toPedido(dto);
        pedido.persist();
        return Response.status(Response.Status.CREATED).build();
    }

    @GET
    public List<PedidoDTO> buscar() {
        Stream<Pedido> pedidos = Pedido.streamAll();
        return pedidos.map(p ->
                pedidoMapper.toPedidoDTO(p)).collect(Collectors.toList());
    }

    @DELETE
    @Path("{id}")
    @Transactional
    public void deletar (@PathParam("id") Long id) {
        Optional<Pedido> pedidoOp = Pedido.findByIdOptional(id);
        pedidoOp.ifPresentOrElse(Pedido::delete, () ->{
            throw new NotFoundException();
        });
    }

}
