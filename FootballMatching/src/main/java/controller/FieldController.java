package controller;

import domain.Fields;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseBody;
import service.FieldService;

@Controller
@RequestMapping(value = "/field")
public class FieldController {

    @Autowired
    private FieldService fieldService;

    // 지역별 구장 조회
    @ResponseBody
    @RequestMapping(value = "/search", method = RequestMethod.GET)
    public ResponseEntity search_field(@RequestParam(value = "location_code") int location_code){
        return new ResponseEntity(fieldService.search_field(location_code), HttpStatus.OK);
    }
}
