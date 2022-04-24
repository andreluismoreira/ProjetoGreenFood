package greenfood.resource;

import greenfood.DTO.prato.AdicionarPratoDTO;
import greenfood.DTO.prato.AtualizarPratoDTO;
import greenfood.DTO.prato.PratoDTO;
import greenfood.DTO.prato.PratoMapper;
import greenfood.DTO.restaurante.AdicionarRestauranteDTO;
import greenfood.DTO.restaurante.AtualizarRestauranteDTO;
import greenfood.DTO.restaurante.RestauranteDTO;
import greenfood.DTO.restaurante.RestauranteMapper;
import greenfood.entity.Prato;
import greenfood.entity.Restaurante;
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
import javax.ws.rs.core.Response.Status;
import java.util.List;
import java.util.Optional;
import java.util.stream.Collectors;
import java.util.stream.Stream;

@Path("/restaurantes")
@Produces(MediaType.APPLICATION_JSON)
@Consumes(MediaType.APPLICATION_JSON)
public class RestauranteResource {

    @Inject
    RestauranteMapper restauranteMapper;

    @Inject
    PratoMapper pratoMapper;

    @POST
    @Transactional
    @APIResponse(responseCode = "201", description = " Caso o restaurante seja cadastrado com sucesso")
    @APIResponse(responseCode = "400",content = @Content(schema = @Schema(allOf= ConstraintViolationResponse.class)))
    @Tag(name="Cadastro-Restaurante")
    public Response adicionar(@Valid AdicionarRestauranteDTO dto) {
        Restaurante restaurante = restauranteMapper.toRestaurante(dto);
        restaurante.persist();
        return Response.status(Status.CREATED).build();
    }

    @GET
    @Tag(name="Marketplace")
    public List<RestauranteDTO> buscar() {
        Stream<Restaurante> restaurantes = Restaurante.streamAll();
        return restaurantes.map(r ->
                restauranteMapper.toRestauranteDTO(r)).collect(Collectors.toList());
    }

    @PUT
    @Path("{id}")
    @Transactional
    @Tag(name="Cadastro-Restaurante")
    public void atualizar (@PathParam("id") Long id, AtualizarRestauranteDTO dto) {
    	Optional<Restaurante> restauranteOp = Restaurante.findByIdOptional(id);
    	if(restauranteOp.isEmpty()) {
    		throw new NotFoundException();
    	}
    	Restaurante restaurante = restauranteOp.get();
    	restauranteMapper.toRestaurante(dto, restaurante);
    	restaurante.persist();
    }
    
    @DELETE
    @Path("{id}")
    @Transactional
    @Tag(name="Cadastro-Restaurante")
    public void deletar (@PathParam("id") Long id) {
    	Optional<Restaurante> restauranteOp = Restaurante.findByIdOptional(id);
        //validar se tem prato cadastrado e avisar que precisa apagar os pratos antes do restaurante
    	restauranteOp.ifPresentOrElse(Restaurante::delete, () ->{
    		throw new NotFoundException();
    	});
    }
    
    //Pratos
    @GET
    @Path("{idRestaurante}/pratos")
    @Tag(name="Marketplace")
    public List<PratoDTO> buscarPratos(@PathParam("idRestaurante") Long idRestaurante) {
       Optional<Restaurante> restauranteOp = Restaurante.findByIdOptional(idRestaurante);
       if(restauranteOp.isEmpty()) {
    	   throw new NotFoundException("Restaurante não existe");
       }
        Stream<Prato> pratos = Prato.stream("restaurante", restauranteOp.get());
        return pratos.map(p -> pratoMapper.toPratoDTO(p)).collect(Collectors.toList());
    }
    
    @POST
    @Path("{idRestaurante}/pratos")
    @Transactional
    @Tag(name="Cadastro-Restaurante")
    public Response adicionarPrato(@PathParam("idRestaurante") Long idRestaurante, AdicionarPratoDTO dto) {
    	   Optional<Restaurante> restauranteOp = Restaurante.findByIdOptional(idRestaurante);
           if(restauranteOp.isEmpty()) {
        	   throw new NotFoundException("Restaurante não existe");
           }
    	Prato prato = pratoMapper.toPrato(dto);
        prato.restaurante = restauranteOp.get();
    	prato.persist();
    	return Response.status(Status.CREATED).build();
    }
    
    @PUT
    @Path("{idRestaurante}/pratos/{id}")
    @Transactional
    @Tag(name="Cadastro-Restaurante")
    public void atualizar (@PathParam("idRestaurante") Long idRestaurante, @PathParam("id") Long id,
                           AtualizarPratoDTO dto) {
    	Optional<Restaurante> restauranteOp = Restaurante.findByIdOptional(idRestaurante);
    	if(restauranteOp.isEmpty()) {
    		throw new NotFoundException("Restaurante não existe");
    	}
    	Optional<Prato> pratoOp = Prato.findByIdOptional(id);
    	if(pratoOp.isEmpty()) {
    		throw new NotFoundException("Prato não existe");
    	}
    	Prato prato = pratoOp.get();
    	pratoMapper.toPrato(dto, prato);
        prato.persist();
    }
    
    @DELETE
    @Transactional
    @Path("{idRestaurante}/pratos/{id}")
    @Tag(name="Cadastro-Restaurante")
    public void deletar (@PathParam("idRestaurante") Long idRestaurante, @PathParam("id") Long id) {
    	Optional<Restaurante> restauranteOp = Restaurante.findByIdOptional(idRestaurante);
    	if(restauranteOp.isEmpty()) {
    		throw new NotFoundException("Restaurante não existe");
    	}
    	Optional<Prato> pratoOp = Prato.findByIdOptional(id);
    	pratoOp.ifPresentOrElse(Prato::delete, () ->{
    		throw new NotFoundException();
    	});
    }
}