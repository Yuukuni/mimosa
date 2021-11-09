package tw.com.fcb.mimosa.examples.gettingstarted;

import java.util.Collection;

import org.springframework.stereotype.Service;

import lombok.RequiredArgsConstructor;
import tw.com.fcb.mimosa.http.APIErrorT9nException;

@RequiredArgsConstructor
@Service
public class UserService {

	final UserRepository repository;
	final UserDtoMapper mapper;
//	public UserService(UserRepository repository) {
//		this.repository = repository;
//	}
	
	public User getByName(String name) {
		return repository.findByName(name)
				.orElseThrow(() -> {
					//return new APIErrorException(err -> err.code("ERR1").message("name not found"));
					return new APIErrorT9nException(err -> err.term(MyErrorCode.NAME_NOT_FOUND));
				});
	}
	
	public Collection<User> getUsers() {
		return repository.findAll();
	}
	
	public User createUser(CreateUserDto user) {
		return repository.save(mapper.createUser(user));
	}
	public User replaceUser(long id,ReplaceUserDto user) {
//		return repository.findById(id).map(db -> {
//			db.setAge(user.getAge());
//			db.setName(user.getName());
//			return db;
//			}).map(repository::save)
//				.orElseThrow(() -> new IllegalArgumentException("id ["+id+"] not exist!"));			
	
		return repository.findById(id).map(db ->{
			mapper.copyUser(user, db);
			return db;
		}).map(repository::save)
				.orElseThrow(()->new IllegalArgumentException("id ["+id+"] not exist!"));
		
	}
	
	public void deleteUser(Long id) {
		 repository.deleteById(id);
	}
	
}
