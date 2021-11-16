package tw.com.fcb.mimosa.examples.gettingstarted.client;

import java.util.List;

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
import tw.com.fcb.mimosa.examples.gettingstarted.CreateUserDto;
import tw.com.fcb.mimosa.examples.gettingstarted.ReplaceUserDto;
import tw.com.fcb.mimosa.examples.gettingstarted.UserDto;
import tw.com.fcb.mimosa.http.APIRequest;
import tw.com.fcb.mimosa.http.APIResponse;

@RestController
@RequestMapping("/client/users")
@RequiredArgsConstructor
public class MyClientController {
	
	final UserRestTemplate service;
	
	@GetMapping
	APIResponse<List<UserDto>> getUsers(){
		return service.getUsers();
	}
	
	@PostMapping
	APIResponse<Long> createUser(@Validated @RequestBody APIRequest<CreateUserDto> user) {
		//list.add(user);
		//service.createUser(user.getBody());
		return service.createUser(user);
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
//		list.removeIf(user -> user.getId() == id);
		service.deleteUser(id);
	}
}
