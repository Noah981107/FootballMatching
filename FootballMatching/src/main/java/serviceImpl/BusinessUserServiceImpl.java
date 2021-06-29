package serviceImpl;

import domain.BusinessUsers;
import org.mindrot.jbcrypt.BCrypt;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import repository.BusinessUserMapper;
import repository.FieldMapper;
import service.BusinessUserService;

@Service
public class BusinessUserServiceImpl implements BusinessUserService {

    @Autowired
    private BusinessUserMapper b_userMapper;

    @Autowired
    private FieldMapper fieldMapper;

    @Override
    public String check_id(String id) {
        return b_userMapper.check_id(id);
    }

    @Override
    public String check_phoneNumber(String phoneNumber) {
        return b_userMapper.check_phoneNumber(phoneNumber);
    }

    @Override
    public int check_fieldName(String field_name) {
        return fieldMapper.check_fieldName(field_name);
    }

    @Override
    public String sign_up(BusinessUsers b_user) {
        String return_id = check_id(b_user.getId());
        System.out.println("return_id : " + return_id);
        if(return_id == null || return_id.isEmpty()){
            String return_phoneNumber = check_phoneNumber(b_user.getPhoneNumber());
            System.out.println("return_phoneNumber : " + return_phoneNumber);
            if(return_phoneNumber == null || return_phoneNumber.isEmpty()){
                int return_fieldId = check_fieldName(b_user.getField_name());
                System.out.println("return_fieldID : " + return_fieldId);
                if(return_fieldId > 0){
                    b_user.setPassword(BCrypt.hashpw(b_user.getPassword(), BCrypt.gensalt()));
                    b_user.setField_id(return_fieldId);
                    System.out.println("id : "+ b_user.getId());
                    System.out.println("password : "+ b_user.getPassword());
                    System.out.println("name : "+ b_user.getName());
                    System.out.println("phoneNumber : "+ b_user.getPhoneNumber());
                    System.out.println("fieldname : "+ b_user.getField_name());
                    System.out.println("fieldid : "+ b_user.getField_id());
                    b_userMapper.sign_up(b_user);
                    return "Membership Success";
                }
                else{
                    return "The selected stadium does not exist.";
                }
            }
            else{
                return "There are members with duplicate phone numbers.";
            }
        }
        else {
            return "There is a member with a duplicate ID.";
        }
    }
}
