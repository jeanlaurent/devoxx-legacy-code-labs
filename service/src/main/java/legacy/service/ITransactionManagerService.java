package legacy.service;

import legacy.persistence.ObjectDTO;


public interface ITransactionManagerService {

	public <T extends ObjectDTO> T classStorageAction(Position hp);

}
