package org.wit.placemark.console.controllers

import mu.KotlinLogging
//import org.wit.placemark.console.main.collegeView
import org.wit.placemark.console.main.colleges
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

//var colleges = CollegeMemStore()

class CourseController {

    val courses = CourseMemStore()
    val courseView = CourseView()
    val logger = KotlinLogging.logger {}

    init {
        logger.info { "Launching Unibase Console App" }
        println("Unibase Kotlin App Version 1.0")
    }

    fun start() {
        var input: Int

        do {
            input = menu()
            when (input) {
                2 -> add()
//                2 -> update()
                5 -> list()
//                4 -> search()
                -1 -> println("Exiting App")
                else -> println("Invalid Option")
            }
            println()
        } while (input != -1)
        logger.info { "Shutting Down Unibase Console App" }
    }

    fun menu() :Int { return courseView.menu() }

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

    /*fun addModule() {
        collegeView.listColleges(colleges)
        var collegeId = collegeView.getId()
        var aCollege = searchCollege(collegeId)

        if (aCollege != null) {
            courseView.listCollegeCourses(aCollege.courses)
        }
        var searchId = courseView.getId()
        val aCourse = search(searchId)

        print("this " + aCollege)

        var aModule = ModuleModel()


        if (aModule != null) {
            if (moduleView.addModuleData(aModule)) {
                modules.create(aModule)
                if (aCourse != null) {
                    aCourse.modules.add(aModule)
                    if (aCollege != null) {
                        aCollege.courses.get(searchId.toInt()).modules.add(aModule)
                    }
                }
            } else {
                logger.info("Module Not Added")
            }
        }
        else{
            logger.info("Module Not Added")
        }
    }*/

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