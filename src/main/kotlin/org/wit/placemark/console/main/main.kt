package org.wit.placemark.console.main

import mu.KotlinLogging
import org.wit.placemark.console.controllers.CollegeController
import org.wit.placemark.console.models.*
import org.wit.placemark.console.views.CollegeView

private val logger = KotlinLogging.logger {}

var college = CollegeModel()
var course = CourseModel()
var module = ModuleModel()

val colleges = CollegeMemStore()
val courses = CourseMemStore()
val modules = ModuleMemStore()

var collegeView = CollegeView()


val controller = CollegeController()

fun main(args: Array<String>){
    CollegeController().start()
}