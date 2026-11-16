package com.office.ex;

import java.util.HashMap;
import java.util.Map;

public class MemberDao {
	
	Map<String, MemberDto> memDB = new HashMap<String, MemberDto>();

	public int insertNewMember(MemberDto memberDto) {
		
		memDB.put(memberDto.getMemId(), memberDto);
		
		return memDB.get(memberDto.getMemId()) != null ? 1 : 0;
	}

	public MemberDto selectMemberByMemIdAndMemPw(String memId) {
		
		MemberDto memberDto = memDB.get(memId);
		
		// Dao와 관련되지 않은 비즈니스로직이기 때문에 여기서 사용하지 않습니다.
//		if (memberDto != null && memberDto.getMemPw().equals(memPw)) {
//			return memberDto;
//		} else {
//			return null;
//		}
		
		return memberDto;
		
	}

}
