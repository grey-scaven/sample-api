package controller;

import controller.dto.TestRequestIF;
import org.modelmapper.ModelMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import service.SampleService;
import service.dto.TestRequestDto;

import javax.validation.Valid;

@RestController
public class ApiSampleController {

    @Autowired
    private ModelMapper modelMapper;

    @Autowired
    private SampleService sampleService;

    @RequestMapping(value = "/api/v1/test", method = RequestMethod.POST)
    @ResponseBody
    public ResponseEntity test(@Valid @RequestBody TestRequestIF requestIF) {
        try {
            TestRequestDto requestDto = modelMapper.map(requestIF, TestRequestDto.class);
            sampleService.test(requestDto);

            return new ResponseEntity(HttpStatus.OK);
        } catch (Exception e) {
            e.printStackTrace();
            return new ResponseEntity(HttpStatus.BAD_REQUEST);
        }
    }
}
