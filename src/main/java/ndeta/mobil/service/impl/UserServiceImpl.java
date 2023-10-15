package ndeta.mobil.service.impl;

import lombok.RequiredArgsConstructor;
import ndeta.mobil.dto.AccountInfo;
import ndeta.mobil.dto.BankResponse;
import ndeta.mobil.dto.EmailDetails;
import ndeta.mobil.dto.UserRequest;
import ndeta.mobil.entity.User;
import ndeta.mobil.repository.UserRepository;
import ndeta.mobil.service.EmailService;
import ndeta.mobil.service.UserService;
import org.jetbrains.annotations.NotNull;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.web.bind.annotation.RequestBody;

import java.math.BigDecimal;

import static ndeta.mobil.utils.AccountUtils.*;

@Service
@RequiredArgsConstructor
public class UserServiceImpl implements UserService {
     @Autowired
     private   UserRepository userRepository;
     @Autowired
     private EmailService emailService;

    @Override
    public BankResponse createAccount(@RequestBody @NotNull UserRequest userRequest) {
        //Check if user exist
        if(userRepository.existsByEmail(userRequest.getEmail())){
            return BankResponse.builder()
                    .responseCode(ACCOUNT_EXIST_CODE)
                    .responseMessage(ACCOUNT_EXIST_MESSAGE)
                    .accountInfo(null)
                    .build();
        }
        User newUser=User.builder()
                .firstName(userRequest.getFirstName())
                .lastName(userRequest.getLastName())
                .otherName(userRequest.getOtherName())
                .gender(userRequest.getGender())
                .address(userRequest.getAddress())
                .stateOfOrigin(userRequest.getStateOfOrigin())
                .accountNumber(GenerateAccountNumber())
                .email(userRequest.getEmail())
                .accountBalance(BigDecimal.ZERO)
                .status("ACTIVE")
                .phoneNumber(userRequest.getPhoneNumber())
                .alternativePhoneNumber(userRequest.getAlternativePhoneNumber())
                .build();
        User savedUser=userRepository.save(newUser);
        //send email alert
        EmailDetails emailDetails= EmailDetails.builder()
                .recipient(savedUser.getEmail())
                .subject("Mobil")
                .messageBody("Welcome to Mobil account created successfully.\n Account details \n"
                + "Account Name:"+ savedUser.getFirstName()+" "
                        + " " + savedUser.getLastName() + " "+ savedUser.getOtherName() + "\n"
                + "Account Number" + savedUser.getAccountNumber())
                .build();
        emailService.sendEmailAlert(emailDetails);
    return BankResponse.builder()
            .responseCode(ACCOUNT_CREATION_SUCCESS)
            .responseMessage(ACCOUNT_CREATION_MESSAGE)
            //Account builder
            .accountInfo(AccountInfo.builder()
                    .accountBalance(savedUser.getAccountBalance())
                    .accountNumber(savedUser.getAccountNumber())
                    .accountName(savedUser.getFirstName()+ " "
                            + savedUser.getLastName()+
                            " " + savedUser.getOtherName() )
                    .build())
            .build();
    }
}
