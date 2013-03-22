package legacy.hedge;

import legacy.error.ARPSystemException;
import legacy.error.CheckResult;

/**
 * <p>
 *     Title: legacy.hedge.IHedgingPositionManagement
 * </p>
 * @author bPoolvoorde
 * @version 1.0
 * @creationDate May 6, 2005
 */
public interface IHedgingPositionManagement {

	CheckResult<HedgingPosition> initAndSendHedgingPosition(HedgingPosition hedgingPosition) throws ARPSystemException;

}
