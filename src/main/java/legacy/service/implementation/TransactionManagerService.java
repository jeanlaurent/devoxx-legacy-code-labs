package legacy.service.implementation;

import legacy.persistence.ObjectDTO;
import legacy.service.ITransactionManagerService;
import legacy.service.Position;

public class TransactionManagerService implements ITransactionManagerService {

	@Override
	public <T extends ObjectDTO> T classStorageAction(Position pos) {
		return (T) pos;
	}

}
