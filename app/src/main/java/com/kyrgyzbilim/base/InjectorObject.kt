package com.kyrgyzbilim.base

import com.kyrgyzbilim.data.remote.course.repository.CourseRepository
import com.kyrgyzbilim.data.remote.course.repository.CourseRepositoryImpl
import com.kyrgyzbilim.data.remote.ServiceClient
import com.kyrgyzbilim.data.remote.sections.repository.SectionRepository
import com.kyrgyzbilim.data.remote.sections.repository.SectionRepositoryImpl
import com.kyrgyzbilim.ui.courses.CourseViewModelFactory
import com.kyrgyzbilim.ui.courses.sections.SectionViewModelFactory

object InjectorObject {

    private val serviceClient = ServiceClient()

    private val coursesRepository: CourseRepository = CourseRepositoryImpl(serviceClient)
    fun getCourseViewModelFactory() = CourseViewModelFactory(coursesRepository)

    private val sectionRepository: SectionRepository = SectionRepositoryImpl(serviceClient)
    fun getSectionViewModelFactory() = SectionViewModelFactory(sectionRepository)



}