package com.kyrgyzbilim.data.remote.subTopic.repository

import com.kyrgyzbilim.base.ApiResult
import com.kyrgyzbilim.data.remote.subTopic.SubTopic

interface SubTopicRepository {
    suspend fun getSubTopic(token: String?, id: Int): ApiResult<List<SubTopic>>
}
