package legacy.service;

import legacy.dto.ErrorLevel;
import legacy.persistence.StorageActionEnum;

import java.math.BigInteger;
import java.util.Date;

public interface Position {
    int getTransactionId();

    Date getValueDate();

    Date getNoticePeriodEndDate();

    // TODO: Rename this to CombockArray
    String getCombck();

    BigInteger getCodtyptra();

    int getCodetyptkt();

    double getPrxref();

    double getBasprx();

    Date getDaprx();

    String getQuantity();

    String getTransactionWay();

    String getMsgdev();

    String getMsgerr();

    Integer getNiverr();

    String getMsgusr();

    ErrorLevel getErrorLevel();

    String getHedgeMsg();

    Date getDatefinthe();

    StorageActionEnum getStorageUpdate();

    String getIkRtH();

    String getHedgingTransactionId();
}
