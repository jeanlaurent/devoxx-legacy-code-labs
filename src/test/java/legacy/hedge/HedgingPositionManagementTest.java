/*
 * Copyright 2013- Yan Bonnel
 *
 *  Licensed under the Apache License, Version 2.0 (the "License");
 *  you may not use this file except in compliance with the License.
 *  You may obtain a copy of the License at
 *
 *
 *      http://www.apache.org/licenses/LICENSE-2.0
 *
 * Unless required by applicable law or agreed to in writing, software
 * distributed under the License is distributed on an "AS IS" BASIS,
 * WITHOUT WARRANTIES OR CONDITIONS OF ANY KIND, either express or implied.
 * See the License for the specific language governing permissions and
 * limitations under the License.
 */
package legacy.hedge;

import legacy.error.CheckResult;
import legacy.service.DataAccessService;
import legacy.service.ITradingDataAccessService;
import legacy.service.implementation.TradingDataAccessServiceImpl;
import org.apache.commons.lang3.SerializationUtils;
import org.junit.Before;
import org.junit.Test;

import java.util.Date;

import static org.fest.assertions.api.Assertions.assertThat;

public class HedgingPositionManagementTest {

    private HedgingPositionManagementImpl object;

    @Before
    public void setup() {
        object = new HedgingPositionManagementImpl();

        HedgingPositionMgt serviceBizare = new HedgingPositionMgt(){
            @Override
            public CheckResult<HedgingPosition> hedgingPositionMgt(HedgingPosition hp) {
                CheckResult<HedgingPosition> result = new CheckResult<>();
                result.setCheckIsOk(true);
                result.setResult(SerializationUtils.clone(hp));
                return result;
            }
        };



        object.setHedgingPositionMgt(serviceBizare);

        DataAccessService dataAccessService = new DataAccessService() {
            @Override
            public ITradingDataAccessService getTradingDataAccessService() {
                return new TradingDataAccessServiceImpl() {
                    @Override
                    protected void synchronizationTimer(int countInSeconds) {
                    }
                };
            }
        };

        object.setDataAccessService(dataAccessService);
    }


    @Test
    public void firstTest() {
        Date dateBefore = new Date();
        HedgingPosition hp = new HedgingPosition();

        CheckResult<HedgingPosition> result = object.initAndSendHedgingPosition(hp);
        HedgingPosition hedgingPosition = result.getResult();


        Date dateAfter = new Date();
        assertThat(result.isCheckIsOk()).isTrue();
        assertThat(hedgingPosition.toString()).isEqualTo("HedgingPosition{transactionId=0, type=INI, status=HEDGED, valueDate=null, noticePeriodEndDate=null, combck='0 0 CONTROL: [0x0x0x01h]', codetyptkt=20, transactionWay='S', errorLevel=null, hedgeMsg='null', storageAction=CREATE, prxref=0.0, basprx=0.0, daprx=null, quantity='0.0', datefinthe=null, codtyptra=23, msgdev='null', msgerr='null', niverr=null, msgusr='null', ikRtH='autobot', hedgingTransactionId='null'} BaseDTO{id=0} ObjectDTO{updateDate=null, lastModificatin=null, version=0, updateVersion=0}");
        assertThat(hedgingPosition.getCreDate()).isNotNull();
        assertThat(hedgingPosition.getCreDate()).isAfterOrEqualsTo(dateBefore);
        assertThat(hedgingPosition.getCreDate()).isBeforeOrEqualsTo(dateAfter);
    }


    @Test
    public void firstTypeCancelPosition() {
        Date dateBefore = new Date();
        HedgingPosition hp = new HedgingPosition();
        hp.setType(HedgingPositionTypeConst.CANCEL_POSITION);

        CheckResult<HedgingPosition> result = object.initAndSendHedgingPosition(hp);
        HedgingPosition hedgingPosition = result.getResult();


        Date dateAfter = new Date();
        assertThat(result.isCheckIsOk()).isTrue();
        assertThat(hedgingPosition.toString()).isEqualTo("HedgingPosition{transactionId=0, type=CANCEL_POSITION, status=HEDGED, valueDate=null, noticePeriodEndDate=null, combck='null', codetyptkt=20, transactionWay='null', errorLevel=null, hedgeMsg='null', storageAction=UPDATE, prxref=0.0, basprx=100.0, daprx=null, quantity='null', datefinthe=null, codtyptra=42, msgdev='null', msgerr='null', niverr=null, msgusr='null', ikRtH='autobot', hedgingTransactionId='0xid.42'} BaseDTO{id=0} ObjectDTO{updateDate=null, lastModificatin=null, version=0, updateVersion=0}");
        assertThat(hedgingPosition.getCreDate()).isNotNull();
        assertThat(hedgingPosition.getCreDate()).isAfterOrEqualsTo(dateBefore);
        assertThat(hedgingPosition.getCreDate()).isBeforeOrEqualsTo(dateAfter);
    }

