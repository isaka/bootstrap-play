/*
 * Copyright 2023 HM Revenue & Customs
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

package uk.gov.hmrc.play.bootstrap.backend

import play.api.inject.Binding
import play.api.{Configuration, Environment}
import uk.gov.hmrc.play.bootstrap.BootstrapModule
import uk.gov.hmrc.play.bootstrap.config.DeprecatedConfigChecker
import uk.gov.hmrc.play.bootstrap.filters.{AuditFilter, MDCFilter}
import uk.gov.hmrc.play.bootstrap.backend.filters.{DefaultBackendAuditFilter, BackendMdcFilter}

class BackendModule extends BootstrapModule {
  override def bindings(environment: Environment, configuration: Configuration): Seq[Binding[_]] =
    super.bindings(environment, configuration) ++ Seq(
      bind[AuditFilter].to[DefaultBackendAuditFilter],
      bind[MDCFilter].to[BackendMdcFilter],
      bind[DeprecatedConfigChecker]
        .toInstance(
          new DeprecatedConfigChecker(
            configuration,
            deprecatedClasses ++ uk.gov.hmrc.play.bootstrap.deprecatedClasses
          )
        ).eagerly()
    )
}
