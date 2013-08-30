package com.heroku.platform.api.model

import com.heroku.platform.api.ErrorResponseJson

trait ApiRequestJson extends CollaboratorRequestJson with HerokuAppRequestJson with RegionRequestJson

trait ApiResponseJson extends ErrorResponseJson with CollaboratorResponseJson with HerokuAppResponseJson with RegionResponseJson