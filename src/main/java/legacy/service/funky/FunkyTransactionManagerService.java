package legacy.service.funky;

import legacy.hedge.HedgingPosition;
import legacy.persistence.ObjectDTO;
import legacy.service.ITransactionManagerService;

public class FunkyTransactionManagerService implements ITransactionManagerService {

	@Override
	public <T extends ObjectDTO> T classStorageAction(HedgingPosition hp) {
		return (T) hp;
	}

}
