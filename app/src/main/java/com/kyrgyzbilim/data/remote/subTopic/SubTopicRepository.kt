package com.kyrgyzbilim.data.remote.subTopic

import com.kyrgyzbilim.base.ApiResult

interface SubTopicRepository {
    suspend fun getSubTopic(id: Int): ApiResult<List<SubTopic>>
}