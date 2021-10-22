package org.wit.placemark.console.models

data class CollegeModel(
    var id: Long = 0,
    var name: String = "",
    var address: String = "",
    var courses: ArrayList<CourseModel> = ArrayList<CourseModel>(),
)