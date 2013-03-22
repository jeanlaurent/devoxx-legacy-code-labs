package legacy.service;

import legacy.hedge.HedgingPosition;
import legacy.persistence.ObjectDTO;


public interface ITransactionManagerService {

	public <T extends ObjectDTO> T classStorageAction(HedgingPosition hp);

}
