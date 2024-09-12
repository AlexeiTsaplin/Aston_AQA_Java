package lesson16.dto;

import lombok.Builder;
import lombok.Data;

@Builder
@Data
public class PaymentSectionDto {

    private final String paymentType;
    private final String specialText;
    private final String sum;
    private final String email;
}
