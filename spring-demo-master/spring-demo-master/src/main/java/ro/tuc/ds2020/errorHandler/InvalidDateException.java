package ro.tuc.ds2020.errorHandler;

import org.springframework.http.HttpStatus;

public class InvalidDateException extends RuntimeException{
    public static final HttpStatus HTTP_STATUS =HttpStatus.CONFLICT;
    private final String dateConflict;

    public InvalidDateException(String dateConflict,Object fieldValue){
        super(String.format("Date conflict in %s : %s",dateConflict,fieldValue));
        this.dateConflict=dateConflict;
    }
    public  String getDateConflict(){ return dateConflict;}


}
