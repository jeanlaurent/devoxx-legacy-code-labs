package legacy.service.implementation;

import legacy.hedge.HedgingPosition;
import legacy.persistence.ObjectDTO;
import legacy.service.ITransactionManagerService;

public class TransactionManagerService implements ITransactionManagerService {

	@Override
	public <T extends ObjectDTO> T classStorageAction(HedgingPosition hp) {
		return (T) hp;
	}

}
