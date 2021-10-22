package org.wit.placemark.console.models

import com.google.gson.Gson
import com.google.gson.GsonBuilder
import com.google.gson.reflect.TypeToken
import mu.KotlinLogging

import org.wit.placemark.console.helpers.*
import org.wit.placemark.console.main.colleges
import org.wit.placemark.console.main.course
import java.util.*


private val logger = KotlinLogging.logger {}
val gsonBuilderCourse = GsonBuilder().setPrettyPrinting().create()
val listTypeCourse = object : TypeToken<java.util.ArrayList<CourseModel>>() {}.type

var college = CollegeModel()

val COURSE_JSON_FILE = "courses.json"


class CourseJSONStore : CourseStore {

    var courses = mutableListOf<CourseModel>()

    init {
        if (exists(COURSE_JSON_FILE)) {
            deserialize()
        }
    }

    override fun findAll(): List<CourseModel> {
        return courses
    }

    override fun findOne(id: Long) : CourseModel? {
        var foundCourse: CourseModel? = courses.find { p -> p.id == id }
        return foundCourse
    }

    override fun create(course: CourseModel) {
        course.id = generateRandomId()
        courses.add(course)

        serialize()
    }

    override fun update(course: CourseModel) {
        var foundCourse = findOne(course.id!!)
        if (foundCourse != null) {
            foundCourse.name = foundCourse.name
            foundCourse.description = foundCourse.description
            foundCourse.years = foundCourse.years
        }
        serialize()
    }

    override fun delete(course: CourseModel) {
        var foundCourse = findOne(course.id!!)
        if(foundCourse != null){
            courses.remove(foundCourse)
        }
        serialize()
    }

    internal fun logAll() {
        courses.forEach { logger.info("${it}") }
    }

    private fun serialize() {
        val jsonString = gsonBuilderCourse.toJson(courses, listTypeCourse)
        write(COURSE_JSON_FILE, jsonString)
    }

    private fun deserialize() {
        val jsonString = read(COURSE_JSON_FILE)
        courses = Gson().fromJson(jsonString, listTypeCourse)
    }
}