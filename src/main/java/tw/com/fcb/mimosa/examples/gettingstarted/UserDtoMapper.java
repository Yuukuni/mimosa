package tw.com.fcb.mimosa.examples.gettingstarted;

import org.mapstruct.Mapper;
import org.mapstruct.Mapping;
import org.mapstruct.MappingTarget;

@Mapper
public interface UserDtoMapper {
	
	@Mapping(source = "name",target="userName")
	UserDto from(User user);
	
	User createUser(CreateUserDto dto);
	
	void copyUser(ReplaceUserDto source,@MappingTarget User target);
}
