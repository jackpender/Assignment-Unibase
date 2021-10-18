package org.wit.placemark.console.models

data class CourseModel(
    var id: Long = 0,
    var name: String = "",
    var description: String = "",
    var years: Int = 0,
    var modules: ArrayList<ModuleModel> = ArrayList<ModuleModel>(),
)