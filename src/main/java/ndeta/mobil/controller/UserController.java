package ndeta.mobil.controller;

import lombok.RequiredArgsConstructor;
import ndeta.mobil.dto.BankResponse;
import ndeta.mobil.dto.UserRequest;
import ndeta.mobil.service.UserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("/api/v1/user")
@RequiredArgsConstructor
public class UserController {
    @Autowired
   private UserService userService;
    @PostMapping
    public BankResponse createAccount(@RequestBody UserRequest request){
    return userService.createAccount(request);
    }
}
