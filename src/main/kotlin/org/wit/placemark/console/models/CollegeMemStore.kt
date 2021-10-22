package org.wit.placemark.console.models

import mu.KotlinLogging

private val logger = KotlinLogging.logger {}
var lastId = 0L

internal fun getId(): Long {
    return lastId++
}

class CollegeMemStore : CollegeStore {

    val colleges = ArrayList<CollegeModel>()

    override fun findAll(): List<CollegeModel> {
        return colleges
    }

    override fun findOne(id: Long) : CollegeModel? {
        var foundCollege: CollegeModel? = colleges.find { p -> p.id == id }
        return foundCollege
    }

    override fun create(college: CollegeModel) {
        college.id = getId()
        colleges.add(college)
        logAll()
    }

    override fun update(college: CollegeModel) {
        var foundCollege = findOne(college.id!!)
        if (foundCollege != null) {
            foundCollege.name = college.name
            foundCollege.address = college.address
        }
    }

    override fun delete(college: CollegeModel) {
        var foundCollege = findOne(college.id!!)
        if(foundCollege != null){
            colleges.remove(college)
        }
    }

    internal fun logAll() {
        colleges.forEach { logger.info("${it}") }
    }
}
