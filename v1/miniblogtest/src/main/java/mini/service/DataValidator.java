package mini.service;

import mini.systemvalue.SystemValue;

import org.springframework.stereotype.Service;
@Service
public class DataValidator  implements DataValidatorServiceInterface{

	@Override
	public boolean validateUserGetInfoParameter(String mode) {
		if(
				mode !=null &&
				(mode.equals("current")||
				mode.equals("id"))
		){ return true;}
		else
		{ return false;}
	}

	@Override
	public boolean validateSearchQuery(String query) {
		if(
				query !=null && 
				query.length() <= SystemValue.USERNAME_MAX_LENGTH &&
				query.matches(SystemValue.USERNAME_STRING_RANGE)
		){ return true;}
		else
		{ return false;}
	}
	
	@Override
	public boolean validatePostGetParameter(String mode) {
		if(
				mode !=null &&
				(mode.equals("all")||
				mode.equals("current")||
				mode.equals("postid")||
				mode.equals("userid")||
				mode.equals("top")||
				mode.equals("title"))
		){ return true;}
		else
		{ return false;}
	}

	@Override
	public boolean validateCommentGetParameter(String mode) {
		if(
				mode !=null &&
				(mode.equals("id")||
				mode.equals("current")||
				mode.equals("userid")||
				mode.equals("postid"))
		){ return true;}
		else
		{ return false;}
	}



	
}
