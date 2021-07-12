package controller.non_auth;

import io.swagger.annotations.ApiOperation;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseBody;
import service.non_auth.FieldService;

@Controller
@RequestMapping(value = "/field")
public class FieldController {

    @Autowired
    private FieldService fieldService;

    // 지역별 구장 조회
    @ResponseBody
    @RequestMapping(value = "/search", method = RequestMethod.GET)
    @ApiOperation(value = "지역별 구장 조회", notes = "지역별 구장을 전체 조회합니다.")
    public ResponseEntity searchField(@RequestParam(value = "location-code") int locationCode){
        return new ResponseEntity(fieldService.searchField(locationCode), HttpStatus.OK);
    }
}
