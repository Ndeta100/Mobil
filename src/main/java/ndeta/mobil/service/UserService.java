package ndeta.mobil.service;

import ndeta.mobil.dto.BankResponse;
import ndeta.mobil.dto.UserRequest;

public interface UserService {
    BankResponse createAccount(UserRequest userRequest);
}
