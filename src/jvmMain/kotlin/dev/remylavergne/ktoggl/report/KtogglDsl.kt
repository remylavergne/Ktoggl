package dev.remylavergne.ktoggl.report

import dev.remylavergne.ktoggl.report.models.Account
import dev.remylavergne.ktoggl.report.models.ApiCredentials

@DslMarker
annotation class ApiDsl

@ApiDsl
class AccountBuilderDsl : Account.Builder()

@ApiDsl
class ApiCredentialsBuilderDsl : ApiCredentials.Builder()