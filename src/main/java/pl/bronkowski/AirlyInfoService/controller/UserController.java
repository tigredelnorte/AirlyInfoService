package pl.bronkowski.AirlyInfoService.controller;

import static org.springframework.hateoas.server.mvc.WebMvcLinkBuilder.linkTo;
import static org.springframework.hateoas.server.mvc.WebMvcLinkBuilder.methodOn;

import java.net.URISyntaxException;
import java.util.List;
import java.util.stream.Collectors;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.hateoas.CollectionModel;
import org.springframework.hateoas.EntityModel;
import org.springframework.hateoas.IanaLinkRelations;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RestController;

import pl.bronkowski.AirlyInfoService.UserEntityModelAssembler;
import pl.bronkowski.AirlyInfoService.model.User;
import pl.bronkowski.AirlyInfoService.repository.UserRepository;;

@RestController
public class UserController {

	@Autowired
	private final UserRepository repository;
	
	@Autowired
	private final UserEntityModelAssembler assembler;

	UserController(UserRepository repository, UserEntityModelAssembler assembler) {
		this.repository = repository;
		this.assembler = assembler;
	}

	@GetMapping("/users")
	public CollectionModel<EntityModel<User>> getAll() {
		
		List<EntityModel<User>> users = repository.findAll().stream()
				.map(assembler::toModel)     //map(user -> assembler.toModel(user))
						.collect(Collectors.toList());
		
		return new CollectionModel<>(users,
				linkTo(methodOn(UserController.class).getAll()).withSelfRel());
	}
	
	
	@PostMapping("/users")
	public ResponseEntity<?> newUser(@RequestBody User newUser) throws URISyntaxException {
		
		EntityModel<User> entityModel = assembler.toModel(repository.save(newUser));
		
		return ResponseEntity
				.created(entityModel.getRequiredLink(IanaLinkRelations.SELF).toUri())
				.body(entityModel);
	}


	@GetMapping("/users/{id}")
	public EntityModel<User> getById(@PathVariable Long id){
		
		User user = repository.findById(id)
				.orElseThrow(()->new UserNotFoundException(id));
		
		return assembler.toModel(user);
	}
	
	
		@PutMapping("/users/{id}")
	public ResponseEntity<?> updateUser(@RequestBody User newUser, @PathVariable Long id) throws URISyntaxException {

			User updatedUser = repository.findById(id)
					.map(user -> {
						user.setFirstName(newUser.getFirstName());
						user.setLastName(newUser.getLastName());
						user.setEmail(newUser.getEmail());
						user.setUserAddress(newUser.getUserAddress());
						user.setLongt(newUser.getLongt());
						user.setLatt(newUser.getLatt());
						return repository.save(user);
					})
					.orElseGet(() ->{
						newUser.setId(id);
						return repository.save(newUser);
					});

			EntityModel<User>entityModel = assembler.toModel(updatedUser);
			return ResponseEntity
					.created(entityModel.getRequiredLink(IanaLinkRelations.SELF).toUri())
					.body(entityModel);
	}

	@DeleteMapping("/users/{id}")
	public ResponseEntity<?> deleteUser(@PathVariable Long id) {
		repository.deleteById(id);
		return ResponseEntity.noContent().build();
	}
	 
}
@SuppressWarnings("serial")
class UserNotFoundException extends RuntimeException {

	UserNotFoundException(Long id) {
		super("Could not find employee " + id);
	}
}
