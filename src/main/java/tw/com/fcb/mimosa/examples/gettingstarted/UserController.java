package tw.com.fcb.mimosa.examples.gettingstarted;

import java.util.ArrayList;
import java.util.List;
import java.util.stream.Collectors;

import javax.validation.constraints.Min;

import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import lombok.RequiredArgsConstructor;
import tw.com.fcb.mimosa.http.APIRequest;
import tw.com.fcb.mimosa.http.APIResponse;
import tw.com.fcb.mimosa.tracing.Traced;

@Traced
@Validated
@RequiredArgsConstructor
@RestController
@RequestMapping("/users")
public class UserController {

	static List<User> list = new ArrayList<User>();
	static List<UserDto> listDto = new ArrayList<UserDto>();
	
	final UserDtoMapper mapper;
	final UserService service;
	
	@GetMapping
	List<User> getUsers() {
//		User user = new User();
//		user.setName("Amy");
//		user.setAge(25);
//		return user;
		
		return list;
	}
	
	@PostMapping("/names")
	APIResponse<User> getByName(@RequestBody APIRequest<String> name) {
		return APIResponse.success(service.getByName(name.getBody()));
	}
	
	@GetMapping("/UserDatabase")
	APIResponse<List<UserDto>> getUsers2() {
//		return APIResponse.success(service.getUsers().stream()
//				.map(mapper::from)
//				.collect(Collectors.toList()));
		
		List<UserDto> found = service.getUsers().stream()
				.map(mapper::from)
				.collect(Collectors.toList());
//		if(found.isEmpty()) {
//			return APIResponse.error(err -> err.code("").message(""));
//		}
		return APIResponse.success(found);
	}
	
	@GetMapping("/UserNames")
	List<UserDto> getUserNames() {
//		User user = new User();
//		user.setName("Amy");
//		user.setAge(25);
//		return user;

//java8		
//		for(int i =0;i<list.size();i++) {
//			listDto.add(UserDto.builder().name(list.get(i).getName()).build());
//		}
		
		//java11
		//List<UserDto>userNames11 = list.stream().map(user -> UserDto.builder().name(user.getName()).build()).collect(Collectors.toList());
		
		//mapper
		  List<UserDto> userNames = new ArrayList<UserDto>();
		  for(User user : list) {
		   UserDto dto = mapper.from(user);
		   userNames.add(dto);
		  }
		
		return userNames;
	}
	
	@PostMapping
	APIResponse<Long> createUser(@Validated @RequestBody APIRequest<CreateUserDto> user) {
		//list.add(user);
		//service.createUser(user.getBody());
	
		return APIResponse.success(service.createUser(user.getBody()))
				.map(User::getId);
	}
	
	@DeleteMapping
	void deleteUser(@RequestBody User user) {
		list.remove(user);

	}
	
	@PutMapping("/{name}/{age}")
	void updateUser(@PathVariable("name") String name,@PathVariable("age") int age, @RequestBody User user) {
		User updateUser = new User(); 
		updateUser.setName(name);
		updateUser.setAge(age);
		list.set(list.indexOf(user), updateUser);
	}
	
	@PutMapping("/{id}")
	void replaceUser(@Min(0) @PathVariable("id") Long id,@Validated @RequestBody ReplaceUserDto user) {
//		for(User u:list) {
//			if(u.getId()==id) {
//				u.setName(user.getName());
//				u.setAge(user.getAge());
//			}
//		}
		service.replaceUser(id, user);
	}
	
	@DeleteMapping("/{id}")
	void deleteUser(@PathVariable("id") Long id) {
		list.removeIf(user -> user.getId() == id);
		service.deleteUser(id);
	}
	

	
}
