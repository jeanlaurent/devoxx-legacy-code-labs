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

import com.google.gson.Gson;
import legacy.dto.InputEvent;
import legacy.error.CheckResult;
import legacy.service.ToweringXMLHTTPServiceClient;
import org.apache.commons.lang3.SerializationUtils;
import org.junit.Test;
import org.mockito.Mockito;

import static org.mockito.Matchers.any;

public class HedgingPositionManagementTest {

    @Test
    public void firstTest() {
        HedgingPositionManagementImpl object = new HedgingPositionManagementImpl();

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

        CheckResult<HedgingPosition> result = object.initAndSendHedgingPosition(new HedgingPosition());

        System.out.println(result);

    }


}
