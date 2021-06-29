package controller;

import domain.BusinessUsers;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.ResponseBody;
import service.BusinessUserService;

@Controller
@RequestMapping(value = "/business-user")
public class BusinessUserController {

    @Autowired
    private BusinessUserService b_userService;

    @ResponseBody
    @RequestMapping(value = "/signup", method = RequestMethod.POST)
    public ResponseEntity sign_up(@RequestBody BusinessUsers b_user){
        return new ResponseEntity(b_userService.sign_up(b_user), HttpStatus.OK);
    }
}
