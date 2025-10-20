package InputValidater;

public class ValidatePassword {
    String correctHashedPassword;
    String salt;

    private String hashPassword(String passwordToHash){
        //get user input, add salt
        String passwordWithSalt = this.salt + passwordToHash;
        //hash passwordWithSalt
        return "hashedPassword";
    }

    public boolean validatePassword(){
        String input = ""; //get user to input password

        while(true){
            if(hashPassword(input).equals(this.correctHashedPassword)){
                return true;
            }
            else{
                //ask for new user input
            }
        }
    }
}
