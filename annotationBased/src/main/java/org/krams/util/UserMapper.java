package org.krams.util;

import java.util.ArrayList;
import java.util.List;

import org.krams.response.UserDto;
import org.springframework.data.domain.Page;

import com.sumit.dto.SeatPlan;
import com.sumit.model.StudentExaminationInfo;
import com.sumit.model.StudentResultInfo;
import com.sumit.model.User;

public class UserMapper {

	public static UserDto map(User user) {
		UserDto dto = new UserDto();
		dto.setId(user.getId());
		if (user.getUserInfo() != null) {
			dto.setFirstName(user.getUserInfo().getFirstName());
			dto.setLastName(user.getUserInfo().getLastName());
			dto.setUsername(user.getUsername());
			dto.setEmailAddress(user.getUserInfo().getEmail());
			dto.setAddress(user.getUserInfo().getAddress());
		}
		// dto.setRole(user.getUserRole());
		return dto;
	}

	public static List<UserDto> map(Page<User> users) {
		List<UserDto> dtos = new ArrayList<UserDto>();
		for (User user : users) {
			dtos.add(map(user));
		}
		return dtos;
	}

	public static List<SeatPlan> mapToSeatPlan(
			List<StudentExaminationInfo> list) {
		List<SeatPlan> dtos = new ArrayList<SeatPlan>();
		for (StudentExaminationInfo sei : list) {
			dtos.add(map(sei));
		}
		return dtos;
	}

	private static SeatPlan map(StudentExaminationInfo sei) {
		SeatPlan dto = new SeatPlan();

		if (sei.getStudent().getUserInfo() != null) {
			dto.setName(sei.getStudent().getUserInfo().getFirstName() + "  "
					+ sei.getStudent().getUserInfo().getLastName());

			dto.setEmail(sei.getStudent().getUserInfo().getEmail());
		}
		if (sei.getSet() != null) {
			dto.setAssignedSet(sei.getSet().getName());
		}
		dto.setSeatNumber(sei.getSeatNumber());
		dto.setUsername(sei.getStudent().getUsername());

		return dto;
	}

	public static List<ResultDTO> mapToResultDTO(List<StudentResultInfo> list) {
		List<ResultDTO> dtos = new ArrayList<ResultDTO>();
		for (StudentResultInfo sei : list) {
			dtos.add(map(sei));
		}
		return dtos;
	}

	private static ResultDTO map(StudentResultInfo sei) {
		ResultDTO dto = new ResultDTO();
		dto.setName(sei.getStudentExaminationInfo().getStudent().getUserInfo()
				.getFirstName()
				+ "  "
				+ sei.getStudentExaminationInfo().getStudent().getUserInfo()
						.getLastName());
		dto.setObtainedScore(sei.getObtainedScore());
		dto.setPosition(sei.getPosition());
		dto.setRemarks(sei.getRemarks());
		if (sei.getStatus()) {

			dto.setStatus("PASSED");
		} else {
			dto.setStatus("FAILED");
		}
		return dto;
	}

}
