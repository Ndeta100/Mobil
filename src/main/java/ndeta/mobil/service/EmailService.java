package ndeta.mobil.service;

import ndeta.mobil.dto.EmailDetails;
import org.springframework.stereotype.Service;


public interface EmailService {
    void sendEmailAlert(EmailDetails emailDetails);
}
