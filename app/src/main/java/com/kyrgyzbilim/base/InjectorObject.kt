package com.kyrgyzbilim.base

import com.kyrgyzbilim.data.remote.course.repository.CourseRepository
import com.kyrgyzbilim.data.remote.course.repository.CourseRepositoryImpl
import com.kyrgyzbilim.data.remote.ServiceClient
import com.kyrgyzbilim.data.remote.subTopic.SubTopicRepository
import com.kyrgyzbilim.data.remote.subTopic.SubTopicRepositoryImpl
import com.kyrgyzbilim.data.remote.sections.repository.SectionRepository
import com.kyrgyzbilim.data.remote.sections.repository.SectionRepositoryImpl
import com.kyrgyzbilim.data.remote.user.repository.UserRepository
import com.kyrgyzbilim.data.remote.user.repository.UserRepositoryImpl
import com.kyrgyzbilim.ui.authorization.AuthViewModelFactory
import com.kyrgyzbilim.ui.courses.CourseViewModelFactory
import com.kyrgyzbilim.ui.courses.sections.SectionViewModelFactory
import com.kyrgyzbilim.ui.courses.sections.subtopics.SubTopicViewModelFactory

object InjectorObject {

    private val serviceClient = ServiceClient()

    private val coursesRepository: CourseRepository = CourseRepositoryImpl(serviceClient)
    fun getCourseViewModelFactory() = CourseViewModelFactory(coursesRepository, userRepository)

    private val sectionRepository: SectionRepository = SectionRepositoryImpl(serviceClient)
    fun getSectionViewModelFactory() = SectionViewModelFactory(sectionRepository)

    private val subTopicRepository: SubTopicRepository = SubTopicRepositoryImpl(serviceClient)
    fun getSubTopicViewModelFactory() = SubTopicViewModelFactory(subTopicRepository)

    private val userRepository: UserRepository = UserRepositoryImpl(serviceClient)
    fun getAuthViewModelFactory() = AuthViewModelFactory(userRepository)

}