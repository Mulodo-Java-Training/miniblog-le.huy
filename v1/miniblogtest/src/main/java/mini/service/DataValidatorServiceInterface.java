package mini.service;


public interface DataValidatorServiceInterface {

	public boolean validateUserGetInfoParameter(String mode);
	public boolean validateSearchQuery(String query);

	public boolean validatePostGetParameter(String mode);

	public boolean validateCommentGetParameter(String mode);
}
