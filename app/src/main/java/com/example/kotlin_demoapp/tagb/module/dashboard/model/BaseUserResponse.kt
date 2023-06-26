package com.example.kotlin_demoapp.tagb.module.dashboard.model

import com.example.kotlin_demoapp.tagb.module.dashboard.model.response.PersonAPI

data class BaseUserResponse(val page: Int, val data: List<PersonAPI>)