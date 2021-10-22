package org.wit.placemark.console.controllers

import mu.KotlinLogging

import org.wit.placemark.console.views.CourseView

import org.wit.placemark.console.controllers.CollegeController
import org.wit.placemark.console.main.college
import org.wit.placemark.console.main.modules
import org.wit.placemark.console.models.*
import org.wit.placemark.console.views.CollegeView
import org.wit.placemark.console.views.ModuleView

var collegeController = CollegeController()

var moduleView = ModuleView()

var collegeView = CollegeView()

var colleges = CollegeJSONStore()

class CourseController {

    val courses = CourseJSONStore()
    val courseView = CourseView()
    val logger = KotlinLogging.logger {}

    fun add(){
        collegeView.listColleges(colleges)
        var searchId = collegeView.getId()
        val aCollege = collegeController.search(searchId)

        var aCourse = CourseModel()

        if(aCourse != null) {
            if (courseView.addCourseData(aCourse)) {
                courses.create(aCourse)
                if (aCollege != null) {
                    aCollege.courses.add(aCourse)
                }
            }
            else {
                logger.info("Course Not Added")
            }
        }
        else{
            logger.info("Course Not Added")
        }
    }

    fun list() {
        courseView.listCourses(courses)
    }

    fun listModules() {
        collegeView.listColleges(colleges)
        var searchId = collegeView.getId()
        val aCollege = search(searchId)

        courseView.listCourses(courses)
        var courseId = courseView.getId()
        val aCourse = search(courseId)

        if (aCourse != null) {
            print(" -- "+aCourse.name+" -- \n")
            for(i in aCourse.modules){
                println(
                    "Module Name: " + i.name +
                            "\nModule Description: " + i.description +
                            "\nModule Credits: " + i.credits +
                            "\nModule ID: " + i.id +"\n")
            }
        }
        else{
            logger.info("Module Does Not Exist")
        }
    }

    fun update() {

        courseView.listCourses(courses)
        var searchId = courseView.getId()
        val aCourse = search(searchId)

        if(aCourse != null) {
            if(courseView.updateCourseData(aCourse)) {
                courses.update(aCourse)
                courseView.showCourse(aCourse)
                logger.info("Course Updated : [ $aCourse ]")
            }
            else
                logger.info("Course Not Updated")
        }
        else
            println("Course Not Updated...")
    }

    fun search() {
        val aCourse = search(courseView.getId())!!
        courseView.showCourse(aCourse)
    }


    fun search(id: Long) : CourseModel? {
        var foundCourse = courses.findOne(id)
        return foundCourse
    }

    fun searchCollege(id: Long) : CollegeModel? {
        var foundCollege = org.wit.placemark.console.main.colleges.findOne(id)
        return foundCollege
    }
}
