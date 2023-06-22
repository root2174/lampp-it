package br.com.lamppit.teste.order.dto;

import br.com.lamppit.teste.order.model.DeliveryMethod;
import br.com.lamppit.teste.order.model.PaymentMethod;
import io.swagger.annotations.ApiModelProperty;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

import javax.validation.constraints.NotEmpty;
import javax.validation.constraints.NotNull;
import java.util.List;

@Data
@Builder
@NoArgsConstructor
@AllArgsConstructor
public class CreateOrderRequestData {

    @NotEmpty
    @ApiModelProperty(example = "[1]")
    private List<Long> productsIds;

    @NotNull
    @ApiModelProperty(example = "1")
    private Long companyId;

    @ApiModelProperty(example = "PIX")
    private PaymentMethod paymentMethod;

    @ApiModelProperty(example = "WITHDRAWAL")
    private DeliveryMethod deliveryMethod;
}
