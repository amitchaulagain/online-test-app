package org.krams.util;

import java.util.ArrayList;
import java.util.List;

import org.krams.response.UserDto;
import org.springframework.data.domain.Page;

import com.sumit.model.User;

public class UserMapper {

	public static UserDto map(User user) {
			UserDto dto = new UserDto();
			dto.setId(user.getId());
			if(user.getUserInfo()!=null){
				dto.setFirstName(user.getUserInfo().getFirstName());
				dto.setLastName(user.getUserInfo().getLastName());
				dto.setUsername(user.getUsername());
			}
//			dto.setRole(user.getUserRole());
			return dto;
	}
	
	public static List<UserDto> map(Page<User> users) {
		List<UserDto> dtos = new ArrayList<UserDto>();
		for (User user: users) {
			dtos.add(map(user));
		}
		return dtos;
	}
}
