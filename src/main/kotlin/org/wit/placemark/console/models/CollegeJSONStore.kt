package org.wit.placemark.console.models

import com.google.gson.Gson
import com.google.gson.GsonBuilder
import com.google.gson.reflect.TypeToken
import mu.KotlinLogging

import org.wit.placemark.console.helpers.*
import org.wit.placemark.console.views.aCollege
import java.util.*

private val logger = KotlinLogging.logger {}

val JSON_FILE = "colleges.json"
val gsonBuilder = GsonBuilder().setPrettyPrinting().create()
val listType = object : TypeToken<java.util.ArrayList<CollegeModel>>() {}.type

fun generateRandomId(): Long {
    return Random().nextLong()
}

class CollegeJSONStore : CollegeStore {

    var colleges = mutableListOf<CollegeModel>()
    var courses = mutableListOf<CourseModel>()

    var course = CourseModel()

    init {
        if (exists(JSON_FILE)) {
            deserialize()
        }
    }

    override fun findAll(): MutableList<CollegeModel> {
        return colleges
    }

    override fun findOne(id: Long) : CollegeModel? {
        var foundCollege: CollegeModel? = colleges.find { p -> p.id == id }
        return foundCollege
    }

    override fun create(college: CollegeModel) {
        college.id = generateRandomId()
        colleges.add(college)
        serialize()
    }

    override fun update(college: CollegeModel) {
        var foundCollege = findOne(college.id!!)
        if (foundCollege != null) {
            foundCollege.name = foundCollege.name
            foundCollege.address = foundCollege.address
            foundCollege.courses = foundCollege.courses
        }
        serialize()
    }

    override fun delete(college: CollegeModel) {
        var foundCollege = findOne(college.id!!)
        if(foundCollege != null){
            colleges.remove(foundCollege)
        }
        serialize()
    }

    internal fun logAll() {
        colleges.forEach { logger.info("${it}") }
    }

    fun serialize() {
        val jsonString = gsonBuilder.toJson(colleges, listType)
        write(JSON_FILE, jsonString)
    }

    private fun deserialize() {
        val jsonString = read(JSON_FILE)
        colleges = Gson().fromJson(jsonString, listType)
    }
}