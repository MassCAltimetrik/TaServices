/**
 * 
 */
package com.taleantacq.comon;

/**
 * @author yadak
 *
 */
public final class TAErrorCodes {

	public static final String SOMETHING_WENT_WRONG_CODE = "999";
	public static final String SOMETHING_WENT_WRONG_DESCRIPTION = "Error,Something went wrong";
	
	public static final String SUCCESS = "000";
	public static final String SUCCESS_DESCRIPTION = "Success";
	
	public static final String REQUEST_IS_NULL_CODE = "100";
	public static final String REQUEST_IS_NULL_DESCRIPTION = "Request can not be null";
	
	public static final String FIRSTNAME_IS_NULL_CODE = "101";
	public static final String FIRSTNAME_IS_NULL_DESCRIPTION = "First name can not be null or Blank";
	
	public static final String LASTNAME_IS_NULL_CODE = "102";
	public static final String LASTNAME_IS_NULL_DESCRIPTION = "Last name can not be null or Blank";
	
	public static final String EMAIL_IS_NULL_CODE = "103";
	public static final String EMAIL_IS_NULL_DESCRIPTION = "Email can not be null or Blank";
	
	public static final String MOBILENUMBER_IS_NULL_CODE = "104";
	public static final String MOBILE_IS_NULL_DESCRIPTION = "Mobile Number can not be null or Blank";
	
	public static final String PASSWORD_IS_NULL_CODE = "105";
	public static final String PASSWORD_IS_NULL_DESCRIPTION = "Password can not be null or Blank";
	
	public static final String MOBILENUMBERFORMATINVALID_CODE = "106";
	public static final String MOBILENUMBERFORMATINVALID_DESCRIPTION = "Mobile Number format is INVALID";
	
	public static final String EMAILFORMATINVALID_CODE = "107";
	public static final String EMAILFORMATINVALID_DESCRIPTION = "Email id format is INVALID";
	
	public static final String SECURITY_QUESTION_IS_NULL_CODE = "108";
	public static final String SECURITY_QUESTION_IS_NULL_DESCRIPTION = "Security Question is null or empty";
	
	public static final String SECURITY_ANSWER_IS_NULL_CODE = "109";
	public static final String SECURITY_ANSWER_IS_NULL_DESCRIPTION = "Security Answer is null or empty";
	
	public static final String TA_REGISTRATION_FAILED_CODE = "110";
	public static final String TA_REGISTRATION_FAILED_DESCRIPTION = "Registration Failed";
	
	public static final String INVALID_LOGIN_CREDENTAILS_CODE = "111";
	public static final String INVALID_LOGIN_CREDENTAILS_DESCRIPTION = "INVALID Login Credentails";
	
	public static final String ACCOUNT_ALREADY_EXIST_CODE = "112";
	public static final String ACCOUNT_ALREADY_EXIST_DESCRIPTION = "Account with given Email already exist";

	
	public static final String CALLED_DATE_NULL_CODE = "113";
	public static final String CALLED_DATE_NULL_DESCRIPTION = "Called date can not be null or blank";
	
	public static final String CALLED_DURATION_NULL_CODE = "114";
	public static final String CALLED_DURATION_NULL_DESCRIPTION = "Called duration can not be null or blank";
	
	public static final String CALL_START_TIME_NULL_CODE = "115";
	public static final String CALL_START_TIME_NULL_DESCRIPTION = "Call start time can not be null or blank";
	
	public static final String CALL_END_TIME_NULL_CODE = "116";
	public static final String CALL_END_TIME_NULL_DESCRIPTION = "Call end time can not be null or blank";
	
	public static final String CALL_DURATION_TIME_NULL_CODE = "117";
	public static final String CALL_DURATION_TIME_NULL_DESCRIPTION = "Call duration can not be null or blank";
	
	public static final String TA_ID_NULL_CODE = "117";
	public static final String TA_ID_NULL_DESCRIPTION = "TA ID can not be null or blank";
	
	public static final String CANDIDATE_ID_NULL_CODE = "118";
	public static final String CANDIDATE_ID_NULL_DESCRIPTION = "Candidate ID can not be null or blank";
	
	public static final String IS_CALL_RECIEVED_NULL_CODE = "118";
	public static final String IS_CALL_RECIEVED_NULL_DESCRIPTION = "Is call recieved can not be null or blank";
	
	public static final String CANDIDATE_LIST_EMPTY_CODE = "119";
	public static final String CANDIDATE_LIST_EMPTY_DESCRIPTION = "No candidate present for given ta id";
	
	
	public static final String VIOLATIONS_MIN_CHAR = "Minimun 2 charachers are required";
	
	public static final String VIOLATIONS_MIN_NUMBER = "Minimun 10 digits are required";
	
	public static final String VIOLATIONS_EMAIL_FORMAT = "EMAIL format is invalid";
	
	public static final String ERROR_IN_CONVERTING_EXCEL_DATA_CODE = "120";
	public static final String ERROR_IN_CONVERTING_EXCEL_DATA_DESCRIPTION = "Error in converting excel data ";
	
	public static final String EXCEL_VALIDATION_FAILED_CODE = "121";
	public static final String EXCEL_VALIDATION_FAILED_DESCRIPTION = "Failed in validation the excel data";
	
	public static final String ERROR_SAVING_CADIDATE_LIST_CODE = "122";
	public static final String ERROR_SAVING_CADIDATE_LIST_DESCRIPTION = "Error in saving cadidate list";
	
	public static final String ERROR_GENERATING_ERROR_FILE_CODE = "123";
	public static final String ERROR_GENERATING_ERROR_FILE_DESCRIPTION = "Error in generating error file ";
	
	public static final String ERROR_SAVING_CALL_DETAILS_DESCRIPTION_CODE = "124";
	public static final String ERROR_SAVING_CALL_DETAILS_DESCRIPTION = "Error in saving call details";
	
}
