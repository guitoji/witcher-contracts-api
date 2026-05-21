package com.guitoji.witcher_contracts.validation;

import com.guitoji.witcher_contracts.exception.OperationNotPermittedException;
import com.guitoji.witcher_contracts.model.Contract;
import com.guitoji.witcher_contracts.model.enums.ContractStatus;
import com.guitoji.witcher_contracts.repository.ContractRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Component;

@Component
@RequiredArgsConstructor
public class ContractValidation {

    private ContractRepository contractRepository;

    public void validate(Contract contract) {
        if (statusIncorrectToCreateContract(contract)) {
            throw new OperationNotPermittedException("To create a contract, the status needs to be OPEN");
        }
    }

    public void validateDelete(Contract contract) {
        if (contractIsNotCanceled(contract)) {
            throw new OperationNotPermittedException("Deleting this contract is not allowed");
        }
    }

    private boolean statusIncorrectToCreateContract(Contract contract) {
        return !contract.getStatus().equals(ContractStatus.OPEN);
    }

    private boolean contractIsNotCanceled(Contract contract) {
        return !contract.getStatus().equals(ContractStatus.CANCELED);
    }
}
