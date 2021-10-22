package org.wit.placemark.console.models

import mu.KotlinLogging
import org.wit.placemark.console.main.course

private val logger = KotlinLogging.logger {}
var lastID = 0L

internal fun getCourseId(): Long {
    return lastID++
}

class CourseMemStore : CourseStore {

    val courses = ArrayList<CourseModel>()

    override fun findAll(): List<CourseModel> {
        return courses
    }

    override fun findOne(id: Long) : CourseModel? {
        var foundCourse: CourseModel? = courses.find { p -> p.id == id }
        return foundCourse
    }

    override fun create(course: CourseModel) {
        course.id = getId()
        courses.add(course)
        logAll()
    }

    override fun update(course: CourseModel) {
        var foundCourse = findOne(course.id!!)
        if (foundCourse != null) {
            foundCourse.name = course.name
            foundCourse.description = course.description
            foundCourse.years = course.years
        }
    }

    override fun delete(course: CourseModel) {
        var foundCourse = findOne(course.id!!)
        if(foundCourse != null){
            courses.remove(course)
        }
    }

    internal fun logAll() {
        courses.forEach { logger.info("${it}") }
    }
}
