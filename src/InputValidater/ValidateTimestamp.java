package InputValidater;

public class ValidateTimestamp {

    public boolean validateTimestamp(){
        String input = "";
        //get user input, check with Months enum if the timestamp is valid, if not ask again
        while(true){
            if(validateToEnum(input)){
                return true;
            }
            else{
                //get new input
            }
        }
    }

    private boolean validateToEnum(String test){
        //check if TimeStamp exists with Months enum
        return true;
    }

    //validateToEnum should maybe be independent module to reduce redundancy??
    //validate user input interface may also be smart to make
}
