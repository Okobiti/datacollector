/**
 * Copyright 2017 StreamSets Inc.
 *
 * Licensed under the Apache License, Version 2.0 (the "License");
 * you may not use this file except in compliance with the License.
 * You may obtain a copy of the License at
 *
 *     http://www.apache.org/licenses/LICENSE-2.0
 *
 * Unless required by applicable law or agreed to in writing, software
 * distributed under the License is distributed on an "AS IS" BASIS,
 * WITHOUT WARRANTIES OR CONDITIONS OF ANY KIND, either express or implied.
 * See the License for the specific language governing permissions and
 * limitations under the License.
 */
package com.streamsets.pipeline.lib.parser.net.netflow;

import com.streamsets.pipeline.api.Stage;
import com.streamsets.pipeline.api.base.OnRecordErrorException;
import com.streamsets.pipeline.api.ext.io.OverrunReader;
import com.streamsets.pipeline.lib.parser.net.BaseNetworkMessageDataParser;
import io.netty.buffer.ByteBuf;

import java.io.InputStream;
import java.nio.charset.Charset;

public class NetflowDataParser extends BaseNetworkMessageDataParser<NetflowMessage> {

  private final NetflowDecoder netflowDecoder;

  public NetflowDataParser(
      Stage.Context context,
      String readerId,
      InputStream inputStream,
      Long readerOffset,
      int maxObjectLen,
      Charset charset
  ) {
    super(context, readerId, inputStream, readerOffset, maxObjectLen, charset);
    netflowDecoder = new NetflowDecoder();
  }

  @Override
  protected String getTypeName() {
    return "netflow";
  }

  @Override
  protected void performDecode(ByteBuf byteBuf) throws OnRecordErrorException {
    netflowDecoder.decodeStandaloneBuffer(byteBuf, decodedMessages, null, null);
  }

}
