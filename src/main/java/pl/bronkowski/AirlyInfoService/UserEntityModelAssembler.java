package pl.bronkowski.AirlyInfoService;

import static org.springframework.hateoas.server.mvc.WebMvcLinkBuilder.*;
import org.springframework.hateoas.EntityModel;
import org.springframework.hateoas.server.RepresentationModelAssembler;
import org.springframework.stereotype.Component;

import pl.bronkowski.AirlyInfoService.controller.UserController;
import pl.bronkowski.AirlyInfoService.model.User;

@Component
public class UserEntityModelAssembler implements RepresentationModelAssembler<User, EntityModel<User>> {

	@Override
	public EntityModel<User> toModel(User user){
		return new EntityModel<> (user,
				linkTo(methodOn(UserController.class).getById(user.getId())).withSelfRel(),
				linkTo(methodOn(UserController.class).getAll()).withRel("users"));
	}
}