    @Test
    public void firstTypeCancelTransaction() {
        Date dateBefore = new Date();
        HedgingPosition hp = new HedgingPosition();
        hp.setType(HedgingPositionTypeConst.CANCEL_TRANSACTION);

        CheckResult<HedgingPosition> result = object.initAndSendHedgingPosition(hp);
        HedgingPosition hedgingPosition = result.getResult();


        Date dateAfter = new Date();
        assertThat(result.isCheckIsOk()).isTrue();
        assertThat(hedgingPosition.toString()).isEqualTo("HedgingPosition{transactionId=0, type=CANCEL_TRANSACTION, status=HEDGED, valueDate=null, noticePeriodEndDate=null, combck='null', codetyptkt=20, transactionWay='null', errorLevel=null, hedgeMsg='null', storageAction=UPDATE, prxref=0.0, basprx=100.0, daprx=null, quantity='null', datefinthe=null, codtyptra=42, msgdev='null', msgerr='null', niverr=null, msgusr='null', ikRtH='autobot', hedgingTransactionId='null'} BaseDTO{id=0} ObjectDTO{updateDate=null, lastModificatin=null, version=0, updateVersion=0}");
        assertThat(hedgingPosition.getCreDate()).isNotNull();
        assertThat(hedgingPosition.getCreDate()).isAfterOrEqualsTo(dateBefore);
        assertThat(hedgingPosition.getCreDate()).isBeforeOrEqualsTo(dateAfter);
    }

    @Test
    public void firstTypeExt() {
        HedgingPosition hp = new HedgingPosition();
        hp.setType(HedgingPositionTypeConst.EXT);

        CheckResult<HedgingPosition> result = object.initAndSendHedgingPosition(hp);
        HedgingPosition hedgingPosition = result.getResult();

        assertThat(result.isCheckIsOk()).isTrue();
        assertThat(hedgingPosition.toString()).isEqualTo("HedgingPosition{transactionId=0, type=EXT, status=HEDGED, valueDate=null, noticePeriodEndDate=null, combck='0 0 CONTROL: [0x0x0x01h]', codetyptkt=42, transactionWay='null', errorLevel=null, hedgeMsg='null', storageAction=UPDATE, prxref=0.0, basprx=0.0, daprx=null, quantity='0.0', datefinthe=null, codtyptra=42, msgdev='null', msgerr='null', niverr=null, msgusr='null', ikRtH='autobot', hedgingTransactionId='null'} BaseDTO{id=0} ObjectDTO{updateDate=null, lastModificatin=null, version=0, updateVersion=0}");
        assertThat(hedgingPosition.getCreDate()).isNotNull();
        assertThat(hedgingPosition.getCreDate()).isWithinYear(3910);
        assertThat(hedgingPosition.getCreDate()).isWithinDayOfMonth(9);
        assertThat(hedgingPosition.getCreDate()).isWithinMonth(10);
    }

    @Test
    public void firstTypeIni() {
        Date dateBefore = new Date();
        HedgingPosition hp = new HedgingPosition();
        hp.setType(HedgingPositionTypeConst.INI);

        CheckResult<HedgingPosition> result = object.initAndSendHedgingPosition(hp);
        HedgingPosition hedgingPosition = result.getResult();


        Date dateAfter = new Date();
        assertThat(result.isCheckIsOk()).isTrue();
        assertThat(hedgingPosition.toString()).isEqualTo("HedgingPosition{transactionId=0, type=INI, status=HEDGED, valueDate=null, noticePeriodEndDate=null, combck='0 0 CONTROL: [0x0x0x01h]', codetyptkt=20, transactionWay='S', errorLevel=null, hedgeMsg='null', storageAction=CREATE, prxref=0.0, basprx=0.0, daprx=null, quantity='0.0', datefinthe=null, codtyptra=23, msgdev='null', msgerr='null', niverr=null, msgusr='null', ikRtH='autobot', hedgingTransactionId='null'} BaseDTO{id=0} ObjectDTO{updateDate=null, lastModificatin=null, version=0, updateVersion=0}");
        assertThat(hedgingPosition.getCreDate()).isNotNull();
        assertThat(hedgingPosition.getCreDate()).isAfterOrEqualsTo(dateBefore);
        assertThat(hedgingPosition.getCreDate()).isBeforeOrEqualsTo(dateAfter);
    }




}
