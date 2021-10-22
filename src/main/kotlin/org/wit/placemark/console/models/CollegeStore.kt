package org.wit.placemark.console.models

interface CollegeStore {
    fun findAll(): List<CollegeModel>
    fun findOne(id: Long): CollegeModel?
    fun create(college: CollegeModel)
    fun update(college: CollegeModel)
    fun delete(college: CollegeModel)
}