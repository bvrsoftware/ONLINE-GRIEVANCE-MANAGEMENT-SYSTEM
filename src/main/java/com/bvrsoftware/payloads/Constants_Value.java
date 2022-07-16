package com.bvrsoftware.payloads;

import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.security.core.userdetails.UserDetails;

public class Constants_Value {

	public static final Integer ADMIN_ROLE_ID=501;
	public static final Integer NORMAL_USER_ROLE_ID=502;
	public static final String ADMIN_USER="ADMIN_USER";
	public static final String NORMAL_USER="NORMAL_USER";
	
	public static final String PAGE_NUMBER="0";
	public static final String PAGE_SIZE="5";
	public static final String SORT_BY="id";
	public static final String SORT_DIR="asc";
	
	public static String getCurrentLoginUsername() {
		String username = null;
		try {
			
		   UserDetails userDetails = (UserDetails) SecurityContextHolder.getContext().getAuthentication().getPrincipal();
		   username=userDetails.getUsername();	
		}catch (NullPointerException e) {
			 new ApiResponse(" Login Your account first",false);
		}
		return username;
	}

	
}
