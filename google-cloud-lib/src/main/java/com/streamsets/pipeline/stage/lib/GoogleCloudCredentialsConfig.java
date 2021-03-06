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
package com.streamsets.pipeline.stage.lib;

import com.streamsets.pipeline.api.ConfigDef;
import com.streamsets.pipeline.api.ValueChooserModel;

public class GoogleCloudCredentialsConfig {
  @ConfigDef(
      required = true,
      type = ConfigDef.Type.STRING,
      label = "Project ID",
      displayPosition = 10,
      group = "CREDENTIALS"
  )
  public String projectId = "";

  @ConfigDef(
      required = true,
      type = ConfigDef.Type.MODEL,
      label = "Credentials Provider",
      defaultValue = "DEFAULT_PROVIDER",
      displayPosition = 20,
      group = "CREDENTIALS"
  )
  @ValueChooserModel(CredentialsProviderChooserValues.class)
  public CredentialsProviderType credentialsProvider;

  @ConfigDef(
      required = true,
      type = ConfigDef.Type.STRING,
      label = "Credentials File Path (JSON)",
      description = "Path to the credentials file. Relative path to the Data Collector resources directory, or " +
          "absolute path.",
      dependsOn = "credentialsProvider",
      triggeredByValue = "JSON_PROVIDER",
      displayPosition = 30,
      group = "CREDENTIALS"
  )
  public String path = "";
}
