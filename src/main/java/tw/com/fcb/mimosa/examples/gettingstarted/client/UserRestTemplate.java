package tw.com.fcb.mimosa.examples.gettingstarted.client;

import java.util.List;

import javax.validation.constraints.Min;

import org.springframework.beans.factory.annotation.Value;
import org.springframework.boot.web.client.RestTemplateBuilder;
import org.springframework.core.ParameterizedTypeReference;
import org.springframework.http.HttpEntity;
import org.springframework.http.HttpMethod;
import org.springframework.http.MediaType;
import org.springframework.stereotype.Service;
import org.springframework.web.client.RestTemplate;

import tw.com.fcb.mimosa.examples.gettingstarted.CreateUserDto;
import tw.com.fcb.mimosa.examples.gettingstarted.ReplaceUserDto;
import tw.com.fcb.mimosa.examples.gettingstarted.UserDto;
import tw.com.fcb.mimosa.http.APIRequest;
import tw.com.fcb.mimosa.http.APIResponse;

@Service
public class UserRestTemplate {
	
	@Value("${server.port:8080}")
	int port;
	
	String url() {
		return "http://localhost:" + port + "/users";
	}
	
	RestTemplate buildRestTemplate() {
		return new RestTemplateBuilder()
				.defaultHeader("Content-Type", MediaType.APPLICATION_JSON_VALUE)
				.build();
	}
	
	
	public APIResponse<List<UserDto>> getUsers(){
//		return new RestTemplateBuilder()
//				.defaultHeader("Content-Type", MediaType.APPLICATION_JSON_VALUE)
//				.build()
//			.exchange("http://localhost:8080/users", HttpMethod.GET, null,
//					new ParameterizedTypeReference<APIResponse<List<UserDto>>>() {
//				})
//			.getBody();
		
//		RestTemplate template = new RestTemplateBuilder()
//				.defaultHeader("Content-Type", MediaType.APPLICATION_JSON_VALUE)
//				.build();
//		ResponseEntity<APIResponse<List<UserDto>>> Response = template
//				.exchange(url(), HttpMethod.GET, null,
//					new ParameterizedTypeReference<APIResponse<List<UserDto>>>() {
//				});
//		return Response.getBody();
		return buildRestTemplate()
				.exchange(url() + "/UserDatabase", HttpMethod.GET, null,
					new ParameterizedTypeReference<APIResponse<List<UserDto>>>() {
				})
				.getBody();
		
	}
	
	public APIResponse<Long> createUser(APIRequest<CreateUserDto> user){
		return buildRestTemplate()
				.exchange(url(), HttpMethod.POST, new HttpEntity<>(user),
					new ParameterizedTypeReference<APIResponse<Long>>() {
				})
				.getBody();
		
	}
	
	public void replaceUser(@Min(0) Long id, ReplaceUserDto user){
		buildRestTemplate().put(url() + "/" + id, user);
	}
	
	public void deleteUser(Long id){
		buildRestTemplate().delete(url() + "/" + id);
	}
}
